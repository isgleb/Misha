package com.bunch_of_keys.bunch.domain.contragents;

import com.bunch_of_keys.bunch.domain.documents.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StuffRepository extends JpaRepository<Stuff, Long> {

     List<Stuff> findByStuffStatusIs(StuffStatus stuffStatus);


//     List<Stuff> findDistinctByStatusIs(StuffStatus stuffStatus);
}
