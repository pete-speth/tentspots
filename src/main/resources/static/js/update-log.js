
function deleteEntry(id){


    if(confirm("WARNING: Do you wish to permanently delete this entry?")){

        $.ajax({
            type: "DELETE",
            url: "/api/delete/visit/" + id,

            success: function(){

                alert("Entry deleted");
                location.reload();
            },

            error: function(){

            }
        })
    }
}