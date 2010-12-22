package com.fullsix.committed.batch;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.fullsix.committed.core.dao.CommitDao;
import com.fullsix.committed.core.dao.IndexationDao;
import com.fullsix.committed.core.model.Commit;
import com.fullsix.committed.core.model.CommitItem;
import com.fullsix.committed.core.model.Indexation;
import com.fullsix.committed.core.model.Repository;
import com.fullsix.committed.core.model.SvnConfiguration;

/**
 * Indexer
 * Created on 22 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class Indexer {
    
    private static final Logger LOGGER = Logger.getLogger(Indexer.class);
    
    private static final long STEP = 100;
    
    protected ConfigurableApplicationContext context;
        
    private SvnConfiguration svnConfiguration;
    
    private List<Repository> repositories;
    
    private ISVNAuthenticationManager authManager;    
    
    private CommitDao commitDao;
    
    private IndexationDao indexationDao;
        
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        Indexer indexer = new Indexer();
        indexer.init().execute(args);        
    }
    
    /**
     * context loading
     * 
     * @return
     * @throws Exception
     */
    public Indexer init() throws Exception {
        try {
            context = new ClassPathXmlApplicationContext("classpath:com/fullsix/committed/batch/committed-indexer-context.xml");
            context.getAutowireCapableBeanFactory().autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, true);
            return this;
        } catch (Exception e) {
            LOGGER.error("Error initializing context", e);
            throw e;
        }
    }
    
    private void execute(String[] args) {
        
        long start = System.currentTimeMillis();
        try {
           
            // solr needs this to index remote svn urls
            Authenticator.setDefault(new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(svnConfiguration.getSvnUsername(), svnConfiguration.getSvnPassword().toCharArray());
                }
            });
                        
            DAVRepositoryFactory.setup();        
            
            authManager = SVNWCUtil.createDefaultAuthenticationManager(null, svnConfiguration.getSvnUsername(), svnConfiguration.getSvnPassword(), false);
            
            for (Repository repository : repositories) {                
                LOGGER.info("Indexing repo " + repository.getFullSvnUrl());                      
                indexRevisions(repository);
            }    
                        
        } catch (SVNException svne) {
            LOGGER.error(svne);
        } 
        long end = System.currentTimeMillis();
        LOGGER.info("Indexing took " + (end - start) + " ms for " + IndexerContextHolder.get().getCommits() + " commits");
    }
        
    @SuppressWarnings("unchecked")
    private void indexRevisions(Repository repository) throws SVNException {
        
        LOGGER.info("Indexing repository " + repository.getFullSvnUrl());     
        
        SVNRepository svnRepository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(repository.getFullSvnUrl()));
        svnRepository.setAuthenticationManager(authManager);            
        
        long latestRev = svnRepository.getLatestRevision();
        LOGGER.info("Latest revision for repository " + repository.getFullSvnUrl() + " is " + latestRev);   
        
        Indexation currentIndexation = new Indexation();
        currentIndexation.setRevision(latestRev);
        currentIndexation.setDate(new Date());
        currentIndexation.setRepository(repository.getSvnPath());
        
        long firstRev = 1;
        Indexation lastIndexation = indexationDao.findLastForPath(repository.getSvnPath());
        if (lastIndexation != null) {
            firstRev = lastIndexation.getRevision();
        }
        LOGGER.info("Starting indexation for repository at " + firstRev);           
        
        while (latestRev > firstRev) {
            long lowestRev = latestRev - STEP;
            LOGGER.info("Indexing from " + lowestRev + " to " + latestRev);           
            Collection logEntries = svnRepository.log( new String[] {""}, null, lowestRev > firstRev ? lowestRev : firstRev, latestRev, true, true);
            latestRev = latestRev - STEP;
            for (Iterator entries = logEntries.iterator( ); entries.hasNext();) {
                SVNLogEntry logEntry = (SVNLogEntry) entries.next();
                
                Commit commit = new Commit();
                commit.setAuthor(logEntry.getAuthor());
                commit.setComment(logEntry.getMessage());
                commit.setDate(logEntry.getDate());
                commit.setRevision(logEntry.getRevision());
                commit.setRepositoryPath(repository.getSvnPath());
                
                /*
                SVNProperties revisionProperties = logEntry.getRevisionProperties();
                if (revisionProperties != null && revisionProperties.nameSet() != null) {
                    for (Object propertyName : revisionProperties.nameSet()) {
                        String propertyValue = revisionProperties.getStringValue((String) propertyName);
                        CommitProperty property = new CommitProperty((String) propertyName, propertyValue);
                        commit.addCommitProperty(property);
                    }
                }
                */
                
                if (logEntry.getChangedPaths().size() > 0) {
                    Set<SVNLogEntryPath> changedPathsSet = logEntry.getChangedPaths().keySet();
                    for (Iterator changedPaths = changedPathsSet.iterator(); changedPaths.hasNext();) {
                        SVNLogEntryPath entryPath = (SVNLogEntryPath) logEntry.getChangedPaths().get(changedPaths.next());
                        
                        CommitItem commitItem = new CommitItem();
                        commitItem.setPath(entryPath.getPath());
                        commitItem.setType(String.valueOf(entryPath.getType()));
                        commit.addCommitItem(commitItem);
                    }
                }
                LOGGER.info("Saving commit " + commit.getRevision());    
                commitDao.save(commit);
                IndexerContextHolder.get().addCommit();
            }           
            
        }    
        
        indexationDao.save(currentIndexation);
    }
    
    /**
     * @param configuration the configuration to set
     */
    public void setSvnConfiguration(SvnConfiguration svnConfiguration) {
        this.svnConfiguration = svnConfiguration;
    }

    /**
     * @param commitDao the commitDao to set
     */
    public void setCommitDao(CommitDao commitDao) {
        this.commitDao = commitDao;
    }

    /**
     * @param repositories the repositories to set
     */
    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    /**
     * @param indexationDao the indexationDao to set
     */
    public void setIndexationDao(IndexationDao indexationDao) {
        this.indexationDao = indexationDao;
    }

}
