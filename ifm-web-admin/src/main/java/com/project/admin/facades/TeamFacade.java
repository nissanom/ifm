/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.admin.facades;

import com.project.admin.Team;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Stateless
public class TeamFacade extends AbstractFacade<Team>
{
    
    @PersistenceContext(unitName = "webadminPU")
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
    
    @Override
    public void edit( Team entity )
    {
        
        Team entityOld = getEntityManager().find( Team.class, entity.getId() );
        entityOld.setLogo( entity.getLogo() );
        System.out.println( "updating" );
        entityOld.setName( entity.getName() );
        
        getEntityManager().persist( entityOld );
        getEntityManager().flush();
    }
    
}
