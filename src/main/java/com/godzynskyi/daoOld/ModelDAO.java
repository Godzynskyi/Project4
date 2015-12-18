package com.godzynskyi.daoOld;

import com.godzynskyi.entity.Model;

/**
 * Created by Java Developer on 24.11.2015.
 */
public interface ModelDAO {
    /**
     * Get model by ID
     *
     */
    Model getModel(int id);

    /**
     * Returns ID of Model.
     *
     * @param model Model searched
     * @return ID of model if exist or
     * -1 if not exist
     */
    int getModelId(Model model);

    /**
     * Tries to add Model to DB
     *
     * @return true if new Model has been added
     *
     */
    boolean addModel(Model model);
}
