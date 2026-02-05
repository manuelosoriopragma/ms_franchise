package co.com.nequi.api.mapper;

import co.com.nequi.api.dto.FranchiseDto;
import co.com.nequi.api.dto.UpdateNameFranchiseDto;
import co.com.nequi.model.franchise.Franchise;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FranchiseMapperTest {

    @Test
    void testToDomainFromFranchiseDto() {
        FranchiseDto dto = FranchiseDto.builder()
                .name("Franchise Name")
                .build();

        Franchise franchise = FranchiseMapper.toDomain(dto);

        assertEquals("Franchise Name", franchise.getName());
        assertNull(franchise.getId());
    }

    @Test
    void testToDomainFromUpdateNameFranchiseDto() {
        UpdateNameFranchiseDto dto = UpdateNameFranchiseDto.builder()
                .id(5L)
                .name("Updated Franchise Name")
                .build();

        Franchise franchise = FranchiseMapper.toDomain(dto);

        assertEquals(5L, franchise.getId());
        assertEquals("Updated Franchise Name", franchise.getName());
    }
}
