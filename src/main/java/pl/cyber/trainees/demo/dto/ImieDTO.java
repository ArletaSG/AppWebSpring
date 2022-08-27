package pl.cyber.trainees.demo.dto;

import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class ImieDTO {
    private final String imie;

    @Builder // adnotacja pomaga utworzyć obiekt bez wypełniania konstruktora
    @ConstructorProperties({"imie"}) // nasz aklasa do innej app = prawidłowa struktura JSON
    public ImieDTO(final String imie) {
        this.imie = imie;
    }
}
