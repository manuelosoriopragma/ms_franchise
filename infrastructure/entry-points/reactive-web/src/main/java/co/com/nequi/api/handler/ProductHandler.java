package co.com.nequi.api.handler;

import co.com.nequi.api.dto.ProductDto;
import co.com.nequi.api.helper.ResponseUtil;
import co.com.nequi.api.helper.ValidationUtil;

import co.com.nequi.api.mapper.ProductMapper;
import co.com.nequi.model.enums.ProcessMessage;
import co.com.nequi.usecase.product.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductHandler {
    private final ProductUseCase useCase;
    private final ValidationUtil validationUtil;

    @Transactional
    public Mono<ServerResponse> saveProduct(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ProductDto.class)
                .flatMap(validationUtil::validate)
                .map(ProductMapper::toDomain)
                .flatMap(useCase::saveProduct)
                .map(domain -> ResponseUtil.responseSuccessful(domain, ProcessMessage.SUCCESS_OPERATION))
                .flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response));
    }
}
