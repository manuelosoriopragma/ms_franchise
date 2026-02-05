package co.com.nequi.usecase.product;

import co.com.nequi.model.branch.Branch;
import co.com.nequi.model.branch.gateways.BranchGateway;
import co.com.nequi.model.enums.ProcessMessage;
import co.com.nequi.model.exceptions.BusinessException;
import co.com.nequi.model.franchise.Franchise;
import co.com.nequi.model.franchise.gateways.FranchiseGateway;
import co.com.nequi.model.product.Product;
import co.com.nequi.model.product.gateways.ProductGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @Mock
    private BranchGateway branchGateway;

    @Mock
    private FranchiseGateway franchiseGateway;

    private ProductUseCase productUseCase;

    @BeforeEach
    void setUp() {
        productUseCase = new ProductUseCase(productGateway, branchGateway, franchiseGateway);
    }

    @Test
    void testSaveProductSuccess() {
        Product product = Product.builder().branchId(1L).name("Product 1").stock(10L).build();
        Branch branch = Branch.builder().id(1L).name("Branch 1").build();
        Product savedProduct = Product.builder().id(1L).branchId(1L).name("Product 1").stock(10L).build();

        when(branchGateway.findById(1L)).thenReturn(Mono.just(branch));
        when(productGateway.save(product)).thenReturn(Mono.just(savedProduct));

        StepVerifier.create(productUseCase.saveProduct(product))
                .expectNext(savedProduct)
                .verifyComplete();
    }

    @Test
    void testSaveProductInvalidBranch() {
        Product product = Product.builder().branchId(999L).name("Product 1").build();

        when(branchGateway.findById(999L)).thenReturn(Mono.empty());

        StepVerifier.create(productUseCase.saveProduct(product))
                .expectErrorMatches(error -> error instanceof BusinessException &&
                        ((BusinessException) error).getProcessMessage() == ProcessMessage.INVALID_BRANCH)
                .verify();
    }

    @Test
    void testDeleteProductSuccess() {
        Product product = Product.builder().id(1L).branchId(1L).name("Product 1").build();

        when(productGateway.findById(1L)).thenReturn(Mono.just(product));
        when(productGateway.deleteById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(productUseCase.deleteProduct(1L))
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    void testDeleteProductInvalidProduct() {
        when(productGateway.findById(999L)).thenReturn(Mono.empty());

        StepVerifier.create(productUseCase.deleteProduct(999L))
                .expectErrorMatches(error -> error instanceof BusinessException &&
                        ((BusinessException) error).getProcessMessage() == ProcessMessage.INVALID_PRODUCT)
                .verify();
    }

    @Test
    void testUpdateStockSuccess() {
        Product existingProduct = Product.builder().id(1L).branchId(1L).name("Product 1").stock(10L).build();
        Product productToUpdate = Product.builder().id(1L).branchId(1L).name("Product 1").stock(20L).build();
        Product updatedProduct = Product.builder().id(1L).branchId(1L).name("Product 1").stock(20L).build();

        when(productGateway.findById(1L)).thenReturn(Mono.just(existingProduct));
        when(productGateway.updateStock(productToUpdate)).thenReturn(Mono.just(updatedProduct));

        StepVerifier.create(productUseCase.updateStock(productToUpdate))
                .expectNext(updatedProduct)
                .verifyComplete();
    }

    @Test
    void testUpdateStockInvalidProduct() {
        Product product = Product.builder().id(999L).stock(20L).build();

        when(productGateway.findById(999L)).thenReturn(Mono.empty());

        StepVerifier.create(productUseCase.updateStock(product))
                .expectErrorMatches(error -> error instanceof BusinessException &&
                        ((BusinessException) error).getProcessMessage() == ProcessMessage.INVALID_PRODUCT)
                .verify();
    }

    @Test
    void testUpdateNameSuccess() {
        Product existingProduct = Product.builder().id(1L).branchId(1L).name("Old Name").build();
        Product productToUpdate = Product.builder().id(1L).branchId(1L).name("New Name").build();
        Product updatedProduct = Product.builder().id(1L).branchId(1L).name("New Name").build();

        when(productGateway.findById(1L)).thenReturn(Mono.just(existingProduct));
        when(productGateway.updateName(productToUpdate)).thenReturn(Mono.just(updatedProduct));

        StepVerifier.create(productUseCase.updateName(productToUpdate))
                .expectNext(updatedProduct)
                .verifyComplete();
    }

    @Test
    void testUpdateNameInvalidProduct() {
        Product product = Product.builder().id(999L).name("New Name").build();

        when(productGateway.findById(999L)).thenReturn(Mono.empty());

        StepVerifier.create(productUseCase.updateName(product))
                .expectErrorMatches(error -> error instanceof BusinessException &&
                        ((BusinessException) error).getProcessMessage() == ProcessMessage.INVALID_PRODUCT)
                .verify();
    }

    @Test
    void testFindProductsWithMaxStockByFranchiseSuccess() {
        Franchise franchise = Franchise.builder().id(1L).name("Franchise 1").build();
        Product product1 = Product.builder().id(1L).branchId(1L).name("Product 1").stock(100L).build();
        Product product2 = Product.builder().id(2L).branchId(2L).name("Product 2").stock(150L).build();

        when(franchiseGateway.findById(1L)).thenReturn(Mono.just(franchise));
        when(productGateway.findProductsWithMaxStockByFranchise(1L)).thenReturn(Flux.just(product1, product2));

        StepVerifier.create(productUseCase.findProductsWithMaxStockByFranchise(1L))
                .expectNext(product1)
                .expectNext(product2)
                .verifyComplete();
    }

    @Test
    void testFindProductsWithMaxStockByFranchiseInvalidFranchise() {
        when(franchiseGateway.findById(999L)).thenReturn(Mono.empty());

        StepVerifier.create(productUseCase.findProductsWithMaxStockByFranchise(999L))
                .expectErrorMatches(error -> error instanceof BusinessException &&
                        ((BusinessException) error).getProcessMessage() == ProcessMessage.INVALID_FRANCHISE)
                .verify();
    }
}
