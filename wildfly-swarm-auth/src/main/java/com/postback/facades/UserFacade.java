package com.postback.facades;

import com.postback.dto.UserDTO;
import com.postback.entities.User;
import com.postback.utils.HashUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(name = "userPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public UserDTO doLogin(String email, String password) {
        UserDTO destObject = null;
        try {
            User user = (User) em.createQuery("SELECT o FROM User o WHERE o.email = ?1 AND o.passwd = ?2")
                    .setParameter(1, email)
                    .setParameter(2, HashUtil.hashPassword(password)).getSingleResult();    
            
            if (user == null) {
                return null;
            }            
                      
            
            ModelMapper modelMapper = new ModelMapper();
            destObject = modelMapper.map(user, UserDTO.class);                 
        } catch (Exception e) {
        }
        return destObject;
    }
    
     public List<UserDTO> getUsers() {
        List<UserDTO> finalList = new ArrayList<>();
        try {
            List<User> postList = em.createQuery("SELECT o FROM User o WHERE o.id > 0").getResultList();

            ModelMapper mapper = new ModelMapper();
            postList.stream().map((u) -> mapper.map(u, UserDTO.class)).forEachOrdered((destObject) -> {
                finalList.add(destObject);
            });
        } catch (Exception e) {
        }
        return finalList;
    }
    
    public Long registerNewUser(User user){           
         user.setPasswd(HashUtil.hashPassword(user.getPasswd()));
         user.setRegisterDate(new Date(System.currentTimeMillis()));         
         em.persist(user);
         em.flush();
         Long userId = user.getId();
        return userId;
    }
    


    public User findById(Long id) {
        return this.find(id);
    }

    public Integer getCount() {
        return this.count();
    }

}
