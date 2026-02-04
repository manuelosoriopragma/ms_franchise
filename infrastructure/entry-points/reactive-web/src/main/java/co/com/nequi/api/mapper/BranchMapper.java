package co.com.nequi.api.mapper;

import co.com.nequi.api.dto.BranchDto;
import co.com.nequi.api.dto.FranchiseDto;
import co.com.nequi.model.branch.Branch;
import co.com.nequi.model.franchise.Franchise;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BranchMapper {

    public static Branch toDomain(BranchDto dto){
        return Branch.builder()
                .name(dto.getName())
                .franchiseId(dto.getFranchiseId())
                .build();
    }

}
