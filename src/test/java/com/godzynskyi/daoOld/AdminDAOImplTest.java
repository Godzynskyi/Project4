package com.godzynskyi.daoOld;

import com.godzynskyi.factory.DAOFactory;
import com.godzynskyi.factory.UtilFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminDAOImplTest {

    @Test
    public void testIsAdmin() throws Exception {
        AdminDAO adminDAO = DAOFactory.adminDAO(UtilFactory.getDBConnection());
        boolean actual = adminDAO.isAdmin("admin", "admin");
        assertEquals(true, actual);
    }

    @Test
    public void testIsAdmin1() throws Exception {
        AdminDAO adminDAO = DAOFactory.adminDAO(UtilFactory.getDBConnection());
        boolean actual = adminDAO.isAdmin("admin", "admin");
        assertEquals(false, actual);
    }

    @Test
    public void testIsAdmin2() throws Exception {
        AdminDAO adminDAO = DAOFactory.adminDAO(UtilFactory.getDBConnection());
        boolean actual = adminDAO.isAdmin("admin", "123456");
        assertEquals(false, actual);
    }
}