package pl.cyber.trainees.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pl.cyber.trainees.demo.dto.RownanieKwRequest;

import java.text.DecimalFormat;
import java.util.Locale;

@Service
//@Component
public class KalkulatorService {
    //metoda dodawania
    public Integer getDodawanie(final Integer a, final Integer b) {
        return a + b;
    }

    public Integer getDodawanieParams(final Integer a, final Integer b) {
        return a + b;
    }

    //metoda odejmowania
    public Integer getOdejmowanie(final Integer a, final Integer b) {
        return a - b;
    }

    //metoda mnożenia
    public Integer getMnozenie(final Integer a, final Integer b) {
        return a * b;
    }

    //metoda dzielenia
    public Integer getDzielenie(final Integer a, final Integer b) {
        if (b != 0)
            return a / b;
        else
            throw new RuntimeException("Nie dzielimy przez 0");
        /*
        if (b==0) {
        throw new RuntimeException("Nie dziel przez 0!);
        }
        return a/b;
         */
    }

    // metoda obl. reszty z dzielenia liczb
    public Integer getresztaZDzielenia(final Integer a, final Integer b) {
        if (b != 0)
            return a % b;
        else
            throw new RuntimeException("Nie dzielimy przez 0");
    }


    /*
   Zad.2
   Napisz zapytanie restowe, którego zadaniem będzie wykonanie sprawdzenia czy przekazana liczba jest
   liczba pierwszą
    */
    public boolean czyLiczbaPierwszaMoja(final Integer a) {
        if (a < 2) {
            return false;
        }
        for (int i = 2; i <= a / 2; i++) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }

    public String czyLiczbaPierwszaPana(final Integer a) {
        if (a < 2) {
            return "To nie jest liczba pierwsza";
        }
        for (int i = 2; i <= a / 2; i++) //opcje różne:    for(int i=2; i<a; i++)
        {                                         //for(int i=2 ; i*i<a; i++)
            if (a % i == 0) {
                return "To nie jest liczba pierwsza";
            }
        }
        return "To jest liczba pierwsza";
    }

//region Zadanie3
/*Napisz zapytanie restowe, którego zadaniem będzie wykonanie sklejenia dwóch
Stringów (przekazanych jako RequestBody) a następnie zwróci wynik. Jak zapytanie RequestBOdy to POST lub PUT*/

//Zadanie5
///*
//Napisz zapytanie restowe, którego zadaniem będzie przekazanie liczb a i b (całkowite),
//następnie wykona sprawdzenie czy liczba a jest dzielnikiem liczby b i zwróci
//informację true lub false
//*/

    public boolean czyJestDzielnikiem(final Integer a, final Integer b) {
        Integer czyDzielnik;
 /*      if (b !=0) {
           return a % b == 0;
       }
       else {
           throw new RuntimeException("Nie dzielimy przez 0");
       }

  */
//albo krócej:
        if (b != 0) {
            return a % b == 0;
        } else {
            throw new RuntimeException("Nie dzielimy przez 0");
        }
    }

    public boolean getCzyJestDzielnikiem(final Integer a, final Integer b) {
        if (b == 0) {
            throw new RuntimeException("Nie wolno dzielić");

        }
        return a % b == 0;
    }


    public String rownanieKwadratowe(final Integer a, final Integer b, final Integer c) {
        Double delta = 0.0;
        Double x1 = 0.0;
        Double x2 = 0.0;

        if (a == 0) {
            return "To nie jest równanie kwadratowe";

        }
        delta = Double.valueOf(b * b - 4 * a * c);
        if (delta < 0) {
            return "Brak rozwiązań";
        } else {
            if (delta == 0) {
                x1 = Double.valueOf(-b / (2 * a));
                return ("Jedno rozwiązanie: " + x1);
            } else {
                x1 = (-b - Math.sqrt(delta)) / (2 * a);
                x2 = (-b + Math.sqrt(delta)) / (2 * a);
                return "są dwa rozwiązania: x1 to " + x1 + "i x2 to " + x2;
            }

        }
    }

    public String rownanieKwadratowe(final RownanieKwRequest request) {
        Double delta = 0.0;
        Double x1 = 0.0;
        Double x2 = 0.0;
        Double a = request.getA();
        Double b = request.getB();
        Double c = request.getC();

        delta = b * b - 4 * a * c;

        if (delta == 0) {
            x1 = -b / (2 * a);
            return "Jeden pierwiastek. Wynik x1 to " + x1;

        } else if (delta < 0) {
            return "Brak rozw";
        } else {
            x1 = (-b - Math.sqrt(delta)) / (2 * a);
            x2 = (-b + Math.sqrt(delta)) / (2 * a);
            return "są dwa rozwiązania: x1 to " + x1 + "i x2 to " + x2;
        }
    }

