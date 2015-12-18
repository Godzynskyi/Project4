package com.godzynskyi.daoOld.car.filters;

/**
 * Created by Java Developer on 22.11.2015.
 */
public class BrandFilter implements CarFilter {
    String s;

    public BrandFilter(String brand1, String... brands) {
        StringBuilder s = new StringBuilder();
        s.append(" (brand.name = '")
                .append(brand1)
                .append("'");
        for (String brend: brands) {
            s.append(" or brend.name = '")
                    .append(brend)
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
