package com.postback.facades;

import com.postback.dto.UserDTO;

import com.postback.entities.User;
import com.postback.utils.HashUtil;
import com.postback.utils.UserStatuses;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

/**
 *
 * @author armenar
 */
@Stateless// it has internal pooling to the database
// 1000 user access, by itself it is created 30 instance,
//
//@Stateful
// and it create instance per user, 1000 user , 1000 instance, and it will keep session, untill you kill it
//Pre destory, post construct , for contact statetfull  you should have also Session Bean in frontend.
///

//@Singleton
//@Startup
// 
// Local and Remote, EJB3.1 we can use EJB without interfaces
//local @, it is used only from another EJB-s, 
//@LocalBean///
public class UserFacade extends AbstractFacade<User>
{

//    @Inject
//    private transient Logger logger;
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
//        logger.info( "Email:llllllllllllllllllllllllllllllllllllllllllllll " + email );
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
//            logger.error( e );
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
        }
        return finalList;
    }

    public UserDTO doRegister( UserDTO userDTO ) // Is this method get User ?????????????????????
    {
        User user = new User();
        //ModelMapper modelMapper = new ModelMapper();
        //User user = modelMapper.map( userDTO, User.class );
        user.setFirstname( userDTO.getFirstName() );
        user.setLastname( userDTO.getLastName() );
        user.setEmail( userDTO.getEmail() );
        user.setPasswd( userDTO.getPassword() );//hash passhword
        // boolean check = this.checkEmail( user.getEmail() );
        Long userId = 0L;
        //if ( !check )
        //{
        user.setStatus( UserStatuses.DISABLED_USER );
        em.persist( user );
        //em.merge( user ); for doE
        em.flush();
        userId = user.getId();
        //  }
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
