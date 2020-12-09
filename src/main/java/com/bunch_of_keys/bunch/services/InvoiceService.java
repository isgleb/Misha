package com.bunch_of_keys.bunch.services;

import com.bunch_of_keys.bunch.domain.bills.*;
import com.bunch_of_keys.bunch.domain.documents.OrderRepository;
import com.bunch_of_keys.bunch.dto.InvoiceDto;
import com.bunch_of_keys.bunch.dto.PositionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

//    public List<PositionDto> getPositions (Long orderId){
//
//        List<Position> positions = positionRepository.getByOrder_id(orderId);
//        List<PositionDto> positionDtos = new ArrayList<>();
//
//        for (Position position: positions) {
//
//            PositionDto positionDto = new PositionDto();
//
//            positionDto.setId(position.getId());
//            positionDto.setTotalPrice(position.getTotalPrice());
//            positionDto.setQuantity(position.getQuantity());
//            positionDto.setServiceId(position.getCleaningService().getId());
//
//            positionDtos.add(positionDto);
//        }
//
//        return positionDtos;
//    }

    public List<InvoiceDto> getInvoicesByOrder(Long orderId) {

        List<Invoice> invoices = invoiceRepository.getByOrder_id(orderId);
        List<InvoiceDto> invoiceDtos = new ArrayList<>();

        for (Invoice invoice: invoices) {

            InvoiceDto invoiceDto = new InvoiceDto();
            invoiceDto.setId(invoice.getId());
            invoiceDto.setStuffId(invoice.getStuff().getId());
            invoiceDto.setOrderId(orderId);
            invoiceDto.setSum(invoice.getSum());

            invoiceDtos.add(invoiceDto);
        }

        return invoiceDtos;

    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    public void newOrderInvoice (InvoiceDto invoiceDto) {




    }


//    public void addPosition(PositionDto positionDto, Long orderId) {
//
//        CleaningService service = cleaningServiceRepository.getOne(positionDto.getServiceId());
//
//        Position position = new Position();
//        position.setQuantity(positionDto.getQuantity());
//        position.setCleaningService(service);
//        position.setTotalPrice(positionDto.getTotalPrice());
//        position.setOrder(orderRepository.getOne(orderId));
//
//        positionRepository.save(position);
//    }

}
