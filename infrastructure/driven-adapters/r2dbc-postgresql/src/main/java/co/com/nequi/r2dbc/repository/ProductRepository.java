package co.com.nequi.r2dbc.repository;

import co.com.nequi.r2dbc.entity.BranchEntity;
import co.com.nequi.r2dbc.entity.ProductEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, Long>,
        ReactiveQueryByExampleExecutor<ProductEntity> {

}
