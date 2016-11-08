package com.ifm.beans;

//import org.apache.log4j.Logger;
import com.ifm.handlers.SessionContext;
import com.ifm.dto.UserDTO;
import com.ifm.rest.client.UserRestClient;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Omer
 */
@Named
@SessionScoped
public class LoginBean implements Serializable
{

    @Inject
    private UserRestClient userRestClient;

    @Inject
    private SessionContext sessionContext;
    
//    @Inject
//    private transient Logger logger;

    private String password;
    private String email;

    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean()
    {

    }

    public String doLogin()
    {
        UserDTO user = userRestClient.doLogin( email, password );
        
        if ( user != null && user.getId() != null )
        {
//            logger.info("User: " + user.getFirstName() + user.getLastName() + "has login!");
            sessionContext.setUser( user );
            return "profile";
        } 
        
        

        return "index";
    }

    public String doTransferMyMoney()
    {
        // UserDTO user  =  userRestClient.doTransferMyMoney(sessionContext.getUser().getId(), moneyCouunt);

        return "profile";
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

}
