package com.fullsix.committed.core.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.fullsix.committed.core.model.Commit;
import com.fullsix.committed.core.model.StatsSearch;
import com.fullsix.committed.core.model.StatsSearchResult;
import com.fullsix.committed.core.model.SvnSearch;
import com.fullsix.committed.core.model.SvnSearchResult;
import com.fullsix.committed.core.model.stats.Aggregation;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
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
        ensureIndexes();
    }   
    
    public SvnSearchResult search(SvnSearch search) {
        SvnSearchResult result = new SvnSearchResult();
        long start = System.currentTimeMillis();
        
        Query<Commit> query = this.createQuery();
        if (StringUtils.hasText(search.getAuthor())) {
            query.filter("author", search.getAuthor());
        }
        if (StringUtils.hasText(search.getRootPath())) {
            query.filter("repositoryPath", search.getRootPath());
        }
        if (StringUtils.hasText(search.getText())) {
            Pattern textRegexp = Pattern.compile(search.getText(), Pattern.CASE_INSENSITIVE);
            query.filter("comment", textRegexp);
        }
        if (search.getModifiedBefore() != null) {
            query.filter("date <=", search.getModifiedBeforeForSearch());
        }
        if (search.getModifiedAfter() != null) {
            query.filter("date >=", search.getModifiedAfterForSearch());
        }
        if (StringUtils.hasText(search.getFilePath())) {
            Pattern pathRegexp = Pattern.compile(search.getFilePath(), Pattern.CASE_INSENSITIVE);
            query.filter("commitItems.path", pathRegexp);
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
    
    public SvnSearchResult findLast() {
        SvnSearchResult result = new SvnSearchResult();
        long start = System.currentTimeMillis();
                   
        Query<Commit> query = this.createQuery();
        query.offset(0).limit(SvnSearch.DEFAULT_ROWS);
        query.order("-date");
        
        result.setTotalCommits(count(query));        
        
        QueryResults<Commit> queryResult = this.find(query);
        result.setCommits(queryResult.asList());
        result.setQueryTime(System.currentTimeMillis() - start);
        return result;
    }
    
    public StatsSearchResult findStats(StatsSearch search) {
        
        StatsSearchResult statsSearchResult = new StatsSearchResult();
        long start = System.currentTimeMillis();
        
        BasicDBList result = findStatsByKey(Aggregation.DATE_KEY, search);
        System.out.println("by date : " + result.toString());
        for (Iterator<Object> it = result.iterator(); it.hasNext();) {
            BasicDBObject dbObject = (BasicDBObject) it.next();
            Aggregation dateAggregation = new Aggregation();
            dateAggregation.setAuthor(dbObject.getString("author"));
            dateAggregation.setRepositoryPath(dbObject.getString("repositoryPath"));
            dateAggregation.setCount(dbObject.getInt("count"));
            dateAggregation.setKey(dbObject.getString(Aggregation.DATE_KEY));
            statsSearchResult.getDateAggregations().add(dateAggregation);
        }
        
        result = findStatsByKey(Aggregation.HOUR_KEY, search);
        System.out.println("by hour : " + result.toString());
        for (Iterator<Object> it = result.iterator(); it.hasNext();) {
            BasicDBObject dbObject = (BasicDBObject) it.next();
            Aggregation hourAggregation = new Aggregation();
            hourAggregation.setAuthor(dbObject.getString("author"));
            hourAggregation.setRepositoryPath(dbObject.getString("repositoryPath"));
            hourAggregation.setCount(dbObject.getInt("count"));
            hourAggregation.setKey(dbObject.getString(Aggregation.HOUR_KEY));
            statsSearchResult.getHourAggregations().add(hourAggregation);
        }
        
        statsSearchResult.setQueryTime(System.currentTimeMillis() - start);
        return statsSearchResult;
                
    }
    
    private BasicDBList findStatsByKey(String keyAsString, StatsSearch search) {
        DBObject key = new BasicDBObject();
        key.put(keyAsString, true);
        
        if (!StringUtils.hasText(search.getAuthor())) {
            key.put("author", true);
        }
        if (!StringUtils.hasText(search.getRootPath())) {
            key.put("repositoryPath", true);
        }
          
        DBObject condition = new BasicDBObject();
        if (StringUtils.hasText(search.getAuthor())) {
            condition.put("author", search.getAuthor());
        }
        if (StringUtils.hasText(search.getRootPath())) {
            condition.put("repositoryPath", search.getRootPath());
        }
        if (search.getModifiedBefore() != null) {
            condition.put("date", new BasicDBObject("$lte", search.getModifiedBeforeForSearch()));
        }
        if (search.getModifiedAfter() != null) {
            condition.put("date", new BasicDBObject("$gte", search.getModifiedAfterForSearch()));
        }
        
        DBObject initial = new BasicDBObject();
        initial.put("count", 0);
        
        String reduce = "function(obj,prev){prev.count++}";
        
        BasicDBList result = (BasicDBList) this.getCollection().group(key, condition, initial, reduce);      
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
