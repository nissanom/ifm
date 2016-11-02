/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifm.rest.client;

import com.ifm.dto.UserDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

/**
 *
 * @author Omer
 */
@Named(value = "userRestClient")
@ApplicationScoped
public class UserRestClient {
 
    private static final String REST_PATH ="http://localhost:8080/";
    
     private static final HttpClient HTTP_CLIENT = HttpClientBuilder.create().build();  
    
    public UserRestClient() {
    }
    
     public UserDTO saveUser(UserDTO model) {
        
        
        try {
            HttpPost request = new HttpPost(REST_PATH+ "auth/user/register");
            JSONObject json = new JSONObject();
            json.put("firstname", model.getFirstname());
            json.put("lastname", model.getLastname());
            json.put("email", model.getEmail());
            json.put("password", model.getPasswd());
           
            StringEntity params = new StringEntity(json.toString(), "UTF-8");
            request.addHeader("content-type", "application/json;charset=UTF-8");
            request.addHeader("charset", "UTF-8");
            request.setEntity(params);
            HttpResponse response = (HttpResponse) HTTP_CLIENT.execute(request);
            HttpEntity entity = response.getEntity();
            // userId = (EntityUtils.toString(entity));

            ObjectMapper mapper = new ObjectMapper();
            model = mapper.readValue((EntityUtils.toString(entity)), UserDTO.class);
        } catch (IOException | ParseException ex) {

        } finally {
            HTTP_CLIENT.getConnectionManager().shutdown();
        }

        return model;
    }
     
     
      public List<UserDTO> getAllUsers() {
        System.out.println("REMOTE LIST CALLED ");
        List<UserDTO> list = new ArrayList<>();
        try {
            
            HttpGet request = new HttpGet(REST_PATH+ "auth/user/all");
            request.addHeader("charset", "UTF-8");
            HttpResponse response = HTTP_CLIENT.execute(request);
            response.addHeader("content-type", "application/json;charset=UTF-8");
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue(EntityUtils.toString(entity), List.class);
            
            System.out.println("list size " + list.size());
           

        } catch (IOException | ParseException e) {
            e.printStackTrace();

        }
        return list;
    }

    
}
