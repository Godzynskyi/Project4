package com.godzynskyi.daoOld;

import com.godzynskyi.factory.UtilFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ModelClassDAOImplTest extends Assert{
    ModelClassDAO modelClassDAO = new ModelClassDAOImpl(UtilFactory.getDBConnection());
    Exception ex = null;


    @Before
    public void testAddClassToCar() throws Exception {
        try {
            modelClassDAO.addClassToModel(1, 2);
        } catch (Exception e) {
            ex = e;
        }
    }

    @Test
    public void testRemoveClassCarDependency() throws Exception {
        try {
            modelClassDAO.removeClassCarDependency(1, 2);
        } catch (Exception e) {
            ex=e;
        }

        assertEquals(null, ex);
    }
}