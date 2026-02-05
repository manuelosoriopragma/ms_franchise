package co.com.nequi.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessMessageTest {

    @Test
    void testSuccessOperationValues() {
        assertEquals("B200", ProcessMessage.SUCCESS_OPERATION.getCode());
        assertEquals("Operation carried out successfully.", ProcessMessage.SUCCESS_OPERATION.getMessage());
        assertEquals(200, ProcessMessage.SUCCESS_OPERATION.getStatus());
    }

    @Test
    void testInternalErrorValues() {
        assertEquals("T500", ProcessMessage.INTERNAL_ERROR.getCode());
        assertEquals("An error has occurred in the system, please contact the administrator", ProcessMessage.INTERNAL_ERROR.getMessage());
        assertEquals(500, ProcessMessage.INTERNAL_ERROR.getStatus());
    }

    @Test
    void testInvalidRequestValues() {
        assertEquals("B400", ProcessMessage.INVALID_REQUEST.getCode());
        assertEquals("Bad Request, please verify data", ProcessMessage.INVALID_REQUEST.getMessage());
        assertEquals(400, ProcessMessage.INVALID_REQUEST.getStatus());
    }

    @Test
    void testInvalidFranchiseValues() {
        assertEquals("B400-01", ProcessMessage.INVALID_FRANCHISE.getCode());
        assertEquals("Invalid franchise, the franchise does not exist", ProcessMessage.INVALID_FRANCHISE.getMessage());
        assertEquals(400, ProcessMessage.INVALID_FRANCHISE.getStatus());
    }

    @Test
    void testInvalidBranchValues() {
        assertEquals("B400-02", ProcessMessage.INVALID_BRANCH.getCode());
        assertEquals("Invalid branch, the branch does not exist", ProcessMessage.INVALID_BRANCH.getMessage());
        assertEquals(400, ProcessMessage.INVALID_BRANCH.getStatus());
    }

    @Test
    void testInvalidProductValues() {
        assertEquals("B400-03", ProcessMessage.INVALID_PRODUCT.getCode());
        assertEquals("Invalid product, the product does not exist", ProcessMessage.INVALID_PRODUCT.getMessage());
        assertEquals(400, ProcessMessage.INVALID_PRODUCT.getStatus());
    }

    @Test
    void testEnumValuesCount() {
        assertEquals(6, ProcessMessage.values().length);
    }

    @Test
    void testValueOf() {
        assertEquals(ProcessMessage.SUCCESS_OPERATION, ProcessMessage.valueOf("SUCCESS_OPERATION"));
        assertEquals(ProcessMessage.INTERNAL_ERROR, ProcessMessage.valueOf("INTERNAL_ERROR"));
    }
}
