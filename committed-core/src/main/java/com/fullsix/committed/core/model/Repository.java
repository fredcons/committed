package com.fullsix.committed.core.model;

/**
 * SvnRepository
 * Created on 14 déc. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class Repository {
    
    protected String svnUrl;
    protected String svnPath;
    
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
     * @return the svnPath
     */
    public String getSvnPath() {
        return this.svnPath;
    }
    
    /**
     * @param svnPath the svnPath to set
     */
    public void setSvnPath(String svnPath) {
        this.svnPath = svnPath;
    }
    
    /**
     * @return the full svnUrl
     */
    public String getFullSvnUrl() {
        return this.svnUrl + this.svnPath;
    }
    
    

}
