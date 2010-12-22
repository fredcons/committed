package com.fullsix.committed.core.model;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * StatsSearch
 * Created on 23 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class StatsSearch {
        
    String rootPath;
    String author;
    Date modifiedBefore;
    Date modifiedAfter;
       
    /**
     * @return the author
     */
    public String getAuthor() {
        return this.author;
    }
    
    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * @return the modifiedBefore
     */
    public Date getModifiedBefore() {
        return this.modifiedBefore;
    }
    
    /**
     * @return the modifiedBefore
     */
    public Date getModifiedBeforeForSearch() {
        Date result = null;
        if (modifiedBefore != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(modifiedBefore);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            result = cal.getTime();
        }
        return result;
    }
    
    /**
     * @param modifiedBefore the modifiedBefore to set
     */
    public void setModifiedBefore(Date modifiedBefore) {
        this.modifiedBefore = modifiedBefore;
    }
    
    /**
     * @return the modifiedAfter
     */
    public Date getModifiedAfter() {
        return this.modifiedAfter;
    }
    
    /**
     * @return the modifiedAfter
     */
    public Date getModifiedAfterForSearch() {
        Date result = null;
        if (modifiedAfter != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(modifiedAfter);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            result = cal.getTime();
        }
        return result;
    }
    
    /**
     * @param modifiedAfter the modifiedAfter to set
     */
    public void setModifiedAfter(Date modifiedAfter) {
        this.modifiedAfter = modifiedAfter;
    }
    
    /**
     * @return the rootPath
     */
    public String getRootPath() {
        return this.rootPath;
    }

    /**
     * @param rootPath the rootPath to set
     */
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }


}
