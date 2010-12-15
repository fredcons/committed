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
    //protected String svnPath;
    protected String svnUsername;
    protected String svnPassword;
    
    /*
    protected List<String> prefixWhiteList;
    protected List<String> extensionsWhiteList;
    */
    
    @PostConstruct
    public void dump() {
        LOGGER.info("Using svn configuration : ");
        //LOGGER.info("  svn url    : " + svnUrl);
        //LOGGER.info("  svn path    : " + svnPath);
        LOGGER.info("  svn user    : " + svnUsername);
        /*
        LOGGER.info("  prefixes    : " + StringUtils.collectionToCommaDelimitedString(prefixWhiteList));
        LOGGER.info("  extensions  : " + StringUtils.collectionToCommaDelimitedString(extensionsWhiteList));
        */
    }
    
    /**
     * @return the svnUrl
     */
    public String getSvnUrl() {
        return this.svnUrl;
    }
    
    /**
     * @return the full svnUrl
     */
//    public String getFullSvnUrl() {
//        return this.svnUrl + this.svnPath;
//    }
    
    /**
     * @param svnUrl the svnUrl to set
     */
    public void setSvnUrl(String svnUrl) {
        this.svnUrl = svnUrl;
    }
    
    /**
     * @return the svnPath
     */
//    public String getSvnPath() {
//        return this.svnPath;
//    }
    
    /**
     * @param svnPath the svnPath to set
     */
//    public void setSvnPath(String svnPath) {
//        this.svnPath = svnPath;
//    }
    
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
    

}
