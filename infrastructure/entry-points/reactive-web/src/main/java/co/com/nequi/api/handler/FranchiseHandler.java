package co.com.nequi.api.handler;

import co.com.nequi.api.dto.FranchiseDto;
import co.com.nequi.api.dto.UpdateNameFranchiseDto;
import co.com.nequi.api.helper.ResponseUtil;
import co.com.nequi.api.helper.ValidationUtil;
import co.com.nequi.api.mapper.FranchiseMapper;
import co.com.nequi.model.enums.ProcessMessage;
import co.com.nequi.usecase.franchise.FranchiseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FranchiseHandler {

    private final FranchiseUseCase useCase;
    private final ValidationUtil validationUtil;

    @Transactional
    public Mono<ServerResponse> createFranchise(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(FranchiseDto.class)
                .flatMap(validationUtil::validate)
                .map(FranchiseMapper::toDomain)
                .flatMap(useCase::saveFranchise)
                .map(domain -> ResponseUtil.responseSuccessful(domain, ProcessMessage.SUCCESS_OPERATION))
                .flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response));
    }

    @Transactional
    public Mono<ServerResponse> updateName(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UpdateNameFranchiseDto.class)
                .flatMap(validationUtil::validate)
                .map(FranchiseMapper::toDomain)
                .flatMap(useCase::updateName)
                .map(domain -> ResponseUtil.responseSuccessful(domain, ProcessMessage.SUCCESS_OPERATION))
                .flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response));
    }

}
