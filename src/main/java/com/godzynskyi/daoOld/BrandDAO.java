package com.godzynskyi.daoOld;

/**
 * Created by Java Developer on 24.11.2015.
 */
public interface BrandDAO {

    /**
     * Returns ID of brand by name if exist or -1 otherwise.
     *
     * @param brand name of Brand
     * @return id of brand if exits.
     *  Else return -1
     */
    int getBrandId(String brand);

    /**
     * Returns name of Brand by ID
     *
     * @param id ID of brand
     * @return name of brand.
     */
    String getBrand(int id);

    /**
     * Tries to add Brand to DB
     *
     * @param brand name of Brand (Nissan or BMW etc.)
     * @return true if new Brand has been added.
     *
     * @throws MySQLIntegrityConstraintViolationException if entry is dubicated
     */
    boolean addBrand(String brand);
}