    public String rownanieKwadratowe2miejsca(final RownanieKwRequest request) { //ZAOKRĄGLANIE!!!!
        Locale englishLocale = Locale.ENGLISH;
        Locale polishLocale = Locale.forLanguageTag("pl-PL");
        Locale.setDefault(polishLocale);

        DecimalFormat df = new DecimalFormat("#,###.00");
        /*
    1.10
    #,###.00 >  1,10

    1101.10
    #,###.00 >  1 101,10 ze spacją

    1.10
    #,###.0# >  1,10
    1.1
    #,###.0# >  1,1

    1.1131421125
    #,###.0# >  1,11

    1.1161421125
    #,###.0# >  1,12

    1.1161421125
    #,###.0## >  1,116

    1101.10
    #.# > 1101,1 (bez spacji! przy tys)


    1101.1
    #.0000 > 1101,1000 (bez spacji! przy tys)
     */


        Double delta = 0.0;
        Double x1 = 0.0;
        Double x2 = 0.0;
        Double a = request.getA();
        Double b = request.getB();
        Double c = request.getC();

        delta = b * b - 4 * a * c;

        if (delta == 0) {
            x1 = -b / (2 * a);
            return "Jeden pierwiastek. Wynik x1 to " + df.format(x1);

        } else if (delta < 0) {
            return "Brak rozw";
        } else {
            x1 = (-b - Math.sqrt(delta)) / (2 * a);
            x2 = (-b + Math.sqrt(delta)) / (2 * a);
            return "są dwa rozwiązania: x1 to " + df.format(x1) + "i x2 to " + df.format(x2);
        }
    }

    public String zadanie10a() {
        Integer y = 0;                                                   //  \n nowa linia      \t tabulator
        StringBuilder result = new StringBuilder("Program oblicza wartość funkcji y=3x, dla x zmieniającego się od 0 do 10. \n");

        for (int x = 0; x <= 10; x++) {
            y = 3 * x;
            result.append("x = ").append(x).append("\t").append("y = ").append(y).append("\n");
            //   result +="x = " + x + "\t" + "y = " + y + "\n";        //powyżej wg String Buildera
            //tworzymy parametr typu StringBuilder, to jest bufor, dajemy wartości, jest konwertowany do Stringa
            //napis, wartość, \t itd. konstrukcja StringBuildera jest bardziej czytelna
        }

        return result.toString();
    }

    public String zadanie10b() {
        Integer y = 0;
        Integer x = 0;
        String result = "Program obl wart funcki y=3x, dla x zmieniającego się od 0 do 10." +
                "Za pomocą pętli do...while \n";
        do {
            y = 3 * x;
            result += "x = " + x + "\t" + "y = " + y + "\n";
            x++;

        } while (x <= 10);

        return result;
    }

    public String zadanie10c() {
        Integer y = 0;
        Integer x = 0;

        String result = "Program oblicza wart.y=3x, dla x od 1..10. \n" +
                "Przy pomocy while\n";

        while (x <= 10) {
            y = 3 * x;
            result += "x = " + x + "\t" + "y = " + y + "\n";
            x++;
        }

        return result;
    }

    public String zadanie11a() {

        Integer n = 10;
        String result = "Program obl tabl mnożenia 1..10 przy pomocy dwóch pętli for" + "\n\n";

        for (int wiersz = 1; wiersz <= n; wiersz++) {

            for (int kolumna = 1; kolumna <= n; kolumna++) {

                //  Integer mn = x*y; //czasami trzeba będzie dac nowy parametr, bo nie wykona np. mnożenia

                result += wiersz * kolumna;
                result += "\t";//kontatenacja stringów
            }
            result += "\n";
        }
        return result;
    }


    public String zadanie11aa() { //można dodać parametry w i k w ()
        Integer w = 20;
        Integer k = 15;

        String result = "Program obl tabl mnożenia 1..10 przy pomocy dwóch pętli for" + "\n\n";

        for (int wiersz = 1; wiersz <= w; wiersz++) {

            for (int kolumna = 1; kolumna <= k; kolumna++) {

                //  Integer mn = x*y; //czasami trzeba będzie dac nowy parametr, bo nie wykona np. mnożenia

                result += wiersz * kolumna;
                result += "\t";//kontatenacja stringów
            }
            result += "\n";
        }
        return result;
    }

    //zad 11                                             z do..while
    public String zadanie11b() {
        Integer k = 10;
        Integer w = 10;
        Integer wiersz = 1;
        Integer kolumna = 1;

        String result = "Program obl tabl mnożenia 1..10 przy pomocy pętli do..while \n\n";

        do {
            kolumna = 1;

            do {
                result += wiersz * kolumna + "\t";
                kolumna += 1;
            } while (kolumna <= k);
            result += "\n";
            wiersz += 1;
        } while (wiersz <= w);

        return result;
    }


    public String zadanie11c() { //tabl mnożenie przy samym while
        String result = "Program tabl mnoż przy pomocy while\n\n";
        Integer k = 10;
        Integer w = 10;
        Integer wiersz = 1;
        Integer kolumna = 1;
        while (wiersz<=w) {
            kolumna = 1;
            while (kolumna <= k) {
                result += wiersz * kolumna + "\t";
                kolumna += 1;
            }
            result += "\n";
            wiersz += 1;
        }


//            result += wiersz * kolumna + "\t";
//            kolumna += 1;
//            while(kolumna<=k){
//                result += "\n";
//                wiersz += 1;
//            }



        return result;

    }
}





