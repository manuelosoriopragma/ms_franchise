package co.com.nequi.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static co.com.nequi.api.helper.Constants.REQUIRED_BRANCH_ID;
import static co.com.nequi.api.helper.Constants.REQUIRED_NAME_VALDATION;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UpdateNameBranchDto {
    @NotNull(message = REQUIRED_BRANCH_ID)
    private Long id;
    @NotBlank(message = REQUIRED_NAME_VALDATION)
    private String name;
}
