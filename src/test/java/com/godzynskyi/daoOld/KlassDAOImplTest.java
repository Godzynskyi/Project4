package com.godzynskyi.daoOld;

import com.godzynskyi.factory.UtilFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class KlassDAOImplTest {

    @Test
    public void testAddAllFromEnum() {
        Exception exception = null;
        try {
            new KlassDAOImpl(UtilFactory.getDBConnection()).addAllFromEnum();
        } catch (Exception e) {
            exception = e;
        }
        assertEquals(null, exception);
    }

    @Test
    public void testGetKlassId() {
        int actual = new KlassDAOImpl(UtilFactory.getDBConnection()).getKlassId("EKONOM");
        assertEquals(1, actual);
    }
}