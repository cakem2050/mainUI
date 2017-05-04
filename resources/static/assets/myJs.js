$(document).ready(function(){
	$("#fm_click_login").submit(function(){
		var user = $("input[name=username]").val();
		var pass = $("input[name=password]").val();
		$("#box-status_login").addClass("display-hidden");
		$.ajax({
			type: "POST",
            url: "/checkLogin",
            data: {username:user,password:pass},
            dataType: "json",
            cache: false,
            success: function (data) {
            	if(data.massage1 == "success"){
            		window.location.href="home";
            	}else{
            		$("#box-status_login").removeClass("display-hidden");
            		$("#status_login").text(data.massage2);
            	}
            }
        });

        return false;
    });

	$("#imgInp").change(function(){
	    readURL(this);
	});
});
function readURL(input) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}
