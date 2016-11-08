package com.ifm.handlers;

import com.ifm.dto.UserDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Omer
 */
@Named(value = "sessionContext")
@SessionScoped
public class SessionContext implements Serializable
{

    private UserDTO user = new UserDTO();
    /// my payments DTO
    // my current games///

    /**
     * Creates a new instance of SessionContext
     */
    public SessionContext()
    {
    }

    public UserDTO getUser()
    {
        return user;
    }

    public void setUser( UserDTO user )
    {
        this.user = user;
    }

}
