package br.com.pz7system.validator.yml;

import br.com.pz7system.validator.domain.Errors;
import org.junit.jupiter.api.Test;

import java.io.File;

class ValidatorTest {

    private static final String EMPTY_SCHEMA_PATH = "src/test/resources/emptySchema.json";
    private static final String SOME_SCHEMA_PATH = "src/test/resources/someSchema.json";
    private static final String YAML_DIR = "src/main/resources";

    @Test
    public void deveValidarArquivosEmptySchema() {

        Validator validator = new Validator();
        Errors errors = validator.validateDir(new File(YAML_DIR), new File(EMPTY_SCHEMA_PATH));

        System.out.println(errors);

    }

    @Test
    public void deveValidarArquivosSomeSchema() {

        Validator validator = new Validator();
        Errors errors = validator.validateDir(new File(YAML_DIR), new File(SOME_SCHEMA_PATH));

        System.out.println(errors);

    }

}