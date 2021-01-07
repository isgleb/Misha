var ordersId = parseInt((window.location.href.split("/").pop()));


orderDetails = {id: "",
                status: "",
                customerDto: "",
                address: "",
                meters: "",
                date: ""
                };


$.ajax({
          type: 'GET',
          url: '/the-order?' + $.param({orderId: ordersId}),
          async: false,
          success: function (response) {
                orderDetails = response;
                orderDetails.date = new Date(response.date)
                console.log(response.date);

                $("#order-id").text(orderDetails.id);
                $("#date-time").text(orderDetails.date.toLocaleString());
                $("#chosen-customer").text(orderDetails.customerDto.name + ", " + orderDetails.customerDto.telephone);
                $("#address").text(orderDetails.address.yandexAddress);
                $("#meters").val(orderDetails.meters);
        }
});


function updateOrderCustomer(customer) {

        $.ajax({
               url: '/order/customer?' + $.param({orderId: ordersId}) + "&" +$.param({customerId: customer.id}), // выдает null
               type: 'PUT',
               success: function(){
                    orderDetails.customerDto = customer;
                    $("#chosen-customer").text(orderDetails.customerDto.name + ", " + orderDetails.customerDto.telephone);
               }
        });
}


function updateOrderDate(date) {
//        console.log(date);
//        console.log(orderDetails.date);

    if (date != orderDetails.date) {

        $.ajax({
                url: '/the-order/update-date?' + $.param({orderId: ordersId}), // выдает null
                type: 'PUT',
                async: false,
                contentType: "application/json",
                data: date.toJSON(),
                success: function() {

                        console.log(date);
                        console.log(orderDetails.date);

                        orderDetails.date = date;
                        $("#date-time").text(orderDetails.date.toLocaleString());
                }
        });
    }
}




//
//
//function updateOrderStatus(status) {
//    if (status != orderDetails.status) {
//
//        $.ajax({
////                url: '/the-order/update-status?' + $.param({orderId: ordersId}) + "&" +$.param({status: status}), // выдает null
//                url: '/the-order/update-status?' + $.param({orderId: ordersId})
//                type: 'PUT',
//                async: false,
//                contentType: "application/json",
//                data: JSON.stringify({status: status}),
//                success: function(){ orderDetails.status = status; }
//                });
//    }
//}
//

//
//function updateOrderAddress(address) {
//
//}
//


//
//function updateOrderCalculations(income, expenses, profit) {
//
//}


//$( "#meters" ).change(function() {
//    meters = document.getElementById('meters').value;
//
////    ajax
//});

