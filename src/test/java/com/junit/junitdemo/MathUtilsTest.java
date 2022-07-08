package com.junit.junitdemo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestInstance(TestInstance.Lifecycle.PER_METHOD) // this is the default behavior
@DisplayName("When running MathUtils")
class MathUtilsTest {

    MathUtils utils;
    TestInfo info;
    TestReporter reporter;

    //Initialize before anything in this class runs
    @BeforeAll //must be static
    static void beforeAllInit() {
        System.out.println("This ran before everything else.");
    }

    //Initialize before each method
    @BeforeEach
    void init(TestInfo info, TestReporter reporter) {
        utils = new MathUtils();
        this.info = info;
        this.reporter = reporter;
    }

    //Tear down after each method
    @AfterEach
    void cleanUp() {
        System.out.println("Cleaning up...");
    }

    //Tear down after all methods are done
    //@AfterAll

    @Nested
    @DisplayName("Add method")
    @Tag("Math")
    class AddTest {
        @Test //Tells JUnit that this method is a test
        @DisplayName("when adding two positive numbers")
        void addPositive() {

            int actual = utils.add(1 ,1);
            int expected = 2;

            //assertEquals(expected, actual);

            //Displays a message if the test fails
            assertEquals(expected, actual, () -> "should return the right sum");
        }

        @Test //Tells JUnit that this method is a test
        @DisplayName("When adding two negative numbers")
        void addNegative() {

            int actual = utils.add(-3 ,1);
            int expected = -2;

            //assertEquals(expected, actual);

            //Displays a message if the test fails
            assertEquals(expected, actual, () -> "should return the right sum");
        }
    }

    @Test //Tells JUnit that this method is a test
    @DisplayName("Testing add method")
    @Tag("Math")
    void add() {

        int actual = utils.add(1 ,1);
        int expected = 2;

        //assertEquals(expected, actual);

        //Displays a message if the test fails
        assertEquals(expected, actual, () -> "The add method should add two numbers");
    }

    @RepeatedTest(3) //repeats the test a specified number of times. The test passes only if all repetitions pass
    @DisplayName("Testing computeCircleArea method")
    @Tag("Circle")
    void computeCircleArea(RepetitionInfo info) {
        //gets the current repetition. This is useful if you need to do something on a specific repetition
        int currRep = info.getCurrentRepetition();
        assertEquals(Math.PI * Math.pow(10,2), utils.computeCircleArea(10), () -> "Should return the area of the circle");
    }


    //tests to see if the divide method throws an exception when division by 0 occurs
    @Test
    @DisplayName("Testing divide method")
    @Tag("Math")
    void divide() {
        assertThrows(ArithmeticException.class, () -> utils.divide(1, 0), () -> "Divide by zero should throw an exception");
    }

    @Test
    @DisplayName("multiply method")
    @Tag("Math")
    void multiply() {
        reporter.publishEntry("Running:" + info.getDisplayName() + " with tags " + info.getTags());
        //assertEquals(4, utils.multiply(2, 2), "The method should multiply two numbers");
        assertAll(
                () -> assertEquals(4, utils.multiply(2, 2), () -> "The method should multiply two numbers"),
                () -> assertEquals(0, utils.multiply(0, 100), () -> "The method should multiply two numbers"),
                () -> assertEquals(53, utils.multiply(1, 53), () -> "The method should multiply two numbers"),
                () -> assertEquals(21, utils.multiply(-7, -3), () -> "The method should multiply two numbers"),
                () -> assertEquals(-24, utils.multiply(-6, 4), () -> "The method should multiply two numbers")
        );
    }

    @Test
    @Disabled //disables the method. The test will be skipped
    @DisplayName("TDD method> Should not run")
    void testDisabled() {
        fail("This test should be disabled");
    }

}