package com.fullsix.committed.core.service;

import org.apache.log4j.Logger;

import com.fullsix.committed.core.dao.CommitDao;
import com.fullsix.committed.core.model.SvnSearch;
import com.fullsix.committed.core.model.SvnSearchFormData;
import com.fullsix.committed.core.model.SvnSearchResult;

/**
 * SvnSearcher
 * Created on 28 avr. 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision: 68282 $
 * $Id: SvnSearcher.java 68282 2010-04-29 15:39:18Z cons $
 */
public class SvnSearcher {
    
    private static final Logger LOGGER = Logger.getLogger(SvnSearcher.class);
    
    //private SvnConfiguration svnConfiguration;
    private CommitDao commitDao;
    
    
    public SvnSearchResult search(SvnSearch search) {
        return commitDao.search(search);
    }

    public SvnSearchFormData initSearchData() {
    	SvnSearchFormData formData = new SvnSearchFormData();
    	formData.setAuthors(commitDao.listDistinctAuthors());
    	formData.setFilePaths(commitDao.listDistinctFilePaths());
    	formData.setRootPaths(commitDao.listDistinctRootPaths());
    	return formData;
    }

    /**
     * @param commitDao the commitDao to set
     */
    public void setCommitDao(CommitDao commitDao) {
        this.commitDao = commitDao;
    }
    
    /**
     * @param svnConfiguration the svnConfiguration to set
     */
    /**
    public void setSvnConfiguration(SvnConfiguration svnConfiguration) {
        this.svnConfiguration = svnConfiguration;
    }
    */

    

}
