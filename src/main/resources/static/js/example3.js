$(document).ready(function() {

    var columnDefs = [

//        private long id;
//        private String status;
//        private int customerID;
//        private int cleanigServicesID; // one to many connection/  to test - only one
//        private String address;
//        String dateRecieved;
//        String datetimeOrder;
        {
        data: "id",
        title: "Id",
        type: "readonly"
        },
        {
        data: "status",
        title: "Status"
        },
        {
        data: "customerID",
        title: "CustomerID"
        },
        {
        data: "cleanigServicesID",
        title: "cleanigServicesID"
        },
        {
        data: "address",
        title: "Address"
        },
        {
        data: "dateRecieved",
        title: "dateRecieved"
        },
        {
        data: "dateTimeOrder",
        title: "dateTimeOrder"
        },
        {
        data: "totalPrice",
        title: "totalPrice"
        },

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
                url: '/orders/request',
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(rowdata),
                success: success,
                error: error
            });
        },

        onDeleteRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: '/orders/request?' + $.param({id: rowdata.id}), // выдает null
                type: 'DELETE',
                success: success,
                error: error
            });
        },

        onEditRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: '/orders/request',
                type: 'PUT',
                contentType: "application/json",
                data: JSON.stringify(rowdata),
                success: success,
                error: error
            });
        }
    });
});