
$( document ).ready(function() {

    // SUBMIT FORM
    $("#info").submit(function(event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    });


    function ajaxPost(){
        alert('adfsadfsadf');

        // PREPARE FORM DATA
        var formData = {
            fname : $("#fname").val(),
            address : $("#address").val(),
            dob:$("#dob").val()
        }

        // DO POST
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : window.location + "/save",
            data : JSON.stringify(formData),
            dataType : 'json',
            success : function(result) {
                if(result.status == "Done"){

                }else{
                    $("#postResultDiv").html("<strong>Error</strong>");
                }
                console.log(result);
            },
            error : function(e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });

        // Reset FormData after Posting
        resetData();

    }

    function resetData(){
        $("#fullName").val("");
        $("#address").val("");
        $("#dob").val("");

    }
})