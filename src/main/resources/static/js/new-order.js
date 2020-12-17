
$(document).ready(function() {


    var saveButton = document.querySelector('#save');
    var readyToSave = false;
    var customer = null;

    function updateResultTable() {
        if (customer !== null && invoicesArr.length !== 0) {

            saveButton.classList.add("btn-success");
            saveButton.classList.remove("btn-info");

            saveButton.innerText = "Сохранить заказ";
            readyToSave = true;
        };

        costsSum = function () {
                        sum = 0;
                        invoicesArr.forEach(function(invoice, i, invoicesArr) {
                            sum += parseInt(invoicesArr[i].sum);
                            });
                        return sum;
                        }

        $("#expenses").text(costsSum);
        if (customer !== null) {
            $("#chosen-customer").text(customer["name"] + " " + customer["telephone"]);
        }
    }



    $("#save").click(function() {


        if (readyToSave) {

            var orderId = 0;
            var orderPage = window.location.protocol + "/order/";
            orderDto = {customerId:customer["id"], status: "принят"};

            $.ajax({
                    url: "/create-new-order",
                    type: 'POST',
                    contentType: "application/json",
                    data: JSON.stringify(orderDto),
                    async: false,
                    success: function(response){
                                        orderId = parseInt(response["id"]);
                                        orderPage = orderPage + orderId.toString();
                                }
//                    error: error
                });
            }

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

            window.location.replace(orderPage);

        });



    var clientColumns = [

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
    var clientsTable;

    clientsTable = $('#order-customer').DataTable({
        "sPaginationType": "full_numbers",
        ajax: {
            url : '/customers/request',
            // our data is an array of objects, in the root node instead of /data node, so we need 'dataSrc' parameter
            dataSrc : ''
        },
        columns: clientColumns,
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

        customer = clientsTable.row('.selected').data();
        updateResultTable();

    }


    var stuff;
    var stuffMap = new Map();
    $.ajax({
            type: 'GET',
            url: "/stuff/request",
            async: false,
            success: function (response) {stuff = response;}
            });

    stuff.forEach(function(theStuff){stuffMap.set(theStuff.id, theStuff.name);})

    var stuffOptions = Object.fromEntries(stuffMap.entries());


    var invoiceColumns = [
        {
        data: "id",
        title: "id",
        type: "readonly"
        },
        {
        data: "stuffId",
        title: "Сотрудник",
        type : "select",
        options : stuffOptions,
        select1 : { width: "100%"},
        render: function (data, type, row, meta) {
            if (data == null || !(data in stuffOptions)) { return null;}
            return stuffOptions[data];
            }
        },
        {
        data: "sum",
        title: "Сумма",
        type: "number",
        required: true,
        },
    ];

    var invoiceTable;
    var invoicesArr = [];

    invoiceFrontId = 1;

    invoiceTable = $('#invoices').DataTable({
        "sPaginationType": "full_numbers",

        data: invoicesArr,
        columns: invoiceColumns,
        dom: 'Bfrtip',        // Needs button container
        select: 'single',
        responsive: true,
        altEditor: true,     // Enable altEditor
        buttons: [
                {
                text: 'Add',
                name: 'add',        // do not change name
                },
                {
                extend: 'selected', // Bind to Selected row
                text: 'Edit',
                name: 'edit'        // do not change name
                },

                {
                extend: 'selected', // Bind to Selected row
                text: 'Delete',
                name: 'delete'      // do not change name
                }],
        onAddRow: function(datatable, rowdata, success, error) {

                newId = "new" + invoiceFrontId.toString();
                theRow = {id: newId,
                          stuffId: rowdata["stuffId"],
                          sum: rowdata["sum"],
                          invoiceRelatedDocumentId: null
                          };

                invoiceFrontId += 1;

                invoicesArr.push(theRow);
                updateResultTable();
                success(theRow);
             },

        onDeleteRow: function(datatable, rowdata, success, error) {

                const index = invoicesArr.findIndex(n => n.id === rowdata["id"]);
                if (index !== -1) {invoicesArr.splice(index, 1);}

                updateResultTable();
                success(rowdata);
             },

        onEditRow: function(datatable, rowdata, success, error) {

                const index = invoicesArr.findIndex(n => n.id === rowdata["id"]);

                theRow = {id: rowdata["id"],
                          stuffId: rowdata["stuffId"],
                          sum: rowdata["sum"],
                          invoiceRelatedDocumentId: null
                          };

                invoicesArr[index] =  theRow;
                success(rowdata);

                }
      });


//
//
//
//    var services;
//    var serviceMap = new Map();
//
//    $.ajax({
//            type: 'GET',
//            url: "/services/request",
//            async: false,
//            success: function (response) {services = response;}
//            });
//
//    services.forEach(function(service){
//        serviceMap.set(service.id, service.serviceType);
//        })
//
//    var servicesOptions = Object.fromEntries(serviceMap.entries());
//
//
////    $.ajax({
////          type: 'GET',
////          url: '/order/customer?' + $.param({orderId: ordersId}),
////          async: false,
////          success: function (response) {customer = response;}
////          });
//
//    var columnDefs = [
//
//        {
//        data: "id",
//        title: "id",
//        type: "readonly"
//        },
//        {
//        data: "serviceId",
//        title: "Услуга",
//        type : "select",
//        options : servicesOptions,
//        select1 : { width: "100%"},
//        render: function (data, type, row, meta) {
//
//            if (data == null || !(data in servicesOptions)) {
//            return null;
//            }
//
//            return servicesOptions[data];
//            }
//        },
//        {
//        data: "quantity",
//        title: "Количество",
//        type: "number",
//        required: true,
//        },
//        {
//        data: "totalPrice",
//        title: "сумма",
//        },
//    ];
//
//    var serviceTable;
//
//    serviceTable = $('#positions').DataTable({
//        "sPaginationType": "full_numbers",
//        ajax: {
//            url : '/positions/request?' + $.param({orderId: ordersId}),
//            // our data is an array of objects, in the root node instead of /data node, so we need 'dataSrc' parameter
//            dataSrc : ''
//        },
//        columns: columnDefs,
//        dom: 'Bfrtip',        // Needs button container
//        select: 'single',
//        responsive: true,
//        altEditor: true,     // Enable altEditor
//        buttons: [
//            {
//                text: 'Новый',
//                name: 'add'        // do not change name
//            },
//            {
//                extend: 'selected', // Bind to Selected row
//                text: 'Изменить данные',
//                name: 'edit'        // do not change name
//            },
//            {
//                extend: 'selected', // Bind to Selected row
//                text: 'Удалить',
//                name: 'delete'      // do not change name
//            },
//            {
//                text: 'Обновить таблицу',
//                name: 'refresh'      // do not change name
//            }
//        ],
//
//        onAddRow: function(datatable, rowdata, success, error) {
//
//        var position = {serviceId: rowdata.serviceId,
//                        quantity : rowdata.quantity,
//                        totalPrice : rowdata.totalPrice};
//
//            $.ajax({
//                url: '/positions/request?' + $.param({orderId: ordersId}),
//                type: 'POST',
//                contentType: "application/json",
//                data: JSON.stringify(position),
//                success: success,
//                error: error
//            });
//        },
//
//        onDeleteRow: function(datatable, rowdata, success, error) {
//            $.ajax({
//                url: '/positions/request?' + $.param({id: rowdata.id}), // выдает null
//                type: 'DELETE',
//                success: success,
//                error: error
//            });
//        },
//
//        onEditRow: function(datatable, rowdata, success, error) {
//
//            var position = {id: rowdata.id,
//                            serviceId: rowdata.serviceId,
//                            quantity : rowdata.quantity,
//                            totalPrice : rowdata.totalPrice};
//
//            $.ajax({
//                url: '/positions/request?' + $.param({orderId: ordersId}),
//                type: 'PUT',
//                contentType: "application/json",
//                data: JSON.stringify(position),
//                success: success,
//                error: error
//            });
//        }
//    });
//
//
//

















});