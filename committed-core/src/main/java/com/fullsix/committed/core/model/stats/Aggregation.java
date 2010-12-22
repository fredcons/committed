package com.fullsix.committed.core.model.stats;

/**
 * Aggregation
 * Created on 22 déc. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class Aggregation {
    
    public static final String DATE_KEY = "groupDate";
    public static final String HOUR_KEY = "groupHour";
    
    protected String author;
    protected String repositoryPath;
    protected int count;
    protected String key;
    
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
     * @return the repositoryPath
     */
    public String getRepositoryPath() {
        return this.repositoryPath;
    }
    
    /**
     * @param repositoryPath the repositoryPath to set
     */
    public void setRepositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }
    
    /**
     * @return the count
     */
    public int getCount() {
        return this.count;
    }
    
    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    

}
