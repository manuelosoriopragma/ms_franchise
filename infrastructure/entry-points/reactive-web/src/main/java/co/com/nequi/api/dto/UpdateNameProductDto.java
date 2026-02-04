package co.com.nequi.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static co.com.nequi.api.helper.Constants.REQUIRED_NAME_VALDATION;
import static co.com.nequi.api.helper.Constants.REQUIRED_PRODUCT_ID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateNameProductDto {
    @NotNull(message = REQUIRED_PRODUCT_ID)
    private Long id;
    @NotBlank(message = REQUIRED_NAME_VALDATION)
    private String name;
}
