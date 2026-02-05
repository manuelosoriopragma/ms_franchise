package co.com.nequi.model.exceptions;

import co.com.nequi.model.enums.ProcessMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneralExceptionTest {

    static class TestGeneralException extends GeneralException {
        public TestGeneralException(Throwable cause, ProcessMessage processMessage) {
            super(cause, processMessage);
        }

        public TestGeneralException(String message, ProcessMessage processMessage) {
            super(message, processMessage);
        }
    }

    @Test
    void testConstructorWithThrowableAndProcessMessage() {
        Throwable cause = new RuntimeException("Root cause");
        TestGeneralException exception = new TestGeneralException(cause, ProcessMessage.INTERNAL_ERROR);

        assertEquals(cause, exception.getCause());
        assertEquals(ProcessMessage.INTERNAL_ERROR, exception.getProcessMessage());
    }

    @Test
    void testConstructorWithMessageAndProcessMessage() {
        TestGeneralException exception = new TestGeneralException("Custom error message", ProcessMessage.INVALID_REQUEST);

        assertEquals("Custom error message", exception.getMessage());
        assertEquals(ProcessMessage.INVALID_REQUEST, exception.getProcessMessage());
    }

    @Test
    void testExceptionIsRuntimeException() {
        TestGeneralException exception = new TestGeneralException("Error", ProcessMessage.INTERNAL_ERROR);

        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void testProcessMessageIsImmutable() {
        TestGeneralException exception = new TestGeneralException("Error", ProcessMessage.SUCCESS_OPERATION);

        assertNotNull(exception.getProcessMessage());
        assertEquals("B200", exception.getProcessMessage().getCode());
    }
}
