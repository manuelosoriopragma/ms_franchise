package co.com.nequi.api.handler;

import co.com.nequi.api.mapper.ResponseErrorMapper;
import co.com.nequi.model.enums.ProcessMessage;
import co.com.nequi.model.exceptions.BusinessException;
import co.com.nequi.model.exceptions.TechException;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.webflux.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolationException;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
@Order(-2)
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        return Mono.just(exchange.getResponse())
                .doOnNext(response -> response.getHeaders().setContentType(MediaType.APPLICATION_JSON))
                .flatMap(response -> switch(ex){
                    case ConstraintViolationException validationEx -> toListErrors(validationEx.getConstraintViolations())
                            .flatMap(errors -> writeResponse(response, ProcessMessage.INVALID_REQUEST, errors));
                    case BusinessException businessException -> writeResponse(response,
                            businessException.getProcessMessage(), null);
                    case TechException techException -> {
                        log.error("A technical error has occurred, code: {}, message: {}, exception {}",
                                techException.getProcessMessage().getMessage(),
                                techException.getProcessMessage().getMessage(),
                                techException.getCause().getMessage());
                        yield writeResponse(response, ProcessMessage.INTERNAL_ERROR, null);
                    }
                    default -> writeResponse(response, ProcessMessage.INTERNAL_ERROR, null);
                });
    }

    private Mono<Void> writeResponse(ServerHttpResponse response, ProcessMessage processMessage, List<String> errors){
        return Mono.just(ResponseErrorMapper.toDto(processMessage, errors))
                .flatMap(responseErrorDto -> Mono.fromCallable(() -> {
                            response.setRawStatusCode(processMessage.getStatus());
                            return objectMapper.writeValueAsBytes(responseErrorDto);
                        })
                        .map(response.bufferFactory()::wrap)
                        .flatMap(buffer -> response.writeWith(Mono.just(buffer)))
                        .onErrorResume(IOException.class, e -> {
                            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                            return response.setComplete();
                        }));
    }

    private Mono<List<String>> toListErrors(Set<ConstraintViolation<?>> violations) {
        return Flux.fromIterable(violations)
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collectList();
    }
}
