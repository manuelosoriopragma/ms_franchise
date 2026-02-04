package co.com.nequi.model.exceptions;

import co.com.nequi.model.enums.ProcessMessage;

public class TechException extends GeneralException{

    public TechException(ProcessMessage processMessage) {
        super(processMessage.getMessage(), processMessage);
    }

    public TechException(Throwable cause, ProcessMessage processMessage){
        super(cause, processMessage);
    }
}
