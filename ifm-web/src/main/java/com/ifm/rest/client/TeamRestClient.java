package com.ifm.rest.client;

import com.ifm.dto.TeamDTO;
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
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

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
    
    
     public TeamDTO findTeam(Long id)
    {
        logger.info( "FIND TEAM  IS CALLED" );
        TeamDTO  teamDTO = new TeamDTO();
        try
        {
            HttpGet request = new HttpGet( Constants.REST_PATH + "team/find/"+id );
            HttpResponse response = HTTP_CLIENT.execute( request );
            response.addHeader( Constants.CONTENT_TYPE, Constants.JSON_UTF_8 );
            HttpEntity entity = response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            teamDTO = mapper.readValue( EntityUtils.toString( entity ), TeamDTO.class );

        } catch ( IOException | ParseException e )
        {
            logger.error( e );
        }
        return teamDTO;
    }
}
