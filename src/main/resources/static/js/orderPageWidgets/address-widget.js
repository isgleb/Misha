


$("#edit-address").click(function() {

            address.yandexAddress = document.getElementById('ya-address').innerHTML;
            address.entrance = document.getElementById('entrance').value;
            address.level = document.getElementById('level').value;
            address.accommodation = document.getElementById('accommodation').value;
            address.intercom = document.getElementById('intercom').value;

            updateResultTable();
        });