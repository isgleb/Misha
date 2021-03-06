$(document).ready(function() {

    var table = $('#example').DataTable( {
    responsive: true,
     "ajax": {
                 "url": "orders-table",
                 "dataSrc": ""
             },
     "columns": [
                    {
                    "data": "id",
                    "title": "Id",
                    "type": "readonly"
                    },

                    {
                    "data": "status",
                    "title": "Статус",
                    },
                    {
                    "data": "date",
                    "title": "Дата, время"
                    },
                    {
                    "data": "meters",
                    "title": "Метраж"
                    },
                    {
                    "data": "customerNameAndTelephone",
                    "title": "Клиент"
                    },
                    {
                    "data": "stuff",
                    "title": "Сотрудники"
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