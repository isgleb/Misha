var addressDto = {yandexAddress : null,
                  entrance : null,
                  level : null,
                  accommodation : null,
                  intercom: null
                  };

var orderDetails = {id: "",
                status: "accepted",
                customerDto: "",
                address: addressDto,
                meters: "",
                date: new Date(),
                };





//$.ajax({
//        type: 'GET',
//        url: '/the-order?' + $.param({orderId: orderId}),
//        async: true,
//        success: function (response) {
//            orderDetails = response;
//            orderDetails.date = new Date(response.date)
//
//            $("#order-id").text(orderDetails.id);
//            $("#date-time").text(orderDetails.date.toLocaleString());
//            $("#date-time-selector").text(orderDetails.date.toLocaleString());
//
//            $("#chosen-customer").text(orderDetails.customerDto.name + ", " + orderDetails.customerDto.telephone);
//            $("#address").text(orderDetails.addressDto.yandexAddress);
//            $("#meters").val(orderDetails.meters);
//
//
//            $("#ya-address").text(orderDetails.addressDto.yandexAddress);
//            $("#entrance").val(orderDetails.addressDto.entrance);
//            $("#level").val(orderDetails.addressDto.level);
//            $("#accommodation").val(orderDetails.addressDto.accommodation);
//            $("#intercom").val(orderDetails.addressDto.intercom);
//
//        }
//});


function updateOrderCustomer(customer) {

    orderDetails.customerDto = customer;
    $("#chosen-customer").text(orderDetails.customerDto.name + ", " + orderDetails.customerDto.telephone);


//        $.ajax({
//               url: '/order/customer?' + $.param({orderId: orderId}) + "&" +$.param({customerId: customer.id}), // выдает null
//               type: 'PUT',
//               success: function(){
//                    orderDetails.customerDto = customer;
//                    $("#chosen-customer").text(orderDetails.customerDto.name + ", " + orderDetails.customerDto.telephone);
//               }
//        });
};


function updateOrderDate(newDate) {
    orderDetails.date = newDate;
    $("#date-time").text(orderDetails.date.toLocaleString());

//    $.ajax({
//            url: '/the-order/update-date?' + $.param({orderId: orderId}), // выдает null
//            type: 'PUT',
//            async: false,
//            contentType: "application/json",
//            data: JSON.stringify(newDate),
//            success: function() {
////                        console.log(newDate);
////                        console.log(orderDetails.date);
//                    orderDetails.date = newDate;
//                    $("#date-time").text(orderDetails.date.toLocaleString());
//            },
//    });

//    orderDetails.date = newDate;
//    $("#date-time").text(orderDetails.date.toLocaleString());
};


function updateOrderStatus(status) {
    if (status != orderDetails.status) {
        orderDetails.status = status;

//        $.ajax({
//                url: '/the-order/update-status?' + $.param({orderId: orderId}),
//                type: 'PUT',
//                async: false,
//                contentType: "application/json",
//                data: JSON.stringify(status),
//                success: function(){ orderDetails.status = status; }
//                });
    };
};


function updateOrderAddress(address) {

    if (address != orderDetails.address) {

        orderDetails.addressDto = address;
        $("#address").text(orderDetails.addressDto.yandexAddress);

//            $.ajax({
//                    url: '/the-order/update-address?' + $.param({orderId: orderId}),
//                    type: 'PUT',
//                    async: false,
//                    contentType: "application/json",
//                    data: JSON.stringify(address),
//                    success: function(){
//                            orderDetails.addressDto = address;
//                            $("#address").text(orderDetails.addressDto.yandexAddress);
//                            }
//                    });
    };
};


function updateInvoicesSum(invoicesArr) {

    costsSum = 0;

    invoicesArr.forEach(function(invoice, i, invoicesArr) {
                costsSum += parseInt(invoicesArr[i].sum);
                });

    updateOrderCalculations();
};


function updateIncomeSum(servicesArr) {
    incomeSum = 0;

    servicesArr.forEach(function(aService, i, servicesArr) {
                incomeSum += parseInt(servicesArr[i].totalPrice);
                });

    updateOrderCalculations();
}


function updateOrderCalculations() {

    profitSum = incomeSum - costsSum;

    $("#income").text(incomeSum);
    $("#expenses").text(costsSum);
    $("#profit").text(profitSum);
}


$( "#meters" ).change(function() {

    orderDetails.meters = document.getElementById('meters').value;

//    $.ajax({
//            url: '/the-order/update-meters?' + $.param({orderId: orderId}),
//            type: 'PUT',
//            async: false,
//            contentType: "application/json",
//            data: JSON.stringify(meters),
//            });
});


var saveButton = document.querySelector('#save');
var readyToSave = false;

$("#edit-address").click(function() {

        address.yandexAddress = document.getElementById('ya-address').innerHTML;
        address.entrance = document.getElementById('entrance').value;
        address.level = document.getElementById('level').value;
        address.accommodation = document.getElementById('accommodation').value;
        address.intercom = document.getElementById('intercom').value;

        updateResultTable();
    });

    function updateResultTable() {
        if (customer !== null && invoicesArr.length !== 0) {

            saveButton.classList.add("btn-success");
            saveButton.classList.remove("btn-info");

            saveButton.innerText = "Сохранить заказ";
            readyToSave = true;
        };



    $("#save").click(function() {
        if (readyToSave) {

            meters = document.getElementById('meters').value;

            var orderId = 0;
            var orderPage = window.location.protocol + "/order/";
            orderDto = {customerId:customer["id"],
                        status: orderStatus,
                        address: address,
                        meters: meters,
                        date: date};


            $.ajax({
                    url: "/create-new-order",
                    type: 'POST',
                    contentType: "application/json",
                    data: JSON.stringify(orderDto),
                    async: false,
                    success: function(response){
                                        orderId = parseInt(response["id"]);
                                        orderPage = orderPage + orderId.toString();
                                }
//                    error: error
                });


        invoicesArr.forEach(function(invoice, i, invoicesArr) {
              invoicesArr[i].id = null;
              invoicesArr[i].invoiceRelatedDocumentId = orderId;
            });



            $.ajax({
                url: "/new-invoices/array",
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(invoicesArr)
    //                    success: success,
    //                    error: error
            });

            servicesArr.forEach(function(invoice, i, invoicesArr) {
                  servicesArr[i].id = null;
                });


            $.ajax({
                url: "/positions-array/request?" + $.param({orderId: orderId}),
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(servicesArr)
    //                success: success,
    //                error: error
            });


    //        переход на страницу созданного заказа
            window.location.replace(orderPage);
        }

        });