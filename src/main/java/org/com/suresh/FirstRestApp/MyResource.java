package org.com.suresh.FirstRestApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public UserInfo getIt(User user) throws SQLException, ClassNotFoundException {
    	System.out.println("Hello World...");
    	System.out.println("am in... "+user.getUname() +" and "+user.getPwd());
    	Class.forName("com.mysql.jdbc.Driver");
    	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/user_mgmt_db", "root", "mysql");
    	String query = "select * from user_info where email_id=? and password=?";;
    	PreparedStatement ps = connection.prepareStatement(query);
    	ps.setString(1, user.getUname());
    	ps.setString(2, user.getPwd());
    	ResultSet rs = ps.executeQuery();
    	UserInfo info = new UserInfo();
    	int i =0;
    	while(rs.next()) {
    		System.out.println("valid user found...");
    		i +=1;
        	info.setFname(rs.getString("fname"));
        	info.setLname(rs.getString("lname"));
        	info.setStatus(rs.getString("status"));
    		
    	}
    	
    	
    	if(i==0) {
    		System.out.println("Invalid credentials...");
    		info.setErrorMsg("Invalid User");
    	}
    	
        return info;
    }
    
    public void m1(){
    	System.out.println("Am from m1() method");
    	System.out.println("closing resources...");
    }
    
  /*  public static void main(String[] args) throws SQLException {
		System.out.println(DriverManager.getConnection("jdbc:mysql://localhost:3307/user_mgmt_db", "root", "mysql"));
	}*/
    public static void main(String[] args) {
    	for (int i = 0; i <=9; i++) {
    		System.out.println("printing numbers..."+i);
    		if(i==4) {
    			System.out.println("found 4");
    		} else{
    			System.out.println("other number found");
    		}
			
		}
    }
}
