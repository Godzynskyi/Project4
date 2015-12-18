package com.godzynskyi.controller;


import com.godzynskyi.annotation.RequestMapper;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Java Developer on 04.12.2015.
 */
public class AnnotationCommands {


    Map<String, Command> putCommandsToMap(String pathPackage, String prefix) {
        Map<String, Command> result = new HashMap<>();
        Reflections reflections = new Reflections(pathPackage, new SubTypesScanner(false));

        Set<Class<? extends Object>> allClasses =
                reflections.getSubTypesOf(Object.class);

        for(Class c: allClasses) {
            if (c.isAnnotationPresent(RequestMapper.class)) {
                RequestMapper annotation = (RequestMapper) c.getAnnotation(RequestMapper.class);
                try {
                    String s = prefix + annotation.value();
                    Constructor con = c.getConstructor();
                    Command command = (Command) con.newInstance();
                    result.put(s, command);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
