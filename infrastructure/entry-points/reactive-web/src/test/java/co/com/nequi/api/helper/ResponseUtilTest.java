package co.com.nequi.api.helper;

import co.com.nequi.api.dto.ResponseDto;
import co.com.nequi.model.enums.ProcessMessage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseUtilTest {

    @Test
    void testResponseSuccessfulWithStringData() {
        String data = "Test Data";
        ResponseDto<String> response = ResponseUtil.responseSuccessful(data, ProcessMessage.SUCCESS_OPERATION);

        assertEquals("B200", response.getCode());
        assertEquals("Operation carried out successfully.", response.getMessage());
        assertEquals("Test Data", response.getData());
    }

    @Test
    void testResponseSuccessfulWithObjectData() {
        TestObject data = new TestObject("value1", 123);
        ResponseDto<TestObject> response = ResponseUtil.responseSuccessful(data, ProcessMessage.SUCCESS_OPERATION);

        assertEquals("B200", response.getCode());
        assertEquals("Operation carried out successfully.", response.getMessage());
        assertEquals(data, response.getData());
    }

    @Test
    void testResponseSuccessfulWithNullData() {
        ResponseDto<Object> response = ResponseUtil.responseSuccessful(null, ProcessMessage.SUCCESS_OPERATION);

        assertEquals("B200", response.getCode());
        assertEquals("Operation carried out successfully.", response.getMessage());
        assertNull(response.getData());
    }

    @Test
    void testResponseSuccessfulWithDifferentProcessMessage() {
        String data = "Error Data";
        ResponseDto<String> response = ResponseUtil.responseSuccessful(data, ProcessMessage.INTERNAL_ERROR);

        assertEquals("T500", response.getCode());
        assertEquals("An error has occurred in the system, please contact the administrator", response.getMessage());
        assertEquals("Error Data", response.getData());
    }

    static class TestObject {
        private final String field1;
        private final Integer field2;

        TestObject(String field1, Integer field2) {
            this.field1 = field1;
            this.field2 = field2;
        }
    }
}
