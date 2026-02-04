package co.com.nequi.usecase.branch;

import co.com.nequi.model.branch.Branch;
import co.com.nequi.model.branch.gateways.BranchGateway;
import co.com.nequi.model.enums.ProcessMessage;
import co.com.nequi.model.exceptions.BusinessException;
import co.com.nequi.model.franchise.gateways.FranchiseGateway;
import reactor.core.publisher.Mono;

public record BranchUseCase(BranchGateway branchGateway, FranchiseGateway franchiseGateway) {

    public Mono<Branch> assingBranch(Branch branch){
        return franchiseGateway.findById(branch.getFranchiseId())
                .switchIfEmpty(Mono.error(new BusinessException(ProcessMessage.INVALID_FRANCHISE)))
                .thenReturn(branch)
                .flatMap(branchGateway::save);
    }

    public Mono<Branch> updateName(Branch branch){
        return branchGateway.findById(branch.getId())
                .switchIfEmpty(Mono.error(new BusinessException(ProcessMessage.INVALID_BRANCH)))
                .flatMap(b -> branchGateway.updateName(branch));
    }
}
