package co.com.nequi.api;

import co.com.nequi.api.dto.*;
import co.com.nequi.api.handler.BranchHandler;
import co.com.nequi.api.handler.FranchiseHandler;
import co.com.nequi.api.handler.GlobalExceptionHandler;
import co.com.nequi.api.handler.ProductHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RouterRest.class, FranchiseHandler.class, BranchHandler.class, 
        ProductHandler.class, GlobalExceptionHandler.class})
@WebFluxTest
class RouterRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private FranchiseHandler franchiseHandler;

    @MockitoBean
    private BranchHandler branchHandler;

    @MockitoBean
    private ProductHandler productHandler;

    @Test
    void testCreateFranchiseRoute() {
        when(franchiseHandler.createFranchise(any())).thenReturn(
                Mono.just(Mono.empty()).flatMap(m -> m.then(Mono.empty()))
        );

        webTestClient.post()
                .uri("/franchise/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(FranchiseDto.builder().name("Test").build())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateFranchiseNameRoute() {
        when(franchiseHandler.updateName(any())).thenReturn(Mono.empty());

        webTestClient.put()
                .uri("/franchise/rename")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(UpdateNameFranchiseDto.builder().id(1L).name("Updated").build())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testAssignBranchRoute() {
        when(branchHandler.assingBranch(any())).thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/branch/assign")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(BranchDto.builder().name("Branch").franchiseId(1L).build())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateBranchNameRoute() {
        when(branchHandler.updateName(any())).thenReturn(Mono.empty());

        webTestClient.put()
                .uri("/branch/rename")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(UpdateNameBranchDto.builder().id(1L).name("Updated").build())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testSaveProductRoute() {
        when(productHandler.saveProduct(any())).thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/product/save")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(ProductDto.builder().name("Product").branchId(1L).stock(10L).build())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testDeleteProductRoute() {
        when(productHandler.deleteProduct(any())).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/product/1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateStockRoute() {
        when(productHandler.updateStock(any())).thenReturn(Mono.empty());

        webTestClient.put()
                .uri("/product/updateStock")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(UpdateStockProductDto.builder().id(1L).stock(50L).build())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateProductNameRoute() {
        when(productHandler.updateName(any())).thenReturn(Mono.empty());

        webTestClient.put()
                .uri("/product/rename")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(UpdateNameProductDto.builder().id(1L).name("Updated").build())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testFindProductsWithMaxStockRoute() {
        when(productHandler.findProductsWithMaxStockByFranchise(any())).thenReturn(Mono.empty());

        webTestClient.get()
                .uri("/product/maxStockProducts/1")
                .exchange()
                .expectStatus().isOk();
    }
}
