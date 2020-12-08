package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.bills.CleaningService;
import com.bunch_of_keys.bunch.domain.bills.CleaningServiceRepository;
import com.bunch_of_keys.bunch.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CleaningServService {

    @Autowired
    CleaningServiceRepository cleaningServiceRepository;


    public List<ServiceDto> getServices (){
        List<CleaningService> services = cleaningServiceRepository.findAll();
        List<ServiceDto> serviceDtos = new ArrayList<>();

        for (CleaningService service: services) {

            ServiceDto serviceDto = new ServiceDto();
            serviceDto.setId(service.getId());
            serviceDto.setServiceType(service.getServiceType());
            serviceDto.setPriceModel(service.getPriceModel());
            serviceDto.setPrice(service.getPrice());

            serviceDtos.add(serviceDto);
        }
        return serviceDtos;
    }


    public void addService (ServiceDto serviceDto){

        CleaningService cleaningService = new CleaningService();
        cleaningService.setServiceType(serviceDto.getServiceType());
        cleaningService.setPriceModel(serviceDto.getPriceModel());
        cleaningService.setPrice(serviceDto.getPrice());

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
