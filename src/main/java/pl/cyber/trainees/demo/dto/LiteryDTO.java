package pl.cyber.trainees.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder // do zbudowania obiektu w oparciu o konstruktor klasy /
/*
new LiteryDTO("a", 1);

builder daje:
LiteryDTO.builder()
.litera("a"
.ilosc(1)
.build()
Litera -> a, jej ilosc->1
.......................................
new LiteryDTO("a", null);

LiteryDTO.builder()
.litera("a"
.build()
Litera -> a, jej ilosc->null
 */
@AllArgsConstructor //zbuduje konstruktor ze wszystkich parametrów
public class LiteryDTO {
    private String litera;
    private Integer ilosc;


}
