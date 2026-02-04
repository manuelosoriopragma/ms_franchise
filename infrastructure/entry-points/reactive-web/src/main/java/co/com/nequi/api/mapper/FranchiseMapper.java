package co.com.nequi.api.mapper;

import co.com.nequi.api.dto.FranchiseDto;
import co.com.nequi.model.franchise.Franchise;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FranchiseMapper {

    public static Franchise toDomain(FranchiseDto dto){
        return Franchise.builder()
                .name(dto.getName())
                .build();
    }

}
