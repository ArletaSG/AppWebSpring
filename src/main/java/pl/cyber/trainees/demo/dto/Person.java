package pl.cyber.trainees.demo.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor //mói aplikacji, żeby utworzyła konstruktor ze wszystkich parametrów dostępnych z obiektu
public class Person {
    private final Integer id;
    private final String imie;
    private final String nazwisko;
    private final LocalDate dataUrodzenia;
    @Setter
    private String miasto; //Setter dotyczy tylko miasto
    private final String plec;


}
