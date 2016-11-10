package com.ifm.beans;

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
public class RegisterBean implements Serializable
{

    @Inject
    private UserRestClient userRestClient;

    @Inject
    private SessionContext sessionContext;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    /**
     * Creates a new instance of RegisterBean
     */
    public RegisterBean()
    {

    }

    public String doRegister()
    {
        UserDTO userDTO = userRestClient.doRegister( firstName, lastName, email, password );
        if ( userDTO != null )
        {
            return "success";
        }

        //TODO -
        // How to handle verify????
        //return "profileVerify";
        //TODEL
        return "fail";
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

}
