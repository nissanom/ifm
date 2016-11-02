/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifm.beans;

import com.ifm.dto.UserDTO;
import com.ifm.entities.User;
import com.ifm.facades.UserFacade;
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
public class UserBean implements Serializable {

    @Inject
    private UserFacade userFacade;
    
    @Inject
    private UserRestClient userRestClient;
    
    

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
    
    
    // get information directly from database
    public List<User>  getUserList(){
        return userFacade.findAll();
    }
    
    // get infromation via REST from remote microservice 
     public List<UserDTO>  getUserRemoteList(){
        return userRestClient.getAllUsers();
    }
    
}
