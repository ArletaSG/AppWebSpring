package pl.cyber.trainees.demo.endpoint;


//tutaj implementuje się usługi rest, linki, któe będziemy używali w ramach naszej aplikacji (metoda HTTP lub link http)

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cyber.trainees.demo.dto.ImieDTO;

@RestController //mówi dla serwera Spring, że w tym miejscu będą fukcjonalności REST API
@RequestMapping("/v1/first") //
public class PierwszyController {

    //HTTP metoda GET - pobieranie info z serwera oraz wysłania ich do zewn systemu
    @GetMapping("/{imie}") //mapowanie linka na http
    public ImieDTO getImie(@PathVariable final String imie) {
        return ImieDTO.builder()
                .imie(imie)
                .build();
            }

}
