package com.fullsix.mongo;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;

import com.google.code.morphia.Morphia;

/**
 * MongoDbFactoryBean
 * Created on 22 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
public class MorphiaFactoryBean implements FactoryBean<Morphia> {
    
    private static final Logger LOGGER = Logger.getLogger(MorphiaFactoryBean.class);
        
    private Morphia morphia;
    
    private List<Class<?>> mappedClasses;
    private List<String> mappedPackages;
    
    @PostConstruct
    protected void initialize() {
        morphia = new Morphia();
        if (mappedClasses != null) {
            for (Class<?> clazz : mappedClasses) {
                LOGGER.info("Mapping class : " + clazz.getName());
                morphia.map(clazz);
            }
        }
        if (mappedPackages != null) {
            for (String mappedPackage : mappedPackages) {
                LOGGER.info("Mapping package : " + mappedPackage);
                morphia.mapPackage(mappedPackage);
            }
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.FactoryBean#getObject()
     */
    @Override
    public Morphia getObject() throws Exception {
        return morphia;
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.FactoryBean#getObjectType()
     */
    @Override
    public Class<Morphia> getObjectType() {
        return Morphia.class;
    } 

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.FactoryBean#isSingleton()
     */
    @Override
    public boolean isSingleton() {
        return true;
    }

    /**
     * @param mappedClasses the mappedClasses to set
     */
    public void setMappedClasses(List<Class<?>> mappedClasses) {
        this.mappedClasses = mappedClasses;
    }

    /**
     * @param mappedPackages the mappedPackages to set
     */
    public void setMappedPackages(List<String> mappedPackages) {
        this.mappedPackages = mappedPackages;
    }

}
