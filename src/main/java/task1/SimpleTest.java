package task1;

import task1.annotations.AfterSuite;
import task1.annotations.BeforeSuite;
import task1.annotations.BeforeTest;
import task1.annotations.Test;

public class SimpleTest {

    @BeforeSuite
    public static void beforeSuit(){
        System.out.println("Method before suit");
    }

    @BeforeTest
    public static void firstBeforeTest(){
        System.out.println("First method before test");
    }

    @BeforeTest
    public static void secondBeforeTest(){
        System.out.println("Second method before test");
    }

    @Test
    public static void test(){
        System.out.println("Some test");
    }

    @AfterSuite
    public static void afterSuit(){
        System.out.println("After suit");
    }
}
