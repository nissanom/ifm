package com.ifm.beans;

import com.ifm.dto.UserDTO;
import com.ifm.rest.client.UserRestClient;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Omer
 */
//@ManagedBean(name="userBean")
@Named
@SessionScoped
public class UserBean implements Serializable
{

    @Inject
    private UserRestClient userRestClient;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean()
    {
    }

    // get infromation via REST from remote microservice 
    public List<UserDTO> getUserRemoteList()
    {
        return userRestClient.getAllUsers();
    }

}
