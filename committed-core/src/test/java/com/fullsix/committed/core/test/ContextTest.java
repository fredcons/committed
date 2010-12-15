package com.fullsix.committed.core.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * ContextTest
 * Created on 22 juin 10
 * @author Fred Cons (<i>cons@fullsix.com</i>)
 * @version $Revision$
 * $Id$
 */
@ContextConfiguration(locations={"classpath:/com/fullsix/committed/core/test/committed-core-test-context.xml"})
public class ContextTest extends AbstractTestNGSpringContextTests {

    @Test
    public void initSpringConfig() {
        
    }
}
