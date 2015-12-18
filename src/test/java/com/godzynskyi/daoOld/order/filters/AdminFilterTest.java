package com.godzynskyi.daoOld.order.filters;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdminFilterTest {

    @Test
    public void testStringPattern() throws Exception {
        AdminFilter adminFilter = new AdminFilter(null);
        String actual = adminFilter.stringPattern();

        assertEquals("admin_id = null", actual);

    }
}