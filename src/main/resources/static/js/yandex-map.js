
function init() {

//        var myPlacemark;
        var myMap = new ymaps.Map('map', {
            center: [55.753994, 37.622093],
            zoom: 9,
            controls: []
        });


        var searchControl = new ymaps.control.SearchControl({
                    options: {
                    provider: 'yandex#map'
                    }
                });

//      Добавим поиск на карту
        myMap.controls.add(searchControl);


//      Нужное нам событие (выбор результата поиска)
        searchControl.events.add('resultselect', function(e) {
            var index = e.get('index');
            searchControl.getResult(index).then(function(res) {

                coords = res.geometry.getCoordinates();


                console.log(coords);


                var myPlacemark = new ymaps.Placemark(coords, {}, {
                draggable: true,
                preset: "islands#circleDotIcon",
                // Задаем цвет метки (в формате RGB).
                iconColor: '#ff0000'});
                myMap.geoObjects.add(myPlacemark);

                myPlacemark.events.add('dragend', function(e) {
                   // Получение ссылки на объект, который был передвинут.
                   var thisPlacemark = e.get('target');
                   // Определение координат метки
                   var coords = thisPlacemark.geometry.getCoordinates();
                   // и вывод их при щелчке на метке
                   thisPlacemark.properties.set('balloonContent', coords);
                });


            });
        });
}

ymaps.ready(init);




