package com.godzynskyi.daoOld.car.filters;

import com.godzynskyi.entity.Klass;


/**
 * Created by Java Developer on 22.11.2015.
 */
public class KlassFilter implements CarFilter {
    String s;

    public KlassFilter(Klass klass1, Klass... klasses) {
        StringBuilder s = new StringBuilder();
        s.append(" (classes.name = '")
                .append(klass1.toString())
                .append("'");
        for (Klass klass : klasses) {
            s.append(" OR classes.name = '")
                    .append(klass.toString())
                    .append("'");
        }
        s.append(")");
        this.s = s.toString();
    }

    @Override
    public String stringPattern() {
        return s;
    }
}
