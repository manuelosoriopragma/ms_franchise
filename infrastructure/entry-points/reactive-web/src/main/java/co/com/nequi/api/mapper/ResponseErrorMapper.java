package co.com.nequi.api.mapper;

import co.com.nequi.api.dto.ResponseErrorDto;
import co.com.nequi.model.enums.ProcessMessage;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ResponseErrorMapper {
    public static ResponseErrorDto toDto(ProcessMessage processMessage, List<String> errors){
        return ResponseErrorDto.builder()
                .code(processMessage.getCode())
                .message(processMessage.getMessage())
                .errors(errors)
                .build();
    }
}
