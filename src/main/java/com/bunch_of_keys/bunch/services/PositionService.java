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


    public List<PositionDto> getPositions (Long orderId){

        List<Position> positions = positionRepository.getByOrder_id(orderId);
        List<PositionDto> positionDtos = new ArrayList<>();

        for (Position position: positions) {

            PositionDto positionDto = new PositionDto();

            positionDto.setId(position.getId());
            positionDto.setTotalPrice(position.getTotalPrice());
            positionDto.setQuantity(position.getQuantity());
            positionDto.setServiceId(position.getCleaningService().getId());

            positionDtos.add(positionDto);
        }

        return positionDtos;
    }

    public void addPosition(PositionDto positionDto, Long orderId) {

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
