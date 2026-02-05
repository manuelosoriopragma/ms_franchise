package co.com.nequi.api.mapper;

import co.com.nequi.api.dto.ProductDto;
import co.com.nequi.api.dto.UpdateNameProductDto;
import co.com.nequi.api.dto.UpdateStockProductDto;
import co.com.nequi.model.product.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void testToDomainFromProductDto() {
        ProductDto dto = ProductDto.builder()
                .name("Product Name")
                .branchId(1L)
                .stock(100L)
                .build();

        Product product = ProductMapper.toDomain(dto);

        assertEquals("Product Name", product.getName());
        assertEquals(1L, product.getBranchId());
        assertEquals(100L, product.getStock());
        assertNull(product.getId());
    }

    @Test
    void testToDomainFromUpdateStockProductDto() {
        UpdateStockProductDto dto = UpdateStockProductDto.builder()
                .id(10L)
                .stock(200L)
                .build();

        Product product = ProductMapper.toDomain(dto);

        assertEquals(10L, product.getId());
        assertEquals(200L, product.getStock());
        assertNull(product.getName());
        assertNull(product.getBranchId());
    }

    @Test
    void testToDomainFromUpdateNameProductDto() {
        UpdateNameProductDto dto = UpdateNameProductDto.builder()
                .id(15L)
                .name("Updated Product Name")
                .build();

        Product product = ProductMapper.toDomain(dto);

        assertEquals(15L, product.getId());
        assertEquals("Updated Product Name", product.getName());
        assertNull(product.getStock());
        assertNull(product.getBranchId());
    }
}
