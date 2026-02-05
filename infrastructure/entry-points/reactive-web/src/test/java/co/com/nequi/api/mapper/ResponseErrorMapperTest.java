package co.com.nequi.api.mapper;

import co.com.nequi.api.dto.ResponseErrorDto;
import co.com.nequi.model.enums.ProcessMessage;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResponseErrorMapperTest {

    @Test
    void testToDtoWithErrors() {
        List<String> errors = Arrays.asList("Error 1", "Error 2");

        ResponseErrorDto dto = ResponseErrorMapper.toDto(ProcessMessage.INVALID_REQUEST, errors);

        assertEquals("B400", dto.getCode());
        assertEquals("Bad Request, please verify data", dto.getMessage());
        assertEquals(2, dto.getErrors().size());
        assertEquals("Error 1", dto.getErrors().get(0));
        assertEquals("Error 2", dto.getErrors().get(1));
    }

    @Test
    void testToDtoWithEmptyErrors() {
        List<String> errors = List.of();

        ResponseErrorDto dto = ResponseErrorMapper.toDto(ProcessMessage.INTERNAL_ERROR, errors);

        assertEquals("T500", dto.getCode());
        assertEquals("An error has occurred in the system, please contact the administrator", dto.getMessage());
        assertTrue(dto.getErrors().isEmpty());
    }

    @Test
    void testToDtoWithSingleError() {
        List<String> errors = List.of("Single error message");

        ResponseErrorDto dto = ResponseErrorMapper.toDto(ProcessMessage.INVALID_FRANCHISE, errors);

        assertEquals("B400-01", dto.getCode());
        assertEquals("Invalid franchise, the franchise does not exist", dto.getMessage());
        assertEquals(1, dto.getErrors().size());
        assertEquals("Single error message", dto.getErrors().get(0));
    }
}
