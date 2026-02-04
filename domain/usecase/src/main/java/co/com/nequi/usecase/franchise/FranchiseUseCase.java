package co.com.nequi.usecase.franchise;


import co.com.nequi.model.enums.ProcessMessage;
import co.com.nequi.model.exceptions.BusinessException;
import co.com.nequi.model.franchise.Franchise;
import co.com.nequi.model.franchise.gateways.FranchiseGateway;
import reactor.core.publisher.Mono;

public record FranchiseUseCase(FranchiseGateway gateway) {

    public Mono<Franchise> saveFranchise(Franchise franchise){
        return gateway.save(franchise);
    }

    public Mono<Franchise> updateName(Franchise franchise){
        return gateway.findById(franchise.getId())
                        .switchIfEmpty(Mono.error(new BusinessException(ProcessMessage.INVALID_FRANCHISE)))
                .flatMap(f -> gateway.updateName(franchise));
    }
}
