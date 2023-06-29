package br.com.pz7system.validator.properties.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Environment {

    private String environmentName;
    private String requirement;
    private String defaultValue;

}
