package com.ifm.handlers;

/**
 *
 * @author Omer
 */
import java.io.IOException;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import org.apache.logging.log4j.Logger;

public class AuthenticationPhaseListener implements PhaseListener
{

    private static final long serialVersionUID = 1L;

    @Inject
    private SessionContext sessionContext;

    @Inject
    private ApplicationManager applicationManager;

    @Inject
    private transient Logger logger;

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

            String viewId = "/index.xhtml";

            if ( context.getViewRoot() != null && context.getViewRoot().getViewId() != null )
            {
                viewId = context.getViewRoot().getViewId();

            }
            String permissions = applicationManager.getPagePermissionMapping().get( viewId );
            //SessionContext sessionContext = (SessionContext) ex.getSessionMap().get("sessionContext");
            logger.info( viewId + " - permission " + permissions );

//            if ( sessionContext.getUser().getId() != null )
//            {
//                // for user manipulate
//            }
//            if (sessionContext == null && !viewId.contains("index.xhtml") 
//                    && !viewId.contains("register.xhtml") && !viewId.contains("login.xhtml")) {
//              
//            }
            if ( permissions != null && permissions.contains( "PUBLIC" ) )
            {
                return;
            }

            if ( permissions != null && sessionContext.getUser().getId() == null && permissions.contains( "LOGGED" ) && !viewId.contains( "index.xhtml" ) )
            {
                logger.info( "wrongAccess! redirect to index page" );
                redirect( context, "../index.jsf?wrongAccess" );
            }

        } catch ( Exception ex1 )
        {
            redirect( context, "../index.jsf?illegalAccess" );
            logger.error( ex1 );
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
