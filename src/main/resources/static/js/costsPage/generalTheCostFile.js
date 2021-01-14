var costId = parseInt((window.location.href.split("/").pop()));

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

//            console.log(invoiceDto);
//            updateCostContragent(invoiceDto.stuffId);
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
    cost.contragent = contragent;
    $("#chosen-contragent").text(cost.contragent.name);
    updateResultTable()
};


function updateInvoicesSum(theArray) {

    costSum = 0;

    theArray.forEach( function(aPosition, i, theArray) {
                costSum += parseInt(theArray[i].price);
                });

    $("#sum").text(costSum);
//    updateResultTable()
};
