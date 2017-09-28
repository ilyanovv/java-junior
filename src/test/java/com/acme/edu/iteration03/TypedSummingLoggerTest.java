package com.acme.edu.iteration03;

import com.acme.edu.TypedSummingLogger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TypedSummingLoggerTest implements SysoutCaptureAndAssertionAbility {
    private final String NEW_LINE = System.lineSeparator();
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

   @Test
    public void shouldLogIntegersArray() throws IOException {
        //region when
        TypedSummingLogger.log(new int[] {-1, 0, 1});
        TypedSummingLogger.close();
        //endregion

        //region then
        assertSysoutEquals(
            "primitives array: {-1, 0, 1}" + NEW_LINE
        );
        //endregion
    }

    //TODO: implement TypedSummingLogger solution to match specification as tests
    /*
    @Test
    public void shouldLogIntegersMatrix() throws IOException {
        //region when
        TypedSummingLogger.log(new int[][] {{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        //endregion

        //region then
        assertSysoutEquals(
            "primitives matrix: {" + NEW_LINE  +
                "{-1, 0, 1}" + NEW_LINE +
                "{1, 2, 3}" + NEW_LINE +
                "{-1, -2, -3}" + NEW_LINE +
            "}" + NEW_LINE
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMulitidimentionalArray() throws IOException {
        //region when
        TypedSummingLogger.log(new int[][][][] {{{{0}}}});
        //endregion

        //region then
        assertSysoutEquals(
            "primitives multimatrix: {" + NEW_LINE +
                "{" + NEW_LINE +  "{" + NEW_LINE + "{" + NEW_LINE +
                    "0" + NEW_LINE +
                "}" + NEW_LINE + "}" + NEW_LINE + "}" + NEW_LINE +
            "}" + NEW_LINE
        );
        //endregion
    }

    @Test
    public void shouldLogStringsWithOneMethodCall() throws IOException {
        //region when
        TypedSummingLogger.log("str1", "string 2", "str 3");
        //endregion

        //region then
        assertSysoutContains("str1\nstring 2\nstr 3");
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws IOException {
        //region when
        TypedSummingLogger.log(-1, 0, 1, 3);
        //endregion

        //region then
        assertSysoutContains("3");
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException {
        //region when
        TypedSummingLogger.log(1);
        TypedSummingLogger.log("str");
        TypedSummingLogger.log(Integer.MAX_VALUE - 10);
        TypedSummingLogger.log(11);
        //endregion

        //region then
        assertSysoutContains(1);
        assertSysoutContains("str");
        assertSysoutContains(Integer.MAX_VALUE - 10);
        assertSysoutContains(11);
        //endregion
    }*/

}