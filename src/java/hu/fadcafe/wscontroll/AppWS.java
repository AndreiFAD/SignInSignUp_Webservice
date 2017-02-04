/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.fadcafe.wscontroll;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Fekete András Demeter
 */
@WebService(serviceName = "AppWS")
public class AppWS {

    /**
     * 
     * This is the registration web service operations
     * 
     */
    @WebMethod(operationName = "registration", action= "registration")
    public String registration(@WebParam(name = "user") String user,@WebParam(name = "pass") String pass,@WebParam(name = "mailaddress") String mailaddress,@WebParam(name = "level") Integer level) {
        
        String results = "";
        String mailcheckquery = "";
        String usernamecheckquery = "";
        String userresults = "";
        String mailresults = "";
        Integer userresultsnum = null;
        Integer mailresultsnum = null;
        Integer levelInt = 1;
        MySqlMethod con = new MySqlMethod();
        
        mailcheckquery = "SELECT count(1) FROM employs where upper(mailaddress)=upper('"+mailaddress+"');";
        usernamecheckquery = "SELECT count(1) FROM employs where upper(username)=upper('"+user+"');";
        userresults = con.ElementFromDB("select",usernamecheckquery);
        mailresults = con.ElementFromDB("select",mailcheckquery);
        userresultsnum = Integer.parseInt(userresults.replace("#';'", ""));
        mailresultsnum = Integer.parseInt(mailresults.replace("#';'", ""));
        System.out.println("covert");
        levelInt = level;
        System.out.println("after convert");
        if (userresultsnum == 0 && mailresultsnum == 0) {
                
            String sqlquery = "INSERT INTO employs VALUES (NOW(),'"+user+"','"+pass+"','"+ mailaddress +"',"+ levelInt +", 0);";
            System.out.println(sqlquery);
            con.ElementFromDB("insert",sqlquery);
            results = "Successful registration";
        
        } else if (userresultsnum == 0 && mailresultsnum != 0){
            results = mailaddress+", e-mail address is already in use";
            
        } else if (userresultsnum != 0 && mailresultsnum == 0){
            results = user+", username is already in use";
        
        } else if (userresultsnum != 0 && mailresultsnum != 0){
            results = mailaddress+", e-mail address and "+user+", username is already in use";
 
        }
       
        return "Registration information: " + results + "!";
    }
    
     /**
      * 
      * This is the login web service operations
      * 
      */
    @WebMethod(operationName = "login", action= "login")
    public String login(@WebParam(name = "user") String user,@WebParam(name = "pass") String pass) {
        
        String results = "";
        String login = "";
        String logincheckquery = "";
        String loginresults = "";
        String usernamecheckquery = "";
        String userresults = "";
        Integer userresultsnum = null;
        
        MySqlMethod con = new MySqlMethod();
        usernamecheckquery = "SELECT count(1) FROM employs where upper(username)=upper('"+user+"');";
        logincheckquery = "SELECT password FROM employs where upper(username)=upper('"+user+"');";
        login = con.ElementFromDB("select",logincheckquery);
        userresults = con.ElementFromDB("select",usernamecheckquery);
        loginresults = login.replace("#';'", "");
        userresultsnum = Integer.parseInt(userresults.replace("#';'", ""));
        if (loginresults.equals(pass) && userresultsnum == 1) {
               
           results = "Sign in successful";
        
        } else {
            
            results = "Sign in faild! Username or password is faild";
            
        }
       
        return "Login information: " + results + "!";
    }  
    
}
