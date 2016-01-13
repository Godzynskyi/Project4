package com.godzynskyi.dao;

import com.godzynskyi.dao.cache.CarDAOWithCache;
import com.godzynskyi.dao.*;

/**
 * Created by Java Developer on 26.11.2015.
 */
public class DAOFactory {

    public static AdminDAO adminDAO() {
        return new AdminDAO();
    }

    public static ClientDAO clientDAO() {
        return new ClientDAO();
    }

    public static CarDAO carDAO() {
        return new CarDAOWithCache();
    }

    public static OrderDAO orderDAO() {
        return new OrderDAO();
    }

    public static DefectDAO defectDAO() {
        return new DefectDAO();
    }

}

