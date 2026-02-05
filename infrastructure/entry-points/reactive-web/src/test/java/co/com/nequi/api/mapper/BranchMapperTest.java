package co.com.nequi.api.mapper;

import co.com.nequi.api.dto.BranchDto;
import co.com.nequi.api.dto.UpdateNameBranchDto;
import co.com.nequi.model.branch.Branch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BranchMapperTest {

    @Test
    void testToDomainFromBranchDto() {
        BranchDto dto = BranchDto.builder()
                .name("Branch Name")
                .franchiseId(1L)
                .build();

        Branch branch = BranchMapper.toDomain(dto);

        assertEquals("Branch Name", branch.getName());
        assertEquals(1L, branch.getFranchiseId());
        assertNull(branch.getId());
    }

    @Test
    void testToDomainFromUpdateNameBranchDto() {
        UpdateNameBranchDto dto = UpdateNameBranchDto.builder()
                .id(10L)
                .name("Updated Branch Name")
                .build();

        Branch branch = BranchMapper.toDomain(dto);

        assertEquals(10L, branch.getId());
        assertEquals("Updated Branch Name", branch.getName());
        assertNull(branch.getFranchiseId());
    }
}
