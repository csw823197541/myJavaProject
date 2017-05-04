package classloader;

import groovy.lang.GroovyClassLoader;

/**
 * Created by csw on 2016/11/29 19:07.
 * Explain:
 */
public class GroovyClass {

    private GroovyClass() {}

    private static class ClassFactory {
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

    }
}
