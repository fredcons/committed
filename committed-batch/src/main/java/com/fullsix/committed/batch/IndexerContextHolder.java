package com.fullsix.committed.batch;

/**
 * 
 * IndexerContextHolder
 * Created on 22 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class IndexerContextHolder {
    
    private static ThreadLocal<IndexerContext> holder = new ThreadLocal<IndexerContext>();
    
    public static IndexerContext get() {
        IndexerContext context = holder.get();
        if (context == null) {
            context = new IndexerContext();
            holder.set(context);
        }
        return context;
    }

}
