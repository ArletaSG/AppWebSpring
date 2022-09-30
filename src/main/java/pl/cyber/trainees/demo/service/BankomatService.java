package pl.cyber.trainees.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.cyber.trainees.demo.domain.BankomatEntry;
import pl.cyber.trainees.demo.dto.BankomatDTO;
import pl.cyber.trainees.demo.repository.BankomatRepository;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@RequiredArgsConstructor
@Service
@Slf4j //odpowiada za użycie log
public class BankomatService {
    private final BankomatRepository bankomatRepository;
    private final FileReaderService fileReaderService;


    public List<BankomatDTO> getAllAtms() {
        /*
        1. Połączenie do b.d. oraz pobranie odpowiednich inf
        2. Przygotowanie listy wynikowej
        3. Pętla konwertująca Obiekt B.D. na obiekt dla użytkownika
        kometarz po zrobieniu nie może być dostępny do wiedzy innych deweloperów w grupie
        usunąć go przed zakomitowaniem
         */
        log.info("Wyszukanie wszystkich bankomatów");
        log.warn("Coś poszło nie tak!!! :)"); // używa się jak parametr jest pusty lub walidacja zła
        log.error("Rest comunication failed!!!"); //łaczenie rest z CIDG, do pobrania konkretnych inf, jak nie działa lub błędy to wtedy ten error

        var allAtms = bankomatRepository.findAll(); //ad.1
        List<BankomatDTO> result = new ArrayList<>(); //ad.2
        for (BankomatEntry entry : allAtms) {
            result.add(entry.convertToDTO());

        }
        return result;
    }

    public void create(final BankomatDTO bankomatDTO) {
        bankomatRepository.save(BankomatEntry.builder() //id nie trzeba samo się zrobi
                        .miasto(bankomatDTO.getMiasto())
                        .czyAktywny(bankomatDTO.getCzyAktywny())
                        .name(bankomatDTO.getName())
                        .saldo(bankomatDTO.getSaldo())
                        .ulica(bankomatDTO.getUlica())
                .build());
    }
    public void create(final MultipartFile file) {

    }
@Transactional
    public void updateName(final String id, final String name) {
        /* I metoda:
        var allAtms = bankomatRepository.findAll(); //niekorzystne, ze wzgl na ilość bankomatów
        for (BankomatEntry entry:
             allAtms)   {
            if (entry.getId().equals(id)) { //dla obiektów własnych lub Integer equals hashcode
                entry.setName(name);
                bankomatRepository.save(entry);
            }

        }*/
       // var atm = bankomatRepository.findById(id).orElseThrow(() -> new RuntimeException("Brak rekordu")); //Optional - byt pod abstrakcje, jak wartość null to nie wywali app
        //II metoda: na pozbycie sie Optional to po findById(id).orElseThrow(()-> new RuntimeException("Brak rekordu!));
        //III metoda:
        /*bankomatRepository.findById(id) //metoda isPresent nie zwraca wartości czyli może być bez var
                .ifPresent(entry -> {
                    entry.setName(name);
                    bankomatRepository.save(entry);
                });

         */
         /* IV metoda*/
         bankomatRepository.findById(id)

                .ifPresentOrElse(entry -> {
                    entry.setName(name);
                    bankomatRepository.save(entry);
                },
                        () -> {
                    throw new RuntimeException("Brak rekordu!");
                        });
        /* V metoda
        var atm = bankomatRepository.findById(id).orElse(null);
        if(Objects.nonNull(atm)) {
            atm.setName(name);
            bankomatRepository.save(atm);
        }
        */
        //  bankomatRepository.updateName(id, name); //potrzeba transakcji, zawarte w 1 procesie
    }


}
