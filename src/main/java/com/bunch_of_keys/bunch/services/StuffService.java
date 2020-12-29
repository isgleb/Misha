package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.contragents.Stuff;
import com.bunch_of_keys.bunch.domain.contragents.StuffRepository;
import com.bunch_of_keys.bunch.domain.contragents.StuffStatus;
import com.bunch_of_keys.bunch.dto.StuffDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StuffService {

    @Autowired
    private StuffRepository stuffRepository;

    public void addStuff(StuffDto stuffDto) throws Exception {

        Stuff stuff = new Stuff();

        stuff.setName(stuffDto.getName());
        stuff.setSurname(stuffDto.getSurname());
        stuff.setEmail(stuffDto.getEmail());
        stuff.setTelephone(stuffDto.getTelephone());

        switch (stuffDto.getStuffStatus()) {
            case ("работает"):
                stuff.setStuffStatus(StuffStatus.active);
                break;
            case ("уволен"):
                stuff.setStuffStatus(StuffStatus.fired);
            default:
                throw new Exception();
        }

        stuffRepository.save(stuff);

    }

    public List<StuffDto> getAllStuff () throws Exception {
        Iterable<Stuff> allStuff = stuffRepository.findAll();
        List<StuffDto> respCustomers = new ArrayList<>();
        for (Stuff aStuff : allStuff) {

            StuffDto stuffDto = new StuffDto();

            stuffDto.setId(aStuff.getId());
            stuffDto.setName(aStuff.getName());
            stuffDto.setSurname(aStuff.getSurname());
            stuffDto.setEmail(aStuff.getEmail());
            stuffDto.setTelephone(aStuff.getTelephone());

            switch (aStuff.getStuffStatus()) {
                case active:
                    stuffDto.setStuffStatus("работает");
                    break;
                case fired:
                    stuffDto.setStuffStatus("уволен");
                    break;
                default:
                    throw new Exception();
            }

            respCustomers.add(stuffDto);
        }
        return respCustomers;
    }

    public List<StuffDto> getActiveStuff () throws Exception {
        Iterable<Stuff> allStuff = stuffRepository.findByStuffStatusIs(StuffStatus.active);
        List<StuffDto> respCustomers = new ArrayList<>();
        for (Stuff aStuff : allStuff) {

            StuffDto stuffDto = new StuffDto();

            stuffDto.setId(aStuff.getId());
            stuffDto.setName(aStuff.getName());
            stuffDto.setSurname(aStuff.getSurname());
            stuffDto.setEmail(aStuff.getEmail());
            stuffDto.setTelephone(aStuff.getTelephone());

            switch (aStuff.getStuffStatus()) {
                case active:
                    stuffDto.setStuffStatus("работает");
                    break;
                case fired:
                    stuffDto.setStuffStatus("уволен");
                    break;
                default:
                    throw new Exception();
            }

            respCustomers.add(stuffDto);
        }
        return respCustomers;
    }









    public StuffDto getTheStuff (Long stuffId) throws Exception {
        Stuff theStuff = stuffRepository.getOne(stuffId);

        StuffDto stuffDto = new StuffDto();

        stuffDto.setId(theStuff.getId());
        stuffDto.setName(theStuff.getName());
        stuffDto.setSurname(theStuff.getSurname());
        stuffDto.setEmail(theStuff.getEmail());
        stuffDto.setTelephone(theStuff.getTelephone());

        switch (theStuff.getStuffStatus()) {
            case active:
                stuffDto.setStuffStatus("работает");
                break;
            case fired:
                stuffDto.setStuffStatus("уволен");
                break;
            default:
                throw new Exception();
        }

        return stuffDto;
    }


    public void deleteStuff(Long id) {
        stuffRepository.deleteById(id);
    }

    public StuffDto editStuff(StuffDto stuffDto) throws Exception {
        long id = stuffDto.getId();
        Stuff theStuff = stuffRepository.getOne(id);
        theStuff.setName(stuffDto.getName());
        theStuff.setSurname(stuffDto.getSurname());
        theStuff.setEmail(stuffDto.getEmail());
        theStuff.setTelephone(stuffDto.getTelephone());

        switch (stuffDto.getStuffStatus()) {
            case ("работает"):
                theStuff.setStuffStatus(StuffStatus.active);
                break;
            case ("уволен"):
                theStuff.setStuffStatus(StuffStatus.fired);
                break;
            default:
                throw new Exception();
        }

        stuffRepository.save(theStuff);

        return stuffDto;
    }
}
