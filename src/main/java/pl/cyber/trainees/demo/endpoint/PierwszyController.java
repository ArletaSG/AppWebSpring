package pl.cyber.trainees.demo.endpoint;


//tutaj implementuje się usługi rest, linki, któe będziemy używali w ramach naszej aplikacji (metoda HTTP lub link http)

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.cyber.trainees.demo.dto.ImieDTO;
import pl.cyber.trainees.demo.dto.PersonDTO;
import pl.cyber.trainees.demo.dto.PersonRequest;
import pl.cyber.trainees.demo.service.PersonService;

import java.time.LocalDate;

@Slf4j //odpowiada za logi w ramach serwera, zamiast sout
@RestController //mówi dla serwera Spring, że w tym miejscu będą fukcjonalności REST API
@RequiredArgsConstructor
@RequestMapping("/v1/first") //
public class PierwszyController {

    private final PersonService service;

    //HTTP metoda GET - pobieranie info z serwera oraz wysłania ich do zewn systemu
    @GetMapping("/{imie}") //mapowanie linka na http
    public ImieDTO getImie(@PathVariable final String imie) {
        return ImieDTO.builder()
                .imie(imie)
                .build();
            }
// dotąd jest pierwszy kontroler

}
