package co.com.nequi.model.exceptions;

import co.com.nequi.model.enums.ProcessMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TechExceptionTest {

    @Test
    void testConstructorWithProcessMessage() {
        TechException exception = new TechException(ProcessMessage.INTERNAL_ERROR);

        assertEquals("An error has occurred in the system, please contact the administrator", exception.getMessage());
        assertEquals(ProcessMessage.INTERNAL_ERROR, exception.getProcessMessage());
    }

    @Test
    void testConstructorWithThrowableAndProcessMessage() {
        Throwable cause = new RuntimeException("Database connection failed");
        TechException exception = new TechException(cause, ProcessMessage.INTERNAL_ERROR);

        assertEquals(cause, exception.getCause());
        assertEquals(ProcessMessage.INTERNAL_ERROR, exception.getProcessMessage());
    }

    @Test
    void testExceptionIsRuntimeException() {
        TechException exception = new TechException(ProcessMessage.INTERNAL_ERROR);

        assertInstanceOf(RuntimeException.class, exception);
        assertInstanceOf(GeneralException.class, exception);
    }

    @Test
    void testProcessMessagePreserved() {
        TechException exception = new TechException(ProcessMessage.INTERNAL_ERROR);

        assertEquals("T500", exception.getProcessMessage().getCode());
        assertEquals(500, exception.getProcessMessage().getStatus());
    }
}
