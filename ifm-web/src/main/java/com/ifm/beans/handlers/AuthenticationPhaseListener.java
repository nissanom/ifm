/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifm.beans.handlers;

/**
 *
 * @author Omer
 */
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
///import org.apache.log4j.Logger;

public class AuthenticationPhaseListener implements PhaseListener
{

    private static final long serialVersionUID = 1L;
    private static HashMap<String, String> pagePermissionMapping = null;
    ///  Logger log = Logger.getLogger(AuthenticationPhaseListener.class);

    @Inject
    private SessionContext sessionContext;

    private void initPagePermissionMapping()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if ( pagePermissionMapping == null )
        {
            pagePermissionMapping = new HashMap();
            try
            {
                ResourceBundle bundle = facesContext.getApplication().getResourceBundle( facesContext, "accessProp" );
                if ( bundle != null )
                {
                    Enumeration e = bundle.getKeys();
                    while ( e.hasMoreElements() )
                    {
                        String key = (String) e.nextElement();
                        String value = bundle.getString( key );
                        pagePermissionMapping.put( key, value );
                    }
                }
            } catch ( Exception e )
            {
            }
        }
    }

    @Override
    public void afterPhase( PhaseEvent event )
    {
    }

    @Override
    public synchronized void beforePhase( PhaseEvent event )
    {
        FacesContext context = event.getFacesContext();
        ExternalContext ex = context.getExternalContext();
        try
        {
            initPagePermissionMapping();

            String viewId = "/index.xhtml";

            if ( context.getViewRoot() != null && context.getViewRoot().getViewId() != null )
            {
                viewId = context.getViewRoot().getViewId();

            }
            String permissions = pagePermissionMapping.get( viewId );
            // SessionContext sessionContext = (SessionContext) ex.getSessionMap().get("sessionContext");

            if ( sessionContext.getUser().getId() != null )
            {
                // for user manipulate
            }

//            if (sessionContext == null && !viewId.contains("index.xhtml")) 
//                    && !viewId.contains("register.xhtml") && !viewId.contains("login.xhtml")) {
//              
//            }
            if ( permissions != null && permissions.contains( "PUBLIC" ) )
            {
                return;
            }
            if ( permissions != null && sessionContext.getUser().getId() == null && permissions.contains( "LOGGED" ) && !viewId.contains( "index.xhtml" ) )
            {
                redirect( context, "../index.jsf?illegalAccess" );
            }

        } catch ( Exception ex1 )
        {
            redirect( context, "../index.jsf?illegalAccess" );
            ex1.printStackTrace();
        }
    }

    private static void redirect( FacesContext facesContext, String url )
    {
        try
        {
            facesContext.getExternalContext().redirect( url );
        } catch ( IOException e )
        {
            throw new FacesException( "Cannot redirect to " + url + " due to IO exception.", e );
        }
    }

    @Override
    public PhaseId getPhaseId()
    {
        return PhaseId.RENDER_RESPONSE;
    }

}
