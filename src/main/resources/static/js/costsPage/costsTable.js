$(document).ready(function() {


var costsColumns = [
    {
    data: "id",
    title: "id",
    type: "readonly"
    },
    {
    data: "date",
    title: "Дата"
    },
    {
    data: "contragent",
    title: "Контрагент",
    required: true,
    },
    {
    data: "sum",
    title: "Сумма",
    type: "number",
    required: true,
    },
];

    var table = $('#costs-table').DataTable( {

       "sPaginationType": "full_numbers",
//        "initComplete": function() {
//            updateContragent(invoiceTable.data().toArray());
//        },


        ajax: {
            url : "/costs-table",
            dataSrc : '',

        },
        columns: costsColumns,
        dom: 'Bfrtip',        // Needs button container
        select: 'single',
        responsive: true,
        altEditor: true,     // Enable altEditor
        buttons: [
            {
                text: 'Новый',

                action: function(){window.location.replace("/newCostPage");}
            },
            {
                extend: 'selected', // Bind to Selected row
                text: 'Изменить данные',
                action: function(){window.location.replace(window.location.protocol + "/cost/" + table.row('.selected').data().id)}
            },
            {
                extend: 'selected', // Bind to Selected row
                text: 'Удалить',
                action: function () {
                                $.ajax({
                                        url: '/cost/delete?' + $.param({id: table.row('.selected').data().id}), // выдает null
                                        type: 'DELETE',
                                        success: function() {table.row('.selected').remove().draw( false );}
                        //                error: error
                                        });
//                                table.row('.selected').remove().draw( false );
                                }
            },
            {
                text: 'Обновить таблицу',
                name: 'refresh'      // do not change name
            }
        ],
    });

})