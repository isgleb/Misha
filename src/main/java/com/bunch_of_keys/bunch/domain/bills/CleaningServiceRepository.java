package com.bunch_of_keys.bunch.domain.bills;

import com.bunch_of_keys.bunch.domain.bills.CleaningService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "cleaning-services")
public interface CleaningServiceRepository extends JpaRepository<CleaningService, Long> {

}

