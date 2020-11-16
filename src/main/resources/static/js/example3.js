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
                // a tipycal url would be
                url: '/orders/request',
                type: 'POST',
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(rowdata),
                cache: false,
                processData: false,
                success: success,
                error: error
            });
        },

        onDeleteRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: '/orders/request?' + $.param({id: rowdata.id}), // выдает null
                type: 'DELETE',
                cache: false,
                processData: false,
                success: success,
                error: error
            });
        },

        onEditRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: '/orders/request?' + $.param({id: rowdata.id}),
                type: 'PUT',
                cache: false,
                processData: false,
                success: success,
                error: error
            });
        }
    });
});