package com.ifm.beans;

import com.ifm.handlers.SessionContext;
import com.ifm.dto.UserDTO;
import com.ifm.rest.client.UserRestClient;
import com.ifm.utils.Constants;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.Logger;

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

    @Inject
    private transient Logger logger;

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
        logger.debug( "doLogin()" );
        UserDTO user = userRestClient.doLogin( email, password );

        if ( user != null && user.getId() != null )
        {
            logger.info( "User: " + user.getFirstName() + user.getLastName() + " has logged!" );
            sessionContext.setUser( user );
            return Constants.TEAMS_PAGE;
        }

        return Constants.INDEX_PAGE;
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
