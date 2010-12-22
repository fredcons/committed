package com.fullsix.committed.core.dao;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.fullsix.committed.core.model.Indexation;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.mongodb.Mongo;

/**
 * IndexationDao
 * Created on 14 déc. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class IndexationDao extends BasicDAO<Indexation, Serializable> {
    
    private static final Logger LOGGER = Logger.getLogger(IndexationDao.class);
    
    public IndexationDao(Mongo mongo, Morphia morphia, String dbName) {
        super(Indexation.class, mongo, morphia, dbName);
        ensureIndexes();
    }   
    
    public Indexation findLastForPath(String path) {
        Query<Indexation> query = this.createQuery();
        query.filter("repository", path);
        query.order("-date");
        QueryResults<Indexation> allIndexations = this.find(query);
        LOGGER.info(allIndexations.countAll() + " were found for path " + path);
        if (allIndexations.countAll() > 0) {
            return allIndexations.iterator().next();
        }
        return null;
    }

}
