package br.com.pz7system.validator.properties;

import br.com.pz7system.validator.domain.Errors;
import br.com.pz7system.validator.domain.Error;
import br.com.pz7system.validator.domain.enums.SourceEnum;
import br.com.pz7system.validator.domain.enums.TypeEnum;
import br.com.pz7system.validator.properties.domain.Environment;
import br.com.pz7system.validator.properties.domain.Propertie;
import br.com.pz7system.validator.properties.domain.Properties;
import br.com.pz7system.validator.properties.domain.enums.EnvironmentEnum;
import br.com.pz7system.validator.properties.domain.enums.RequirementEnum;
import br.com.pz7system.validator.properties.manipulator.PropertiesManipulator;

import java.io.IOException;
import java.util.Arrays;

public class Validator {

    public static final String PROPERTIE_NOT_FOUND = "A propriedade %s não foi encontrada no ambiente %s";
    public static final String VALUE_NOT_FOUND = "O valor da propriedade %s('%s') não se adequa aos valores permitidos %s";

    public Errors validate(String propertiePath, EnvironmentEnum environment, String schemaPath) {

        try {

            Errors errors = new Errors();
            PropertiesManipulator manipulator = new PropertiesManipulator(propertiePath);
            Properties properties = new Properties(schemaPath);

            properties.getPropertieList().stream().forEach(propertie -> {
                validate(propertie, manipulator, environment, errors);
            });

            return errors;

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

    private void validate(Propertie propertie, PropertiesManipulator manipulator, EnvironmentEnum environmentName, Errors errors) {

        Environment environment = propertie.getEnvironment(environmentName);
        if (environment == null)
            return;

        String propValue = manipulator.getPropertie(propertie.getName());
        if ((propValue == null) || (propValue.isEmpty())) {
            if (environment.getRequirement().equalsIgnoreCase(RequirementEnum.MANDATORY.getValue()) ||
                    environment.getRequirement().equalsIgnoreCase(RequirementEnum.DESIRED.getValue()))
                    errors.getErrorList().add(
                            Error.builder()
                            .identificator(propertie.getName())
                            .message(String.format(PROPERTIE_NOT_FOUND, propertie.getName(), environmentName))
                            .source(SourceEnum.PROPERTIE)
                            .type(environment.getRequirement().equalsIgnoreCase(RequirementEnum.MANDATORY.getValue())
                                    ? TypeEnum.ERRO : TypeEnum.WARNING)
                            .build()
                    );
            return;
        }

        if ((propertie.getValues()!= null) && (!propertie.getValues().isEmpty())) {

            for (String value : propertie.getValues()) {

                if (value.equals(propValue))
                    return;
            }

        } else
            return;

        errors.getErrorList().add(
                Error.builder()
                        .identificator(propertie.getName())
                        .message(String.format(VALUE_NOT_FOUND, propertie.getName(),
                                propValue, Arrays.toString(propertie.getValues().toArray())))
                        .source(SourceEnum.PROPERTIE)
                        .type(TypeEnum.ERRO)
                        .build()
        );

    }

}
