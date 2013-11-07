import com.payexchange.epins.ws.dao.EpinsUpload;




public class simu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		EpinsUpload upload = new EpinsUpload();
		
		String prodcode = "GT";
		int qty = 1;
		String target = "09276598742";
		String appname = "PC2MWEB";
		String trantype = "topup";
		String denom = "100";
		String message = "this is a message";
		String username = "Loadcentral";
		String password = "l0adcentral";
		String tranid = "1";
		
		upload.epinsupload("",prodcode,qty,"",target,appname,"",trantype,denom,message,username,password,tranid);
		
		
		
	}

}
