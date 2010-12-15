package com.fullsix.committed.core.model;

import java.util.Date;

import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;

/**
 * Indexation
 * Created on 14 déc. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class Indexation {
    
    @Id 
    String id;
    @Indexed
    long revision;
    @Indexed
    Date date;
    @Indexed
    String repository;
    
    /**
     * @return the id
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return the revision
     */
    public long getRevision() {
        return this.revision;
    }
    
    /**
     * @param revision the revision to set
     */
    public void setRevision(long revision) {
        this.revision = revision;
    }
    
    /**
     * @return the date
     */
    public Date getDate() {
        return this.date;
    }
    
    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    /**
     * @return the repository
     */
    public String getRepository() {
        return this.repository;
    }
    
    /**
     * @param repository the repository to set
     */
    public void setRepository(String repository) {
        this.repository = repository;
    }
    
    

}
