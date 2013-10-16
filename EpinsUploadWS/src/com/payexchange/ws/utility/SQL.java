package com.payexchange.ws.utility;

public class SQL {

	public static final String SQL_MAX_ID_UPLOAD = "Select max(id) from Upload upload";
    public static final String SQL_MAX_ID_EPINS = "Select max(id) from Epins epins";


    public String sqlLogin(String username, String password) {

        String sql = "from Admin as a where a.username = '"+username+"' AND " +
                        "a.password = '"+password+"'";
        return sql;
    }

    public String sqlLogin(String username) {

        String sql = "Select a.username, a.password from Admin a where a.username = '"+username+"'";
        return sql;
    }

     public String checkEpin(String denom, String telco, String qty) {

//        String sql = "Select id,epin,uploaded_by,date_uploaded from Epins epins where status='0' and telco_type='"+telco+"' and denom='"+denom+"'" +
//                " and rownum <='"+qty+"'";
        String sql = "Select id,epin,uploaded_by,date_uploaded from Epins epins where status='0' and telco_type='"+telco+"' and denom='"+denom+"'";                ;
        return sql;
    }

    public String inventory() {

        String sql = "select denom,telco_type,status,count(status) from Epins epins group by status,denom,telco_type order by denom,telco_type asc";
        return sql;
    }
	
}
