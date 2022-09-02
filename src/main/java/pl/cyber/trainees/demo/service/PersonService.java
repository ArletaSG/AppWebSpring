package pl.cyber.trainees.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cyber.trainees.demo.dto.Person;
import pl.cyber.trainees.demo.dto.PersonDTO;
import pl.cyber.trainees.demo.dto.PersonRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service // Framework Spring wie, że może ją używać i dac do defaultowego konstruktora, powołać go do życia

@RequiredArgsConstructor
public class PersonService {

    private List<Person> personList = new ArrayList<>();

    public void createPerson (final PersonRequest request) {
        //validator czy obiekt, który chcemy dodać już nie istnieje
        walidujOsobę(request);
        personList.add(Person.builder()
                .imie(request.getImie())
                .nazwisko(request.getNazwisko())
                .miasto(request.getMiasto())
                .dataUrodzenia(request.getDataUrodzenia())
                .plec(request.getPlec())
                .build());
    }

    private void walidujOsobę( final PersonRequest request) {
        boolean czyIstnieje = false;
        for (Person element :personList) {
            if(element.getImie().equals(request.getImie()) //albo tak
            && Objects.equals(element.getNazwisko(), request.getNazwisko()) //albo z klasą Objects (ma metody isNull, nonNull, przydatne)
            && Objects.equals(element.getDataUrodzenia(), request.getDataUrodzenia()))
                    {
                czyIstnieje = true;
            }
            }
        if(czyIstnieje) {
            throw new RuntimeException("Taka osoba już istnieje");
        }
    }

    public PersonDTO getPerson(final PersonRequest request) {
        for (Person element: personList) {
            if (element.getImie().equals(request.getImie())
            && element.getNazwisko().equals(request.getNazwisko())
            && element.getDataUrodzenia().equals(request.getDataUrodzenia())) {
                return PersonDTO.builder()
                        .imie(element.getImie())
                        .nazwisko(element.getNazwisko())
                        .dataUrodzenia(element.getDataUrodzenia())
                        .miasto(element.getMiasto())
                        .plec(element.getPlec())
                        .build();
            }
        }
        return PersonDTO.builder().build();
    }


    public PersonDTO getPersonParams(final String imie, final String nazwisko) {
        for (Person element: personList) {
            if (element.getImie().equals(imie)
                    && element.getNazwisko().equals(nazwisko)) {
                return PersonDTO.builder()
                        .imie(element.getImie())
                        .nazwisko(element.getNazwisko())
                        .dataUrodzenia(element.getDataUrodzenia())
                        .miasto(element.getMiasto())
                        .plec(element.getPlec())
                        .build();
            }
        }
        return PersonDTO.builder().build();
    }

    public void updatePerson(final PersonRequest request) {
        for (Person element : personList) {
            if (element.getImie().equals(request.getImie())
                    && element.getNazwisko().equals(request.getNazwisko())
                    && element.getDataUrodzenia().equals(request.getDataUrodzenia())) {
                element.setMiasto(request.getMiasto());
            }
        }
    }
}