package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.CleaningService;
import com.bunch_of_keys.bunch.domain.CleaningServiceRepository;
import com.bunch_of_keys.bunch.domain.Position;
import com.bunch_of_keys.bunch.domain.PositionRepository;
import com.bunch_of_keys.bunch.dto.PositionDto;
import com.bunch_of_keys.bunch.dto.ServiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionService {

    @Autowired
    PositionRepository positionRepository;


    public List<PositionDto> getPositions (){
        List<Position> positions = positionRepository.findAll();
        List<PositionDto> positionDtos = new ArrayList<>();

        for (Position position: positions) {
            ServiceDto serviceDto = new ServiceDto(position.getCleaningService().getId(),
                    position.getCleaningService().getServiceType(),
                    position.getCleaningService().getPriceModel(),
                    position.getCleaningService().getPrice());

            positionDtos.add(new PositionDto(
                    position.getId(),
                    serviceDto,
                    position.getQuantity(),
                    position.getTotalPrice()
            ));
        }

        return positionDtos;
    }

    public void addPosition(PositionDto positionDto) {

        CleaningService service = new CleaningService(
                positionDto.getServiceDto().getId(),
                positionDto.getServiceDto().getServiceType(),
                positionDto.getServiceDto().getPriceModel(),
                positionDto.getServiceDto().getPrice());

        Position position = new Position(
                service,
                positionDto.getQuantity(),
                positionDto.getTotalPrice());

        positionRepository.save(position);

    }


//    public void addService (ServiceDto serviceDto){
//
//        CleaningService cleaningService = new CleaningService(
//                serviceDto.getServiceType(),
//                serviceDto.getPriceModel(),
//                serviceDto.getPrice());
//
//        cleaningServiceRepository.save(cleaningService);
//    }
//
//    public void deleteService (Long id) {
//        cleaningServiceRepository.deleteById(id);
//    }
//
//
//    public ServiceDto editService (ServiceDto serviceDto){
//        long id = serviceDto.getId();
//        CleaningService cleaningService = cleaningServiceRepository.getOne(id);
//        cleaningService.setPrice(serviceDto.getPrice());
//        cleaningService.setPriceModel(serviceDto.getPriceModel());
//        cleaningService.setServiceType(serviceDto.getServiceType());
//        cleaningServiceRepository.save(cleaningService);
//        return serviceDto;
//    }

}
