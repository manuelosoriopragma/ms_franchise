package co.com.nequi.api.mapper;

import co.com.nequi.api.dto.BranchDto;
import co.com.nequi.api.dto.ProductDto;
import co.com.nequi.model.branch.Branch;
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

}
