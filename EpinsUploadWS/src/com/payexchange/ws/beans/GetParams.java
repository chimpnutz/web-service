package com.payexchange.ws.beans;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.paysetter.security.Encrypter;

public class GetParams {
	
	private String user;
    private String pass;
    private String dialect;
    private String driver;
    private String pool;
    private String session;
    private String cache;
    private String sql;
    private String url;
    
    public void getValues() throws ParserConfigurationException, SAXException, IOException {
        Encrypter enc = new Encrypter();
        //File file = new File("/home/jollibee/apache-tomcat-6.0.20/configs/epin-config.xml");
        File file = new File("epin-config.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
    	System.out.println(file.getAbsolutePath());
        doc.getDocumentElement().normalize();
        NodeList nodeLst = doc.getElementsByTagName("config");


    for (int s = 0; s < nodeLst.getLength(); s++) {
    	
    	System.out.println(nodeLst.getLength());

    Node fstNode = nodeLst.item(s);

    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
        Element fstElmnt = (Element) fstNode;
        NodeList dialectNmElmntLst = fstElmnt.getElementsByTagName("dialect");
        Element dialectNmElmnt = (Element) dialectNmElmntLst.item(0);
        NodeList dialectNm = dialectNmElmnt.getChildNodes();
        this.setDialect(((Node) dialectNm.item(0)).getNodeValue());
        NodeList driverNmElmntLst = fstElmnt.getElementsByTagName("driver");
        Element driverNmElmnt = (Element) driverNmElmntLst.item(0);
        NodeList driverNm = driverNmElmnt.getChildNodes();
        this.setDriver(((Node) driverNm.item(0)).getNodeValue());
        NodeList userNmElmntLst = fstElmnt.getElementsByTagName("user");
        Element userNmElmnt = (Element) userNmElmntLst.item(0);
        NodeList usrNm = userNmElmnt.getChildNodes();
        this.setUser(enc.decryptBase64String(((Node) usrNm.item(0)).getNodeValue()));
        NodeList passNmElmntLst = fstElmnt.getElementsByTagName("pass");
        Element passNmElmnt = (Element) passNmElmntLst.item(0);
        NodeList passNm = passNmElmnt.getChildNodes();
        this.setPass(enc.decryptBase64String(((Node) passNm.item(0)).getNodeValue()));
        NodeList urlNmElmntLst = fstElmnt.getElementsByTagName("url");
        Element urlNmElmnt = (Element) urlNmElmntLst.item(0);
        NodeList urlNm = urlNmElmnt.getChildNodes();
        this.setUrl(((Node) urlNm.item(0)).getNodeValue());
        NodeList poolNmElmntLst = fstElmnt.getElementsByTagName("pool");
        Element poolNmElmnt = (Element) poolNmElmntLst.item(0);
        NodeList poolNm = poolNmElmnt.getChildNodes();
        this.setPool(((Node) poolNm.item(0)).getNodeValue());
        NodeList sessionNmElmntLst = fstElmnt.getElementsByTagName("session");
        Element sessionNmElmnt = (Element) sessionNmElmntLst.item(0);
        NodeList sessionNm = sessionNmElmnt.getChildNodes();
        this.setSession(((Node) sessionNm.item(0)).getNodeValue());
        NodeList cacheNmElmntLst = fstElmnt.getElementsByTagName("cache");
        Element cacheNmElmnt = (Element) cacheNmElmntLst.item(0);
        NodeList cacheNm = cacheNmElmnt.getChildNodes();
        this.setCache(((Node) cacheNm.item(0)).getNodeValue());
        NodeList sqlNmElmntLst = fstElmnt.getElementsByTagName("sql");
        Element sqlNmElmnt = (Element) sqlNmElmntLst.item(0);
        NodeList sqlNm = sqlNmElmnt.getChildNodes();
        this.setSql(((Node) sqlNm.item(0)).getNodeValue());
        }

    }
    }
	
    public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getDialect() {
		return dialect;
	}
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getPool() {
		return pool;
	}
	public void setPool(String pool) {
		this.pool = pool;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getCache() {
		return cache;
	}
	public void setCache(String cache) {
		this.cache = cache;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	
}
