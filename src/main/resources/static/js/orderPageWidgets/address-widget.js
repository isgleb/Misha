
$("#edit-address").click(function() {
            newAddress =  {};


            newAddress.yandexAddress = document.getElementById('ya-address').innerHTML;
            newAddress.entrance = document.getElementById('entrance').value;
            newAddress.level = document.getElementById('level').value;
            newAddress.accommodation = document.getElementById('accommodation').value;
            newAddress.intercom = document.getElementById('intercom').value;

            updateOrderAddress(newAddress);
        });