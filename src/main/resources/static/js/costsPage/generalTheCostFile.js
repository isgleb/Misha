var costId = parseInt((window.location.href.split("/").pop()));
$("#order-id").text(costId);

var costDto;
var invoiceDto;

$.ajax({
        type: 'GET',
        url: '/the-cost?' + $.param({costId: costId}),
        async: true,
        success: function (response) {
            costDto = response;
        }
});


$.ajax({
        type: 'GET',
        url: '/the-invoice?' + $.param({costId: costId}),
        async: true,
        success: function (response) {
            invoiceDto = response;
            initPositionsTable(invoiceDto);

            $("#chosen-contragent").text(invoiceDto.contragentId);

        }
});


var cost = {id: null,
            date: new Date,
            contragent: null,
            invoicePositionsArr: []
            };

var comments = "";

var costSum = 0;


$("#date-time").text(cost.date.toLocaleString());


function updateCostContragent(contragent) {
    invoiceDto.contragentId = contragent.id;
    $("#chosen-contragent").text(invoiceDto.contragentId);

    $.ajax({
            type: 'PUT',
            url: "/invoice/request",
            contentType: "application/json",
            data: JSON.stringify(invoiceDto),
            async: true,
            success: function () {
                $("#chosen-contragent").text(invoiceDto.contragentId);

            }
    });
};


function updateInvoicesSum(theArray) {

    costSum = 0;

    theArray.forEach( function(aPosition, i, theArray) {
                costSum += parseInt(theArray[i].price);
                });

    $("#sum").text(costSum);
};

