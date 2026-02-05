package co.com.nequi.usecase.franchise;

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
class FranchiseUseCaseTest {

    @Mock
    private FranchiseGateway franchiseGateway;

    private FranchiseUseCase franchiseUseCase;

    @BeforeEach
    void setUp() {
        franchiseUseCase = new FranchiseUseCase(franchiseGateway);
    }

    @Test
    void testSaveFranchiseSuccess() {
        Franchise franchise = Franchise.builder().name("Franchise 1").build();
        Franchise savedFranchise = Franchise.builder().id(1L).name("Franchise 1").build();

        when(franchiseGateway.save(franchise)).thenReturn(Mono.just(savedFranchise));

        StepVerifier.create(franchiseUseCase.saveFranchise(franchise))
                .expectNext(savedFranchise)
                .verifyComplete();
    }

    @Test
    void testUpdateNameSuccess() {
        Franchise existingFranchise = Franchise.builder().id(1L).name("Old Name").build();
        Franchise franchiseToUpdate = Franchise.builder().id(1L).name("New Name").build();
        Franchise updatedFranchise = Franchise.builder().id(1L).name("New Name").build();

        when(franchiseGateway.findById(1L)).thenReturn(Mono.just(existingFranchise));
        when(franchiseGateway.updateName(franchiseToUpdate)).thenReturn(Mono.just(updatedFranchise));

        StepVerifier.create(franchiseUseCase.updateName(franchiseToUpdate))
                .expectNext(updatedFranchise)
                .verifyComplete();
    }

    @Test
    void testUpdateNameInvalidFranchise() {
        Franchise franchise = Franchise.builder().id(999L).name("New Name").build();

        when(franchiseGateway.findById(999L)).thenReturn(Mono.empty());

        StepVerifier.create(franchiseUseCase.updateName(franchise))
                .expectErrorMatches(error -> error instanceof BusinessException &&
                        ((BusinessException) error).getProcessMessage() == ProcessMessage.INVALID_FRANCHISE)
                .verify();
    }
}
