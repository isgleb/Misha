package com.bunch_of_keys.bunch.controllers;

import com.bunch_of_keys.bunch.domain.OrderDao;
import com.bunch_of_keys.bunch.dto.NewOrderRequest;
import com.bunch_of_keys.bunch.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    private OrderService orderService;

//    @PutMapping("/orders/request")
//    public ResponseEntity addOrder (@RequestBody NewOrderRequest newOrderRequest) {
//        System.out.println(newOrderRequest.getCustomer());
//        orderService.newOrder(newOrderRequest);
//        return new ResponseEntity(HttpStatus.OK);
//    }


    @GetMapping("/orders/request")
    public ResponseEntity getOrders () {
        orderService.setSomeOrders();

        List<OrderDao> ordersResp = orderService.getOrders();

        return new ResponseEntity(ordersResp, HttpStatus.OK);
    }

//    @PutMapping("/orders/request")
//    public ResponseEntity addOrder (String model) {
////        сам запрос проходит и ловится здесь но тело пустое в chrome тоже
//
//        System.out.println(model);
////        System.out.println(newOrderRequest.getCustomer());
////        orderService.newOrder(newOrderRequest);
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @PostMapping("/orders/requestok")
    public ResponseEntity deleteOrder (@RequestBody NewOrderRequest newOrderRequest) {
        System.out.println(newOrderRequest.getCustomer());
        orderService.newOrder(newOrderRequest);
        return new ResponseEntity(HttpStatus.OK);
    }


//    может быть для js тянется версия с компа а надо загружать новую версию datatable
//    попробуй создать чистый проект



