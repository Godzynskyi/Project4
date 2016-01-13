package com.godzynskyi.model;

import java.util.Date;

/**
 * Created by Java Developer on 24.11.2015.
 */
public class RepairedDefect {

    Defect defect;
    RepairExecutor executor;
    float priceFromService;
    Date repairDate;
    boolean repaired;

    static class RepairExecutor {
        String name;
        String phone;
    }
}
