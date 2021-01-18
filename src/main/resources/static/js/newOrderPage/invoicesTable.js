var stuff;

var stuffMap = new Map();

$.ajax({
        type: 'GET',
        url: "/active-stuff/request",
        async: false,
        success: function (response) {stuff = response;}
        });

stuff.forEach(function(theStuff){stuffMap.set(theStuff.id, theStuff.name);})

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
        if (data == null || !(data in stuffOptions)) {return null;}
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

invoiceFrontId = 1;

invoiceTable = $('#invoices').DataTable({
    "sPaginationType": "full_numbers",

    data: invoicesArr,
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
        }
    ],
    onAddRow: function(datatable, rowdata, success, error) {

            newId = "new" + invoiceFrontId.toString();
            theRow = {id: newId,
                      contragentId: rowdata["contragentId"],
                      sum: rowdata["sum"],
                      invoiceRelatedDocumentId: null
                      };

            invoiceFrontId += 1;

            invoicesArr.push(theRow);
            updateInvoicesSum(invoicesArr);
            success(theRow);
         },

    onDeleteRow: function(datatable, rowdata, success, error) {

            const index = invoicesArr.findIndex(n => n.id === rowdata["id"]);
            if (index !== -1) {invoicesArr.splice(index, 1);}

            updateInvoicesSum(invoicesArr);
            success(rowdata);
         },

    onEditRow: function(datatable, rowdata, success, error) {

            const index = invoicesArr.findIndex(n => n.id === rowdata["id"]);

            theRow = {id: rowdata["id"],
                      contragentId: rowdata["contragentId"],
                      sum: rowdata["sum"],
                      invoiceRelatedDocumentId: null
                      };

            invoicesArr[index] = theRow;
            updateInvoicesSum(invoicesArr);
            success(rowdata);
            }
  });