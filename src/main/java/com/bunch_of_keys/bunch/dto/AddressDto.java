package com.bunch_of_keys.bunch.dto;


import com.bunch_of_keys.bunch.domain.documents.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private long id;

    private String yandexAddress;
    private String entrance;
    private String level;
    private String accommodation;
    private String intercom;

    public static AddressDto dtoFromAddress(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setYandexAddress(address.getYandexAddress());
        addressDto.setEntrance(address.getEntrance());
        addressDto.setLevel(address.getLevel());
        addressDto.setAccommodation(address.getAccommodation());
        addressDto.setIntercom(address.getIntercom());

        return addressDto;
    }


    public static Address addressFromDto(AddressDto addressDto) {
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setYandexAddress(addressDto.getYandexAddress());
        address.setEntrance(addressDto.getEntrance());
        address.setLevel(addressDto.getLevel());
        address.setAccommodation(addressDto.getAccommodation());
        address.setIntercom(addressDto.getIntercom());

        return address;
    }

}
