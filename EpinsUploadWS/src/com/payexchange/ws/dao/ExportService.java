package com.payexchange.ws.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

//import com.models.Epins;
import com.payexchange.ws.utility.HibernateUtil;
import com.payexchange.ws.utility.MessageModels;
import com.payexchange.ws.utility.ViewModels;
import com.payexchange.ws.beans.DetailsBean;
import com.payexchange.ws.utility.SQL;
import com.payexchange.ws.utility.ValidatorService;
import com.paysetter.security.Encrypter;
import com.payexchange.ws.utility.MailService;

public class ExportService {

	
	private ValidatorService validatorService;
	//for zip file
	public DetailsBean emailEpins(HttpServletRequest request){
		DetailsBean detailsbean = new DetailsBean();
		String epinFile = "";
        String zipFile = "";
        String rptDate = "";
        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMMddHHmmss");
        rptDate=sdf.format(date);
        HttpSession httpSession = request.getSession();
        Session session = null;
        Transaction tx = null;
        SQL sql = new SQL();
        
        boolean ifSessionExist = validatorService.checkIfSessionExist(request);
        if(!ifSessionExist) {
        detailsbean.setModel(MessageModels.SESSION_NOT_OK);
        detailsbean.setNextView(ViewModels.INDEX);
        return detailsbean;
        }

        
        try {
        	
        	
            String denom = request.getParameter("denom");
            String email = request.getParameter("email");
            String qty = request.getParameter("qty");
            String telco = request.getParameter("telco");
            String password = request.getParameter("password");
            String username = (String) httpSession.getAttribute("username");

            boolean ifDenom = validatorService.checkDenom(denom);
            if(!ifDenom) {
                detailsbean.setModel(MessageModels.INVALID_DENOM);
                return detailsbean;
            }
            boolean ifTelco = validatorService.checkTelco(telco);
            if(!ifTelco) {
                detailsbean.setModel(MessageModels.INVALID_TELCO);
                return detailsbean;
            }

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
           
            Query query = session.createQuery(sql.checkEpin(denom, telco, qty));
            //SQLQuery sss = session.createSQLQuery(telco);
  

            query = query.setMaxResults(Integer.parseInt(qty));
            List result = query.list();

            if (result.isEmpty()) {
                detailsbean.setModel(MessageModels.RETRY_SEARCH);
                return detailsbean;
            }
            try {
                epinFile = writeAFile(username,qty,denom,telco,email,rptDate,query,session,tx);
            } catch(NullPointerException npe) {
                detailsbean.setModel(MessageModels.RETRY_SEARCH);
                return detailsbean;
            }
            //add if for checking email                
            //System.out.println(epinFile);
            zipFile = writePrefix(username,denom,telco,rptDate)+".zip";
            //zipFile = "D:\\"+writePrefix(username,denom,telco,rptDate)+".zip";
            File outFile = new File(zipFile);
            File inFile = new File(epinFile);
            
            sendMail(email,zipFile);
            tx.commit();
        } catch(NullPointerException npe) {
            detailsbean.setModel(MessageModels.PARAMS);
        } catch(FileNotFoundException fnfe) {
           detailsbean.setModel(MessageModels.FILE_NOT_FOUND);
        } catch (IOException e) {
            detailsbean.setNextView(ViewModels.EXPORT);
        } catch(NumberFormatException nfe) {
            detailsbean.setModel(MessageModels.PARAMS);
        } catch (HibernateException he) {
            tx.rollback();
            detailsbean.setModel(he.getMessage());
            he.printStackTrace();
        }
        
		return detailsbean;
        
	}
	//excel file
	public ValidatorService getValidatorService() {
        return validatorService;
    }

    public void setValidatorService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    private String writeAFile(String username, String qty, String denom, String telco, String email, String rptDate, Query query, Session session, Transaction tx) throws FileNotFoundException, IOException {
        String fileName = "";
        try {
        fileName = writePrefix(username,denom,telco,rptDate)+".xls";
       
        FileOutputStream fileOut = new FileOutputStream(fileName);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet("sheet");
        
        int i = 0;        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        for(Iterator it=query.iterate();it.hasNext();) {
           Object[] row = (Object[]) it.next();
           String dec = goDecryption(row[1].toString());
           String[] decArray = validatorService.getDecrypted(dec);
          
           HSSFRow rowExcel = worksheet.createRow(i);
           for(int j = 0;j<decArray.length;j++) {
               rowExcel.createCell(j).setCellValue(decArray[j]);
           }
           
           System.out.println("row[0]: "+row[0].toString()+" row[2]: "+row[2].toString()+" row[3]: "+row[3].toString());
//           Epins epinsNew = new Epins();
//           epinsNew.setId(Integer.parseInt(row[0].toString()));
//           epinsNew.setEpin(row[1].toString());
//           epinsNew.setDenom(Integer.parseInt(denom));
//           epinsNew.setTelco_type(telco);
//           epinsNew.setUploaded_by(row[2].toString());
//           epinsNew.setTo_whom(email);
//           epinsNew.setDate_uploaded(df.parse(row[3].toString()));
//           epinsNew.setDate_used(new Date());
//           epinsNew.setStatus("1");
//           session.update(epinsNew);
           i++;
        }
        
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        } catch (HibernateException he) {
                he.printStackTrace();
        } 
//        catch (ParseException pe) {
//                pe.getMessage();
//        }
        return fileName;
    }

  
   //mail service

   private void sendMail(String to, String attach) {
    try {
            MailService client = new MailService();
            String from="chato@paysetter.com";
            String cc =  "";
            String subject = "E-FILE";
            String message="Attached here is an encrypted file. Use winzip or winrar for the attached file.";
            String[] filenames = {attach};
            //System.out.println("Sending...");
            client.sendMail(from,to,cc,subject,message,filenames);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
 }

   private String writePrefix(String username, String denom, String telco, String rptDate) {
       return username+""+denom+""+telco+""+rptDate;
   }


   public static void main(String[] args) {
    String fileName = "D:\\xtian10002010Aug24153856.zip";
    // A File object to represent the filename
    File f = new File(fileName);

    // Make sure the file or directory exists and isn't write protected
    if (!f.exists())
      throw new IllegalArgumentException(
          "Delete: no such file or directory: " + fileName);

    if (!f.canWrite())
      throw new IllegalArgumentException("Delete: write protected: "
          + fileName);

    // If it is a directory, make sure it is empty
    if (f.isDirectory()) {
      String[] files = f.list();
      if (files.length > 0)
        throw new IllegalArgumentException(
            "Delete: directory not empty: " + fileName);
    }

    // Attempt to delete it
    boolean success = f.delete();

    if (!success)
      throw new IllegalArgumentException("Delete: deletion failed");
  }

  private String goDecryption(String var) {
        Encrypter enc = new Encrypter();
        String xD = enc.decryptBase64String(var);
        return xD;
    }
}
