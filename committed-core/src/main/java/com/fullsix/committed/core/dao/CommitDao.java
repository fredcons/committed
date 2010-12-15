package com.fullsix.committed.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.fullsix.committed.core.model.Commit;
import com.fullsix.committed.core.model.SvnSearch;
import com.fullsix.committed.core.model.SvnSearchResult;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.mongodb.Mongo;

/**
 * CommitDao
 * Created on 22 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class CommitDao extends BasicDAO<Commit, Serializable> {
    
    public CommitDao(Mongo mongo, Morphia morphia, String dbName) {
        super(Commit.class, mongo, morphia, dbName);
    }   
    
    public SvnSearchResult search(SvnSearch search) {
        SvnSearchResult result = new SvnSearchResult();
        long start = System.currentTimeMillis();
        
        Query<Commit> query = this.createQuery();
        if (StringUtils.hasText(search.getAuthor())) {
            query.filter("author", search.getAuthor());
        }
        if (StringUtils.hasText(search.getText())) {
            Pattern textRegexp = Pattern.compile(search.getText(), Pattern.CASE_INSENSITIVE);
            query.filter("comment", textRegexp);
        }
        if (search.getModifiedBefore() != null) {
            query.filter("date <=", search.getModifiedBefore());
        }
        if (search.getModifiedAfter() != null) {
            query.filter("date >=", search.getModifiedAfter());
        }
        
        result.setTotalCommits(count(query));        
           
        query.offset((search.getPageNumber() - 1) * search.getRows())
             .limit(search.getRows());
        
        query.order("-date");
        QueryResults<Commit> queryResult = this.find(query);
        result.setCommits(queryResult.asList());
        result.setQueryTime(System.currentTimeMillis() - start);
        return result;
    }
    
    @SuppressWarnings("unchecked")
    public List<String> listDistinctRootPaths() {
    	return this.getCollection().distinct("repositoryPath");
    }
    
    @SuppressWarnings("unchecked")
    public List<String> listDistinctFilePaths() {
    	return this.getCollection().distinct("commitItems.path");
    }
    
    @SuppressWarnings("unchecked")
    public List<String> listDistinctAuthors() {
    	return this.getCollection().distinct("author");
    }
    
    

}
