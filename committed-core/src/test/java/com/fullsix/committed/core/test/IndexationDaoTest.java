package com.fullsix.committed.core.test;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fullsix.committed.core.dao.CommitDao;
import com.fullsix.committed.core.dao.IndexationDao;
import com.fullsix.committed.core.model.Commit;
import com.fullsix.committed.core.model.Indexation;
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
public class IndexationDaoTest extends AbstractTestNGSpringContextTests {
    
    private static final Logger LOGGER = Logger.getLogger(IndexationDaoTest.class);
    
    IndexationDao indexationDao;
    
    @BeforeMethod
    public void clean() {
        indexationDao.getCollection().drop();
    }
    
    @Test
    public void findLastIndexation() {
        Indexation last = indexationDao.findLastForPath("blah");
        Assert.assertNull(last, "No indexation defined");
        
        Calendar now = Calendar.getInstance();  
        
        Indexation first = new Indexation();
        first.setRepository("blah");
        first.setDate(now.getTime());
        first.setRevision(1000);
        indexationDao.save(first);
        
        now.add(Calendar.DATE, 1);
        Indexation second = new Indexation();
        second.setRepository("blah");
        second.setDate(now.getTime());
        second.setRevision(2000);
        indexationDao.save(second);
        
        last = indexationDao.findLastForPath("blah");
        Assert.assertNotNull(last, "One indexation should be found");
        Assert.assertEquals(last.getRevision(), 2000, "The second revisions should be found");
                        
    }

    /**
     * @param indexationDao the indexationDao to set
     */
    @Autowired
    public void setIndexationDao(IndexationDao indexationDao) {
        this.indexationDao = indexationDao;
    }
    
}
