package com.ifm.handlers;

import javax.inject.Named;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Omer
 */
@Named(value = "applicationManager")
@ApplicationScoped
public class ApplicationManager implements Serializable // Is it need to implements Serializable??
{
    
    @Inject
    private transient Logger logger;
    // Is @ApplicationScoped acts like a singleton or should I make it singleton by holder or something like this??
    private HashMap<String, String> pagePermissionMapping = null; // Is it should be static??
    private String imagePath = "resources/assets/img/bg/img_ball14.jpg";

    /**
     * Creates a new instance of ApplicationManager
     */
    public ApplicationManager() // Should I use @PostConstruct??
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        initPagePermissionMapping(); // Where I need to call initPagePermissionMapping()??
    }
    
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
                logger.error( e.getMessage() );
            }
        }
    }
    
    public String getImagePath()
    {
        return imagePath;
    }
    
    public void setImagePath( String imagePath )
    {
        this.imagePath = imagePath;
    }
    
    public HashMap<String, String> getPagePermissionMapping()
    {
        return pagePermissionMapping;
    }
    
}
