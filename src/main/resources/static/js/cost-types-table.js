// вызов базовой функции jQuery
$(document).ready(function() {

    var columnDefs = [

        {
        data: "id",
        title: "id",
        type: "readonly"
        },
        {
        data: "costType",
        title: "Вид затрат",
        required: true,
        unique: true
        },
    ];

    var myTable;

    myTable = $('#cost-Types-table').DataTable({
        "sPaginationType": "full_numbers",
        ajax: {
            url : '/costs-types/request',
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
        ],

        onAddRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: 'costs-types/request',
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(rowdata),
                success: success,
                error: error
            });
        },

        onDeleteRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: 'costs-types/request?' + $.param({id: rowdata.id}), // выдает null
                type: 'DELETE',
                success: success,
                error: error
            });
        },

        onEditRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: 'costs-types/request',
                type: 'PUT',
                contentType: "application/json",
                data: JSON.stringify(rowdata),
                success: success,
                error: error
            });
        }
    });
});