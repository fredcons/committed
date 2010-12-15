package com.fullsix.committed.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * SvnSearchResult
 * Created on 23 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class SvnSearchResult {
    
    long queryTime;
    long totalCommits;
    
    List<Commit> commits;

    /**
     * @return the commits
     */
    public List<Commit> getCommits() {
        if (this.commits == null) {
            this.commits = new ArrayList<Commit>();
        }
        return this.commits;
    }
    
    public void addCommit(Commit commit) {
        this.getCommits().add(commit);
    }

    /**
     * @param commits the commits to set
     */
    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    /**
     * @return the queryTime
     */
    public long getQueryTime() {
        return this.queryTime;
    }

    /**
     * @param queryTime the queryTime to set
     */
    public void setQueryTime(long queryTime) {
        this.queryTime = queryTime;
    }

    /**
     * @return the totalDocs
     */
    public long getTotalCommits() {
        return this.totalCommits;
    }

    /**
     * @param totalDocs the totalDocs to set
     */
    public void setTotalCommits(long totalCommits) {
        this.totalCommits = totalCommits;
    }
    
    public int getTotalPages() {
        return (int) totalCommits / SvnSearch.DEFAULT_ROWS + ((int) totalCommits % SvnSearch.DEFAULT_ROWS == 0 ? 0 : 1);
    }

}
