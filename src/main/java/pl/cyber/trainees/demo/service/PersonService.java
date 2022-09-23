package pl.cyber.trainees.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cyber.trainees.demo.dto.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service // Framework Spring wie, że może ją używać i dac do defaultowego konstruktora, powołać go do życia

@RequiredArgsConstructor
public class PersonService {

    private List<Person> personList = new ArrayList<>();

    public void createPerson(final PersonRequest request) {
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

    private void walidujOsobę(final PersonRequest request) {
        boolean czyIstnieje = false;
        for (Person element : personList) {
            if (element.getImie().equals(request.getImie()) //albo tak
                    && Objects.equals(element.getNazwisko(), request.getNazwisko()) //albo z klasą Objects (ma metody isNull, nonNull, przydatne)
                    && Objects.equals(element.getDataUrodzenia(), request.getDataUrodzenia())) {
                czyIstnieje = true;
            }
        }
        if (czyIstnieje) {
            throw new RuntimeException("Taka osoba już istnieje");
        }
    }

    public PersonDTO getPerson(final PersonRequest request) {
        for (Person element : personList) {
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
        for (Person element : personList) {
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
    /*----------------------------zad12-------------------------------------------------------------------
          Wykonaj createPerson z PersonController aby utworzyć 6 różnych osób (przy użyciu Postman), następnie
          napisz program, który z kolekcji typu List posiadającej  6 różnych osób(w oparciu o poprzednie zadanie)
          posortuje ich względem wskazanego klucza (np. miasta, datyUrodzenia, nazwiska lub imienia).
          Po czym zostanie zwrócona posortowana lista do użytkownika.
        */
    public List<PersonDTO> zadanie12(final KluczSortowaniaEnum klucz) {
        //utworzenie listy pomocniczej, która będzie wynikową
        List<PersonDTO> resultPersonList = new ArrayList<>();

        //przepisanie wartości tych utworzonych z Person DTO for each'em na liście pomocniczej
        for (Person element :
                personList) {
            resultPersonList.add(PersonDTO.builder()
                    .imie(element.getImie())
                    .nazwisko(element.getNazwisko())
                    .miasto(element.getMiasto())
                    .dataUrodzenia(element.getDataUrodzenia())
                    .plec(element.getPlec())
                    .build());

        }
        //if/else-if//else

        if (klucz.equals(KluczSortowaniaEnum.DATA)) {
            resultPersonList.sort(Comparator.comparing(PersonDTO::getDataUrodzenia));
        //alternatywa:
        // resultPersonList.sort(Comparator.comparing(PersonDTO::getDataUrodzenia));
    } else if(klucz.equals(KluczSortowaniaEnum.IMIE)) {
        resultPersonList.sort(Comparator.comparing(PersonDTO::getImie));
    } else if(klucz.equals(KluczSortowaniaEnum.NAZWISKO)){
        resultPersonList.sort(Comparator.comparing(PersonDTO::getNazwisko));
    } else if (klucz.equals(KluczSortowaniaEnum.MIASTO)) {
            resultPersonList.sort(Comparator.comparing(PersonDTO::getMiasto));
        }
            //switch (na 2 sposoby - po staremu i po nowemu)



return resultPersonList;
}

    public List<PersonDTO> zadanie12a(final KluczSortowaniaEnum klucz) { //ze switchem

        List<PersonDTO> resultPersonList = new ArrayList<>();

        for (Person element :
                personList) {
            resultPersonList.add(PersonDTO.builder()
                    .imie(element.getImie())
                    .nazwisko(element.getNazwisko())
                    .miasto(element.getMiasto())
                    .dataUrodzenia(element.getDataUrodzenia())
                    .plec(element.getPlec())
                    .build());

        }

        //switch (na 2 sposoby - po staremu)

        switch (klucz) {
            case DATA:
                resultPersonList.sort(Comparator.comparing(PersonDTO::getDataUrodzenia));
                break;
            case IMIE:
                resultPersonList.sort(Comparator.comparing(PersonDTO::getImie));
                break;
            case NAZWISKO:
                resultPersonList.sort(Comparator.comparing(PersonDTO::getNazwisko));
                break;
            case MIASTO:
                resultPersonList.sort(Comparator.comparing(PersonDTO::getMiasto));
                break;
        } //default niepotrzebny



        return resultPersonList;
    }

    public List<PersonDTO> zadanie12b(final KluczSortowaniaEnum klucz) { //ze switchem

        List<PersonDTO> resultPersonList = new ArrayList<>();

        for (Person element :
                personList) {
            resultPersonList.add(PersonDTO.builder()
                    .imie(element.getImie())
                    .nazwisko(element.getNazwisko())
                    .miasto(element.getMiasto())
                    .dataUrodzenia(element.getDataUrodzenia())
                    .plec(element.getPlec())
                    .build());

        }

        //switch (na 2 sposoby - po nowemu, potrafi zwracać wartość!!!) przy switch ALT+Enter i tam pierwsza opcja

        switch (klucz) {
            case DATA -> resultPersonList.sort(Comparator.comparing(PersonDTO::getDataUrodzenia));
            case IMIE -> resultPersonList.sort(Comparator.comparing(PersonDTO::getImie));
            case NAZWISKO -> resultPersonList.sort(Comparator.comparing(PersonDTO::getNazwisko));
            case MIASTO -> resultPersonList.sort(Comparator.comparing(PersonDTO::getMiasto));
        } //default też niepotrzebny


        return resultPersonList;
    }


}