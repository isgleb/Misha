var orderId = parseInt((window.location.href.split("/").pop()));

var costsSum = 0;
var incomeSum = 0;

var orderDetails = {id: "",
                status: "",
                customerDto: "",
                addressDto: "",
                meters: "",
                date: ""
                };


$.ajax({
        type: 'GET',
        url: '/the-order?' + $.param({orderId: orderId}),
        async: true,
        success: function (response) {
            orderDetails = response;
            orderDetails.date = new Date(response.date)

            $("#order-id").text(orderDetails.id);
            $("#date-time").text(orderDetails.date.toLocaleString());
            $("#date-time-selector").text(orderDetails.date.toLocaleString());

            $("#chosen-customer").text(orderDetails.customerDto.name + ", " + orderDetails.customerDto.telephone);
            $("#address").text(orderDetails.addressDto.yandexAddress);
            $("#meters").val(orderDetails.meters);


            $("#ya-address").text(orderDetails.addressDto.yandexAddress);
            $("#entrance").val(orderDetails.addressDto.entrance);
            $("#level").val(orderDetails.addressDto.level);
            $("#accommodation").val(orderDetails.addressDto.accommodation);
            $("#intercom").val(orderDetails.addressDto.intercom);

        }
});


function updateOrderCustomer(customer) {

        $.ajax({
               url: '/order/customer?' + $.param({orderId: orderId}) + "&" +$.param({customerId: customer.id}), // выдает null
               type: 'PUT',
               success: function(){
                    orderDetails.customerDto = customer;
                    $("#chosen-customer").text(orderDetails.customerDto.name + ", " + orderDetails.customerDto.telephone);
               }
        });
};


function updateOrderDate(newDate) {

    $.ajax({
            url: '/the-order/update-date?' + $.param({orderId: orderId}), // выдает null
            type: 'PUT',
            async: false,
            contentType: "application/json",
            data: JSON.stringify(newDate),
            success: function() {
//                        console.log(newDate);
//                        console.log(orderDetails.date);
                    orderDetails.date = newDate;
                    $("#date-time").text(orderDetails.date.toLocaleString());
            },
    });

//    orderDetails.date = newDate;
//    $("#date-time").text(orderDetails.date.toLocaleString());
};


function updateOrderStatus(status) {
    if (status != orderDetails.status) {

        $.ajax({
                url: '/the-order/update-status?' + $.param({orderId: orderId}),
                type: 'PUT',
                async: false,
                contentType: "application/json",
                data: JSON.stringify(status),
                success: function(){ orderDetails.status = status; }
                });
    };
};


function updateOrderAddress(address) {

    if (address != orderDetails.address) {

            $.ajax({
                    url: '/the-order/update-address?' + $.param({orderId: orderId}),
                    type: 'PUT',
                    async: false,
                    contentType: "application/json",
                    data: JSON.stringify(address),
                    success: function(){
                            orderDetails.addressDto = address;
                            $("#address").text(orderDetails.addressDto.yandexAddress);
                            }
                    });
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

    $.ajax({
            url: '/the-order/update-meters?' + $.param({orderId: orderId}),
            type: 'PUT',
            async: false,
            contentType: "application/json",
            data: JSON.stringify(orderDetails.meters),
            });
});
