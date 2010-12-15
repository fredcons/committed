package com.fullsix.committed.batch;

/**
 * 
 * IndexerContext
 * Created on 22 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class IndexerContext {
    
    private int commits;
    
    public void addCommit() {
        commits++;
    }

    /**
     * @return the commits
     */
    public int getCommits() {
        return this.commits;
    }
    
    

}
