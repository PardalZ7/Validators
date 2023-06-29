package br.com.pz7system.validator.domain;

import br.com.pz7system.validator.domain.enums.SourceEnum;
import br.com.pz7system.validator.domain.enums.TypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Error {

    private SourceEnum source;
    private TypeEnum type;
    private String identificator;
    private String message;

    @Override
    public String toString() {

        return "[" + type.toString() + "] " + source.toString() + " " + identificator + ": " + message;

    }
}
