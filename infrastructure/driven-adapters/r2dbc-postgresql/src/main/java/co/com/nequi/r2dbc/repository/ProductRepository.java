package co.com.nequi.r2dbc.repository;

import co.com.nequi.r2dbc.entity.ProductEntity;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveCrudRepository<ProductEntity, Long>,
        ReactiveQueryByExampleExecutor<ProductEntity> {

    @Modifying
    @Query("UPDATE products SET stock = :stock WHERE id = :id")
    Mono<Integer> updateStock(@Param("id") Long id, @Param("stock") Long stock);

    @Modifying
    @Query("UPDATE products SET name = :name WHERE id = :id")
    Mono<Integer> updateName(@Param("id") Long id, @Param("name") String name);

    @Query("SELECT p.* " +
           "FROM franchises f " +
           "JOIN branches b ON b.franchise_id = f.id " +
           "JOIN products p ON p.branch_id = b.id " +
           "WHERE f.id = :franchiseId " +
           "AND p.stock = (SELECT MAX(p2.stock) FROM products p2 WHERE p2.branch_id = b.id)")
    Flux<ProductEntity> findProductsWithMaxStockByFranchise(@Param("franchiseId") Long franchiseId);

}
