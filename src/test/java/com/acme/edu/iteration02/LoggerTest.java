package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
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



    //TODO: implement Logger solution to match specification as tests

    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        Logger.log("str 1");
        Logger.log(1);
        Logger.log(2);
        Logger.log("str 2");
        Logger.log(0);
        Logger.close();
        //endregion

        //region then
        assertSysoutContains(Logger.STRING_PREFIX + "str 1" + NEW_LINE);
        assertSysoutContains(Logger.PRIMITIVE_PREFIX + "3" + NEW_LINE);
        assertSysoutContains(Logger.STRING_PREFIX + "str 2" + NEW_LINE);
        assertSysoutContains(Logger.PRIMITIVE_PREFIX + "0" + NEW_LINE);
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        Logger.log("str 1");
        Logger.log(10);
        Logger.log(Integer.MAX_VALUE);
        Logger.log("str 2");
        Logger.log(0);
        Logger.close();
        //endregion

        //region then
        assertSysoutContains("str 1" + NEW_LINE);
        assertSysoutContains(Integer.MAX_VALUE + NEW_LINE);
        assertSysoutContains("10" + NEW_LINE);
        assertSysoutContains("str 2" + NEW_LINE);
        assertSysoutContains("0" + NEW_LINE);
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        Logger.log("str 1");
        Logger.log((byte)10);
        Logger.log((byte)Byte.MAX_VALUE);
        Logger.log("str 2");
        Logger.log(0);
        Logger.close();
        //endregion

        //region then
        assertSysoutContains("str 1" + NEW_LINE);
        assertSysoutContains(Byte.MAX_VALUE + NEW_LINE);
        assertSysoutContains("10" + NEW_LINE);
        assertSysoutContains("str 2" + NEW_LINE);
        assertSysoutContains("0" + NEW_LINE);
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        Logger.log("str 1");
        Logger.log("str 2");
        Logger.log("str 2");
        Logger.log(0);
        Logger.log("str 2");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.close();
        //endregion

        //region then
        assertSysoutContains("str 1" + NEW_LINE);
        assertSysoutContains("str 2 (x2)" + NEW_LINE);
        assertSysoutContains("0" + NEW_LINE);
        assertSysoutContains("str 2" + NEW_LINE);
        assertSysoutContains("str 3 (x3)" + NEW_LINE);
        //endregion
    }
}