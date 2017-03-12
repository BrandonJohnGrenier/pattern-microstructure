package fm.pattern.validation.repository;

import org.junit.Test;

import fm.pattern.validation.PatternAssertions;

public class ValidationRepositoryFactoryTest {

    @Test
    public void shouldBeAbleToExplicitlySetTheValidationRepositoryToUse() {
        ValexConfigurationFactory.use(new ValexYamlConfiguration());
        PatternAssertions.assertThat(ValexConfigurationFactory.getRepository().getClass()).isEqualTo(ValexYamlConfiguration.class);

        ValexConfigurationFactory.use(null);
        PatternAssertions.assertThat(ValexConfigurationFactory.getRepository().getClass()).isEqualTo(ValexPropertiesConfiguration.class);
    }

    @Test
    public void theClassShouldBeAWellDefinedUtilityClass() {
        PatternAssertions.assertClass(ValexConfigurationFactory.class).isAWellDefinedUtilityClass();
    }

}
