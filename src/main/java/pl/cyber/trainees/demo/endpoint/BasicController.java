package pl.cyber.trainees.demo.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.cyber.trainees.demo.dto.IntegerListRequest;
import pl.cyber.trainees.demo.dto.OnetStringRequest;
import pl.cyber.trainees.demo.dto.RownanieKwRequest;
import pl.cyber.trainees.demo.dto.StringRequest;
import pl.cyber.trainees.demo.service.KalkulatorService;
import pl.cyber.trainees.demo.service.ZnajdzService;

import java.util.List;

@RestController
@RequestMapping("/v1/basics")
@RequiredArgsConstructor
public class BasicController {
    private final KalkulatorService kalkulatorService; //wywołanie klasy typu service z final

    private final BasicsService basicsService;
    private final ZnajdzService znajdzService;

    /*
    Zad.1
    Napisać zapytania dla prostego kalkulatora, który będzie obsługiwać pięć operacji
    (każdna z nich powinna być oddzielnym zapytaniem restowym)
    - dodawanie
    - odejmowanie
    - mnożenie
    - dzielenie
    - reszta z dzielenia
    Zwrócenie wyniku naszych operacji
    @PathVariable - podajemy dwie zmienne
    Np.
    link dodawanie czyli: "/dodawanie/{zmienne}
     */
    @GetMapping("/dodawanie/{a}/{b}")
    public Integer getDodawanie(@PathVariable("a") final Integer liczbaA, @PathVariable("b") final Integer liczbaB) {

        return kalkulatorService.getDodawanie(liczbaA, liczbaB);
    }

    @GetMapping("/dodawanieParams")
    public Integer getDodawanieParams(@RequestParam("a") final Integer liczbaA, @RequestParam("b") final Integer liczbaB) {

        return kalkulatorService.getDodawanie(liczbaA, liczbaB);
    }

    @GetMapping("/odejmowanie/{a}/{b}")
    public Integer getOdejmowanie(@PathVariable("a") final Integer liczbaA, @PathVariable("b") final Integer liczbaB) {
        return kalkulatorService.getOdejmowanie(liczbaA, liczbaB);
    }

    @GetMapping("/mnozenie/{a}/{b}")
    public Integer getMnozenie(@PathVariable("a") final Integer liczbaA, @PathVariable("b") final Integer liczbaB) {
        return kalkulatorService.getMnozenie(liczbaA, liczbaB);
    }

    @GetMapping("/dzielenie/{a}/{b}")
    public Integer getDzielenie(@PathVariable("a") final Integer liczbaA, @PathVariable("b") final Integer liczbaB) {
        return kalkulatorService.getDzielenie(liczbaA, liczbaB);
    }

    @GetMapping("/resztaZDzielenia/{a}/{b}")
    public Integer getresztaZDzielenia(@PathVariable("a") final Integer liczbaA, @PathVariable("b") final Integer liczbaB) {
        return kalkulatorService.getresztaZDzielenia(liczbaA, liczbaB);
    }

    /*
    Zad.2
    Napisz zapytanie restowe, którego zadaniem będzie wykonanie sprawdzenia czy przekazana liczba jest
    liczba pierwszą
     */
    @GetMapping("/liczba-pierwszaMoja/{a}")
    public boolean czyLiczbaPierwszaMoja(@RequestParam("a") final Integer liczbaA) {
        return kalkulatorService.czyLiczbaPierwszaMoja(liczbaA);
    }

    @GetMapping("/liczba-pierwszaPana/{a}")
    public String czyLiczbaPierwszaPana(@PathVariable("a") final Integer liczbaA) {
        return kalkulatorService.czyLiczbaPierwszaPana(liczbaA);
    }

    //region Zadanie3
/*Napisz zapytanie restowe, którego zadaniem będzie wykonanie sklejenia dwóch
Stringów (przekazanych jako RequestBody) a następnie zwróci wynik. Jak zapytanie RequestBOdy to POST lub PUT*/

