$(document).ready(function() {

    var table = $('#example').DataTable( {
//    responsive: true,
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
                    "title": "Status",
                    },
                    {
                    "data": "customerName",
                    "title": "CustomerName"
                    },
                    {
                    "data": "customerSurname",
                    "title": "CustomerSurname"
                    },
                    {
                    "data": "customerTelephone",
                    "title": "CustomerTelephone"
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
        return location.href = '/order?' + $.param({id: table.row('.selected').data().id})
    } );


})