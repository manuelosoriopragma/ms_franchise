package co.com.nequi.r2dbc.repository;

import co.com.nequi.r2dbc.entity.FranchiseEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FranchiseRepository extends ReactiveCrudRepository<FranchiseEntity, Long>,
        ReactiveQueryByExampleExecutor<FranchiseEntity> {

}
