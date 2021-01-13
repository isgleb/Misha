var costTypes;
var costTypesMap = new Map();


$.ajax({
        type: 'GET',
        url: "/costs-types/request",
        async: false,
        success: function (response) {costTypes = response;}
        });


costTypes.forEach(function(costType){
    costTypesMap.set(costType.id, costType.costType);
    })

var costTypesOptions = Object.fromEntries(costTypesMap.entries());


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
//var serviceFrontId = 1;

function initServiceTable(invoiceDto) {

    serviceTable = $('#invoice-positions').DataTable({
            "sPaginationType": "full_numbers",
    //        "initComplete": function() {updateIncomeSum(serviceTable.data().toArray());},
            ajax: {
                async: false,
                url : '/invoicePositions/byInvoice?' + $.param({invoiceId: invoiceDto.id}),
                // our data is an array of objects, in the root node instead of /data node, so we need 'dataSrc' parameter
                dataSrc : ''
            },
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


//        private long id;
//            private Long costTypeId;
//            private Long invoiceID;
//            private int price;
//            private String good;

            var positionDto = {id: rowdata.serviceId,
                                costTypeId : rowdata.quantity,
                                invoiceID : rowdata.totalPrice,
                                price : ,
                                good : };

                $.ajax({
                    url: '/invoicePositions/request',
                    type: 'POST',
                    contentType: "application/json",
                    data: JSON.stringify(positionDto),
                    success: success,
                    error: error,
                    complete: function() {updateIncomeSum(serviceTable.data().toArray());}
                });
            },

        onDeleteRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: '/positions/request?' + $.param({id: rowdata.id}), // выдает null
                type: 'DELETE',
                success: success,
                error: error,
                complete: function() {updateIncomeSum(serviceTable.data().toArray());}
            });
        },

        onEditRow: function(datatable, rowdata, success, error) {

            var position = {id: rowdata.id,
                            serviceId: rowdata.serviceId,
    //                        quantity : rowdata.quantity,
                            totalPrice : rowdata.totalPrice};

            $.ajax({
                url: '/positions/request?' + $.param({orderId: orderId}),
                type: 'PUT',
                contentType: "application/json",
                data: JSON.stringify(position),
                success: success,
                error: error,
                complete: function() {updateIncomeSum(serviceTable.data().toArray());}
            });
        }
    });

}