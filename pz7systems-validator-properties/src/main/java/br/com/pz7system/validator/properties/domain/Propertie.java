package br.com.pz7system.validator.properties.domain;

import br.com.pz7system.validator.properties.domain.enums.EnvironmentEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Propertie {

    private String name;
    private String description;
    private List<String> values;
    private Environment defaultEnvironment;
    private List<Environment> environments;

    public Environment getEnvironment(EnvironmentEnum environment) {

        if (this.environments == null)
            return defaultEnvironment;

        for (Environment env : environments) {
            if (env.getEnvironmentName().equals(environment))
                return env;
        }
        return defaultEnvironment;
    }

}
