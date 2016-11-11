package com.ifm.rest.client;

import com.ifm.dto.TeamDTO;
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
@Named(value = "teamRestClient")
@ApplicationScoped
public class TeamRestClient
{

    @Inject
    private transient Logger logger;

    private static final HttpClient HTTP_CLIENT = HttpClientBuilder.create().build();

    public TeamRestClient()
    {
    }

    

    public List<TeamDTO> getAllTeams()
    {
        logger.info( "TEAM LIST IS CALLED" );
        List<TeamDTO> list = new ArrayList<>();
        try
        {
            HttpGet request = new HttpGet( Constants.REST_PATH + "team/find/all" );
            
            HttpResponse response = HTTP_CLIENT.execute( request );
            response.addHeader( Constants.CONTENT_TYPE, "application/json;charset=UTF-8" );//????
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            list = mapper.readValue( EntityUtils.toString( entity ), List.class );

        } catch ( IOException | ParseException e )
        {
            logger.error( e );
        }
        return list;
    }
}