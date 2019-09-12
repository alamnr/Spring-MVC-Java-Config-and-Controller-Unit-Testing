$(function() {

    $(".well").on("click", "#delete-todo-link", function(e) {
        e.preventDefault();

        var todoDeleteDialogTempate = Handlebars.compile($("#template-delete-todo-confirmation-dialog").html());

        $("#view-holder").append(todoDeleteDialogTempate());
        $("#delete-todo-confirmation-dialog").modal();
    })

    $("#view-holder").on("click", "#cancel-todo-button", function(e) {
        e.preventDefault();

        var deleteConfirmationDialog = $("#delete-todo-confirmation-dialog")
        deleteConfirmationDialog.modal('hide');
        deleteConfirmationDialog.remove();
    });

    $("#view-holder").on("click", "#delete-todo-button", function(e) {
        e.preventDefault();
        console.log('todo id-'+$("#todo-id").text());
        console.log('user id-'+$("#user-id").text());
        window.location.href = ctx+"/todo/delete/" + $("#todo-id").text();        
    });
});
