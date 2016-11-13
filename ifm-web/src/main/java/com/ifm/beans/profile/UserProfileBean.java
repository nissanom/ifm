package com.ifm.beans.profile;


import com.ifm.dto.TeamDTO;
import com.ifm.rest.client.TeamRestClient;
import com.ifm.utils.ParamUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@ViewScoped
public class UserProfileBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Inject
    private TeamRestClient teamFacade;
   
    
    
    private Long teamId;
    private TeamDTO team = null;
    private FacesContext context = null;
    private ExternalContext externalContext = null;
    
    public UserProfileBean() {
        
    }
    
    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
        team = new TeamDTO();
        
         teamId = ParamUtil.longValue((this.getRequestParameter("teamId")));
         System.out.println("Team id 2 " + teamId);
        
        if(teamId != null){
           team = teamFacade.findTeam(teamId);
        }
        
    }
     private String getRequestParameter(String paramName) {
        String returnValue = null;
        if (externalContext.getRequestParameterMap().containsKey(paramName)) {
            returnValue = (externalContext.getRequestParameterMap().get(paramName));
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
