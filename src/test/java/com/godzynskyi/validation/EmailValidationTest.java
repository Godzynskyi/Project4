package com.godzynskyi.validation;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidationTest {

    @Test
     public void testIsEmail() throws Exception {
        String email = "ivan.godzynskyi@gmail.com";
        boolean actual = EmailValidation.isEmail(email);
        assertEquals(true, actual);
    }

    @Test
    public void testIsEmail1() throws Exception {
        String email = "agne4ka@i.ua";
        boolean actual = EmailValidation.isEmail(email);
        assertEquals(true, actual);
    }
}