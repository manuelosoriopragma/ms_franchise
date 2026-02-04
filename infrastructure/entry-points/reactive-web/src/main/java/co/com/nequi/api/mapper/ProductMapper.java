package co.com.nequi.api.mapper;

import co.com.nequi.api.dto.ProductDto;
import co.com.nequi.api.dto.UpdateNameProductDto;
import co.com.nequi.api.dto.UpdateStockProductDto;
import co.com.nequi.model.product.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {

    public static Product toDomain(ProductDto dto){
        return Product.builder()
                .name(dto.getName())
                .branchId(dto.getBranchId())
                .stock(dto.getStock())
                .build();
    }

    public static Product toDomain(UpdateStockProductDto dto){
        return Product.builder()
                .id(dto.getId())
                .stock(dto.getStock())
                .build();
    }

    public static Product toDomain(UpdateNameProductDto dto){
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

}
