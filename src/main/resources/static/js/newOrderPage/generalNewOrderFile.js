var addressDto = {yandexAddress : null,
                  entrance : null,
                  level : null,
                  accommodation : null,
                  intercom: null
                  };

var orderDetails = {id: "",
                    status: "accepted",
                    customerDto: "",
                    addressDto: addressDto,
                    meters: "",
                    date: new Date(),
                    };

var costsSum = 0;
var incomeSum = 0;
var invoicesArr = [];
var servicesArr = [];

$("#date-time").text(orderDetails.date.toLocaleString());

function updateOrderCustomer(customer) {
    orderDetails.customerDto = customer;
    $("#chosen-customer").text(orderDetails.customerDto.name + ", " + orderDetails.customerDto.telephone);
};


function updateOrderDate(newDate) {
    orderDetails.date = newDate;
    $("#date-time").text(orderDetails.date.toLocaleString());
};


function updateOrderStatus(status) {
    if (status != orderDetails.status) {
        orderDetails.status = status;
    };
};


function updateOrderAddress(address) {

    if (address != orderDetails.address) {
        orderDetails.addressDto = address;
        $("#address").text(orderDetails.addressDto.yandexAddress);
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

    console.log("here i am");
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
    if (orderDetails.status !== null &&
        orderDetails.customerDto !== null &&
        orderDetails.addressDto !== null &&
        orderDetails.meters !== null &&
        orderDetails.date !== null &&
        invoicesArr.length !== 0 &&
        servicesArr.length !== 0) {

        saveButton.classList.add("btn-success");
        saveButton.classList.remove("btn-info");

        saveButton.innerText = "Сохранить заказ";
        readyToSave = true;
    };
};


$("#save").click(function() {
    console.log(orderDetails);
    if (readyToSave) {

        meters = document.getElementById('meters').value;

        var orderId = 0;
        var orderPage = window.location.protocol + "/order/";

        $.ajax({
                url: "/create-new-order",
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(orderDetails),
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


//      переход на страницу созданного заказа
        window.location.replace(orderPage);
    }
});