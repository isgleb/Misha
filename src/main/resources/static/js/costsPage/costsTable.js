$(document).ready(function() {


var costsColumns = [
    {
    data: "id",
    title: "id",
    type: "readonly"
    },
    {
    data: "date",
    title: "Дата",
    required: true,
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
//            updateInvoicesSum(invoiceTable.data().toArray());
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
//                name: 'add'        // do not change name
                action: function(){window.location.replace("/newCostPage");}
            },
            {
                extend: 'selected', // Bind to Selected row
                text: 'Изменить данные',
//                name: 'edit'        // do not change name

//    $('#edit').click( function () {
//        var id = table.row('.selected').data().id;
//        return location.href = '/order/' + table.row('.selected').data().id
//
//    } );

            },
            {
                extend: 'selected', // Bind to Selected row
                text: 'Удалить',
                name: 'delete'      // do not change name

//                  $('#delete').click( function () {
//
//                        $.ajax({
//                                url: '/orders/request?' + $.param({id: table.row('.selected').data().id}), // выдает null
//                                type: 'DELETE',
//                //                success: success;
//                //                error: error
//                                });
//                        table.row('.selected').remove().draw( false );
//                    } );




            },
            {
                text: 'Обновить таблицу',
                name: 'refresh'      // do not change name
            }
        ],
    })

})