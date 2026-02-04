package co.com.nequi.usecase.product;

import co.com.nequi.model.branch.gateways.BranchGateway;
import co.com.nequi.model.enums.ProcessMessage;
import co.com.nequi.model.exceptions.BusinessException;
import co.com.nequi.model.product.Product;
import co.com.nequi.model.product.gateways.ProductGateway;
import reactor.core.publisher.Mono;

public record ProductUseCase(ProductGateway productGateway, BranchGateway branchGateway) {

    public Mono<Product> saveProduct(Product product){
        return branchGateway.findById(product.getBranchId())
                .switchIfEmpty(Mono.error(new BusinessException(ProcessMessage.INVALID_BRANCH)))
                .thenReturn(product)
                .flatMap(productGateway::save);
    }

    public Mono<Product> deleteProduct(Long productId){
        return productGateway.findById(productId)
                .switchIfEmpty(Mono.error(new BusinessException(ProcessMessage.INVALID_PRODUCT)))
                .flatMap(product -> productGateway.deleteById(product.getId())
                        .thenReturn(product)
                );

    }
}
