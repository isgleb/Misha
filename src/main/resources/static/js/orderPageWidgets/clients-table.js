
var clientsTableColumns = [

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


var clientsTable = $('#order-customer').DataTable({
    "sPaginationType": "full_numbers",
    ajax: {
        url : '/customers/request',
        // our data is an array of objects, in the root node instead of /data node, so we need 'dataSrc' parameter
        dataSrc : ''
    },
    columns: clientsTableColumns,
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
            action: function() { updateOrderCustomer(clientsTable.row('.selected').data());}
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