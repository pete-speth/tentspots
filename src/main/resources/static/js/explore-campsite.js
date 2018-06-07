

displayRecentLogs();


function displayRecentLogs() {

    var id = $("#campsiteId").val();

    $.ajax({
        type: "GET",
        url: "/api/campsite/" + id + "/recentVisits",

        success: function (visits) {
            
            for (var i = 0; i < visits.length; i++) {

                var html = "<div class='visitView'>";
                html += "<div class='visitHeader'>"
                html += "<span class='name'>" + visits[i].groupLeader + "</span>";
                html += "<span class='dates'>" + visits[i].startDateStr + " - " + visits[i].endDateStr + "</span>";
                html += "</div>"
                html += "<p class='notes'>" + visits[i].notes + "</p>";
                html += "</div>";

                $("#recentVisits").append(html);
            }
        },

        error: function () {

        }
    })
}


function renderMap() {

    var id = $("#campsiteId").val();

    $.ajax({
        type: "GET",
        url: "/api/campsite/" + id + "/location",

        success: function (location) {

            var pin = { lat: location.latitude, lng: location.longitude };

            var map = new google.maps.Map(
                document.getElementById("map"), { zoom: 15, center: pin, mapTypeId: "hybrid" });

            var marker = new google.maps.Marker({ position: pin, map: map });

        }
    })
}