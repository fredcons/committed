package com.fullsix.committed.core.model;

import java.util.Date;

/**
 * 
 * SvnSearch
 * Created on 23 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class SvnSearch {
    
    public static final int DEFAULT_ROWS = 10;
    
    public static enum Sort { ASC, DESC};
    
    String text;
    String author;
    Date modifiedBefore;
    Date modifiedAfter;
        
    Sort sortDirection;
    int pageNumber;
    int rows;
    
    boolean go;
    
    /**
     * @return the pageNumber
     */
    public int getPageNumber() {
        return this.pageNumber > 0 ? this.pageNumber : 1;
    }

    /**
     * @param pageNumber the pageNumber to set
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
   

    /**
     * @return the text
     */
    public String getText() {
        return this.text;
    }
    
    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
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
     * @return the modifiedBefore
     */
    public Date getModifiedBefore() {
        return this.modifiedBefore;
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
     * @param modifiedAfter the modifiedAfter to set
     */
    public void setModifiedAfter(Date modifiedAfter) {
        this.modifiedAfter = modifiedAfter;
    }
    
    /**
     * @return the rows
     */
    public int getRows() {
        return this.rows != 0 ? this.rows : DEFAULT_ROWS;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * @return the go
     */
    public boolean isGo() {
        return this.go;
    }

    /**
     * @param go the go to set
     */
    public void setGo(boolean go) {
        this.go = go;
    }

    /**
     * @return the sortDirection
     */
    public Sort getSortDirection() {
        return this.sortDirection;
    }

    /**
     * @param sortDirection the sortDirection to set
     */
    public void setSortDirection(Sort sortDirection) {
        this.sortDirection = sortDirection;
    }
    
    

}
