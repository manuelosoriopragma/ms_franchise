package co.com.nequi.usecase.product;

import co.com.nequi.model.branch.gateways.BranchGateway;
import co.com.nequi.model.enums.ProcessMessage;
import co.com.nequi.model.exceptions.BusinessException;
import co.com.nequi.model.franchise.Franchise;
import co.com.nequi.model.franchise.gateways.FranchiseGateway;
import co.com.nequi.model.product.Product;
import co.com.nequi.model.product.gateways.ProductGateway;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public record ProductUseCase(ProductGateway productGateway, BranchGateway branchGateway, FranchiseGateway franchiseGateway) {

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

    public Mono<Product> updateStock(Product product){
        return productGateway.findById(product.getId())
                .switchIfEmpty(Mono.error(new BusinessException(ProcessMessage.INVALID_PRODUCT)))
                .thenReturn(product)
                .flatMap(productGateway::updateStock);
    }

    public Flux<Product> findProductsWithMaxStockByFranchise(Long franchiseId){
        return franchiseGateway.findById(franchiseId)
                        .switchIfEmpty(Mono.error(new BusinessException(ProcessMessage.INVALID_FRANCHISE)))
                .map(Franchise::getId)
                .flatMapMany(productGateway::findProductsWithMaxStockByFranchise);

    }
}
