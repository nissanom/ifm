package com.project.web.team;



import com.project.admin.Team;
import com.project.admin.facades.TeamFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;


@Named
@ViewScoped
public class TeamBean implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Inject
    private TeamFacade teamFacade;
    private Part uploadedFile = null;    
    private Team team = null;
    private FacesContext context = null;
    private ExternalContext externalContext = null;

    public TeamBean() {

    }

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
        team = new Team();

    }
    
    public void delete(Long teamId){
        teamFacade.remove(team);
    }
    
    public List<Team> getTeamList(){
        return teamFacade.findAll();
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

}
