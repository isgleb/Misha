

var cost = {id: null,
            date: new Date,
            contragent: null,
            invoicePositionsArr: []
            };


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