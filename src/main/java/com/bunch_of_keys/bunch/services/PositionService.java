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

    @Autowired
    CleaningServiceRepository cleaningServiceRepository;


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
                    position.getCleaningService().getId(),
                    position.getQuantity(),
                    position.getTotalPrice()
            ));
        }

        return positionDtos;
    }

    public void addPosition(PositionDto positionDto) {

        CleaningService service = cleaningServiceRepository.getOne(positionDto.getServiceId());

        Position position = new Position(
                service,
                positionDto.getQuantity(),
                positionDto.getTotalPrice());

        positionRepository.save(position);

    }


    public void deleteService (Long id) {
        positionRepository.deleteById(id);
    }

    public Object editService(PositionDto positionDto) {

        long id = positionDto.getId();
        Position position = positionRepository.getOne(id);
        long cleaningServiceId = positionDto.getServiceId();

        CleaningService cleaningService = cleaningServiceRepository.getOne(cleaningServiceId);

        position.setQuantity(positionDto.getQuantity());
        position.setTotalPrice(positionDto.getTotalPrice());
        position.setCleaningService(cleaningService);


        positionRepository.save(position);
        return positionDto;
    }
}
