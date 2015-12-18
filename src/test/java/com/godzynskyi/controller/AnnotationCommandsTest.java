package com.godzynskyi.controller;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class AnnotationCommandsTest {

    @Test
    public void testPutCommandsToMap() throws Exception {
        AnnotationCommands annotationCommands = new AnnotationCommands();
        Map<String, Command> stringCommandMap = annotationCommands.putCommandsToMap("com.godzynskyi.command","/page");
        assertNotEquals(0, stringCommandMap.size());
    }
}