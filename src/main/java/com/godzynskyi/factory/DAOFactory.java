package com.godzynskyi.factory;

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
        return new CarDAO();
    }

    public static OrderDAO orderDAO() {
        return new OrderDAO();
    }

    public static DefectDAO defectDAO() {
        return new DefectDAO();
    }

}

