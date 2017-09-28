package com.acme.edu.iteration02;

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
    public void shouldLogSequentIntegersAsSum() throws IOException {
        //region when
        TypedSummingLogger.log("str 1");
        TypedSummingLogger.log(1);
        TypedSummingLogger.log(2);
        TypedSummingLogger.log("str 2");
        TypedSummingLogger.log(0);
        TypedSummingLogger.close();
        //endregion

        //region then
        assertSysoutContains(TypedSummingLogger.STRING_PREFIX + "str 1" + NEW_LINE);
        assertSysoutContains(TypedSummingLogger.PRIMITIVE_PREFIX + "3" + NEW_LINE);
        assertSysoutContains(TypedSummingLogger.STRING_PREFIX + "str 2" + NEW_LINE);
        assertSysoutContains(TypedSummingLogger.PRIMITIVE_PREFIX + "0" + NEW_LINE);
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
        //region when
        TypedSummingLogger.log("str 1");
        TypedSummingLogger.log(10);
        TypedSummingLogger.log(Integer.MAX_VALUE);
        TypedSummingLogger.log("str 2");
        TypedSummingLogger.log(0);
        TypedSummingLogger.close();
        //endregion

        //region then
        assertSysoutContains("str 1" + NEW_LINE);
        assertSysoutContains(Integer.MAX_VALUE + " x 1" + NEW_LINE);
        assertSysoutContains("10" + NEW_LINE);
        assertSysoutContains("str 2" + NEW_LINE);
        assertSysoutContains("0" + NEW_LINE);
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
        //region when
        TypedSummingLogger.log("str 1");
        TypedSummingLogger.log((byte)10);
        TypedSummingLogger.log((byte)Byte.MAX_VALUE);
        TypedSummingLogger.log("str 2");
        TypedSummingLogger.log(0);
        TypedSummingLogger.close();
        //endregion

        //region then
        assertSysoutContains("str 1" + NEW_LINE);
        assertSysoutContains(Byte.MAX_VALUE + " x 1" + NEW_LINE);
        assertSysoutContains("10" + NEW_LINE);
        assertSysoutContains("str 2" + NEW_LINE);
        assertSysoutContains("0" + NEW_LINE);
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
        //region when
        TypedSummingLogger.log("str 1");
        TypedSummingLogger.log("str 2");
        TypedSummingLogger.log("str 2");
        TypedSummingLogger.log(0);
        TypedSummingLogger.log("str 2");
        TypedSummingLogger.log("str 3");
        TypedSummingLogger.log("str 3");
        TypedSummingLogger.log("str 3");
        TypedSummingLogger.close();
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