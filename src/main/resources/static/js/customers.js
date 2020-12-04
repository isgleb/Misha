//var statusOptions = { "уборка балкона" : "уборка балкона"};


// вызов базовой функции jQuery
$(document).ready(function() {

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


    $.ajax({
          type: 'GET',
          url: '/order/customer?' + $.param({orderId: ordersId}),
          async: false,
          success: function (response) {customer = response;}
          });


//    $("#chosen-customer").text("id " + customer["id"] + ", " + "name " + customer["name"]);

    var columnDefs = [

        {
        data: "id",
        title: "id",
        type: "readonly"
        },
        {
        data: "serviceDto",
        title: "Услуга",
        type : "select",

        options : servicesOptions,
        select1 : { width: "100%"},
        render: function (data, type, row, meta) {
            console.log(data)
            if (data == null || !(data.id in servicesOptions)) return null;
            return servicesOptions[data.id];
            }
        },
        {
        data: "quantity",
        title: "Количество"
        },
        {
        data: "totalPrice",
        title: "сумма"
        },
    ];

    var serviceTable;

    serviceTable = $('#positions').DataTable({
        "sPaginationType": "full_numbers",
        ajax: {
            url : '/positions/request',
            // our data is an array of objects, in the root node instead of /data node, so we need 'dataSrc' parameter
            dataSrc : ''
        },
        columns: columnDefs,
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
            $.ajax({
                url: '/customers/request',
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(rowdata),
                success: success,
                error: error
            });
        },

        onDeleteRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: '/customers/request?' + $.param({id: rowdata.id}), // выдает null
                type: 'DELETE',
                success: success,
                error: error
            });
        },

        onEditRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: '/customers/request',
                type: 'PUT',
                contentType: "application/json",
                data: JSON.stringify(rowdata),
                success: success,
                error: error
            });
        }
    });


























    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    var ordersId = urlParams.get('id');

    var customer;


    $.ajax({
          type: 'GET',
          url: '/order/customer?' + $.param({orderId: ordersId}),
          async: false,
          success: function (response) {customer = response;}
          });


    $("#chosen-customer").text("id " + customer["id"] + ", " + "name " + customer["name"]);

    var columnDefs = [

        {
        data: "id",
        title: "id",
        type: "readonly"
        },
        {
        data: "name",
        title: "Имя",
        },
        {
        data: "surname",
        title: "Фамилия"
        },
        {
        data: "email",
        title: "email"
        },
        {
        data: "telephone",
        title: "Телефон"
        },
    ];

    var myTable;

    myTable = $('#order-customer').DataTable({
        "sPaginationType": "full_numbers",
        ajax: {
            url : '/customers/request',
            // our data is an array of objects, in the root node instead of /data node, so we need 'dataSrc' parameter
            dataSrc : ''
        },
        columns: columnDefs,
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
            },
            {
                text: 'Выбрать клиента',
                name: 'choose',
                action: changeClient
            }
        ],

        onAddRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: '/customers/request',
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(rowdata),
                success: success,
                error: error
            });
        },

        onDeleteRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: '/customers/request?' + $.param({id: rowdata.id}), // выдает null
                type: 'DELETE',
                success: success,
                error: error
            });
        },

        onEditRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: '/customers/request',
                type: 'PUT',
                contentType: "application/json",
                data: JSON.stringify(rowdata),
                success: success,
                error: error
            });
        }
    });

    function changeClient () {
            var selectedCustomerId = myTable.row('.selected').data().id;
            $.ajax({
                   url: '/order/customer?' + $.param({orderId: ordersId}) + "&" +$.param({customerId: selectedCustomerId}), // выдает null
                   type: 'PUT',
                   success: function(){
                        customer = myTable.row('.selected').data();
                        $("#chosen-customer").text("id " + customer["id"] + ", " + "name " + customer["name"]);
                   }
            });
    }
});