var costTypes;
var costTypesMap = new Map();


$.ajax({
        type: 'GET',
        url: "/costs-types/request",
        async: false,
        success: function (response) {costTypes = response;}
        });

//console.log(costTypes);

costTypes.forEach(function(costType){
    costTypesMap.set(costType.id, costType.costType);
    })

var costTypesOptions = Object.fromEntries(costTypesMap.entries());

//console.log(costTypesOptions);

var serviceColumns = [


    {
    data: "id",
    title: "id",
    type: "readonly"
    },
    {
    data: "costTypeId",
    title: "Статья затрат",
    type : "select",
    options : costTypesOptions,
    select1 : { width: "100%"},
    render: function (data, type, row, meta) {
        if (data == null || !(data in costTypesOptions)) {
        return null;
        }
        return costTypesOptions[data];
        }
    },
    {
    data: "good",
    title: "Покупка",
    },
    {
    data: "price",
    title: "Сумма",
    },
];

var serviceTable;
var serviceFrontId = 1;

serviceTable = $('#invoice-positions').DataTable({
    "sPaginationType": "full_numbers",

    data: cost.invoicePositionsArr,
    columns: serviceColumns,
    dom: 'Bfrtip',        // Needs button container
    select: 'single',
    responsive: true,
    altEditor: true,     // Enable altEditor
    buttons: [
        {
            text: 'Новый',
            name: 'add'        // do not change name
        },
        {
            extend: 'selected', // Bind to Selected row
            text: 'Изменить данные',
            name: 'edit'        // do not change name
        },
        {
            extend: 'selected', // Bind to Selected row
            text: 'Удалить',
            name: 'delete'      // do not change name
        }
    ],

    onAddRow: function(datatable, rowdata, success, error) {

        newId = "new" + serviceFrontId.toString();
        theRow = {id: newId,
                  costTypeId: rowdata["costTypeId"],
                  good: rowdata["good"],
                  price : rowdata["price"],
                  invoiceID : ""
                  };

        serviceFrontId += 1;

        cost.invoicePositionsArr.push(theRow);
//            updateIncomeSum(cost.invoicePositionsArr);
        success(theRow);

    },

    onDeleteRow: function(datatable, rowdata, success, error) {

        const index = cost.invoicePositionsArr.findIndex(n => n.id === rowdata["id"]);
        if (index !== -1) {cost.invoicePositionsArr.splice(index, 1);}

//            updateIncomeSum(cost.invoicePositionsArr);
        success(rowdata);

    },

    onEditRow: function(datatable, rowdata, success, error) {

        const index = cost.invoicePositionsArr.findIndex(n => n.id === rowdata["id"]);

        theRow = {id: newId,
                  costTypeId: rowdata["costTypeId"],
                  good: rowdata["good"],
                  price : rowdata["price"],
                  invoiceID : ""
                  };

        cost.invoicePositionsArr[index] =  theRow;
//            updateIncomeSum(cost.invoicePositionsArr);
        success(rowdata);
    }
});