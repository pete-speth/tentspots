


function deleteCampsite(id){


    if(confirm("WARNING: All visits associated with this campsite will be deleted as well. Do you wish to continue?")){

        $.ajax({
            type: "DELETE",
            url: "/api/delete/campsite/" + id,

            success: function(){

                alert("Campsite deleted");
                location.reload();
            },

            error: function(){

            }
        })
    }
}