package co.com.nequi.api.helper;

import co.com.nequi.api.dto.ResponseDto;
import co.com.nequi.model.enums.ProcessMessage;

public class ResponseUtil {
    public static <T> ResponseDto<T> responseSuccessful(T data, ProcessMessage processMessage) {
        return ResponseDto.<T>builder()
                .code(processMessage.getCode())
                .message(processMessage.getMessage())
                .data(data)
                .build();
    }
}
