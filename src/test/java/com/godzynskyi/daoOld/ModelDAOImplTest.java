package com.godzynskyi.daoOld;

import com.godzynskyi.entity.Model;
import com.godzynskyi.factory.UtilFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelDAOImplTest {

    @Test
    public void testGetModel() throws Exception {

        int find = 1;
        ModelDAO modelDAO = new ModelDAOImpl(UtilFactory.getDBConnection());
        Model model = modelDAO.getModel(find);
        Model model1 = new Model(model.getBrand(), model.getModel());

        int actual = modelDAO.getModelId(model1);

        assertEquals(find, actual);
    }

}