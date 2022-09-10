package pl.cyber.trainees.demo.dto;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class OnetStringRequest {
    private final String value;

    @ConstructorProperties({"value"})
    public OnetStringRequest(final String value) {
        if(value == null || value.length() == 0) {
            throw new RuntimeException("Nie podano zdania.");
        }
        this.value = value;
    }
}

