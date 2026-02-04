package co.com.nequi.r2dbc;

import co.com.nequi.r2dbc.adapter.FranchiseAdapter;
import co.com.nequi.r2dbc.repository.FranchiseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FranchiseAdapterTest {
    // TODO: change four you own tests

    @InjectMocks
    FranchiseAdapter repositoryAdapter;

    @Mock
    FranchiseRepository repository;

    @Mock
    ObjectMapper mapper;


}
