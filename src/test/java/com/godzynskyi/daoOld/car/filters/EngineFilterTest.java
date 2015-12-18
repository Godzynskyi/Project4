package com.godzynskyi.daoOld.car.filters;

import org.junit.Test;

import static org.junit.Assert.*;

public class EngineFilterTest {

    @Test
    public void testStringPattern() throws Exception {
        EngineFilter filter = new EngineFilter(1.6, 2.0);

        assertEquals("car.engine BETWEEN 1.6 AND 2.0", filter.stringPattern());
    }

    @Test
    public void testStringPattern1() throws Exception {
        EngineFilter filter = new EngineFilter(2.0, 1.6);

        assertEquals("car.engine BETWEEN 1.6 AND 2.0", filter.stringPattern());
    }
}