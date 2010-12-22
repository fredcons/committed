package com.fullsix.committed.core.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.StringUtils;

import com.fullsix.committed.core.model.stats.Aggregation;

/**
 * StatsSearchResult
 * Created on 22 déc. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class StatsSearchResult {
    
    long queryTime;
    List<Aggregation> dateAggregations = new ArrayList<Aggregation>();
    List<Aggregation> hourAggregations = new ArrayList<Aggregation>();
       
    /**
     * @return the dateAggregations
     */
    public List<Aggregation> getDateAggregations() {
        return this.dateAggregations;
    }
    
    /**
     * @return the dateAggregations
     */
    public List<Aggregation> getSortedDateAggregations() {
        Collections.sort(this.dateAggregations, new Comparator<Aggregation>() {
            public int compare(Aggregation o1, Aggregation o2) {
                String[] splittedKey1 = o1.getKey().split("/");
                ArrayUtils.reverse(splittedKey1);
                String[] splittedKey2 = o2.getKey().split("/");
                ArrayUtils.reverse(splittedKey2);
                String key1 = StringUtils.arrayToDelimitedString(splittedKey1, "/");
                String key2 = StringUtils.arrayToDelimitedString(splittedKey2, "/");
                return key1.compareTo(key2);
            }  
        });
        return dateAggregations;
    }

    /**
     * @param dateAggregations the dateAggregations to set
     */
    public void setDateAggregations(List<Aggregation> dateAggregations) {
        this.dateAggregations = dateAggregations;
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
     * @return the hourAggregations
     */
    public List<Aggregation> getHourAggregations() {
        return this.hourAggregations;
    }
    
    /**
     * @return the dateAggregations
     */
    public List<Aggregation> getSortedHourAggregations() {
        Collections.sort(this.hourAggregations, new Comparator<Aggregation>() {
            public int compare(Aggregation o1, Aggregation o2) {
                return o1.getKey().compareTo(o2.getKey());
            }  
        });
        return hourAggregations;
    }

    /**
     * @param hourAggregations the hourAggregations to set
     */
    public void setHourAggregations(List<Aggregation> hourAggregations) {
        this.hourAggregations = hourAggregations;
    }
    
}
