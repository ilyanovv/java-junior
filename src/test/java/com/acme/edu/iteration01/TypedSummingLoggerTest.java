package com.acme.edu.iteration01;

import com.acme.edu.TypedSummingLogger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

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
    public void shouldLogInteger() throws IOException {
        //region when
        TypedSummingLogger.log(1);
        TypedSummingLogger.log(0);
        TypedSummingLogger.log(-1);
        TypedSummingLogger.close();
        //endregion

        //region then
        assertSysoutEquals("primitive: 0" + NEW_LINE);
        //endregion
    }

    @Test
    public void shouldLogByte() throws IOException {
        //region when
        TypedSummingLogger.log((byte)1);
        TypedSummingLogger.log((byte)0);
        TypedSummingLogger.log((byte)-1);
        TypedSummingLogger.close();
        //endregion

        //region then
        assertSysoutEquals("primitive: 0" + NEW_LINE);
        //endregion
    }

    @Test
    public void shouldLogChar() throws IOException {
        //region when
        TypedSummingLogger.log('a');
        TypedSummingLogger.log('b');
        TypedSummingLogger.close();
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

    @Test
    public void shouldLogString() throws IOException {
        //region when
        TypedSummingLogger.log("test string 1");
        TypedSummingLogger.log("other str");
        TypedSummingLogger.close();
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws IOException {
        //region when
        TypedSummingLogger.log(true);
        TypedSummingLogger.log(false);
        TypedSummingLogger.close();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }

    @Test
    public void shouldLogReference() throws IOException {
        //region when
        TypedSummingLogger.log(new Object());
        TypedSummingLogger.close();
        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }
}