    @PostMapping("/sklejenie-stringow") //RequestBody
    public String getSklejenie(
            @RequestBody final StringRequest request //bez nawiasów, musi być klasa DTO - tworzymy nowy w DTO

    ) {
        return basicsService.getSklejenie(request); //dodajemy do góry BasicsService basicsService
    }

    /* Zadanie 4
    /*Napisz zapytanie restowe, którego zadaniem będzie przyjęcie napisu  jako zdania
(przekazanych jako RequestBody)
 Program powinien policzyć ilość wystąpień poszczególnych liter zdania i zwrócić
 odpowiednio przygotowane dane.
Uwaga należy pominąć litery, których w zdaniu nie ma oraz wszystkie znaki puste.

Przykład.
Ala ma kota

a - 4
k - 1
l - 1
m - 1
o - 1
t - 1
@POSTMapping
@RequestBody
...............................................ROZW.1
List<LiteryDTO> wystąpienia
Set<String>
List<String> wynik

pętla for przejście po każdym znaku zdania
if jeśli znak zdania jest literą, to należy wykonać dodawanie lub aktualizację naszej listy

Obiekt String posiada metodę matches (//regexp//) .matches("[a-zA-z]+
Obiekt List posiada metodę sort (//Comparator//) wynik.sort(String::compareTo)

     */

    @PostMapping("/zliczanie")
    public List<String> getWystapieniaLiteryWZadaniu(
            @RequestBody final OnetStringRequest request
    ) {
//return basicsService.getWystapieniaLiterWZadaniu(request);
        //.................WERSJA 2 z MAP zad.4...............................................
        return basicsService.getWystapieniaLiterWZdaniuMap(request);


    }

    //Zadanie5
/*
Napisz zapytanie restowe, którego zadaniem będzie przekazanie liczb a i b (całkowite),
następnie wykona sprawdzenie czy liczba a jest dzielnikiem liczby b i zwróci
informację true lub false
*/
    @PostMapping("/czyDzielnikiem/{a}/{b}") //moje
    public boolean getDzielnikiem(@PathVariable("a") final Integer liczbaA, @PathVariable("b") final Integer liczbaB) {
    return  kalkulatorService.czyJestDzielnikiem(liczbaA, liczbaB);
    }

    @GetMapping("/czy-jest-dzielnikiem/{a}/{b}") //Pana
    public boolean getCzyJestDzielnikiem (
            @PathVariable("a") final Integer a,
            @PathVariable("b") final Integer b) {
        return kalkulatorService.getCzyJestDzielnikiem(a, b);
    }
    //zadanie 6
/*
Napisz program, który wygeneruje liczbę Random z przedziału od 10 - 1000.
Naszym zadaniem będzie odnalezienie wygenerowanej liczby.
W tym celu należy utworzyć zapytanie restowe, które będzie przyjmowało liczbę
i porównywało ją z wygenerowaną przez system.
Jeśli wprowadzona liczba będzie tą wygenerowaną zostanie zwrócony napis "Udało się!!"
Jeśli wprowadzona liczba będzie mniejsza od wygenerowanej zostanie zwrócony napis
"Wygenerowana liczba jest większa"
Jeśli wprowadzona liczba będzie większa od wygenerowanej zostanie zwrócony napis
"Wygenerowana liczba jest mniejsza"

Uwaga aby generowana liczba powinna być parametrem klasy aby przy każdym zapytaniu
restowym nie doszło do jej modyfikacji.
*/
    @GetMapping("porownanie-liczb/{a}")

    public String getPorowanie (@PathVariable("a") final Integer a) {

                return  znajdzService.getPorowanie(a);
    }


// porównywaie stringów
// wersja 1:
// String string1 = "";
  //  String string2 = "asd";
    //if(string1.equals(string2)) {
      //  return string1;
    //wersja 2:
    //if StringUtils.equals albo contains
    //www mvnrepository.com
    //wpisać apache commons
    //klasa util apache commons util



