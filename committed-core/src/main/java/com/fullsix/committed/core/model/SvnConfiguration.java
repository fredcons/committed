package com.fullsix.committed.core.model;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;

/**
 * 
 * SvnConfiguration
 * Created on 23 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class SvnConfiguration {
    
    private static final Logger LOGGER = Logger.getLogger(SvnConfiguration.class);
        
    protected String svnUrl;
    protected String viewSvnUrl;
    protected String svnUsername;
    protected String svnPassword;
        
    @PostConstruct
    public void dump() {
        LOGGER.info("Using svn configuration : ");
        LOGGER.info("  svn url    : " + svnUrl);
    }
    
    /**
     * @return the svnUrl
     */
    public String getSvnUrl() {
        return this.svnUrl;
    }
    
    /**
     * @param svnUrl the svnUrl to set
     */
    public void setSvnUrl(String svnUrl) {
        this.svnUrl = svnUrl;
    }
   
    /**
     * @return the svnUsername
     */
    public String getSvnUsername() {
        return this.svnUsername;
    }
    
    /**
     * @param svnUsername the svnUsername to set
     */
    public void setSvnUsername(String svnUsername) {
        this.svnUsername = svnUsername;
    }
    
    /**
     * @return the svnPassword
     */
    public String getSvnPassword() {
        return this.svnPassword;
    }
    
    /**
     * @param svnPassword the svnPassword to set
     */
    public void setSvnPassword(String svnPassword) {
        this.svnPassword = svnPassword;
    }

    /**
     * @return the viewSvnUrl
     */
    public String getViewSvnUrl() {
        return this.viewSvnUrl;
    }

    /**
     * @param viewSvnUrl the viewSvnUrl to set
     */
    public void setViewSvnUrl(String viewSvnUrl) {
        this.viewSvnUrl = viewSvnUrl;
    }
    

}
