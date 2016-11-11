package com.ifm.teams;

import com.ifm.dto.TeamDTO;
import com.ifm.rest.client.TeamRestClient;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Omer
 */
@Named(value = "teamLoaderBean")
@ApplicationScoped
public class TeamLoaderBean
{

    @Inject
    private TeamRestClient teamRestClient;

    private List<TeamDTO> allTeams = new ArrayList<>();

    /**
     * Creates a new instance of TeamLoaderBean
     */
    public TeamLoaderBean()
    {
    }

    @PostConstruct
    public void init()
    {
        allTeams = teamRestClient.getAllTeams();
    }

    public List<TeamDTO> getAllTeams()
    {
        return allTeams;
    }

}
