var statusOptions = { "принят" : "принят", "выполнен" : "выполнен"};

// вызов базовой функции jQuery
$(document).ready(function() {

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    var orderId = urlParams.get('id');

    var customer;

    $.ajax({
          type: 'GET',
          url: '/order/customer?' + $.param({id: orderId}),
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
                text: 'Add',
                name: 'add'        // do not change name
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
            },
            {
                text: 'Refresh',
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

    $('#choose').click( function () {

        var selectedCustomerId = myTable.row('.selected').data().id;

//        $.ajax({
//               url: '/order/customer?' + $.param({id: orderId}{customerId: selectedCustomerId}), // выдает null
//               type: 'PUT'
//                });


    });

});