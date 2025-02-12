<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<script src="<%=request.getContextPath()%>/resources/jquery-3.7.1.js"></script>
</head>
<body>
	<h2>방명록</h2>
	<input type="text" id="guest_name" placeholder="이름" name="guest_name">
	<textarea id="guest_message" placeholder="메시지" name="guest_message"></textarea>
	<button id="submit_btn">등록</button>
	<h3>📝 남긴 메시지</h3>
	<ul id="guestbook_list"></ul>
	<script>
	$(function() {
	    $("#submit_btn").click(function() {
		let name = $("#guest_name").val();
		let msg = $("#guest_message").val();
		$.ajax({
		    url : "/guestBookEnd",
		    data : {
			"guest_name" : name,
			"guest_message" : msg
		    },
		    type : "get",
		    dataType : "JSON",
		    success : function(data) {
			console.log(data);
			$("#guestbook_list").append("<li>" + data.name+" : "+ data.msg + "</li>");
		    }
		});
	    });
	});
    </script>
</body>
</html>