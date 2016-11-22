package com.ifm.beans.profile;

import com.ifm.dto.TeamDTO;
import com.ifm.dto.UserDTO;
import com.ifm.handlers.SessionContext;
import com.ifm.rest.client.TeamRestClient;
import com.ifm.rest.client.UserRestClient;
import com.ifm.utils.ParamUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.faces.view.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class UserProfileBean implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Inject
    private UserRestClient userRestClient;

    @Inject
    private TeamRestClient teamRestClient;

    @Inject
    private SessionContext sessionContext;

    private Long teamId;
    private TeamDTO team = null;
    private UserDTO userDTO = null;
    private FacesContext context = null;
    private ExternalContext externalContext = null;

    public UserProfileBean()
    {

    }

    @PostConstruct
    public void init()
    {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
        userDTO = userRestClient.findUser( sessionContext.getUser().getId() );
        team = new TeamDTO();

        teamId = ParamUtil.longValue( (this.getRequestParameter( "teamId" )) );
        System.out.println( "Team id 2 " + teamId );

        if ( teamId != null )
        {
            team = teamRestClient.findTeam( teamId );
            sessionContext.setTeam( team );
            sessionContext.getUser().setTeamId( teamId );
            doInBackgroud();
        }

    }

    @Asynchronous
    public void doInBackgroud()
    {
        sessionContext.getUser().setTeamId( sessionContext.getTeam().getId() );
        System.out.println( "######Update user async: team id is:=> " + sessionContext.getTeam().getId() );
        userRestClient.doUserUpdate( sessionContext.getUser() );
    }

    public UserDTO getUserDTO()
    {
        return userDTO;
    }

    public void setUserDTO( UserDTO userDTO )
    {
        this.userDTO = userDTO;
    }

    public void doUpdate()
    {
        userDTO.setTeamId( sessionContext.getTeam().getId() );
        userRestClient.doUserUpdate( userDTO );
    }

    private String getRequestParameter( String paramName )
    {
        String returnValue = null;
        if ( externalContext.getRequestParameterMap().containsKey( paramName ) )
        {
            returnValue = (externalContext.getRequestParameterMap().get( paramName ));
        }
        return returnValue;
    }

    public Long getTeamId()
    {
        return teamId;
    }

    public void setTeamId( Long teamId )
    {
        this.teamId = teamId;
    }

    public TeamDTO getTeam()
    {
        return team;
    }

    public void setTeam( TeamDTO team )
    {
        this.team = team;
    }

}
