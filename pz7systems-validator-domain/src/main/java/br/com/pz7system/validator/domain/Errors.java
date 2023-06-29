package br.com.pz7system.validator.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Errors {

    private List<Error> errorList = new ArrayList<>();

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("");
        errorList.stream().map(error -> error.toString()).forEach(str -> builder.append("\n" + str));

        return builder.toString();

    }
}
