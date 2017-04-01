package fm.pattern.valex.config;

import org.junit.Test;

import fm.pattern.valex.PatternAssertions;

public class ValexConfigurationTest {

    @Test
    public void shouldBeAbleToExplicitlySetTheValidationRepositoryToUse() {
        ValexConfiguration.use(new YamlConfigurationFile());
        PatternAssertions.assertThat(ValexConfiguration.getConfiguration().getClass()).isEqualTo(YamlConfigurationFile.class);

        ValexConfiguration.use(new PropertyConfigurationFile());
        PatternAssertions.assertThat(ValexConfiguration.getConfiguration().getClass()).isEqualTo(PropertyConfigurationFile.class);
    }

    @Test
    public void shouldBeAbleToSpecifyTheConfigurationFileToUseViaSystemProperty() {
        ValexConfiguration.use(null);

        System.setProperty("valex.config", "ValidationMessages.yml");
        PatternAssertions.assertThat(ValexConfiguration.getConfiguration().getClass()).isEqualTo(YamlConfigurationFile.class);

        System.setProperty("valex.config", "ValidationMessages.properties");
        PatternAssertions.assertThat(ValexConfiguration.getConfiguration().getClass()).isEqualTo(PropertyConfigurationFile.class);
    }

    @Test(expected = ValexConfigurationException.class)
    public void shouldThrowAConfigurationExceptionIfTheSpecifiedConfigurationFileIsNotSupported() {
        ValexConfiguration.use(null);

        System.setProperty("valex.config", "ValidationMessages.xml");
        ValexConfiguration.getConfiguration();
    }

    @Test
    public void theClassShouldBeAWellDefinedUtilityClass() {
        PatternAssertions.assertClass(ValexConfiguration.class).isAWellDefinedUtilityClass();
    }

}
