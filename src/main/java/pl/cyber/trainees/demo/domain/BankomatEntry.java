package pl.cyber.trainees.demo.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import pl.cyber.trainees.demo.dto.BankomatDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BANKOMAT")
public class BankomatEntry {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", updatable = false, nullable = false)
    private String id;

    @Setter
    @Column(name = "NAME")
    private String name;

    @Column(name ="SALDO")
    private BigDecimal saldo;

    @Column(name = "MIASTO")
    private String miasto;

    @Column(name = "ULICA")
    private String ulica;

    @Column(name = "CZY_AKTYWNY")
    private Boolean czyAktywny;


    public BankomatDTO convertToDTO() { //ad.3

        return BankomatDTO.builder()
                .id(this.id)
                .name(this.name)
                .saldo(this.saldo)
                .miasto(this.miasto)
                .ulica(this.ulica)
                .czyAktywny(this.czyAktywny)
                .build();
    }

}
