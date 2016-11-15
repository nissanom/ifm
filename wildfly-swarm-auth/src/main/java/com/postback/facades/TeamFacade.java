package com.postback.facades;

import com.postback.dto.TeamDTO;
import com.postback.entities.Team;

import java.util.ArrayList;
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

public class TeamFacade extends AbstractFacade<Team>
{

    @PersistenceContext(name = "userPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public TeamFacade()
    {
        super( Team.class );
    }

     public TeamDTO findTeam(Long id) {
         Team team = getEntityManager().find(Team.class, id);
         System.out.println("BYTE ARRAY " + team.getLogo());
          ModelMapper modelMapper = new ModelMapper();
          TeamDTO destObject = modelMapper.map( team, TeamDTO.class );
        return destObject;
    }

    public List<TeamDTO> getAllTeams()
    {
        List<TeamDTO> finalList = new ArrayList<>();
        try
        {
            List<Team> postList = em.createQuery( "SELECT o FROM Team o WHERE o.id > 0" ).getResultList();
            ModelMapper mapper = new ModelMapper();
            postList.stream().map( ( u ) -> mapper.map( u, TeamDTO.class ) ).forEachOrdered( ( destObject ) ->
            {
                finalList.add( destObject );
            } );
        } catch ( Exception e )
        {
        }
        return finalList;
    }

   

    public Integer getCount()
    {
        return this.count();
    }

}
