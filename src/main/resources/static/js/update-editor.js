
newPark = true;
var id = $("#campsiteId").val();

if (id > 0){
    checkFeatures(id);
}


function checkFeatures(id){

    

    $.ajax({
        method: "GET",
        url: "/api/campsite/" + id + "/features",

        success: function(features){

            for (var i=0; i<features.length; i++){
                var htmlId = "#feature" + features[i].id;
                $(htmlId).prop("checked",true);
            }
        },

        error: function(){

        }
    })
}

function showParkFields(){

    $("#newPark").toggleClass("d-none");
    $("#existingPark").toggleClass("d-none");
    
    if (newPark) {
        $("#parkBtn").text("Use Existing Park");
        $("#clicked").val("true");
        $("#parkName").prop("required",true);
    } else {
        $("#parkBtn").text("Add A New Park");
        $("#clicked").val("false");
        $("#parkName").prop("required",false);
    }

    newPark = !newPark;
}