package com.postback.facades;

import com.postback.dto.UserDTO;

import com.postback.entities.User;
import com.postback.utils.UserStatuses;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Omer
 */
@Stateless

public class UserFacade extends AbstractFacade<User>
{

    private static final Logger LOGGER = LogManager.getLogger( UserFacade.class.getName() );

    @PersistenceContext(name = "userPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public UserFacade()
    {
        super( User.class );
    }

    public UserDTO doLogin( String email, String password )
    {
        LOGGER.info( "User email: " + email );
        UserDTO destObject = null;
        try
        {
            User user = (User) em.createQuery( "SELECT o FROM User o WHERE o.email = ?1 AND o.passwd = ?2" )
                    .setParameter( 1, email )
                    //      .setParameter(2, HashUtil.hashPassword(password)).getSingleResult();   
                    .setParameter( 2, (password) ).getSingleResult();

            if ( user == null )
            {
                return null;
            }

            ModelMapper modelMapper = new ModelMapper();
            destObject = modelMapper.map( user, UserDTO.class );
        } catch ( Exception e )
        {
            LOGGER.error( e.getMessage() );
            return null;
        }
        return destObject;
    }

    public List<UserDTO> getUsers()
    {
        List<UserDTO> finalList = new ArrayList<>();
        try
        {
            List<User> postList = em.createQuery( "SELECT o FROM User o WHERE o.id > 0" ).getResultList();

            ModelMapper mapper = new ModelMapper();
            postList.stream().map( ( u ) -> mapper.map( u, UserDTO.class ) ).forEachOrdered( ( destObject ) ->
            {
                finalList.add( destObject );
            } );
        } catch ( Exception e )
        {
            LOGGER.error( e.getMessage() );
        }
        return finalList;
    }

    public UserDTO doRegister( UserDTO userDTO )
    {
        User user = new User();
        user.setFirstname( userDTO.getFirstName() );
        user.setLastname( userDTO.getLastName() );
        user.setEmail( userDTO.getEmail() );
        user.setPasswd( userDTO.getPassword() ); //hash passhword
        Long userId = 0L;
        user.setStatus( UserStatuses.DISABLED_USER );
        em.persist( user );
        em.flush();
        userId = user.getId();
        userDTO.setId( userId );
        return userDTO;
    }

    public boolean checkEmail( String email )
    {
        return false;
    }

    public UserDTO findById( Long id )
    {
        User user = this.find( id );
        ModelMapper modelMapper = new ModelMapper();
        UserDTO destObject = modelMapper.map( user, UserDTO.class );
        return destObject;
    }

    public Integer getCount()
    {
        return this.count();
    }

    public Long updateUser( UserDTO userDTO )
    {
        Long id = 0L;
        User existingUser = getEntityManager().find( User.class, userDTO.getId() );
        if ( existingUser != null )
        {
            ModelMapper modelMapper = new ModelMapper();
            existingUser = modelMapper.map( userDTO, User.class );
            getEntityManager().merge( existingUser );
            getEntityManager().flush();
            id = existingUser.getId();

        }
        return id;
    }

}
