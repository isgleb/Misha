//$(document).ready(function() {


var stuff;
var stuffMap = new Map();
var incomeSum = 0;

$.ajax({
        type: 'GET',
        url: "/active-stuff/request",
        async: false,
        success: function (response) {stuff = response;}
        });


stuff.forEach(function(theStuff){
    stuffMap.set(theStuff.id, theStuff.name);
    })




var stuffOptions = Object.fromEntries(stuffMap.entries());

var invoiceColumns = [
    {
    data: "id",
    title: "id",
    type: "readonly"
    },
    {
    data: "contragentId",
    title: "Сотрудник",
    type : "select",
    options : stuffOptions,
    select1 : { width: "100%"},
    render: function (data, type, row, meta) {
        if (data == null || !(data in stuffOptions)) {
                $.ajax({
                        type: 'GET',
                        url: "/get-the-stuff/request?" + $.param({contragentId: data}),
                        async: false,
                        success: function (response) {
                            stuffMap.set(response.id, response.name)
                            stuffOptions = Object.fromEntries(stuffMap.entries());
                            }
                        });
        }
        return stuffOptions[data];
        }
    },
    {
    data: "sum",
    title: "Сумма",
    type: "number",
    required: true,
    },
];

var invoiceTable;

invoiceTable = $('#invoices').DataTable({


    "sPaginationType": "full_numbers",
    "initComplete": function() {
        updateInvoicesSum(invoiceTable.data().toArray());
    },


    ajax: {
        url : '/invoice/request?' + $.param({orderId: orderId}),
        dataSrc : '',

    },
    columns: invoiceColumns,
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
        }
    ],

    onDeleteRow: function(datatable, rowdata, success, error) {
                $.ajax({
                    url: '/invoice/request?' + $.param({id: rowdata.id}), // выдает null
                    type: 'DELETE',
                    success: success,
                    error: error,
                    complete: function() {updateInvoicesSum(invoiceTable.data().toArray());}
                });
            },


    onAddRow: function(datatable, rowdata, success, error) {

        invoiceDto = {id:rowdata.id, contragentID: rowdata.contragentID, sum: rowdata.sum, invoiceRelatedDocumentId: orderId};

            $.ajax({
                url: '/invoice/request',
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(invoiceDto),
                success: success,
                error: error,
                complete: function() {updateInvoicesSum(invoiceTable.data().toArray());}
            });
    },


    onEditRow: function(datatable, rowdata, success, error) {

        invoiceDto = {id: rowdata.id, contragentID: rowdata.contragentID, sum: rowdata.sum, invoiceRelatedDocumentId: orderId};

        $.ajax({
            url: '/invoice/request',
            type: 'PUT',
            contentType: "application/json",
            data: JSON.stringify(invoiceDto),
            success: success,
            error: error,
            complete: function() {updateInvoicesSum(invoiceTable.data().toArray());}
        });
    }
});

//});