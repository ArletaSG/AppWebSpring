package pl.cyber.trainees.demo.service;


import org.springframework.stereotype.Service;
import pl.cyber.trainees.demo.dto.IntegerListRequest;
import pl.cyber.trainees.demo.dto.LiteryDTO;
import pl.cyber.trainees.demo.dto.OnetStringRequest;
import pl.cyber.trainees.demo.dto.StringRequest;

import java.util.*;

@Service

public class BasicsService {

    private static String SPACJA = " "; //nie stosujemy konstruktora!

    public String getSklejenie(final StringRequest request) {
        return request.getStringPierwszy() + request.getStringDrugi(); //druga wersja
    }

    //druga wersja :

    //var string = new StringBuilder();
    //return string.append(request.getStringPierwszy())
    //(gdy ze spacja: )    .append(SPACJA)
    //             .append(request.getStringPierwszy())
    //             .toString();

    public List<String> getWystapieniaLiterWZadaniu(final OnetStringRequest request) {
        /*List<LiteryDTO> wystąpienia
Set<String>
List<String> wynik
*/
        List<LiteryDTO> wystapienia = new ArrayList<>();
        Set<String> litery = new HashSet<>();
        List<String> wynik = new ArrayList<>();

        String zdanie = request.getValue();

        for (int i = 0; i < zdanie.length(); i++) {
            String litera = String.valueOf(zdanie.charAt(i));

            if (litera.matches("[a-zA-Z]+")) //+ do regex
                if (wystapienia.size() == 0) {
                    litery.add(litera.toLowerCase());
                    wystapienia.add(LiteryDTO.builder() // lub poprzez konstruktor wystapienia.add(new LiteryDTO(litera.toLowerCase(), ilosc:1));
                            .litera(litera.toLowerCase())
                            .ilosc(1)
                            .build());              // lepiej z builder:)
                } else {
                    if (litery.contains(litera.toLowerCase())) {
                        for (LiteryDTO element :
                                wystapienia) {
                            if (element.getLitera().equals(litera.toLowerCase())) {
                                element.setIlosc(element.getIlosc() + 1);
                            }
                        }
                    } else {
                        litery.add(litera.toLowerCase());
                        wystapienia.add(LiteryDTO.builder()
                                .litera(litera.toLowerCase())
                                .ilosc(1)
                                .build());
                    }
                }
        }
        for (LiteryDTO element : wystapienia) {
            wynik.add((element.getLitera() + "-" + element.getIlosc()));
        }
        wynik.sort((String::compareTo)); // zmienna określająca liste Stringów jako wynik, który zostaie wzrócony do użytkownika
        //sort to metoda, która odpowiada za sortowanie zgodne z kluczem wskazanej listy (wynik)
        //String::compareTo to kluzcz sortowania, po którym nasza lista zostanie posortowana alfabetycznie w sposób taki,
        //że zostanie porównany element n z elementem n+1, a następnie zostanie zamieniony zgodnie z wystąpieniem w alfabecie

        return wynik;
    }

    //.................WERSJA 2 z MAP ...............................................

    public List<String> getWystapieniaLiterWZdaniuMap(final OnetStringRequest request) { //Map(Key, Value>)
        Map<String, Integer> wystapienia = new HashMap<>();
        List<String> wynik = new ArrayList<>();

        String zdanie = request.getValue().toLowerCase();

        for (int i = 0; i < zdanie.length(); i++) {
            String litera = String.valueOf(zdanie.charAt(i));

            if (litera.matches("[a-zA-Z]+")) {
                if (wystapienia.containsKey(litera)) {
                    wystapienia.put(litera, wystapienia.get(litera) + 1);
                } else {
                    wystapienia.put(litera, 1);
                }
            }

        }
        for (String element :
                wystapienia.keySet()) {
            wynik.add(element + " - " + wystapienia.get(element));

        }
        return wynik;
    }

    //............................zad.7............................
    public String sumaLiczbPomiedzy(final Integer a, final Integer b) {
        Integer wynik = 0;
        if (a > b) {
            throw new RuntimeException("Wartośc parametrtu a powinna być mniejsza od b");
        }
        if(a==b) {
            wynik = a+b;
        }
        for (int i = a; i <= b; i++) {
            wynik += i;
        }
        return "Wynik dodawania liczb pomiędzy a: " + a + " oraz b: " + b + " to " + wynik;
    }

    //............................zad.8............................
    public String zadanie8(final IntegerListRequest request) {
        List<Integer> listaujemnych = new ArrayList<>();
        Integer sumaDodatnich = 0;

        for (Integer element :
                request.getIntList()) {
            if (element < 0) {
                listaujemnych.add(element);
            } else {
                sumaDodatnich += element;
            }
        }
        return listaujemnych + " oraz suma liczb dodatnich wynosi:  " + sumaDodatnich;




    }
}