//
//    @GetMapping("/alterthree/request")
//    public ResponseEntity getOrdersd (Model model) {
//        String resp = "[\n" +
//                "{\"id\":1, \"name\":\"Tiger Nixon\", \"position\":\"System Architect\", \"office\":\"Edinburgh\", \"extension\":\"5421\", \"startDate\":\"2011/04/25\", \"salary\":\"Tiger Nixon\"},\n" +
//                "{\"id\":2, \"name\":\"Garrett Winters\", \"position\":\"Accountant\", \"office\":\"Tokyo\", \"extension\":\"8422\", \"startDate\":\"2011/07/25\", \"salary\":\"Garrett Winters\"},\n" +
//                "{\"id\":3, \"name\":\"Ashton Cox\", \"position\":\"Junior Technical Author\", \"office\":\"San Francisco\", \"extension\":\"1562\", \"startDate\":\"2009/01/12\", \"salary\":\"Ashton Cox\"},\n" +
//                "{\"id\":4, \"name\":\"Cedric Kelly\", \"position\":\"Senior Javascript Developer\", \"office\":\"Edinburgh\", \"extension\":\"6224\", \"startDate\":\"2012/03/29\", \"salary\":\"Cedric Kelly\"},\n" +
//                "{\"id\":5, \"name\":\"Airi Satou\", \"position\":\"Accountant\", \"office\":\"Tokyo\", \"extension\":\"5407\", \"startDate\":\"2008/11/28\", \"salary\":\"Airi Satou\"},\n" +
//                "{\"id\":6, \"name\":\"Brielle Williamson\", \"position\":\"Integration Specialist\", \"office\":\"New York\", \"extension\":\"4804\", \"startDate\":\"2012/12/02\", \"salary\":\"Brielle Williamson\"},\n" +
//                "{\"id\":7, \"name\":\"Herrod Chandler\", \"position\":\"Sales Assistant\", \"office\":\"San Francisco\", \"extension\":\"9608\", \"startDate\":\"2012/08/06\", \"salary\":\"Herrod Chandler\"},\n" +
//                "{\"id\":8, \"name\":\"Rhona Davidson\", \"position\":\"Integration Specialist\", \"office\":\"Tokyo\", \"extension\":\"6200\", \"startDate\":\"2010/10/14\", \"salary\":\"Rhona Davidson\"},\n" +
//                "{\"id\":9, \"name\":\"Colleen Hurst\", \"position\":\"Javascript Developer\", \"office\":\"San Francisco\", \"extension\":\"2360\", \"startDate\":\"2009/09/15\", \"salary\":\"Colleen Hurst\"},\n" +
//                "{\"id\":10, \"name\":\"Sonya Frost\", \"position\":\"Software Engineer\", \"office\":\"Edinburgh\", \"extension\":\"1667\", \"startDate\":\"2008/12/13\", \"salary\":\"Sonya Frost\"},\n" +
//                "{\"id\":11, \"name\":\"Jena Gaines\", \"position\":\"Office Manager\", \"office\":\"London\", \"extension\":\"3814\", \"startDate\":\"2008/12/19\", \"salary\":\"Jena Gaines\"},\n" +
//                "{\"id\":12, \"name\":\"Quinn Flynn\", \"position\":\"Support Lead\", \"office\":\"Edinburgh\", \"extension\":\"9497\", \"startDate\":\"2013/03/03\", \"salary\":\"Quinn Flynn\"},\n" +
//                "{\"id\":13, \"name\":\"Charde Marshall\", \"position\":\"Regional Director\", \"office\":\"San Francisco\", \"extension\":\"6741\", \"startDate\":\"2008/10/16\", \"salary\":\"Charde Marshall\"},\n" +
//                "{\"id\":14, \"name\":\"Haley Kennedy\", \"position\":\"Senior Marketing Designer\", \"office\":\"London\", \"extension\":\"3597\", \"startDate\":\"2012/12/18\", \"salary\":\"Haley Kennedy\"},\n" +
//                "{\"id\":15, \"name\":\"Tatyana Fitzpatrick\", \"position\":\"Regional Director\", \"office\":\"London\", \"extension\":\"1965\", \"startDate\":\"2010/03/17\", \"salary\":\"Tatyana Fitzpatrick\"},\n" +
//                "{\"id\":16, \"name\":\"Michael Silva\", \"position\":\"Marketing Designer\", \"office\":\"London\", \"extension\":\"1581\", \"startDate\":\"2012/11/27\", \"salary\":\"Michael Silva\"},\n" +
//                "{\"id\":17, \"name\":\"Paul Byrd\", \"position\":\"Chief Financial Officer (CFO)\", \"office\":\"New York\", \"extension\":\"3059\", \"startDate\":\"2010/06/09\", \"salary\":\"Paul Byrd\"},\n" +
//                "{\"id\":18, \"name\":\"Gloria Little\", \"position\":\"Systems Administrator\", \"office\":\"New York\", \"extension\":\"1721\", \"startDate\":\"2009/04/10\", \"salary\":\"Gloria Little\"},\n" +
//                "{\"id\":19, \"name\":\"Bradley Greer\", \"position\":\"Software Engineer\", \"office\":\"London\", \"extension\":\"2558\", \"startDate\":\"2012/10/13\", \"salary\":\"Bradley Greer\"},\n" +
//                "{\"id\":20, \"name\":\"Dai Rios\", \"position\":\"Personnel Lead\", \"office\":\"Edinburgh\", \"extension\":\"2290\", \"startDate\":\"2012/09/26\", \"salary\":\"Dai Rios\"},\n" +
//                "{\"id\":21, \"name\":\"Jenette Caldwell\", \"position\":\"Development Lead\", \"office\":\"New York\", \"extension\":\"1937\", \"startDate\":\"2011/09/03\", \"salary\":\"Jenette Caldwell\"},\n" +
//                "{\"id\":22, \"name\":\"Yuri Berry\", \"position\":\"Chief Marketing Officer (CMO)\", \"office\":\"New York\", \"extension\":\"6154\", \"startDate\":\"2009/06/25\", \"salary\":\"Yuri Berry\"},\n" +
//                "{\"id\":23, \"name\":\"Caesar Vance\", \"position\":\"Pre-Sales Support\", \"office\":\"New York\", \"extension\":\"8330\", \"startDate\":\"2011/12/12\", \"salary\":\"Caesar Vance\"},\n" +
//                "{\"id\":24, \"name\":\"Doris Wilder\", \"position\":\"Sales Assistant\", \"office\":\"Sidney\", \"extension\":\"3023\", \"startDate\":\"2010/09/20\", \"salary\":\"Doris Wilder\"},\n" +
//                "{\"id\":25, \"name\":\"Angelica Ramos\", \"position\":\"Chief Executive Officer (CEO)\", \"office\":\"London\", \"extension\":\"5797\", \"startDate\":\"2009/10/09\", \"salary\":\"Angelica Ramos\"},\n" +
//                "{\"id\":26, \"name\":\"Gavin Joyce\", \"position\":\"Developer\", \"office\":\"Edinburgh\", \"extension\":\"8822\", \"startDate\":\"2010/12/22\", \"salary\":\"Gavin Joyce\"},\n" +
//                "{\"id\":27, \"name\":\"Jennifer Chang\", \"position\":\"Regional Director\", \"office\":\"Singapore\", \"extension\":\"9239\", \"startDate\":\"2010/11/14\", \"salary\":\"Jennifer Chang\"},\n" +
//                "{\"id\":28, \"name\":\"Brenden Wagner\", \"position\":\"Software Engineer\", \"office\":\"San Francisco\", \"extension\":\"1314\", \"startDate\":\"2011/06/07\", \"salary\":\"Brenden Wagner\"},\n" +
//                "{\"id\":29, \"name\":\"Fiona Green\", \"position\":\"Chief Operating Officer (COO)\", \"office\":\"San Francisco\", \"extension\":\"2947\", \"startDate\":\"2010/03/11\", \"salary\":\"Fiona Green\"},\n" +
//                "{\"id\":30, \"name\":\"Shou Itou\", \"position\":\"Regional Marketing\", \"office\":\"Tokyo\", \"extension\":\"8899\", \"startDate\":\"2011/08/14\", \"salary\":\"Shou Itou\"},\n" +
//                "{\"id\":30, \"name\":\"Michelle House\", \"position\":\"Integration Specialist\", \"office\":\"Sidney\", \"extension\":\"2769\", \"startDate\":\"2011/06/02\", \"salary\":\"Michelle House\"},\n" +
//                "{\"id\":32, \"name\":\"Suki Burks\", \"position\":\"Developer\", \"office\":\"London\", \"extension\":\"6832\", \"startDate\":\"2009/10/22\", \"salary\":\"Suki Burks\"},\n" +
//                "{\"id\":33, \"name\":\"Prescott Bartlett\", \"position\":\"Technical Author\", \"office\":\"London\", \"extension\":\"3606\", \"startDate\":\"2011/05/07\", \"salary\":\"Prescott Bartlett\"},\n" +
//                "{\"id\":34, \"name\":\"Gavin Cortez\", \"position\":\"Team Leader\", \"office\":\"San Francisco\", \"extension\":\"2860\", \"startDate\":\"2008/10/26\", \"salary\":\"Gavin Cortez\"},\n" +
//                "{\"id\":35, \"name\":\"Martena Mccray\", \"position\":\"Post-Sales support\", \"office\":\"Edinburgh\", \"extension\":\"8240\", \"startDate\":\"2011/03/09\", \"salary\":\"Martena Mccray\"},\n" +
//                "{\"id\":36, \"name\":\"Unity Butler\", \"position\":\"Marketing Designer\", \"office\":\"San Francisco\", \"extension\":\"5384\", \"startDate\":\"2009/12/09\", \"salary\":\"Unity Butler\"}\n" +
//                "]";
//
//        return new ResponseEntity(resp, HttpStatus.CREATED);
//    }
//
//
//    @GetMapping("/alterthree/request/get")
//    public ResponseEntity addOrderd (@RequestBody String model) {
//        System.out.println(model);
////        System.out.println(newOrderRequest.getCustomer());
////        orderService.newOrder(newOrderRequest);
//        return new ResponseEntity(HttpStatus.OK);
//    }
}