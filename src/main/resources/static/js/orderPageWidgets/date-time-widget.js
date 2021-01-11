$(document).ready(function() {

    $('#date-time-selector').datetimepicker({

        yearStart: 2020,
        dayOfWeekStart : 1,
        step: 5,
        defaultTime: orderDetails.date.getHours() +":" + orderDetails.date.getMinutes(),
        defaultDate: orderDetails.date.getFullYear()+ "/" + (orderDetails.date.getMonth()+ 1) + "/" + orderDetails.date.getDate(),
        lang:'ru',
        inline: true,
        onChangeDateTime: function() {updateOrderDate($('#date-time-selector').datetimepicker('getValue'));},
    });

    $.datetimepicker.setLocale('ru');

})