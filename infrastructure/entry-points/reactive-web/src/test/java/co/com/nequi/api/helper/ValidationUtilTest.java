package co.com.nequi.api.helper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidationUtilTest {

    @Mock
    private Validator validator;

    private ValidationUtil validationUtil;

    @BeforeEach
    void setUp() {
        validationUtil = new ValidationUtil(validator);
    }

    @Test
    void testValidateSuccess() {
        TestObject testObject = new TestObject("Valid Name", 10);
        Set<ConstraintViolation<TestObject>> violations = new HashSet<>();

        when(validator.validate(testObject)).thenReturn(violations);

        StepVerifier.create(validationUtil.validate(testObject))
                .expectNext(testObject)
                .verifyComplete();

        verify(validator).validate(testObject);
    }

    @Test
    void testValidateWithViolations() {
        TestObject testObject = new TestObject(null, null);
        Set<ConstraintViolation<TestObject>> violations = new HashSet<>();
        ConstraintViolation<TestObject> violation = mock(ConstraintViolation.class);
        violations.add(violation);

        when(validator.validate(testObject)).thenReturn(violations);

        StepVerifier.create(validationUtil.validate(testObject))
                .expectError(ConstraintViolationException.class)
                .verify();

        verify(validator).validate(testObject);
    }

    @Test
    void testValidateWithMultipleViolations() {
        TestObject testObject = new TestObject("", -1);
        Set<ConstraintViolation<TestObject>> violations = new HashSet<>();
        violations.add(mock(ConstraintViolation.class));
        violations.add(mock(ConstraintViolation.class));

        when(validator.validate(testObject)).thenReturn(violations);

        StepVerifier.create(validationUtil.validate(testObject))
                .expectError(ConstraintViolationException.class)
                .verify();

        verify(validator).validate(testObject);
    }

    static class TestObject {
        @NotBlank
        private final String name;

        @NotNull
        private final Integer value;

        TestObject(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
    }
}
