////var statusOptions = { "уборка балкона" : "уборка балкона"};
//
//
//// вызов базовой функции jQuery
//$(document).ready(function() {
//
//    var ordersId = parseInt((window.location.href.split("/").pop()));
//
//
//    var stuff;
//    var stuffMap = new Map();
//    $.ajax({
//            type: 'GET',
//            url: "/active-stuff/request",
//            async: false,
//            success: function (response) {stuff = response;}
//            });
//
//
//    stuff.forEach(function(theStuff){
//        stuffMap.set(theStuff.id, theStuff.name);
//        })
//
//
//    var stuffOptions = Object.fromEntries(stuffMap.entries());
//
//    var invoiceColumns = [
//        {
//        data: "id",
//        title: "id",
//        type: "readonly"
//        },
//        {
//        data: "stuffId",
//        title: "Сотрудник",
//        type : "select",
//        options : stuffOptions,
//        select1 : { width: "100%"},
//        render: function (data, type, row, meta) {
//            if (data == null || !(data in stuffOptions)) {
//                    $.ajax({
//                            type: 'GET',
//                            url: "/get-the-stuff/request?" + $.param({stuffId: data}),
//                            async: false,
//                            success: function (response) {
//                                stuffMap.set(response.id, response.name)
//                                stuffOptions = Object.fromEntries(stuffMap.entries());
//                                }
//                            });
//            }
//            return stuffOptions[data];
//            }
//        },
//        {
//        data: "sum",
//        title: "Сумма",
//        type: "number",
//        required: true,
//        },
//    ];
//
//    var invoiceTable;
//
//    invoiceTable = $('#invoices').DataTable({
//        "sPaginationType": "full_numbers",
//        "initComplete": function() {
//            updateInvoicesSum(invoiceTable.data().toArray());
//        },
//
//
//        ajax: {
//            url : '/invoice/request?' + $.param({orderId: ordersId}),
//            dataSrc : '',
//
//        },
//        columns: invoiceColumns,
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
//        onDeleteRow: function(datatable, rowdata, success, error) {
//                    $.ajax({
//                        url: '/invoice/request?' + $.param({id: rowdata.id}), // выдает null
//                        type: 'DELETE',
//                        success: success,
//                        error: error,
//                        complete: function() {updateInvoicesSum(invoiceTable.data().toArray());}
//                    });
//                },
//
//
//        onAddRow: function(datatable, rowdata, success, error) {
//
//            invoiceDto = {id:rowdata.id, stuffId: rowdata.stuffId, sum: rowdata.sum, invoiceRelatedDocumentId: ordersId};
//
//                $.ajax({
//                    url: '/invoice/request',
//                    type: 'POST',
//                    contentType: "application/json",
//                    data: JSON.stringify(invoiceDto),
//                    success: success,
//                    error: error,
//                    complete: function() {updateInvoicesSum(invoiceTable.data().toArray());}
//                });
//        },
//
//
//        onEditRow: function(datatable, rowdata, success, error) {
//
//            invoiceDto = {id: rowdata.id, stuffId: rowdata.stuffId, sum: rowdata.sum, invoiceRelatedDocumentId: ordersId};
//
//            $.ajax({
//                url: '/invoice/request',
//                type: 'PUT',
//                contentType: "application/json",
//                data: JSON.stringify(invoiceDto),
//                success: success,
//                error: error,
//                complete: function() {updateInvoicesSum(invoiceTable.data().toArray());}
//            });
//        }
//    });
//
//
//
//
////    var services;
////    var serviceMap = new Map();
////
////    $.ajax({
////            type: 'GET',
////            url: "/services/request",
////            async: false,
////            success: function (response) {services = response;}
////            });
////
////    services.forEach(function(service){
////        serviceMap.set(service.id, service.serviceType);
////        })
////
////    var servicesOptions = Object.fromEntries(serviceMap.entries());
//
//
////    $.ajax({
////          type: 'GET',
////          url: '/order/customer?' + $.param({orderId: ordersId}),
////          async: false,
////          success: function (response) {customer = response;}
////          });
//
////    var columnDefs = [
////
////        {
////        data: "id",
////        title: "id",
////        type: "readonly"
////        },
////        {
////        data: "serviceId",
////        title: "Услуга",
////        type : "select",
////        options : servicesOptions,
////        select1 : { width: "100%"},
////        render: function (data, type, row, meta) {
////
////            if (data == null || !(data in servicesOptions)) {
////            return null;
////            }
////
////            return servicesOptions[data];
////            }
////        },
////        {
////        data: "quantity",
////        title: "Количество",
////        type: "number",
////        required: true,
////        },
////        {
////        data: "totalPrice",
////        title: "сумма",
////        },
////    ];
//
////    var serviceTable;
////
////    serviceTable = $('#positions').DataTable({
////        "sPaginationType": "full_numbers",
////        "initComplete": function() {updateIncomeSum(serviceTable.data().toArray());},
////        ajax: {
////            url : '/positions/request?' + $.param({orderId: ordersId}),
////            // our data is an array of objects, in the root node instead of /data node, so we need 'dataSrc' parameter
////            dataSrc : ''
////        },
////        columns: columnDefs,
////        dom: 'Bfrtip',        // Needs button container
////        select: 'single',
////        responsive: true,
////        altEditor: true,     // Enable altEditor
////        buttons: [
////            {
////                text: 'Новый',
////                name: 'add'        // do not change name
////            },
////            {
////                extend: 'selected', // Bind to Selected row
////                text: 'Изменить данные',
////                name: 'edit'        // do not change name
////            },
////            {
////                extend: 'selected', // Bind to Selected row
////                text: 'Удалить',
////                name: 'delete'      // do not change name
////            },
////            {
////                text: 'Обновить таблицу',
////                name: 'refresh'      // do not change name
////            }
////        ],
////
////        onAddRow: function(datatable, rowdata, success, error) {
////
////        var position = {serviceId: rowdata.serviceId,
////                        quantity : rowdata.quantity,
////                        totalPrice : rowdata.totalPrice};
////
////            $.ajax({
////                url: '/positions/request?' + $.param({orderId: ordersId}),
////                type: 'POST',
////                contentType: "application/json",
////                data: JSON.stringify(position),
////                success: success,
////                error: error,
////                complete: function() {updateIncomeSum(serviceTable.data().toArray());}
////            });
////        },
////
////        onDeleteRow: function(datatable, rowdata, success, error) {
////            $.ajax({
////                url: '/positions/request?' + $.param({id: rowdata.id}), // выдает null
////                type: 'DELETE',
////                success: success,
////                error: error,
////                complete: function() {updateIncomeSum(serviceTable.data().toArray());}
////            });
////        },
////
////        onEditRow: function(datatable, rowdata, success, error) {
////
////            var position = {id: rowdata.id,
////                            serviceId: rowdata.serviceId,
////                            quantity : rowdata.quantity,
////                            totalPrice : rowdata.totalPrice};
////
////            $.ajax({
////                url: '/positions/request?' + $.param({orderId: ordersId}),
////                type: 'PUT',
////                contentType: "application/json",
////                data: JSON.stringify(position),
////                success: success,
////                error: error,
////                complete: function() {updateIncomeSum(serviceTable.data().toArray());}
////            });
////        }
////    });
//
//
//    var customer;
//
//    $.ajax({
//          type: 'GET',
//          url: '/order/customer?' + $.param({orderId: ordersId}),
//          async: false,
//          success: function (response) {customer = response;}
//          });
//
//
////    function updateClient() {
////        $("#chosen-customer").text("id " + customer["id"] + ", " + "name " + customer["name"]);
////    }
//
//
//    var costsSum = 0;
//    var incomeSum = 0;
//
//    function updateInvoicesSum(invoicesArr) {
//        costsSum = 0;
//
//        invoicesArr.forEach(function(invoice, i, invoicesArr) {
//                    costsSum += parseInt(invoicesArr[i].sum);
//                    });
//
//        var profitSum = incomeSum - costsSum;
//
//        $("#expenses").text(costsSum);
//        $("#profit").text(profitSum);
//    }
//
//
//    function updateIncomeSum(servicesArr) {
//            incomeSum = 0;
//
//            servicesArr.forEach(function(aService, i, servicesArr) {
//                        incomeSum += parseInt(servicesArr[i].totalPrice);
//                        });
//
//            var profitSum = incomeSum - costsSum;
//
//            $("#income").text(incomeSum);
//            $("#profit").text(profitSum);
//        }
//
//
//
//
//
//
////
////
////    var columnDefs = [
////
////        {
////        data: "id",
////        title: "id",
////        type: "readonly"
////        },
////        {
////        data: "name",
////        title: "Имя",
////        },
////        {
////        data: "surname",
////        title: "Фамилия"
////        },
////        {
////        data: "email",
////        title: "email"
////        },
////        {
////        data: "telephone",
////        title: "Телефон"
////        },
////    ];
////
////    var myTable;
////
////    myTable = $('#order-customer').DataTable({
////        "sPaginationType": "full_numbers",
////        ajax: {
////            url : '/customers/request',
////            // our data is an array of objects, in the root node instead of /data node, so we need 'dataSrc' parameter
////            dataSrc : ''
////        },
////        columns: columnDefs,
////        dom: 'Bfrtip',        // Needs button container
////        select: 'single',
////        responsive: true,
////        altEditor: true,     // Enable altEditor
////        buttons: [
////            {
////                text: 'Новый',
////                name: 'add'        // do not change name
////            },
////            {
////                extend: 'selected', // Bind to Selected row
////                text: 'Изменить данные',
////                name: 'edit'        // do not change name
////            },
////            {
////                extend: 'selected', // Bind to Selected row
////                text: 'Удалить',
////                name: 'delete'      // do not change name
////            },
////            {
////                text: 'Обновить таблицу',
////                name: 'refresh'      // do not change name
////            },
////            {
////                text: 'Выбрать клиента',
////                name: 'choose',
////                action: changeClient
////            }
////        ],
////
////        onAddRow: function(datatable, rowdata, success, error) {
////            $.ajax({
////                url: '/customers/request',
////                type: 'POST',
////                contentType: "application/json",
////                data: JSON.stringify(rowdata),
////                success: success,
////                error: error
////            });
////        },
////
////        onDeleteRow: function(datatable, rowdata, success, error) {
////            $.ajax({
////                url: '/customers/request?' + $.param({id: rowdata.id}), // выдает null
////                type: 'DELETE',
////                success: success,
////                error: error
////            });
////        },
////
////        onEditRow: function(datatable, rowdata, success, error) {
////            $.ajax({
////                url: '/customers/request',
////                type: 'PUT',
////                contentType: "application/json",
////                data: JSON.stringify(rowdata),
////                success: success,
////                error: error
////            });
////        }
////    });
////
////    function changeClient () {
////            var selectedCustomerId = myTable.row('.selected').data().id;
////            $.ajax({
////                   url: '/order/customer?' + $.param({orderId: ordersId}) + "&" +$.param({customerId: selectedCustomerId}), // выдает null
////                   type: 'PUT',
////                   success: function(){
////                        customer = myTable.row('.selected').data();
////                        updateClient();
////                   }
////            });
////    }
////
////    updateClient();
////    $("#order-id").text(ordersId);
//
//
//});