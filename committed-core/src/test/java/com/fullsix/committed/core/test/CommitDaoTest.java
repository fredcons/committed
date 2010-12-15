package com.fullsix.committed.core.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fullsix.committed.core.dao.CommitDao;
import com.fullsix.committed.core.model.Commit;
import com.fullsix.committed.core.model.CommitItem;
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
                
    }
    
    @Test
    public void searchByComment() {
    	
        long initialCount = commitDao.count();
        Assert.assertEquals(initialCount, 0, "Collection should be empty");
        Commit commit = new Commit();
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
    
    @Test
    public void searchByAuthor() {
    	
        long initialCount = commitDao.count();
        Assert.assertEquals(initialCount, 0, "Collection should be empty");
        Commit commit = new Commit();
        commit.setAuthor("fred");
        commitDao.save(commit);
        long finalCount = commitDao.count();
        Assert.assertEquals(finalCount, 1, "One commit should be present");
        
        SvnSearch search = new SvnSearch();
        
        search.setAuthor("fred");
        SvnSearchResult result = commitDao.search(search);
        LOGGER.info("Found " + result.getTotalCommits() + " for search " + search.getAuthor());
        Assert.assertEquals(result.getTotalCommits(), 1, "fred should match fred");
        
        search.setAuthor("xx");
        result = commitDao.search(search);
        LOGGER.info("Found " + result.getTotalCommits() + " for search " + search.getAuthor());
        Assert.assertEquals(result.getTotalCommits(), 0, "xx should not match fred");
                
    }
    
    @Test
    public void listDistinctValues() {
    	
        long initialCount = commitDao.count();
        Assert.assertEquals(initialCount, 0, "Collection should be empty");
        
        Commit commit1 = new Commit();
        commit1.setAuthor("fred");
        commit1.setRepositoryPath("/blah");
        commitDao.save(commit1);
        
        Commit commit2 = new Commit();
        commit2.setAuthor("fred");
        commit2.setRepositoryPath("/bleh");
        commitDao.save(commit2);
        
        Commit commit3 = new Commit();
        commit3.setAuthor("xx");
        commit3.setRepositoryPath("/bleh");
        CommitItem item1 = new CommitItem();
        item1.setPath("/foo/bar");
        commit3.addCommitItem(item1);
        CommitItem item2 = new CommitItem();
        item2.setPath("/baz");
        commit3.addCommitItem(item2);
        commitDao.save(commit3);
        
        List<String> distinctAuthors = commitDao.listDistinctAuthors();
        
        Assert.assertEquals(distinctAuthors.size(), 2, "we should have 2 distinct authors");
        Assert.assertTrue(distinctAuthors.contains("fred"), "fred should be there");
        Assert.assertTrue(distinctAuthors.contains("xx"), "xx should be there");
        
        List<String> distinctRootPaths = commitDao.listDistinctRootPaths();
        
        Assert.assertEquals(distinctRootPaths.size(), 2, "we should have 2 distinct paths");
        Assert.assertTrue(distinctRootPaths.contains("/blah"), "/blah should be there");
        Assert.assertTrue(distinctRootPaths.contains("/bleh"), "/bleh should be there");
        
        List<String> distinctFilePaths = commitDao.listDistinctFilePaths();
        
        Assert.assertEquals(distinctFilePaths.size(), 2, "we should have 2 distinct paths");
        Assert.assertTrue(distinctFilePaths.contains("/foo/bar"), "/foo/bar should be there");
        Assert.assertTrue(distinctFilePaths.contains("/baz"), "/baz should be there");        
                
    }

    

    /**
     * @param commitDao the commitDao to set
     */
    @Autowired
    public void setCommitDao(CommitDao commitDao) {
        this.commitDao = commitDao;
    }
    
}
