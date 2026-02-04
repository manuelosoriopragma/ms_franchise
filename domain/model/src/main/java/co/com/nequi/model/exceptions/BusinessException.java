package co.com.nequi.model.exceptions;

import co.com.nequi.model.enums.ProcessMessage;
import lombok.Getter;

@Getter
public class BusinessException extends GeneralException{
    public BusinessException(ProcessMessage processMessage) {
        super(processMessage.getMessage(), processMessage);
    }
}
