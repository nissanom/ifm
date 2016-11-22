package com.ifm.rest.client;

import com.ifm.dto.UserDTO;
import com.ifm.utils.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

/**
 *
 * @author Omer
 */
@Named(value = "userRestClient")
@ApplicationScoped
public class UserRestClient
{

    @Inject
    private transient Logger logger;

    private static final HttpClient HTTP_CLIENT = HttpClientBuilder.create().build();

    public UserRestClient()
    {
    }

    public UserDTO doRegister( String firstName, String lastName, String email, String password )
    {
        UserDTO userDTO = null;
        try
        {
            HttpPost request = new HttpPost( Constants.REST_PATH + "auth/user/register" );
            JSONObject json = new JSONObject();
            json.put( "firstName", firstName );
            json.put( "lastName", lastName );
            json.put( "email", email );
            json.put( "password", password );

            StringEntity params = new StringEntity( json.toString(), Constants.UTF_8 );
            request.addHeader( Constants.CONTENT_TYPE, Constants.JSON_UTF_8 );//????
            request.setEntity( params );
            HttpResponse response = (HttpResponse) HTTP_CLIENT.execute( request );
            HttpEntity entity = response.getEntity();
            // userId = (EntityUtils.toString(entity));

            ObjectMapper mapper = new ObjectMapper();
            userDTO = mapper.readValue( (EntityUtils.toString( entity )), UserDTO.class );
        } catch ( IOException | ParseException e )
        {
            logger.error( e );
        }

        return userDTO;
    }

    public UserDTO doLogin( String email, String password )
    {
        logger.info( "REMOTE LIST CALLED" );
        UserDTO userModel = null;
        try
        {
            HttpPost request = new HttpPost( Constants.REST_PATH + "auth/user/login" );
            JSONObject json = new JSONObject();
            json.put( "email", email );
            json.put( "password", password );
            StringEntity params = new StringEntity( json.toString(), Constants.UTF_8 );
            request.addHeader( Constants.CONTENT_TYPE, Constants.JSON_UTF_8 );
            request.setEntity( params );
            HttpResponse response = (HttpResponse) HTTP_CLIENT.execute( request );
            HttpEntity entity = response.getEntity();
            if ( entity != null )
            {
                ObjectMapper mapper = new ObjectMapper();
                userModel = mapper.readValue( (EntityUtils.toString( entity )), UserDTO.class );
            }

        } catch ( IOException | ParseException e )
        {
            logger.error( e );
        }
        return userModel;
    }

    public List<UserDTO> getAllUsers()
    {
        logger.info( "REMOTE LIST CALLED" );
        List<UserDTO> list = new ArrayList<>();
        try
        {
            HttpGet request = new HttpGet( Constants.REST_PATH + "auth/user/all" );
            HttpResponse response = HTTP_CLIENT.execute( request );
            response.addHeader( Constants.CONTENT_TYPE, Constants.JSON_UTF_8 );
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue( EntityUtils.toString( entity ), List.class );

        } catch ( IOException | ParseException e )
        {
            logger.error( e );
        }
        return list;
    }
    
    public void doUserUpdate(UserDTO userDTO){
          Long userModel = null;
        try
        {
            HttpPut request = new HttpPut( Constants.REST_PATH + "auth/user/update" );
            JSONObject json = new JSONObject();
            json.put( "firstName", userDTO.getFirstName() );
            json.put( "lastName",  userDTO.getLastName());
            json.put( "email",  userDTO.getEmail());
            json.put( "id",  userDTO.getId());
            json.put( "teamId",  userDTO.getTeamId());
            
            StringEntity params = new StringEntity( json.toString(), Constants.UTF_8 );
            request.addHeader( Constants.CONTENT_TYPE, Constants.JSON_UTF_8 );
            request.setEntity( params );
            HttpResponse response = (HttpResponse) HTTP_CLIENT.execute( request );
            HttpEntity entity = response.getEntity();
            if ( entity != null ) // What is this if block?????
            {
                ObjectMapper mapper = new ObjectMapper();
                ///userModel = mapper.readValue( (EntityUtils.toString( entity )), Long.class );
            }

        } catch ( IOException | ParseException e )
        {
            logger.error( e );
        }
        
    }
    
     public UserDTO findUser(Long id)
    {
        logger.info( "Find user by id " + id );
        UserDTO user = null;
        try
        {
            HttpGet request = new HttpGet( Constants.REST_PATH + "auth/user/find/" +id );
            HttpResponse response = HTTP_CLIENT.execute( request );
            response.addHeader( Constants.CONTENT_TYPE, Constants.JSON_UTF_8 );
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue( EntityUtils.toString( entity ), UserDTO.class );

        } catch ( IOException | ParseException e )
        {
            logger.error( e );
        }
        return user;
    }
}
