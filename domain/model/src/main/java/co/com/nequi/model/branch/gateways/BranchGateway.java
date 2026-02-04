package co.com.nequi.model.branch.gateways;

import co.com.nequi.model.branch.Branch;
import reactor.core.publisher.Mono;

public interface BranchGateway {

    Mono<Branch> save(Branch branch);
}
