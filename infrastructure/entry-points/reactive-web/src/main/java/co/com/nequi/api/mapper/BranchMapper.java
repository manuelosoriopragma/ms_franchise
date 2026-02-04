package co.com.nequi.api.mapper;

import co.com.nequi.api.dto.BranchDto;
import co.com.nequi.api.dto.UpdateNameBranchDto;
import co.com.nequi.model.branch.Branch;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BranchMapper {

    public static Branch toDomain(BranchDto dto){
        return Branch.builder()
                .name(dto.getName())
                .franchiseId(dto.getFranchiseId())
                .build();
    }

    public static Branch toDomain(UpdateNameBranchDto dto){
        return Branch.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

}
