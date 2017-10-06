package com.acme.edu.iteration04;

import com.acme.edu.LoggerController;
import com.acme.edu.LoggerException;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.TypedSummingLogger;
import com.acme.edu.encoder.SimpleEncoder;
import com.acme.edu.message.IntegerMessage;
import com.acme.edu.saver.ExceptionSaver;
import com.acme.edu.saver.SaveNotSuccessfulException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.rmi.runtime.Log;

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

    @Test(expected = LoggerException.class)
    public void shouldCatchLoggerExceptionCausedByIllegalArgument() throws LoggerException {
        //region when
        String nullString = null;
        TypedSummingLogger.log(nullString);
        //endregion
    }

    @Test(expected = LoggerException.class)
    public void shouldCatchLoggerExceptionCausedBySaveNotSuccessfulException() throws LoggerException {
        //region when
        LoggerController loggerController = new LoggerController();
        loggerController.log(new IntegerMessage(0, new ExceptionSaver(), new SimpleEncoder()));
        loggerController.close();
        //end region
    }
}
