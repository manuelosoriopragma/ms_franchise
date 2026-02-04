package co.com.nequi.r2dbc.repository;

import co.com.nequi.r2dbc.entity.BranchEntity;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BranchRepository extends ReactiveCrudRepository<BranchEntity, Long>,
        ReactiveQueryByExampleExecutor<BranchEntity> {

    @Modifying
    @Query("UPDATE branches SET name = :name WHERE id = :id")
    Mono<Integer> updateName(@Param("id") Long id, @Param("name") String name);

}
