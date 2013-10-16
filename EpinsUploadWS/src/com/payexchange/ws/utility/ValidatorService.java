package com.payexchange.ws.utility;


import com.payexchange.ws.beans.DetailsBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author xtian
 */
public class ValidatorService {

    public boolean checkLoginParameters(HttpServletRequest request) {
        boolean result = false;

        if(StringUtils.isEmpty(request.getParameter("username")) ||
                StringUtils.isEmpty(request.getParameter("password"))) {
                  result = true;
          }
        return result;
    }

    public boolean checkIfPGP(String fileName) {

        boolean result = false;
        String extension = fileName.substring(fileName.length()-3, fileName.length());
        if (extension.equalsIgnoreCase("pgp")) {
            result = true;
        }
        return result;
    }

    public boolean checkDenom(String denom) {

        boolean result = false;

        if(denom.equals("300") || denom.equals("500") || denom.equals("1000")
                || denom.equals("100") || denom.equals("50")) {
            result = true;
        }
        return result;
    }

    public boolean checkTelco(String telco) {

        boolean result = false;

        if(telco.equals("GT") || telco.equals("TM")) {
            result = true;
        }
        return result;
    }

    public boolean checkIfSessionExist(HttpServletRequest request) {

        boolean result = false;
        HttpSession httpSession = request.getSession();
        String username = (String) httpSession.getAttribute("username");

        if(StringUtils.isNotEmpty(username)) {
            result = true;
        }
        return result;
    }

    public boolean checkIfSameDenom(String fromFileName, String insideFile) {

        boolean result = false;

        if(fromFileName.equals(getDenom(insideFile))) {
            result = true;
        }

        return result;
    }

    public String getDenom(String denom) {

        String[] xx = StringUtils.split(denom, " ");
        if(StringUtils.isNotEmpty(xx[2])) {
            denom = xx[2];
        }
        return denom;
    }

    public String[] getDecrypted(String dec) {

        String[] xx = StringUtils.split(dec, " ");
        return xx;
    }

    public boolean checkValidChanges(String oldpass,String origpass,String newpass,String conpass) {

        boolean result = false;

        if (StringUtils.equals(oldpass, origpass) && StringUtils.equals(newpass, conpass)) {
            result = true;
        }

        return result;
    }

    public boolean validateLogin(HttpServletRequest request,DetailsBean admin) {

        boolean result = true;

        if(StringUtils.isEmpty(admin.getUsername()) || !StringUtils.equals(admin.getUsername(), request.getParameter("username"))
                 || StringUtils.isEmpty(admin.getPassword()) || !StringUtils.equals(admin.getPassword(), request.getParameter("password"))) {
            result = false;
        }

        return result;
    }

    public boolean checkChangePass(HttpServletRequest request) {
        boolean result = false;

        if(StringUtils.isEmpty(request.getParameter("oldpass")) ||
                StringUtils.isEmpty(request.getParameter("newpass")) ||
                StringUtils.isEmpty(request.getParameter("conpass"))) {
                  result = true;
          }
        return result;
    }


}
