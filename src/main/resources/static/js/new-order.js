
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


        var costsSum = 0;
        var incomeSum = 0;

        invoicesArr.forEach(function(invoice, i, invoicesArr) {
            costsSum += parseInt(invoicesArr[i].sum);
            });

     
        servicesArr.forEach(function(aService, i, servicesArr) {
            incomeSum += parseInt(servicesArr[i].totalPrice);
            });

        var profitSum = incomeSum - costsSum;


        $("#profit").text(profitSum);
        $("#expenses").text(costsSum);
        $("#income").text(incomeSum);

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




//            $.ajax({
//                url: '/positions/request?' + $.param({orderId: ordersId}),
//                type: 'POST',
//                contentType: "application/json",
//                data: JSON.stringify(position),
//                success: success,
//                error: error
//            });







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

    var serviceColumns = [

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
    var servicesArr = [];
    var serviceFrontId = 1;

    serviceTable = $('#positions').DataTable({
        "sPaginationType": "full_numbers",

        data: servicesArr,
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
                          serviceId: rowdata["serviceId"],
                          stuffId: rowdata["stuffId"],
                          quantity : rowdata["quantity"],
                          totalPrice : rowdata["totalPrice"]
                          };

                serviceFrontId += 1;

                servicesArr.push(theRow);
                updateResultTable();
                success(theRow);

        },

        onDeleteRow: function(datatable, rowdata, success, error) {

            const index = servicesArr.findIndex(n => n.id === rowdata["id"]);
            if (index !== -1) {servicesArr.splice(index, 1);}

            updateResultTable();
            success(rowdata);

        },

        onEditRow: function(datatable, rowdata, success, error) {

            const index = servicesArr.findIndex(n => n.id === rowdata["id"]);

            theRow = {id: newId,
                      serviceId: rowdata["serviceId"],
                      stuffId: rowdata["stuffId"],
                      quantity : rowdata["quantity"],
                      totalPrice : rowdata["totalPrice"]
                      };

            servicesArr[index] =  theRow;
            success(rowdata);
        }
    });






});