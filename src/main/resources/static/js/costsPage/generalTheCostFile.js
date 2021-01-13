var costId = parseInt((window.location.href.split("/").pop()));

var costDto;
var invoiceDto;

$.ajax({
        type: 'GET',
        url: '/the-cost?' + $.param({costId: costId}),
        async: true,
        success: function (response) {
            costDto = response;
            console.log(costDto);
        }
});


$.ajax({
        type: 'GET',
        url: '/the-invoice?' + $.param({costId: costId}),
        async: true,
        success: function (response) {
            invoiceDto = response;
            initServiceTable(invoiceDto);

            console.log(invoiceDto);
        }
});


var cost = {id: null,
            date: new Date,
            contragent: null,
            invoicePositionsArr: []
            };

var comments = "";






var costSum = 0;


var saveButton = document.querySelector('#save');
var readyToSave = false;


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
    updateResultTable()
};


function updateResultTable() {
    if (cost.date !== null &&
        cost.contragent !== null &&
        cost.invoicePositionsArr.length !== 0) {

        saveButton.classList.add("btn-success");
        saveButton.classList.remove("btn-info");

        saveButton.innerText = "Сохранить затраты";
        readyToSave = true;
    };
};


$("#save").click(function() {

    if (readyToSave) {

//      создание Cost
        costDto = {id: cost.id,
                   date: cost.date,
                   comments: comments
                   };

//        console.log(costDto);

        $.ajax({
                url: "/new-cost",
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(costDto),
                async: false,
                success: function(response){
                                    costDto = response;
//                                    console.log(costDto);
                            }
//                    error: error
        });


////        создание Invoice
        invoiceDto = {id: null,
                      stuffId: cost.contragent.id,
                      invoiceRelatedDocumentId: costDto.id,
                      sum: costSum
                      };

//        console.log(invoiceDto);

        $.ajax({
                url: "/new-invoice",
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(invoiceDto),
                async: false,
                success: function(response){
                                    invoiceDto = response;
//                                    console.log(invoiceDto);
                            }
//                    error: error
        });


//      создание InvoicePositions
        invoicePositionsDtos = cost.invoicePositionsArr;

        invoicePositionsDtos.forEach(function(position, i, invoicePositionsDtos) {
                  invoicePositionsDtos[i].id = null;
                  invoicePositionsDtos[i].invoiceID = invoiceDto.id;
                });


        $.ajax({
                url: "/new-invoice-positions",
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(invoicePositionsDtos),
                async: false,
                success: function(){
                    theCostPage = window.location.protocol + "/cost/" + costDto.id;
                    console.log(theCostPage);
                    window.location.replace(theCostPage);
                }
//                    error: error
        });
    }
});