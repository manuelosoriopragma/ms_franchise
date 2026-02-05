package co.com.nequi.model.exceptions;

import co.com.nequi.model.enums.ProcessMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionTest {

    @Test
    void testConstructorWithProcessMessage() {
        BusinessException exception = new BusinessException(ProcessMessage.INVALID_FRANCHISE);

        assertEquals("Invalid franchise, the franchise does not exist", exception.getMessage());
        assertEquals(ProcessMessage.INVALID_FRANCHISE, exception.getProcessMessage());
    }

    @Test
    void testExceptionIsRuntimeException() {
        BusinessException exception = new BusinessException(ProcessMessage.INVALID_BRANCH);

        assertInstanceOf(RuntimeException.class, exception);
        assertInstanceOf(GeneralException.class, exception);
    }

    @Test
    void testProcessMessagePreserved() {
        BusinessException exception = new BusinessException(ProcessMessage.INVALID_PRODUCT);

        assertEquals("B400-03", exception.getProcessMessage().getCode());
        assertEquals(400, exception.getProcessMessage().getStatus());
    }
}
