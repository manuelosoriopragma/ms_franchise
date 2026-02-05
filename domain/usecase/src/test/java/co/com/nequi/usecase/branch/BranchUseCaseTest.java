package co.com.nequi.usecase.branch;

import co.com.nequi.model.branch.Branch;
import co.com.nequi.model.branch.gateways.BranchGateway;
import co.com.nequi.model.enums.ProcessMessage;
import co.com.nequi.model.exceptions.BusinessException;
import co.com.nequi.model.franchise.Franchise;
import co.com.nequi.model.franchise.gateways.FranchiseGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BranchUseCaseTest {

    @Mock
    private BranchGateway branchGateway;

    @Mock
    private FranchiseGateway franchiseGateway;

    private BranchUseCase branchUseCase;

    @BeforeEach
    void setUp() {
        branchUseCase = new BranchUseCase(branchGateway, franchiseGateway);
    }

    @Test
    void testAssingBranchSuccess() {
        Branch branch = Branch.builder().franchiseId(1L).name("Branch 1").build();
        Franchise franchise = Franchise.builder().id(1L).name("Franchise 1").build();
        Branch savedBranch = Branch.builder().id(1L).franchiseId(1L).name("Branch 1").build();

        when(franchiseGateway.findById(1L)).thenReturn(Mono.just(franchise));
        when(branchGateway.save(branch)).thenReturn(Mono.just(savedBranch));

        StepVerifier.create(branchUseCase.assingBranch(branch))
                .expectNext(savedBranch)
                .verifyComplete();
    }

    @Test
    void testAssingBranchInvalidFranchise() {
        Branch branch = Branch.builder().franchiseId(999L).name("Branch 1").build();

        when(franchiseGateway.findById(999L)).thenReturn(Mono.empty());

        StepVerifier.create(branchUseCase.assingBranch(branch))
                .expectErrorMatches(error -> error instanceof BusinessException &&
                        ((BusinessException) error).getProcessMessage() == ProcessMessage.INVALID_FRANCHISE)
                .verify();
    }

    @Test
    void testUpdateNameSuccess() {
        Branch existingBranch = Branch.builder().id(1L).franchiseId(1L).name("Old Name").build();
        Branch branchToUpdate = Branch.builder().id(1L).franchiseId(1L).name("New Name").build();
        Branch updatedBranch = Branch.builder().id(1L).franchiseId(1L).name("New Name").build();

        when(branchGateway.findById(1L)).thenReturn(Mono.just(existingBranch));
        when(branchGateway.updateName(branchToUpdate)).thenReturn(Mono.just(updatedBranch));

        StepVerifier.create(branchUseCase.updateName(branchToUpdate))
                .expectNext(updatedBranch)
                .verifyComplete();
    }

    @Test
    void testUpdateNameInvalidBranch() {
        Branch branch = Branch.builder().id(999L).name("New Name").build();

        when(branchGateway.findById(999L)).thenReturn(Mono.empty());

        StepVerifier.create(branchUseCase.updateName(branch))
                .expectErrorMatches(error -> error instanceof BusinessException &&
                        ((BusinessException) error).getProcessMessage() == ProcessMessage.INVALID_BRANCH)
                .verify();
    }
}
