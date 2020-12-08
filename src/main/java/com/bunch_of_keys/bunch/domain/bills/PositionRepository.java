package com.bunch_of_keys.bunch.domain.bills;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("select p from Position p join fetch p.cleaningService")
    List<Position> getPositionsForTable();


    List<Position> getByOrder_id(Long orderId);

}
