package com.zhuby.c3p0;

import junit.framework.TestCase;

/**
 * Created by zhuby on 2016/5/4.
 */
public class QueryHandlerTest extends TestCase{
    public void testQueryOne() throws Exception {
        QueryHandler qh = new QueryHandler();
        String queryOne = qh.queryOne();
        assertNotNull( queryOne);
    }
}
