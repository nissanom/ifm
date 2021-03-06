package com.postback.resources;

import com.postback.dto.UserDTO;
import com.postback.entities.User;
import com.postback.facades.UserFacade;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/auth")
@Stateless
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class UserResource
{

    private static final Logger LOGGER = LogManager.getLogger( UserResource.class.getName() );

    @Inject
    private UserFacade userFacade;

    @POST
    @Path("/user/login")
    @Consumes( { MediaType.APPLICATION_JSON })
    @Produces( { MediaType.APPLICATION_JSON })
    public UserDTO login( UserDTO dto )
    {
        LOGGER.info( "DO LOGGIN CALLED!!!!!!!!!!!!" );
        return userFacade.doLogin( dto.getEmail(), dto.getPassword() );
    }

    @GET
    @Path("/user/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getAllUsers()
    {
        return userFacade.getUsers();
    }

    @POST
    @Path("/user/register")
    @Consumes( { MediaType.APPLICATION_JSON })
    public Response create( UserDTO userDTO )
    {
        userDTO = userFacade.doRegister( userDTO );
        return Response.ok().entity( userDTO ).type( MediaType.APPLICATION_JSON ).build();
    }

    @PUT
    @Path("/user/update")
    @Consumes( { MediaType.APPLICATION_JSON })
    public Response update( UserDTO userDTO )
    {
        Long id = userFacade.updateUser( userDTO );
        return Response.ok().entity( id ).type( MediaType.APPLICATION_JSON ).build();
    }

    @DELETE
    @Path("/user/delete/{id}")
    public void remove( @PathParam("id") Long id )
    {
        userFacade.remove( userFacade.find( id ) );
    }

    @GET
    @Path("/user/find/{id}")
    @Produces( { MediaType.APPLICATION_JSON })
    public UserDTO find( @PathParam("id") Long id )
    {
        return userFacade.findById( id );
    }

    @PUT
    @Path("/user/{id}")
    @Consumes( { MediaType.APPLICATION_JSON })
    public void edit( @PathParam("id") Long id, User entity )
    {
        entity.setId( id );
        userFacade.edit( entity );
    }

}
