package com.fullsix.mongo;

import java.net.UnknownHostException;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.StringUtils;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

/**
 * MongoDbFactoryBean
 * Created on 22 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class MongoDbFactoryBean implements FactoryBean<Mongo> {
    
    private static final Logger LOGGER = Logger.getLogger(MongoDbFactoryBean.class);
    
    private static final int DEFAULT_TIMEOUT = 1000;
    
    private Mongo mongo;
    private String host;
    private int port;
    
    @PostConstruct
    public void initialize() {
        try {
            ServerAddress address = new ServerAddress(host, port);
            MongoOptions options = new MongoOptions();
            options.connectTimeout = DEFAULT_TIMEOUT;
            options.maxWaitTime = DEFAULT_TIMEOUT;
            options.socketTimeout = DEFAULT_TIMEOUT;
            mongo = new Mongo(address, options);      
            LOGGER.info("Mongo connection initialized on " + host + ":" + port);
            LOGGER.info("Available databases : " + StringUtils.collectionToCommaDelimitedString(mongo.getDatabaseNames()));
        } catch (UnknownHostException uhe) {
            LOGGER.fatal("Error while configuring mongodb", uhe);
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.FactoryBean#getObject()
     */
    @Override
    public Mongo getObject() throws Exception {
        return mongo;
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.FactoryBean#getObjectType()
     */
    @Override
    public Class<Mongo> getObjectType() {
        return Mongo.class;
    } 

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.FactoryBean#isSingleton()
     */
    @Override
    public boolean isSingleton() {
        return true;
    }

    /**
     * @param host the host to set
     */
    @Required
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @param port the port to set
     */
    @Required
    public void setPort(int port) {
        this.port = port;
    }

}
