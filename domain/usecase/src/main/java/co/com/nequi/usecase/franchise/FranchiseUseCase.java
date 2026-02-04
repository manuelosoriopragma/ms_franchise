package co.com.nequi.usecase.franchise;


import co.com.nequi.model.franchise.Franchise;
import co.com.nequi.model.franchise.gateways.FranchiseGateway;
import reactor.core.publisher.Mono;

public record FranchiseUseCase(FranchiseGateway gateway) {

    public Mono<Franchise> saveFranchise(Franchise franchise){
        return gateway.saveFranchise(franchise);
    }
}
