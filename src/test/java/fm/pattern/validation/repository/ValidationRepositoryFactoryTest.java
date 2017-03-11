package fm.pattern.validation.repository;

import org.junit.Test;

import fm.pattern.validation.PatternAssertions;

public class ValidationRepositoryFactoryTest {

    @Test
    public void shouldBeAbleToExplicitlySetTheValidationRepositoryToUse() {
        ValidationRepositoryFactory.use(new YamlFileValidationRepository());
        PatternAssertions.assertThat(ValidationRepositoryFactory.getRepository().getClass()).isEqualTo(YamlFileValidationRepository.class);

        ValidationRepositoryFactory.use(null);
        PatternAssertions.assertThat(ValidationRepositoryFactory.getRepository().getClass()).isEqualTo(PropertiesFileValidationRepository.class);
    }

    @Test
    public void theClassShouldBeAWellDefinedUtilityClass() {
        PatternAssertions.assertClass(ValidationRepositoryFactory.class).isAWellDefinedUtilityClass();
    }

}
