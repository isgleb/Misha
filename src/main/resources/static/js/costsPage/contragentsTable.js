$(document).ready(function() {

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
        {
        data: "stuffStatus",
        title: "Статус",
        type : "select",
        options : {"работает" : "работает", "уволен" : "уволен"},
        },
    ];

    var contragentsTable;

    contragentsTable = $('#contragents-table').DataTable({
        "sPaginationType": "full_numbers",
        ajax: {
            url : '/stuff/request',
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
            {
                text: 'Выбрать контрагента',
                name: 'choose',
                action: function() { updateCostContragent(contragentsTable.row('.selected').data());}
            }
        ],

        onAddRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: 'stuff/request',
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(rowdata),
                success: success,
                error: error
            });
        },

        onDeleteRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: 'stuff/request?' + $.param({id: rowdata.id}), // выдает null
                type: 'DELETE',
                success: success,
                error: error
            });
        },

        onEditRow: function(datatable, rowdata, success, error) {
            $.ajax({
                url: 'stuff/request',
                type: 'PUT',
                contentType: "application/json",
                data: JSON.stringify(rowdata),
                success: success,
                error: error
            });
        }
    });
});