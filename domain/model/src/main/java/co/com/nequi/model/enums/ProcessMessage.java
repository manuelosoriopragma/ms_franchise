package co.com.nequi.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProcessMessage {
    SUCCESS_OPERATION("B200", "Operation carried out successfully."),
    INTERNAL_ERROR("T500","An error has occurred in the system, please contact the administrator"),
    INVALID_REQUEST("B400", "Bad Request, please verify data");


    private final String code;
    private final String message;
}
