package com.godzynskyi.daoOld;

import com.godzynskyi.entity.Client;
import com.godzynskyi.factory.UtilFactory;
import junit.framework.Assert;
import org.junit.Test;

public class ClientDAOImplTest extends Assert{

    @Test
    public void testAddClient() throws Exception {
        Client client = new Client();
        client.setEmail("ivan.godzynskyi@gmail.com");
        client.setFirstName("Ivan");
        client.setLastName("Godzynskyi");
        client.setPhone("067-240-37-08");
        int actual = new ClientDAOImpl(UtilFactory.getDBConnection()).addClient(client);

        Client clientFromDB = new ClientDAOImpl(UtilFactory.getDBConnection()).getClient(actual);

        assertEquals(true, clientFromDB.equals(client));
    }
}