package com.project.web.team;

import com.project.admin.Team;
import com.project.admin.facades.TeamFacade;
import com.project.web.utils.ParamUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@ViewScoped
public class TeamUpdateBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Inject
    private TeamFacade teamFacade;
    private Part uploadedFile = null;  
    
    
    private Long teamId;
    private Team team = null;
    private FacesContext context = null;
    private ExternalContext externalContext = null;
    
    public TeamUpdateBean() {
        
    }
    
    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
        team = new Team();
        
        teamId = ParamUtil.longValue((this.getRequestParameter("teamId")));
         System.out.println("Team id 2 " + teamId);
        
        if(teamId != null){
        team = teamFacade.find(teamId);
        }
        
    }
     private String getRequestParameter(String paramName) {
        String returnValue = null;
        if (externalContext.getRequestParameterMap().containsKey(paramName)) {
            returnValue = (externalContext.getRequestParameterMap().get(paramName));
        }
        return returnValue;
    }
    public List<Team> getTeamList() {
        return teamFacade.findAll();
    }
    
    public String doAction() throws IOException {
        if (uploadedFile != null) {
            long size = uploadedFile.getSize();
            long maxSize = 5 * 1000000;
            
            String content = uploadedFile.getContentType();
            
            InputStream stream = uploadedFile.getInputStream();
            
            byte[] contentBytes = new byte[(int) size];
            
            stream.read(contentBytes);
            
            team.setLogo(contentBytes);
            
            if (!content.equalsIgnoreCase("image/jpeg") && !content.equalsIgnoreCase("image/pjpeg")
                    && !content.equalsIgnoreCase("image/jpg") && !content.equalsIgnoreCase("image/gif")
                    && !content.equalsIgnoreCase("image/x-png") && !content.equalsIgnoreCase("image/png") 
                    &&  !content.equalsIgnoreCase("image/x-icon")) {
                try {
                    
                 ////   return null;
                } catch (Exception e) {
                }
            }
            team.setId(teamId);
            teamFacade.edit(team);
        }
        return "teamlist";
        
    }
    
    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }
    
    public Part getUploadedFile() {
        return uploadedFile;
    }
    
    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
    
}
