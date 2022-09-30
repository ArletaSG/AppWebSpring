package pl.cyber.trainees.demo.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.cyber.trainees.demo.dto.BankomatDTO;
import pl.cyber.trainees.demo.service.BankomatService;

import java.util.List;

@RestController
@RequiredArgsConstructor //lub@AllArgs Constructor --> tworzy konstruktory do wszystkich parametrów
// @Required tworzy konstruktory tylko do parametrów final
@RequestMapping("/v1/atms") //w liczbie mnogiej atms-bankomaty
public class BankomatController {

    private final BankomatService bankomatService;

    @GetMapping //pierwsza końcówka restowa nie musi mieć nic w nawiasie, druga juz musi mieć ("/get")
    public List<BankomatDTO> getAllAtms() {
       return bankomatService.getAllAtms();
    }
    //metoda na wpisywanie
    @PutMapping
    public void create(@RequestBody final BankomatDTO bankomatDTO) {
        bankomatService.create(bankomatDTO);
    }

    //aktualizacja danych, zmiana pieniędzy w bankomacie lub miejsca bankomatu

    @PostMapping("/name")
    public void updateName(@RequestParam("name") final String name,
                           @RequestParam("id") final String id) { //można tu podac oldname
        bankomatService.updateName(id, name);
    }
    //odczytywanie plików
    //MultipartFile
    @PostMapping("/import-csv")
    public void createFromCSV(
            @RequestPart()MultipartFile file) {
        bankomatService.create(file); //przeciążenie metod (taka sama nazwa, inny parametr i inne zwracanie)
        //create(final BankomatDTO bankomatDTO)
        // i create ta z file

    }



}
