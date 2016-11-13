package com.postback.resources;

import com.postback.dto.TeamDTO;
import com.postback.facades.TeamFacade;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/team")
@Stateless
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class TeamResource
{

    @Inject
    private TeamFacade teamFacade;

    @GET
    @Path("/find/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TeamDTO> getAllUsers()
    {
        return teamFacade.getAllTeams();
    }
    
    
    @GET
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TeamDTO getTeam(@PathParam("id") Long id)
    {
        return teamFacade.findTeam( id );
    }
    
    

}
