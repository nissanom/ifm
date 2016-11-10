package com.ifm.beans;

import io.netty.handler.codec.http.Cookie;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Omer
 */
@Named(value = "logoutBean")
@RequestScoped
public class LogoutBean
{

    private FacesContext context = null;
    private ExternalContext externalContext = null;

    public LogoutBean()
    {
        System.out.println( "UserLogout called" );
    }

    public String doLogout()
    {
        context = FacesContext.getCurrentInstance();
        externalContext = context.getExternalContext();
        //    externalContext.getSessionMap().remove("sessionContext");
        HttpSession session = (HttpSession) externalContext.getSession( true );
        session.invalidate();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        return "logout";
    }

}
