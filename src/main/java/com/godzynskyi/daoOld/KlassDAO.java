package com.godzynskyi.daoOld;

/**
 * Created by Java Developer on 27.11.2015.
 */
public interface KlassDAO {

    /**
     * Initialize classes table. Add all not existing rows from Enumeration Klass
     */
    void addAllFromEnum();

    /**
     *
     * @param klassName value from DB in column name.
     * @return ID of klass from DB
     * or -1 if not exist
     *
     */
    int getKlassId(String klassName);
}
