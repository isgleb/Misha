
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
        data: "totalPrice",
        title: "сумма",
        },
    ];

    var serviceTable;
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
                      totalPrice : rowdata["totalPrice"]
                      };

            serviceFrontId += 1;

            servicesArr.push(theRow);
            updateIncomeSum(servicesArr);
            success(theRow);

        },

        onDeleteRow: function(datatable, rowdata, success, error) {

            const index = servicesArr.findIndex(n => n.id === rowdata["id"]);
            if (index !== -1) {servicesArr.splice(index, 1);}

            updateIncomeSum(servicesArr);
            success(rowdata);

        },

        onEditRow: function(datatable, rowdata, success, error) {

            const index = servicesArr.findIndex(n => n.id === rowdata["id"]);

            theRow = {id: newId,
                      serviceId: rowdata["serviceId"],
                      stuffId: rowdata["stuffId"],
                      totalPrice : rowdata["totalPrice"]
                      };

            servicesArr[index] =  theRow;
            updateIncomeSum(servicesArr);
            success(rowdata);
        }
    });