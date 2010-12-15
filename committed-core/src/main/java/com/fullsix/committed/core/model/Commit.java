package com.fullsix.committed.core.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;

/**
 * Committed
 * Created on 14 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
@Entity("commits")
public class Commit {    
    
    private static final String DISPLAY_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    
    @Id 
    String id;
    @Indexed
    long revision;
    @Indexed
    String comment;
    @Indexed
    String author;
    @Indexed
    Date date;
    @Indexed
    String repositoryPath;
    
    @Embedded
    List<CommitItem> commitItems;
    
    /*
    @Embedded
    List<CommitProperty> commitProperties;
    */
    
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
     * @return the comment
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

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
     * @return the date
     */
    public Date getDate() {
        return this.date;
    }
    
    /**
     * @return the date
     */
    public String getFormattedDate() {
        DateFormat df = new SimpleDateFormat(DISPLAY_DATE_FORMAT);
        return df.format(this.date);
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the files
     */
    public List<CommitItem> getCommitItems() {
        return this.commitItems;
    }

    /**
     * @param files the files to set
     */
    public void setCommitItems(List<CommitItem> commitItems) {
        this.commitItems = commitItems;
    }

    /**
     * @return the properties
     */
    /*
    public List<CommitProperty> getCommitProperties() {
        return this.commitProperties;
    }
    */
    /*
    public void addCommitProperty(CommitProperty property) {
        if (commitProperties == null) {
            commitProperties = new ArrayList<CommitProperty>();            
        }
        commitProperties.add(property);
    }
    */
    
    public void addCommitItem(CommitItem file) {
        if (commitItems == null) {
            commitItems = new ArrayList<CommitItem>();            
        }
        commitItems.add(file);
    }

    /**
     * @return the repository
     */
    public String getRepositoryPath() {
        return this.repositoryPath;
    }

    /**
     * @param repository the repository to set
     */
    public void setRepositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    /**
     * @param properties the properties to set
     */
    /*
    public void setCommitProperties(List<CommitProperty> commitProperties) {
        this.commitProperties = commitProperties;
    }
    */
    
    
    
}
