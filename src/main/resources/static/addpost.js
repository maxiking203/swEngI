$(document).ready(function(){

	console.log("JQ running");
	
	$(".addPostForm").hide();
	
	function validateEmail(email) { 
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if(re.test(email)){
    		if(email.indexOf("@hm.edu", email.length - "@hm.edu".length) !== -1){
        		console.log("e-mail VALID and FROM hm.edu");
        		return true;
    		}
    		else {
    			console.log("e-mail valid, but NOT hm.edu");
    			return false;
    		}
		} else {
    		console.log("e-mail NOT valid");
    		return false;
    	}
	};
	
	$("#showNewPostFormB").click(function(){
    	$(".addPostForm").show(500);
	});
	
	$("#cancelB").click(function(){
    	$(".addPostForm").hide(500);
	});
	
	
	
	$("#requestCodeB").click(function(){
		var emailToPost = $("#emailF").val();
		
		if (validateEmail(emailToPost) == false) {
			console.log("incorrect email");
		} else {
		
		var payload = {}; 
			console.log("requesting code for email " + emailToPost);
		
			var urlPut = '/board/getCode/' + emailToPost;
			console.log("PUT " + urlPut);
		
			$.ajax({
				url: urlPut,
				dataType: 'json',
				type: 'put',
				contentType: 'application/json',
				data: JSON.stringify(payload),
				processData: false,
				success: function( data, textStatus, jQxhr ){
					console.log("PUT success");
					alert("Check your e-mail account for the confirmation code.");
				},
				error: function( jqXhr, textStatus, errorThrown ){
					console.log("PUT failure");
					alert("Code request failure. Make sure you have provided a valid @hm.edu e-mail address.");
				}
			});
		}
	});

        
	$("#addPostB").click(function() {
		console.log("POST attempt");
		
		var correctData = true;
		
	
		var textToPost = $("#postTextF").val();
		
		if (textToPost.length < 1) {
			console.log("empty post text");
			correctData = false;
		}
		
		var titleText = $("#titleF").val();
		
		if (titleText.length < 1) {
			console.log("empty title");
			correctData = false;
		}
		
		var emailToPost = $("#emailF").val();
		
		if (validateEmail(emailToPost) == false) {
			console.log("incorrect email");
			correctData = false;
		}
		
		var tagArray = $('#tagF').val().split(",");
		
		var tagText = $('#tagF').val()
		var re = /\w/;
		
		if (re.test(tagText) == false) {
			console.log("empty tag list");
			correctData = false;
		}
		
		var catText = $('#categoryF').val()
		if (catText.length < 1) {
			console.log("empty category");
			correctData = false;
		}
		
		var urlPost = '/board/' + $("#confCodeF").val();
		if (urlPost.length < 1) {
			console.log("empty code");
			correctData = false;
		}
		
		if (correctData == true) {
			var payload = {"emailAddr":emailToPost, "title":titleText, "postText":textToPost, "tagList":tagArray, "category":catText}; 
			console.log(payload);
		
			$.ajax({
				url: urlPost,
				dataType: 'json',
				type: 'post',
				contentType: 'application/json',
				data: JSON.stringify(payload),
				processData: false,
				success: function( data, textStatus, jQxhr ){
					console.log("POST success");
					location.reload(); //reload page
				},
				error: function( jqXhr, textStatus, errorThrown ){
					alert("Post failure. Make sure no text fields are empty, the confirmation code is correct, and you have provided a valid @hm.edu e-mail address.");
				}
			});
		} else {
			alert("Cannot post this data. Make sure no text fields are empty and you have provided a valid @hm.edu e-mail address.");
		}
		
	});
});