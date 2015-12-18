package com.godzynskyi.dao.order.filters;

/**
 * Created by Java Developer on 06.12.2015.
 */
public class NoAdminFilter implements OrderFilter {
    @Override
    public String stringPattern() {
        return "admin_id is null";
    }
}
