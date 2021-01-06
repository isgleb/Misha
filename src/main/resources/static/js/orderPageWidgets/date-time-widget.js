var now = new Date();

datet = $('#date-time-selector').datetimepicker({

    yearStart: 2020,
    dayOfWeekStart : 1,
    step: 5,
    defaultTime: now.getHours()+":"+now.getMinutes()+":0",
    lang:'ru',
    inline: true,
    onChangeDateTime: function(dp,$input){
        theDate = $('#date-time-selector').datetimepicker('getValue');
//        document.getElementById("date-time").value = theDate.toLocaleString();
        $("#date-time").text(theDate.toLocaleString());


        },
});

$.datetimepicker.setLocale('ru');
datet.width(10000);

//document.getElementById("date-time").value = $('#date-time-selector').datetimepicker('getValue').toLocaleString();

$("#date-time").text($('#date-time-selector').datetimepicker('getValue').toLocaleString());