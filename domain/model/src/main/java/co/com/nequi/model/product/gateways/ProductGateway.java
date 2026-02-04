package co.com.nequi.model.product.gateways;

import co.com.nequi.model.product.Product;
import reactor.core.publisher.Mono;

public interface ProductGateway {
    Mono<Product> save(Product product);
    Mono<Product> findById(Long productId);
    Mono<Void> deleteById(Long productId);
}
