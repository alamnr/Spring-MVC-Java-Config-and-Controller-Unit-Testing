

function openDialog(id,entityName)
{

    $(".well").on("click", "#delete-link", function(e) {
        e.preventDefault();

        var todoDeleteDialogTempate = Handlebars.compile($("#template-delete-confirmation-dialog").html());

        $("#view-holder").append(todoDeleteDialogTempate());
        $("#delete-confirmation-dialog").modal();
    })

    $("#view-holder").on("click", "#cancel-button", function(e) {
        e.preventDefault();

        var deleteConfirmationDialog = $("#delete-confirmation-dialog")
        deleteConfirmationDialog.modal('hide');
        deleteConfirmationDialog.remove();
    });

    $("#view-holder").on("click", "#delete-button", function(e) {
        e.preventDefault();
        //alert(id);
        if(entityName === 'todo'){
        	window.location.href = ctx+"/todo/delete/" + id;
        } else if (entityName === 'user'){
        
        	window.location.href = ctx+"/user/delete/" + id;
        }
    });

}
