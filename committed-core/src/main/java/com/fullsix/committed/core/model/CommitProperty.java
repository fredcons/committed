package com.fullsix.committed.core.model;

/**
 * Property
 * Created on 22 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class CommitProperty {
    
    String name;
    String value;
    
    public CommitProperty() {}
    
    public CommitProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return the value
     */
    public String getValue() {
        return this.value;
    }
    
    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    

}
