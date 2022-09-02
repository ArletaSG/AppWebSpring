package pl.cyber.trainees.demo.dto;

import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
@Getter
public class PersonRequest {
    private final String imie;
    private final String nazwisko;
    private final LocalDate dataUrodzenia;
    @Builder
    @ConstructorProperties({"imie", "nazwisko", "dataUrodzenia", "miasto", "plec"}) //musi się zgadzac kolejnośc z konstruktorem poniżej

    public PersonRequest(final String imie, final String nazwisko, final LocalDate dataUrodzenia, final String miasto, final String plec) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.miasto = miasto;
        this.plec = plec;
    }

    private final String miasto;
    private final String plec;

}
