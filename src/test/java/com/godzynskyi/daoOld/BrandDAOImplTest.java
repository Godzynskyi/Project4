package com.godzynskyi.daoOld;

import com.godzynskyi.factory.UtilFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class BrandDAOImplTest {

    @Test
    public void testGetBrand() {
        BrandDAO brandDAO = new BrandDAOImpl(UtilFactory.getDBConnection());
        int s = brandDAO.getBrandId("Toyota");
        String actual = brandDAO.getBrand(s);
        assertEquals("Toyota", actual);
    }

}