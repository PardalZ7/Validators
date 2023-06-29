package br.com.pz7system.validator.properties;

import br.com.pz7system.validator.domain.Errors;
import br.com.pz7system.validator.properties.domain.enums.EnvironmentEnum;
import org.junit.jupiter.api.Test;

class ValidatorTest {

    private static final String APPLICATION_FILE_PATH = "src/main/resources/application.properties";
    private static final String SCHEMA_PATH = "src/main/test/schema01.json";

    @Test
    public void deveValidarAsPropriedadesGerais() {

        Validator validator = new Validator();
        Errors errors = validator.validate(APPLICATION_FILE_PATH, EnvironmentEnum.GENERAL, SCHEMA_PATH);

        System.out.println(errors);

    }

}