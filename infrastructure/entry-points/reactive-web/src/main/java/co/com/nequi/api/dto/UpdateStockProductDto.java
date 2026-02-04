package co.com.nequi.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static co.com.nequi.api.helper.Constants.REQUIRED_PRODUCT_ID;
import static co.com.nequi.api.helper.Constants.REQUIRED_STOCK;
import static co.com.nequi.api.helper.Constants.STOCK_GRATER_THAN_ZERO;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateStockProductDto {
    @NotNull(message = REQUIRED_PRODUCT_ID)
    private Long id;
    @NotNull(message = REQUIRED_STOCK)
    @Min(value = 0, message = STOCK_GRATER_THAN_ZERO)
    private Long stock;
}
