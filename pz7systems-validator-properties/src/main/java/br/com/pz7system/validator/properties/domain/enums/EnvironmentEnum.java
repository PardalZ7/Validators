package br.com.pz7system.validator.properties.domain.enums;

public enum EnvironmentEnum {

    GENERAL   (""),
    LOCAL   ("local"),
    DEV     ("dev"),
    HOM     ("hom"),
    PROD    ("prod");

    private String env;
    EnvironmentEnum(String env) {

        this.env = env;

    }
}
