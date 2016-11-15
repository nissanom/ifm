package com.postback.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;

/**
 *
 * @author Omer
 */
public class LoggerProducer
{

    @Produces
    @Named
    public Logger produceLogger( InjectionPoint injectionPoint )
    {
        return LogManager.getLogger( injectionPoint.getMember().getDeclaringClass().getName() );
    }
}
