package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.CleaningService;
import com.bunch_of_keys.bunch.domain.CleaningServiceRepository;
import com.bunch_of_keys.bunch.dto.ServiceDto;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CleaningServService {

    @Autowired
    CleaningServiceRepository cleaningServiceRepository;


    public List<ServiceDto> getServices (){
        List<CleaningService> services = cleaningServiceRepository.findAll();
        List<ServiceDto> serviceDtos = new ArrayList<>();

        for (CleaningService service: services) {
            serviceDtos.add(new ServiceDto(
                    service.getId(),
                    service.getServiceType(),
                    service.getPriceModel(),
                    service.getPrice()
            ));
        }

//        serviceDtos.add(new ServiceDto(1,"Уборка","метры",4));
        return serviceDtos;
    }


    public void addService (ServiceDto serviceDto){

        CleaningService cleaningService = new CleaningService(
                serviceDto.getServiceType(),
                serviceDto.getPriceModel(),
                serviceDto.getPrice());

        cleaningServiceRepository.save(cleaningService);
    }

    public void deleteService (Long id) {
        cleaningServiceRepository.deleteById(id);
    }


    public ServiceDto editService (ServiceDto serviceDto){
        long id = serviceDto.getId();
        CleaningService cleaningService = cleaningServiceRepository.getOne(id);
        cleaningService.setPrice(serviceDto.getPrice());
        cleaningService.setPriceModel(serviceDto.getPriceModel());
        cleaningService.setServiceType(serviceDto.getServiceType());
        cleaningServiceRepository.save(cleaningService);
        return serviceDto;
    }

}
