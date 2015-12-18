package com.godzynskyi.daoOld;

import com.godzynskyi.entity.Admin;

/**
 * Created by Java Developer on 28.11.2015.
 */
public interface AdminDAO {

    /**
     * Trying to add Admin to DB. If successfully return true, false otherwise.
     *
     * @param admin new Admin
     * @return true if successfully added or false otherwise.
     */
    boolean addAdmin(Admin admin);

    /**
     * Returns true if authenticated of false otherwise.
     *
     * @param login login of Admin.
     * @param password password of Admin.
     * @return true if authenticated of false otherwise.
     */
    boolean isAdmin(String login, String password);

    /**
     * Returns Admin from DB with current login.
     *
     * @param login login of Admin
     *
     * @return Admin if exists,
     * Admin with login null if not exist,
     * null if there was problem with DB.
     */
    Admin getAdmin(String login);
}
