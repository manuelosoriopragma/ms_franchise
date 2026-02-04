package co.com.nequi.r2dbc.adapter;

import co.com.nequi.model.franchise.Franchise;
import co.com.nequi.model.franchise.gateways.FranchiseGateway;
import co.com.nequi.r2dbc.repository.FranchiseRepository;
import co.com.nequi.r2dbc.entity.FranchiseEntity;
import co.com.nequi.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FranchiseAdapter extends ReactiveAdapterOperations<
        Franchise,
        FranchiseEntity,
        Long,
        FranchiseRepository
> implements FranchiseGateway {
    public FranchiseAdapter(FranchiseRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Franchise.class));
    }

}
