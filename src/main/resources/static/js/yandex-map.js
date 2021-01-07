
function init() {

//        var myPlacemark;
        var myMap = new ymaps.Map('map', {
            center: [55.753994, 37.622093],
            zoom: 9,
            controls: []
        });

        var zoomControl = new ymaps.control.ZoomControl({
                options: {
                    size: "large"
                }
            });
        myMap.controls.add(zoomControl);

        var trafficControl = new ymaps.control.TrafficControl({state: {trafficShown: false}});
        myMap.controls.add(trafficControl, {top: 10, left: 10});


        var searchControl = new ymaps.control.SearchControl({
                    options: {
                    provider: 'yandex#map',
                    noPlacemark: true
                    }
                });

//      Добавим поиск на карту
        myMap.controls.add(searchControl);


//      Нужное нам событие (выбор результата поиска)
        searchControl.events.add('resultselect', function(e) {
            var index = e.get('index');
            searchControl.getResult(index).then(function(res) {

                coords = res.geometry.getCoordinates();

                var myPlacemark = new ymaps.Placemark(coords, {}, {
                    draggable: true,
                    // Задаем цвет метки (в формате RGB).
                    iconColor: '#ff0000'
                    });


                myMap.geoObjects.removeAll();
                myMap.geoObjects.add(myPlacemark);


                getAddress(coords);
                myPlacemark.balloon.open();

                myPlacemark.events.add('dragend', function(e) {
                   // Получение ссылки на объект, который был передвинут.
                   myPlacemark = e.get('target');
                   // Определение координат метки
                   coords = myPlacemark.geometry.getCoordinates();

                   getAddress(coords);
                   myPlacemark.balloon.open();
                });


                function getAddress(coords) {
                    myPlacemark.properties.set('iconCaption', 'поиск...');
                    ymaps.geocode(coords).then(function (res) {
                        var firstGeoObject = res.geoObjects.get(0);

                        myPlacemark.properties
                            .set({
                                // Формируем строку с данными об объекте.
                                iconCaption: [
                                    // Название населенного пункта или вышестоящее административно-территориальное образование.
                                    firstGeoObject.getLocalities().length ? firstGeoObject.getLocalities() : firstGeoObject.getAdministrativeAreas(),
                                    // Получаем путь до топонима, если метод вернул null, запрашиваем наименование здания.
                                    firstGeoObject.getThoroughfare() || firstGeoObject.getPremise()
                                ].filter(Boolean).join(', '),

                                balloonContent: firstGeoObject.getAddressLine() + '<br> <button id="choose-address">Выбрать адрес</button>'

                            });

                        $("#choose-address").click(function() {
                            $("#ya-address").text(firstGeoObject.getAddressLine());
                        })
                    });
                }
            });
        });
}

ymaps.ready(init);
