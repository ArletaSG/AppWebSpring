package pl.cyber.trainees.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.cyber.trainees.demo.domain.BankomatEntry;

@Repository
public interface BankomatRepository extends JpaRepository<BankomatEntry, String> {
    @Modifying
    @Query(value = "UPDATE BANKOMAT set name =:name where id = :id", nativeQuery = true) //albo zapytanie bazodanowea albo tak jak tu value i nativeQuery
    void updateName(@Param("id") String id, @Param("name") String name);
//CRUD - create read update delete



}
