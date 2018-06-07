

wireCarousel()


function wireCarousel() {

    $.ajax({
        type: "GET",
        url: "/api/campsite/top3",

        success: function (campsites) {

            for (i = 0; i < campsites.length; i++) {

                var slide = $("#slide" + i);
                var site = campsites[i]
                
                var html = "<div class='col-md-6'><div class='info'>";
                html += "<a href=/explore/campsite/" + site.id + " class='siteName'>" + site.name + "</a>";
                html += "<p>" + site.location.park.name + ", ";
                html += site.location.state.name + "</p>";
                html += "<p>Features:</p>"
                html += "<ul>";

                for (j = 0; j < site.features.length; j++) {
                    html += "<li>" + site.features[j].name + "</li>";
                }

                html += "</ul>";
                html += "</div></div>";

                slide.prepend(html);
            }
        },

        error: function () {

        }
    })
}

function renderMaps() {

    var id = $("#campsiteId").val();

    $.ajax({
        type: "GET",
        url: "/api/campsite/top3",

        success: function (campsites) {

            location0 = campsites[0].location;
            location1 = campsites[1].location;
            location2 = campsites[2].location;

            var pin0 = { lat: location0.latitude, lng: location0.longitude };
            var pin1 = { lat: location1.latitude, lng: location1.longitude };
            var pin2 = { lat: location2.latitude, lng: location2.longitude };

            var map0 = new google.maps.Map(
                document.getElementById("map0"), { zoom: 5, center: pin0, mapTypeId: "hybrid" });
            var map1 = new google.maps.Map(
                document.getElementById("map1"), { zoom: 5, center: pin1, mapTypeId: "hybrid" });
            var map2 = new google.maps.Map(
                document.getElementById("map2"), { zoom: 5, center: pin2, mapTypeId: "hybrid" });

            var marker0 = new google.maps.Marker({ position: pin0, map: map0 });
            var marker1 = new google.maps.Marker({ position: pin1, map: map1 });
            var marker2 = new google.maps.Marker({ position: pin2, map: map2 });

        },

        error: function () {

        }
    })
}


