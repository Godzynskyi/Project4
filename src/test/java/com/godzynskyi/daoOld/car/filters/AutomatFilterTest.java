package com.godzynskyi.daoOld.car.filters;

import com.godzynskyi.entity.Car;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Java Developer on 24.11.2015.
 */
public class AutomatFilterTest extends TestCase {

    @Test
    public void testStringPattern1() {
        AutomatFilter automatFilter = new AutomatFilter(Car.Transmission.MANUAL);
        String actual = automatFilter.stringPattern();

        assertEquals("car.automat = false", actual);
    }

    @Test
    public void testStringPattern2() {
        AutomatFilter automatFilter = new AutomatFilter(Car.Transmission.AUTOMAT);
        String actual = automatFilter.stringPattern();

        assertEquals("car.automat = true", actual);
    }

}
