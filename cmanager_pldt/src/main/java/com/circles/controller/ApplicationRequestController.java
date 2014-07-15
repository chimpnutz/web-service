package com.circles.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.circles.model.*;
import com.circles.dao.impl.*;
import com.circles.utils.*;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("userid")
public class ApplicationRequestController {

	@Autowired
	private ApplicationDAOImpl applicationDAOImpl;

	@Autowired
	Application2DAOImpl application2DAOImpl;

	@Autowired
	private CommentDAOImpl commentDAOImpl;

	@Autowired
	private PhoneDAOImpl phoneDAOImpl;

	@Autowired
	private ImageDAOImpl imageDAOImpl;

	@Autowired
	private GcmDAOImpl gcmDAOImpl;

	// @Autowired ResetPasswordDAOImpl resetpasswordDAOImpl;

	private static final Logger logger = LoggerFactory
			.getLogger(ApplicationRequestController.class);

	@RequestMapping(value = "/getApplications", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection getApplications() {
		// int isExisting = userDAOImpl.checkIfExists(userParams);

		Collection s = applicationDAOImpl.findAllApplication();

		return s;

	}

	@RequestMapping(value = "/getApplications1", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Collection getApplications1(HttpSession session) {
		// int isExisting = userDAOImpl.checkIfExists(userParams);

		System.out.println(session.getAttribute("username"));
		Collection s = applicationDAOImpl.findAllApplication(session
				.getAttribute("username").toString());

		return s;

	}

	public boolean checksession(HttpSession session)
			throws ConversionNotSupportedException {
		boolean flg = true;
		String username = (String) session.getAttribute("username");
		String imei = (String) session.getAttribute("imei");
		if (username == null || imei == null) {
			throw new ConversionNotSupportedException("unauthrized", null, null);
		}
		return flg;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getApplication", produces = "application/json")
	public @ResponseBody
	Collection getExistingApplication(
			@RequestParam(value = "applicationid", required = true) String applicationid,
			HttpSession session) throws SQLException, NoSuchFieldException,
			SecurityException, ConversionNotSupportedException {
		// checksession(session);
		Application application = new Application();
		Phone phone = new Phone();
		application.setApplication_id(applicationid);
		int isExisting = applicationDAOImpl.checkIfExists(application);
		Collection<Application> s = null;

		Collection test = null;
		Collection phoneInfo = null;
		Collection plan = null;
		ArrayList<Application> copy = null;
		if (isExisting == 1) {
			s = applicationDAOImpl.findApplication2(application);
			copy = new ArrayList<Application>(s);
			System.out.println(copy.get(0).getPhone_id());
			phone.setPhone_id(copy.get(0).getPhone_id());
			phoneInfo = phoneDAOImpl.selectPhone(phone);
			test = s;
			test.add(phoneInfo);

		}
		return test;

	}

	@RequestMapping(value = "/uploadForm")
	public String uploadFormApplication() throws SQLException {
		return "manager/simpleUploader";

	}

	public boolean FileUpload(String imagename, String file) throws IOException{
		String[] allowedExts = {"gif", "jpeg", "jpg", "png","GIF","JPEG","JPG","PNG"};
		String[] temp = imagename.split(".");
		//String ext = temp[temp.length-1];
		Resource resource = new ClassPathResource("../properties/filepath.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);

		String filepath = props.getProperty("filepath.url");
//				"C:/Users/tata/workspace/cmanager-jc/src/main/webapp/resources/uploaded";
//				
		Base64 decoder = new Base64();
		//for(int i = 0;i<allowedExts.length;i++){
			//if(allowedExts[i]==ext){
				//BufferedImage  bi = ImageIO.read(new File(filepath+image));
				//ImageIO.write(, formatName, output)
				@SuppressWarnings("static-access")
				
				byte[] bi = decoder.decode(file);
				FileOutputStream fileOuputStream = 
		                  new FileOutputStream(filepath+imagename); 
			    fileOuputStream.write(bi);
			    fileOuputStream.close();
			    return true;
			//}
		//}

	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/saveApplication", produces = "application/json")
	public @ResponseBody
	boolean saveApplication(
			@RequestParam(value = "application_id", required = false) String application_id,
			@RequestParam(value = "product_id", required = false) String product_id,
			@RequestParam(value = "deviceid", required = false) String deviceid,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "ispushed", required = false) String ispushed,
			@RequestParam(value = "edited_by", required = false) String edited_by,
			@RequestParam(value = "user_id", required = false) String user_id,
			@RequestParam(value = "ispushed", required = false) String ispushed_comment,
			@RequestParam(value = "content", required = false) String content,
			@RequestParam(value = "version", required = false) String version,
			@RequestParam(value = "application_type", required = false) String application_type,
			@RequestParam(value = "details", required = false) String details,
			@RequestParam(value = "comment_id", required = false) String id,
			HttpSession session

	) throws NullPointerException, IOException, SQLException,
			ConversionNotSupportedException {
		Application application = new Application();
		String date = "" + new Date().getTime() + "";
		Comment comment = new Comment();

		Details detail = new Details();

		Gson gson = new Gson();

		GsonBuilder gson_builder = new GsonBuilder();
		detail = gson.fromJson(details, Details.class);

		// gson_builder.registerTypeAdapter(
		// Object.class,
		// new NaturalDeserializer() );
		// Object natural = gson.fromJson(details, Hashtable.class);

		System.out.println("app details: " + details);

		application.setApplication_id(application_id);
		application.setDetails(details);
		application.setIspushed(ispushed);
		application.setEditedBy(edited_by);
		application.setProduct_id(product_id);
		application.setDeviceid(deviceid);
		application.setCreated(date);
		application.setUpdate(date);
		application.setVersion("1");
		application.setUser_id(user_id);
		application.setApplication_type(application_type);
		application.setStatus(status);
		int s = 0;

		int isExisting = applicationDAOImpl.checkIfExists(application);

		if (isExisting == 0) {
			s = applicationDAOImpl.save(application);

		} else {
			Image imageDelete = new Image();
			int delete = imageDAOImpl.delete(imageDelete);
			System.out.println("Deleting images from application id:"
					+ application.getApplication_id() + " for updated images.");
			System.out.println("this will be an update of application:"
					+ application.getApplication_id());
			int newVer = Integer.parseInt(version);
			newVer++;

			application.setVersion(newVer + "");
			s = applicationDAOImpl.update(application);
		}

		int s2 = 0;
		try {
			if (!id.isEmpty() || !id.equals(null) || !id.equals("")
					|| id != null || id != "") {
				comment.setId(id);
				comment.setIspushed("1");
				comment.setEdited_by(edited_by);
				comment.setApplication_id(application_id);
				comment.setUser_id(user_id);
				comment.setVersion(version);
				comment.setCreated(date);
				comment.setContent(content);
				s2 = commentDAOImpl.save(comment);
			}
		} catch (NullPointerException e) {

		}

		Collection gcms = null;

		String regid = "";
		if (s == 1) {
			System.out.println(product_id);
			System.out.println("Added new Application by " + user_id + " on "
					+ new Date().getDate());
			return true;

		}
		return false;

	}

	@RequestMapping(value = "/saveImage", produces = "application/json")
	public @ResponseBody
	String saveImage(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "application_id", required = false) String application_id,
			@RequestParam(value = "filename", required = false) String filename,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "image", required = false) String image_store,
			HttpSession session) throws IOException,
			ConversionNotSupportedException {
		// checksession(session);

		Image image = new Image();

		System.out.println(image_store);
		image.setId(id);
		image.setApplication_id(application_id);
		image.setFilename(filename);
		image.setType(type);
		System.out.println(application_id);
		int isExisting = imageDAOImpl.checkIfExists(image);
		System.out.println(isExisting);
		if (isExisting == 0) {
			Boolean i = FileUpload(filename, image_store);
			if (i) {
				System.out.println("image stored!");
			}
			int isSaved = imageDAOImpl.save(image);
			if (isSaved == 1) {
				return "1";
			}
		}
		return "1";

	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/updateApplication", produces = "application/json")
	public @ResponseBody
	int updateApplication(
			@RequestParam(value = "applicationid", required = true) String applicationid,
			@RequestParam(value = "product_id", required = false) String product_id,
			@RequestParam(value = "deviceid", required = false) String deviceid,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "ispushed", required = false) String ispushed,
			@RequestParam(value = "decline_remarks", required = false) String decline_remarks,
			@RequestParam(value = "edited_by", required = false) String edited_by,
			@RequestParam(value = "version", required = false) String version,
			@RequestParam(value = "application_type", required = false) String application_type,
			@RequestParam(value = "details", required = false) String details,
			HttpSession session) throws NullPointerException, IOException,
			SQLException, ConversionNotSupportedException {
		// checksession(session);

		Application application = new Application();
		String date = "" + new Date().getTime() + "";
		Comment comment = new Comment();

		Details detail = new Details();

		Gson gson = new Gson();

		GsonBuilder gson_builder = new GsonBuilder();
		detail = gson.fromJson(details, Details.class);

		System.out.println("app detaisls: " + details);

		application.setApplication_id(applicationid);
		application.setProduct_id(product_id);
		application.setDeviceid(deviceid);
		application.setStatus("0");
		application.setDecline_remarks(decline_remarks);
		application.setIspushed(ispushed);
		application.setEditedBy(edited_by);
		application.setUpdate(date);
		Application getVersion = new Application();
		getVersion.setApplication_id(applicationid);
		Collection getAppVersion = applicationDAOImpl
				.findApplication2(getVersion);
		ArrayList<Application> appVersion = new ArrayList<Application>(
				getAppVersion);
		int newVersion = Integer.parseInt(appVersion.get(0).getVersion());
		newVersion += 1;
		application.setVersion(newVersion + "");
		application.setApplication_type(application_type);

		Resource resource = new ClassPathResource(
				"../properties/filepath.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		String GOOGLE_SERVER_KEY = props.getProperty("GOOGLE_API_KEY");
		Gcm gcm = new Gcm();
		String regid = "";
		ArrayList<Gcm> copy = null;
		Collection gcms = null;
		Collection app_user = null;
		Image imageDelete = new Image();
		imageDelete.setApplication_id(applicationid);
		imageDAOImpl.delete(imageDelete);
		int s = applicationDAOImpl.update(application);
		if (s == 1) {

			MulticastResult result2 = null;
			Result result = null;
			Application newApp = new Application();
			newApp.setApplication_id(applicationid);
			app_user = applicationDAOImpl.findApplication2(application);
			ArrayList<Application> copy2 = new ArrayList<Application>(app_user);
			gcm.setUsername(copy2.get(0).getEditedBy());
			System.out.println(copy2.get(0).getEditedBy());
			gcms = gcmDAOImpl.findGcm(applicationid);
			copy = new ArrayList<Gcm>(gcms);
			regid = copy.get(0).getRegid();
			Sender sender = new Sender(GOOGLE_SERVER_KEY);
			Message messages = new Message.Builder().delayWhileIdle(false)
					.addData("table", "application").build();
			// result = sender.send(messages, regid, 5);
			ArrayList<String> devices = new ArrayList<String>();
			devices.add(regid);
			System.out.println(messages);
			result2 = sender.send(messages, devices, 5);
			System.out.println(regid);
			System.out.println(result2.toString());

			System.out.println("Updated Application by " + edited_by + " on "
					+ new Date().getDate());
			resource = null;

		}
		return s;

	}

	@RequestMapping(value = "/deleteApplication", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	int deleteApplication(
			@RequestParam(value = "applicationid", required = true) String applicationid,
			HttpSession session) throws ConversionNotSupportedException {
		// checksession(session);
		// int isExisting = userDAOImpl.checkIfExists(userParams);
		Application application = new Application();
		application.setApplication_id(applicationid);
		int s = applicationDAOImpl.delete(application);
		return s;

	}

	@RequestMapping(value = "/uploadTest")
	public String upload(
			@ModelAttribute("applicationForm") Application application,
			Model model,
			@RequestParam(value = "invalid", required = false) String invalid) {
		// int isExisting =
		// userDAOImpl.checkIfExists(userParams);asdasdasdasdasd
		model.addAttribute("error", "display:none");
		return "index";
	}

}
