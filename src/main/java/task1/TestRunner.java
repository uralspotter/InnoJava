package task1;

import task1.annotations.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) {
        runTest(SimpleTest.class);
    }

    public static void runTest(Class c){
        Method[] classMethods = c.getMethods();
        List<Method> beforeSuits = Arrays.stream(classMethods)
            .filter(method -> method.isAnnotationPresent(BeforeSuite.class)).toList();
        List<Method> beforeTests = Arrays.stream(classMethods)
            .filter(method -> method.isAnnotationPresent(BeforeTest.class)).toList();
        List<Method> tests = Arrays.stream(classMethods)
            .filter(method -> method.isAnnotationPresent(Test.class)).toList();
        List<Method> afterTests = Arrays.stream(classMethods)
            .filter(method -> method.isAnnotationPresent(AfterTest.class)).toList();
        List<Method> afterSuits = Arrays.stream(classMethods)
            .filter(method -> method.isAnnotationPresent(AfterSuite.class)).toList();

        if(!tests.isEmpty()){
            try {
                if(beforeSuits.size() > 1 || afterSuits.size() > 1){
                    throw new IllegalArgumentException("Before or after suits method can not be more than one method");
                }
                beforeSuits.get(0).invoke(c);
                tests = tests.stream().sorted(Comparator
                    .comparingInt(method -> method.getAnnotation(Test.class).priority())).toList();
                for(Method test: tests){
                    if(!beforeTests.isEmpty()){
                        for(Method before: beforeTests){
                            before.invoke(c);
                        }
                    }
                    test.invoke(c);
                    if(!afterTests.isEmpty()){
                        for(Method after: afterTests){
                            after.invoke(c);
                        }
                    }
                }
                afterSuits.get(0).invoke(c);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
