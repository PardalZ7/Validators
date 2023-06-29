package br.com.pz7system.validator.properties.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Getter
public class Properties {

    private List<Propertie> propertieList;

    public Properties(String schemaPath) throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        this.propertieList = objectMapper.readValue(this.getAsText(new File(schemaPath)), new TypeReference<List<Propertie>>() {});

    }

    private String getAsText(File file) throws IOException {

        return new String(Files.readAllBytes(file.toPath()));

    }

}
