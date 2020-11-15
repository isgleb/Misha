$(document).ready(function() {

    var columnDefs = [
        {
        data: "id",
        title: "Id",
        type: "readonly"
        },
        {
        data: "customer",
        title: "Customer"
        },
        {
        data: "price",
        title: "Price"
        },
        {
        data: "address",
        title: "Address"
        }
    ];

    var myTable;

    myTable = $('#example').DataTable({
        "sPaginationType": "full_numbers",
        ajax: {
            url : '/orders/request',
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
                // a tipycal url would be / with type='PUT' ссылка передается  корректно
                url: '/orders/requestok',
                type: 'POST',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify({"id": 23, "customer": "Gleb", "price": 10010, "address": "Moskow"}),
                cache: false,
                processData: false,
                success: success,
                error: error
            });
        },
        onDeleteRow: function(datatable, rowdata, success, error) {
            $.ajax({
                // a tipycal url would be /{id} with type='DELETE'
                url: url_ws_mock_ok,
                type: 'GET',
                data: rowdata,
                success: success,
                error: error
            });
        },
        onEditRow: function(datatable, rowdata, success, error) {
            $.ajax({
                // a tipycal url would be /{id} with type='POST'
                url: url_ws_mock_ok,
                type: 'POST',
                data: {
                          "id": 23,
                          "customer": "Gleb",
                          "price": 10010,
                          "address": "Moskow"
                      },
                success: success,
                error: error
            });
        }
    });


});