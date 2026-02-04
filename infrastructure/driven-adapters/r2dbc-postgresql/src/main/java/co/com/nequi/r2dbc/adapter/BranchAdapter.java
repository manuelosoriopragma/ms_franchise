package co.com.nequi.r2dbc.adapter;

import co.com.nequi.model.branch.Branch;
import co.com.nequi.model.branch.gateways.BranchGateway;
import co.com.nequi.model.franchise.Franchise;
import co.com.nequi.model.franchise.gateways.FranchiseGateway;
import co.com.nequi.r2dbc.entity.BranchEntity;
import co.com.nequi.r2dbc.entity.FranchiseEntity;
import co.com.nequi.r2dbc.helper.ReactiveAdapterOperations;
import co.com.nequi.r2dbc.repository.BranchRepository;
import co.com.nequi.r2dbc.repository.FranchiseRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BranchAdapter extends ReactiveAdapterOperations<
        Branch,
        BranchEntity,
        Long,
        BranchRepository
> implements BranchGateway{
    public BranchAdapter(BranchRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Branch.class));
    }

}
