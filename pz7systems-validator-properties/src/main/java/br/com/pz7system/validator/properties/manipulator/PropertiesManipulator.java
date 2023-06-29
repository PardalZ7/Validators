package br.com.pz7system.validator.properties.manipulator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManipulator {

    private Properties props;

    public PropertiesManipulator(String propertiesFile) throws IOException {

        this.props = new Properties();
        FileInputStream file = new FileInputStream(propertiesFile);

        this.props.load(file);

    }

    public String getPropertie(String propertieName) {

        return props.getProperty(propertieName);

    }

}
