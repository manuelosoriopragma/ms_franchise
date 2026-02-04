package co.com.nequi.model.exceptions;
import co.com.nequi.model.enums.ProcessMessage;
import lombok.Getter;


@Getter
public abstract class GeneralException extends RuntimeException {

    private final ProcessMessage processMessage;

    protected GeneralException(Throwable cause, ProcessMessage processMessage){
        super(cause);
        this.processMessage = processMessage;
    }
    protected GeneralException(String message, ProcessMessage processMessage){
        super(message);
        this.processMessage = processMessage;
    }
}
