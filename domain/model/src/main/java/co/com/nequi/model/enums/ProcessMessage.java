package co.com.nequi.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProcessMessage {
    SUCCESS_OPERATION("B200", "Operation carried out successfully.", 200),
    INTERNAL_ERROR("T500","An error has occurred in the system, please contact the administrator", 500),
    INVALID_REQUEST("B400", "Bad Request, please verify data", 400),
    INVALID_FRANCHISE("B400-01", "Invalid franchise, the franchise does not exist", 400),
    INVALID_BRANCH("B400-02", "Invalid branch, the branch does not exist", 400),
    INVALID_PRODUCT("B400-03", "Invalid product, the product does not exist", 400);

    private final String code;
    private final String message;
    private final Integer status;
}
