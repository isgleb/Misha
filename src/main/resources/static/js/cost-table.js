$(document).ready(function() {

    var table = $('#cost-table').DataTable( {
    responsive: true,
     "ajax": {
                 "url": "costs-table",
                 "dataSrc": ""
             },
     "columns": [

         private long id;
         private int date;
         private String contragent;
         private int sum;


                    {
                    "data": "id",
                    "title": "Id",
                    "type": "readonly"
                    },

                    {
                    "data": "date",
                    "title": "Дата",
                    },
                    {
                    "data": "contragent",
                    "title": "Контрагент"
                    },
                    {
                    "data": "sum",
                    "title": "Сумма"
                    },
                ],
    })


    $('#example tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        } );

    $('#delete').click( function () {

        $.ajax({
                url: '/orders/request?' + $.param({id: table.row('.selected').data().id}), // выдает null
                type: 'DELETE',
//                success: success;
//                error: error
                });
        table.row('.selected').remove().draw( false );
    } );


    $('#edit').click( function () {
        var id = table.row('.selected').data().id;
        return location.href = '/order/' + table.row('.selected').data().id

    } );
})