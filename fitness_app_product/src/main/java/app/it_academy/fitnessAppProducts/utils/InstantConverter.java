package app.it_academy.fitnessAppProducts.utils;




import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import java.time.Instant;

public class InstantConverter implements Converter<String, Instant> {


    @Override
    public Instant convert(String value) {
        return Instant.parse(value);
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
