package co.com.nequi.r2dbc.adapter;

import co.com.nequi.model.product.Product;
import co.com.nequi.model.product.gateways.ProductGateway;
import co.com.nequi.r2dbc.entity.ProductEntity;
import co.com.nequi.r2dbc.helper.ReactiveAdapterOperations;
import co.com.nequi.r2dbc.repository.ProductRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductAdapter extends ReactiveAdapterOperations<
        Product,
        ProductEntity,
        Long,
        ProductRepository
> implements ProductGateway {
    public ProductAdapter(ProductRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Product.class));
    }

    @Override
    public Mono<Void> deleteById(Long productId) {
        return repository.deleteById(productId);
    }

    @Override
    public Mono<Product> updateStock(Product product) {
        return repository.updateStock(product.getId(), product.getStock())
                .flatMap(rowsUpdated -> repository.findById(product.getId()))
                .map(this::toEntity);
    }

    @Override
    public Mono<Product> updateName(Product product) {
        return repository.updateName(product.getId(), product.getName())
                .flatMap(rowsUpdated -> repository.findById(product.getId()))
                .map(this::toEntity);
    }

    @Override
    public Flux<Product> findProductsWithMaxStockByFranchise(Long franchiseId) {
        return repository.findProductsWithMaxStockByFranchise(franchiseId)
                .map(this::toEntity);
    }
}
