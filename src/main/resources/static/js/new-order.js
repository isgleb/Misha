
$(document).ready(function() {

//    var invoices = new Array;
//    var positions = new Array;
//
//    var order = {
//                id: null,
//                status:"принят",
//                customerId: null,
//                invoices: invoices,
//                positions: null,
//                income: null,
//                outlay: null,
//                profit: null
//                };







    var currentPage = (window.location.href.split("/").pop());

    if (currentPage == "new-order") {
        orderId = null;
    } else {
        orderId = parseInt(currentPage)
    }

//    var orderId = 1;

    var customer;

    if (orderId !== null) {
        $.ajax({
                     type: 'GET',
                     url: '/order/customer?' + $.param({orderId: orderId}),
                     async: false,
                     success: function (response) {customer = response;}
                     });

            $("#chosen-customer").text("id " + customer["id"] + ", " + "name " + customer["name"]);
    }



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
            var selectedCustomerId = clientsTable.row('.selected').data().id;
            if (orderId !== null) {
                $.ajax({
                       url: '/order/customer?' + $.param({orderId: orderId}) + "&" +$.param({customerId: selectedCustomerId}), // выдает null
                       type: 'PUT',
                       success: function(){
                            customer = clientsTable.row('.selected').data();
                            $("#chosen-customer").text("id " + customer["id"] + ", " + "name " + customer["name"]);
                       }
                });
            } else {
                customer = clientsTable.row('.selected').data();
                $("#chosen-customer").text("id " + customer["id"] + ", " + "name " + customer["name"]);
            }
    }


//    function createOrder(customer) {
//        $.ajax({
//          url: '/order/create-new_order?' + $.param({customerId: customer["id"]}),
//          type: "POST",
//          data: data,
//          success: success,
//          dataType: dataType
//        });
//    }


















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
        buttons: [{
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
                          sum: rowdata["sum"]
                          };

                invoiceFrontId += 1;

                invoicesArr.push(theRow);
                success(theRow);
             },

             onDeleteRow: function(datatable, rowdata, success, error) {

                if (rowdata["id"].includes('new')) {
                    const index = invoicesArr.findIndex(n => n.id === rowdata["id"]);
                    if (index !== -1) {
                      invoicesArr.splice(index, 1);
                    }
                }
                success(rowdata);

             }


//             //        invoiceDto = {id:rowdata.id, stuffId: rowdata.stuffId, sum: rowdata.sum, invoiceRelatedDocumentId: ordersId}
//             //
//             //        console.log(invoiceDto);
//             //            $.ajax({
//             //                url: '/invoice/request',
//             //                type: 'POST',
//             //                contentType: "application/json",
//             //                data: JSON.stringify(invoiceDto),
//             //                success: success,
//             //                error: error
//             //            });
//                     }
      });

    $("#save").click(function() {
                //действия
                console.log(invoicesArr)
            });



//        ajax: {
//            url : '/invoice/request?' + $.param({orderId: orderId}),
//            // our data is an array of objects, in the root node instead of /data node, so we need 'dataSrc' parameter
//            dataSrc : ''
//        },
//
//
//        data: invoicesArr,
////        columns: invoiceColumns,
//        dom: 'Bfrtip',        // Needs button container
//        select: 'single',
//        responsive: true,
//        altEditor: true,     // Enable altEditor
//        buttons: [
//            {
//                text: 'Add',
//                name: 'add'        // do not change name
//            },
//            {
//                extend: 'selected', // Bind to Selected row
//                text: 'Edit',
//                name: 'edit'        // do not change name
//            },
//            {
//                extend: 'selected', // Bind to Selected row
//                text: 'Delete',
//                name: 'delete'      // do not change name
//            }
////            {
////                text: 'Refresh',
////                name: 'refresh'      // do not change name
////            }
//        ],
//
////        onDeleteRow: function(datatable, rowdata, success, error) {
////                    $.ajax({
////                        url: '/invoice/request?' + $.param({id: rowdata.id}), // выдает null
////                        type: 'DELETE',
////                        success: success,
////                        error: error
////                    });
////                },
////
////
////        onAddRow: function(datatable, rowdata, success, error) {
////        console.log("why")
//
////        invoiceDto = {id:rowdata.id, stuffId: rowdata.stuffId, sum: rowdata.sum, invoiceRelatedDocumentId: ordersId}
////
////        console.log(invoiceDto);
////            $.ajax({
////                url: '/invoice/request',
////                type: 'POST',
////                contentType: "application/json",
////                data: JSON.stringify(invoiceDto),
////                success: success,
////                error: error
////            });
////        },
//
//
////        onEditRow: function(datatable, rowdata, success, error) {
////            $.ajax({
////                url: '/invoice/request?',
//                type: 'PUT',
//                contentType: "application/json",
//                data: JSON.stringify(rowdata),
//                success: success,
//                error: error
////            });
////        }
//    });








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