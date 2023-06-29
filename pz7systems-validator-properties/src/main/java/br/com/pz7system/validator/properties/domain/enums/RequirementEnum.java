package br.com.pz7system.validator.properties.domain.enums;

public enum RequirementEnum {

    MANDATORY("mandatory"),
    DESIRED("desired"),
    OPTIONAL("optional");

    String value;
    RequirementEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
