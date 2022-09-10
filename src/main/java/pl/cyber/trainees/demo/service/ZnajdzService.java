package pl.cyber.trainees.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Slf4j
@Service
public class ZnajdzService {
    private Integer losowaLiczba = 0;


    public ZnajdzService() {
        //użycie randomowej funkcji
        Random r = new Random();
        this.losowaLiczba = r.nextInt(990) +10;

        log.info("Wylosowana liczba to: " + this.losowaLiczba.toString());

    }


    public String getPorowanie(final Integer a) {
        if (Objects.equals(a, losowaLiczba)) { // obiektowe porównanie, NIE ROBIMY ==, powinny być metody equals i hashcode
            return ("Udało się!");
        } else
            if (a<losowaLiczba){
                return ("Wygenerowana liczba jest większa");

        } else {
            return ("Wygenerowana liczba jest mniejsza");
        }
    }
}
