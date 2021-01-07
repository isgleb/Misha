
datet = $('#date-time-selector').datetimepicker({

    yearStart: 2020,
    dayOfWeekStart : 1,
    step: 5,
    defaultTime: orderDetails.date.getHours()+":"+orderDetails.date.getMinutes()+":0",
    lang:'ru',
    inline: true,
    onChangeDateTime: function(dp,$input) {
        theDate = $('#date-time-selector').datetimepicker('getValue');
        updateOrderDate(theDate);
//        document.getElementById("date-time").value = theDate.toLocaleString();
//        $("#date-time").text(theDate.toLocaleString());


        },
});

$.datetimepicker.setLocale('ru');

//$("#date-time").text($('#date-time-selector').datetimepicker('getValue').toLocaleString());