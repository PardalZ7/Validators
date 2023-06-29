package br.com.pz7system.validator.yml;

import br.com.pz7system.validator.domain.Error;
import br.com.pz7system.validator.domain.Errors;
import br.com.pz7system.validator.domain.enums.SourceEnum;
import br.com.pz7system.validator.domain.enums.TypeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Set;

public class Validator {

    private static final String INVALID_YAML_FILE = "Arquivo YAML inválido: %s";
    private static final String FILE_NOT_FOUND = "Arquivo não encontrado: %s";
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    JsonSchemaFactory factory = JsonSchemaFactory.builder(
                    JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7)
            )
            .objectMapper(mapper)
            .build();

    public Errors validateDir (File directory, File schema) {

        Errors result = new Errors();
        for (File file : Objects.requireNonNull(directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".yaml")
                || name.toLowerCase().endsWith(".yml")))) {

            result.getErrorList().addAll(this.validate(file, schema).getErrorList());

        }

        return result;

    }

    public Errors validate (File yamlFile, File schema) {

        Errors result = new Errors();

        if (!yamlFile.exists()) {
            result.getErrorList().add(createError(yamlFile.getAbsolutePath(),
                    String.format(FILE_NOT_FOUND, yamlFile.getAbsolutePath())));
            return result;
        }

        Set<ValidationMessage> validations = null;
        try {

            validations = factory.getSchema(this.getText(schema))
                    .validate(mapper.readTree(this.getText(yamlFile)));

        } catch (Exception ex) {
            System.out.println("Invalid YAML file\n\t" + ex.getMessage());
            result.getErrorList().add(createError(yamlFile.getAbsolutePath(),
                    String.format(INVALID_YAML_FILE, yamlFile.getAbsolutePath())));
            return result;
        }

        for (ValidationMessage message : validations)
            result.getErrorList().add(createError(yamlFile.getAbsolutePath(), message.getMessage()));

        return result;

    }

    private Error createError(String identificator, String message) {
        return createErrorWarning(identificator, message, TypeEnum.ERRO);
    }

    private Error createWarning(String identificator, String message) {
        return createErrorWarning(identificator, message, TypeEnum.WARNING);
    }

    private Error createErrorWarning(String identificator, String message, TypeEnum type) {
        return Error.builder()
                .identificator(identificator)
                .message(message)
                .source(SourceEnum.YAML)
                .type(type)
                .build();
    }

    private String getText(File file) throws IOException {

        return new String(Files.readAllBytes(file.toPath()));

    }

}
