var services;
var serviceMap = new Map();

$.ajax({
        type: 'GET',
        url: "/services/request",
        async: false,
        success: function (response) {services = response;}
        });

services.forEach(function(service){
    serviceMap.set(service.id, service.serviceType);
    })

var servicesOptions = Object.fromEntries(serviceMap.entries());


var servicesColumns = [

    {
    data: "id",
    title: "id",
    type: "readonly"
    },
    {
    data: "serviceId",
    title: "Услуга",
    type : "select",
    options : servicesOptions,
    select1 : { width: "100%"},
    render: function (data, type, row, meta) {

        if (data == null || !(data in servicesOptions)) {
        return null;
        }

        return servicesOptions[data];
        }
    },
    {
    data: "quantity",
    title: "Количество",
    type: "number",
    required: true,
    },
    {
    data: "totalPrice",
    title: "сумма",
    },
];


var serviceTable;

serviceTable = $('#positions').DataTable({
    "sPaginationType": "full_numbers",
    "initComplete": function() {updateIncomeSum(serviceTable.data().toArray());},
    ajax: {
        url : '/positions/request?' + $.param({orderId: orderId}),
        // our data is an array of objects, in the root node instead of /data node, so we need 'dataSrc' parameter
        dataSrc : ''
    },
    columns: servicesColumns,
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
        },
        {
            text: 'Обновить таблицу',
            name: 'refresh'      // do not change name
        }
    ],

    onAddRow: function(datatable, rowdata, success, error) {

    var position = {serviceId: rowdata.serviceId,
                    quantity : rowdata.quantity,
                    totalPrice : rowdata.totalPrice};

        $.ajax({
            url: '/positions/request?' + $.param({orderId: orderId}),
            type: 'POST',
            contentType: "application/json",
            data: JSON.stringify(position),
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
                        quantity : rowdata.quantity,
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
