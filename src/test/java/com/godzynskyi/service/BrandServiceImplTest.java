package com.godzynskyi.service;

import com.godzynskyi.factory.ServiceFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class BrandServiceImplTest {

    @Test
    public void testGetBrand() throws Exception {
        BrandService brandService = ServiceFactory.brandService();
        int actual = brandService.getBrandId("Toyota");
        String s = brandService.getBrand(actual);
        int expected = brandService.getBrandId(s);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBrand1() throws Exception {
        BrandService brandService = ServiceFactory.brandService();
        int actual = brandService.getBrandId("Chevrolet");
        int expected = brandService.getBrandId("Chevrolet");
        assertEquals(expected, actual);
    }
}