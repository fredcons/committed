package com.fullsix.committed.core.test;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fullsix.committed.core.dao.CommitDao;
import com.fullsix.committed.core.model.Commit;
import com.fullsix.committed.core.model.SvnSearch;
import com.fullsix.committed.core.model.SvnSearchResult;

/**
 * CommitDaoTest
 * Created on 22 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
@ContextConfiguration(locations={"classpath:/com/fullsix/committed/core/test/committed-core-test-context.xml"})
public class CommitDaoTest extends AbstractTestNGSpringContextTests {
    
    private static final Logger LOGGER = Logger.getLogger(CommitDaoTest.class);
    
    CommitDao commitDao;
    
    @BeforeMethod
    public void clean() {
        commitDao.getCollection().drop();
    }
    
    @Test
    public void insertCommit() {
        long initialCount = commitDao.count();
        Assert.assertEquals(initialCount, 0, "Collection should be empty");
        Commit commit = new Commit();
        commit.setAuthor("fred");
        commit.setComment("blah");
        commitDao.save(commit);
        long finalCount = commitDao.count();
        Assert.assertEquals(finalCount, 1, "One commit should be present");
        
        SvnSearch search = new SvnSearch();
        
        search.setText("bla");
        SvnSearchResult result = commitDao.search(search);
        LOGGER.info("Found " + result.getTotalCommits() + " for search " + search.getText());
        Assert.assertEquals(result.getTotalCommits(), 1, "bla should match blah");
        
        search.setText("blah");
        result = commitDao.search(search);
        LOGGER.info("Found " + result.getTotalCommits() + " for search " + search.getText());
        Assert.assertEquals(result.getTotalCommits(), 1, "blah should match blah");
        
        search.setText("blahh");
        result = commitDao.search(search);
        LOGGER.info("Found " + result.getTotalCommits() + " for search " + search.getText());
        Assert.assertEquals(result.getTotalCommits(), 0, "blahh should not match blah");
        
        search.setText("lah");
        result = commitDao.search(search);
        LOGGER.info("Found " + result.getTotalCommits() + " for search " + search.getText());
        Assert.assertEquals(result.getTotalCommits(), 1, "lah should match blah");
        
    }

    /**
     * @param commitDao the commitDao to set
     */
    @Autowired
    public void setCommitDao(CommitDao commitDao) {
        this.commitDao = commitDao;
    }
    
}
