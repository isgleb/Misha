//$(document).ready(function() {

    var orderStatusButton = document.querySelector('#dropdownMenuLink');
//    var orderStatus = "accepted";


    let statusOptions = new Map();

    statusOptions.set("accepted", {"buttonText": "Принят", "buttonClass" : "btn-info"})
                    .set("done", {"buttonText": "Выполнен", "buttonClass" : "btn-success"})
                    .set("canceled", {"buttonText": "Отменен", "buttonClass" : "btn-danger"});

    updateStatus(orderDetails.status);

    function updateStatus(status){
        orderStatusButton.className = statusOptions.get(status).buttonClass;
        orderStatusButton.classList.add("btn");
        orderStatusButton.classList.add("dropdown-toggle");
        orderStatusButton.innerText = statusOptions.get(status).buttonText;
    };

    $("#accepted").click(function() {
        orderStatus = "accepted";
        updateStatus(orderStatus);
    });

    $("#done").click(function() {
        orderStatus = "done";
        updateStatus(orderStatus);
    });

    $("#canceled").click(function() {
        orderStatus = "canceled";
        updateStatus(orderStatus);
    });
//});