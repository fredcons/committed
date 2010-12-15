package com.fullsix.committed.core.model;

import com.google.code.morphia.annotations.Indexed;

/**
 * File
 * Created on 22 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class CommitItem {
    
    @Indexed
    String path;
    String type;
    
    /*
    @Embedded
    List<CommitProperty> properties;
    */
    /**
     * @return the path
     */
    public String getPath() {
        return this.path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the type
     */
    public String getType() {
        return this.type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    

}
