package co.com.nequi.api.handler;

import co.com.nequi.api.dto.BranchDto;
import co.com.nequi.api.helper.ResponseUtil;
import co.com.nequi.api.helper.ValidationUtil;
import co.com.nequi.api.mapper.BranchMapper;
import co.com.nequi.model.enums.ProcessMessage;
import co.com.nequi.usecase.branch.BranchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BranchHandler {
    private final BranchUseCase useCase;
    private final ValidationUtil validationUtil;

    @Transactional
    public Mono<ServerResponse> assingBranch(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BranchDto.class)
                .flatMap(validationUtil::validate)
                .map(BranchMapper::toDomain)
                .flatMap(useCase::assingBranch)
                .map(domain -> ResponseUtil.responseSuccessful(domain, ProcessMessage.SUCCESS_OPERATION))
                .flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response));
    }
}
