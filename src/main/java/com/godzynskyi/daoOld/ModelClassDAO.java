package com.godzynskyi.daoOld;

/**
 * Created by Java Developer on 30.11.2015.
 */
public interface ModelClassDAO {

    /**
     * Add Car Class Dependency.
     *
     * @param modelId Id of Car from DB.
     * @param classId Id of Class from DB.
     */
    void addClassToModel(int modelId, int classId);

    /**
     * Remove Car Class Dependency.
     *
     * @param modelId Id of Model from DB.
     * @param classId Id of Class from DB.
     */
    void removeClassCarDependency(int modelId, int classId);
}