    //...............................zadanie 7..........................................
     /*
  Napisz program, w którym zostaną przekazane liczby a i b (całkowite) następnie
  zostaną zsumowane wszystkie liczby pomiędzy od a do b
  (jako przedział zamknięty dwustronnie).
  Przykład podajemy: 1 do 10 czego wynikiem będzie 55
  */
//korzystamy z GET PathVariable lub RequestParam
    //lub POST nowy RequestBody
//basicsService
@GetMapping("/suma-liczb")
    public String sumaLiczbPomiedzy(
            @RequestParam("a")final Integer a,
            @RequestParam("b")final Integer b)
{
    return basicsService.sumaLiczbPomiedzy(a, b);
}
//endregion

//region .............................Zadanie 8............................................
    //korzystamy z POST RequestBody
   // klasa DTO o nazwie IntegerListRequest >> w środku list<Integer>
    //JSON
//{
// "lista": [1, 2, 3, 4, 5, 6, 7, 8]
// }
 //   w środku if do sprawdzenia czy liczba z listy jest ujemna czy dodatnia i albo suma liczb dodatnich albo zapis do listy ujemnej (można posortowac list --> sort)
  //dwie zmienne--> lista licz ujemnych i suma dodatnich
    /*Napisz program, w krótym przekażemy listę elementów liczb całkowitych program
  powinien zwrócić listę elementów z wartościami ujemnymi oraz sumę liczb,
  które są dodatnie.
  Np. [1, 2, 3, 4, 5, -3, -2, -1]
  wynik:
  [-3, -2, -1] oraz suma liczb dodatnich wynosi: 15 (w postaci Stringa)

  // {
  // "lista": [1, 2, 3, 4, 5, 6, 7, 8]
  // }
  */

// zad.7 i 8 w klasie basicsService
//endregion

    @PostMapping("/liczby")
    public String zadanie8 (
            @RequestBody final IntegerListRequest request
    ) {
        return basicsService.zadanie8(request);
    }//mógłby być obiekt lista i suma elementów dodatnich
//region.............................. zadanie ....9......................................
    /*
    Napisz zapytanie restowe, którego zadaniem będzie obliczał pierwiastek
    równania kwadratowego ax2 + bx + c = 0.
    (Do wykorzystania instrukcja if). Pamiętać należy że zmienne a, b i c to
    liczby rzeczywiste(NIE jako Path Variable!) może być na Integer.
    Zadanie powinno zwrócić Napis:
    a) To nie jest równanie kwadratowe
    b) Brak pierwiastków
    c) Jeden pierwiastek. Wynik: xxxx
    d) Dwa pierwiastki. Wynik -> x1: xxxx, x2: xxxx
     */

    // w klasie KalkulatorService
    //GET, w linku trzy parametry PathVariable
    //GET z RequestParam
    // lub POST nowy RequestBody
    //endregion

    @GetMapping("/rownanie-kwadratowe/{a}/{b}/{c}")
    //zmiennoprzecinkowe Double, ale wtedy RequestBody (nigdy nie
    //PathVariable czy RequestParam!!!)
    public String rownanieKwadratowe(
            @PathVariable("a") final Integer a,
    @PathVariable("b") final Integer b,
    @PathVariable("c") final Integer c
        ) {
    return kalkulatorService.rownanieKwadratowe(a, b, c);
    }

    @PostMapping("/rownanie-kwadratowe-b")
    //zmiennoprzecinkowe Double, ale wtedy RequestBody (nigdy nie
    //PathVariable czy RequestParam!!!)
    public String rownanieKwadratowe(
            @RequestBody final RownanieKwRequest request
    ) {
        return kalkulatorService.rownanieKwadratowe(request);
    }

    @PostMapping("/rownanie-kwadratowe-b-2miejsca")
    //zmiennoprzecinkowe Double, ale wtedy RequestBody (nigdy nie
    //PathVariable czy RequestParam!!!)
    public String rownanieKwadratowe2miejsca(
            @RequestBody final RownanieKwRequest request
    ) {
        return kalkulatorService.rownanieKwadratowe2miejsca(request);
    }



}




