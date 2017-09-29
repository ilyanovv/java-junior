package com.acme.edu.iteration02;

import com.acme.edu.TypedSummingLogger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TypedSummingLoggerTest implements SysoutCaptureAndAssertionAbility {
    private final String NEW_LINE = System.lineSeparator();
    private final TypedSummingLogger logger = new TypedSummingLogger();
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
        logger.log("str 1");
        logger.log(1);
        logger.log(2);
        logger.log("str 2");
        logger.log(0);
        logger.close();
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
        logger.log("str 1");
        logger.log(10);
        logger.log(Integer.MAX_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.close();
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
        logger.log("str 1");
        logger.log((byte)10);
        logger.log((byte)Byte.MAX_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.close();
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
        logger.log("str 1");
        logger.log("str 2");
        logger.log("str 2");
        logger.log(0);
        logger.log("str 2");
        logger.log("str 3");
        logger.log("str 3");
        logger.log("str 3");
        logger.close();
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