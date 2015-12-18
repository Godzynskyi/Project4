package com.godzynskyi.daoOld.car.filters;

import org.junit.Test;

import static org.junit.Assert.*;

public class YearFilterTest {

    @Test
    public void testStringPattern() throws Exception {
        YearFilter filter = new YearFilter(1990, 2016);
        String actual = filter.stringPattern();

        assertEquals("car.year BETWEEN 1990 AND 2016", actual);
    }

    @Test
    public void testStringPattern1() throws Exception {
        YearFilter filter = new YearFilter(2016, 1990);
        String actual = filter.stringPattern();

        assertEquals("car.year BETWEEN 1990 AND 2016", actual);
    }
}