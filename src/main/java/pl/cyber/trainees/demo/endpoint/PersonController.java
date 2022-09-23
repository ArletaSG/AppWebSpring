package pl.cyber.trainees.demo.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.cyber.trainees.demo.dto.KluczSortowaniaEnum;
import pl.cyber.trainees.demo.dto.PersonDTO;
import pl.cyber.trainees.demo.dto.PersonRequest;
import pl.cyber.trainees.demo.service.PersonService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private PersonService service;

    @PostMapping("/person")
    public PersonDTO getPerson(@RequestBody final PersonRequest request){
        return service.getPerson(request);
    }

    @GetMapping("/person/params")
    public PersonDTO getPersonParam (@RequestParam final String imie, @RequestParam final String nazwisko) {
        return service.getPersonParams(imie, nazwisko);
    }


    @PutMapping("/create")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createPerson(@RequestBody final PersonRequest request) {
        log.info("Dokonuje utworzenia Osoby");
        service.createPerson(request);

    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePerson(@RequestBody final PersonRequest request) {
        log.info("Dokonuje aktualizacji Osoby");
        service.updatePerson(request);
    }
/*
User PersonDTO  <-> Server Person <-> DB PersonEntity
        PersonRequest
 */
    //Zadanie Utworzyć 2 obiekty:
    //1. PersonDTO, który będzie przekazywane dla uzytkowanika
    //2. Person jako obiekt wewnętrzny aplikacji
    //PersonRequest
    //metody GET (@GetMapping), PUT (PutMapping), POST (PostMapping)
    //Utworzyć metody REST do tworzenia użytkownika, zmiany jego danych oraz jego pobierania.

    //IMIE, NAZWISKO, DATA UR, MIASTO ZAMIESZKANIA, PŁEĆ

    //-----------------------------------zad.12-------------------------------------------------
    /*
    /*
      Wykonaj createPerson z PersonController aby utworzyć 6 różnych osób (przy użyciu Postman), następnie
      napisz program, który z kolekcji typu List posiadającej  6 różnych osób(w oparciu o poprzednie zadanie)
      posortuje ich względem wskazanego klucza (np. miasta, datyUrodzenia, nazwiska lub imienia).
      Po czym zostanie zwrócona posortowana lista do użytkownika.
    */



@GetMapping("/zadanie12")
    public List<PersonDTO> zadanie12(
            @RequestParam("klucz") final KluczSortowaniaEnum klucz)// może być  @RequestParam("klucz") final String ale lepiej enum
{                                   //klucz będzie od użytkownika
    return service.zadanie12(klucz);
}

}

