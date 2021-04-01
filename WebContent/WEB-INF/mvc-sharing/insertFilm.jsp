<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert New film</title>

<script rel="javascript" type="text/javascript"
	href="js/jquery-3.5.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#addFilm').click(function() {
			var filmID = $('#filmID').val();
			var title = $('#title').val();
			var year = $('#year').val();
			var director = $('#director').val();
			var stars = $('#stars').val();
			var review = $('#review').val();
			$.ajax({
				type : "POST",
				data : {
					filmID : filmID,
					title : title,
					year : year,
					director : director,
					stars : stars,
					review : review,
					action : 'test1'
				},
				url : "insertFilm",
				success : function(result) {
					$('#filmDetails1').html(result);
				}
			});
		});
	});
</script>
<h1>Insert new film</h1>
</head>
<body>
<a href="/AssignmentAJAX/index.jsp">Home Page</a>
<br><br>
	<form>
			ID: <input type="text" id="filmID" required> Title: <input type="text"
			id="title" required> Year: <input type="text" id="year"
			required> Director: <input type="text" id="director"
			required> Stars: <input type="text" id="stars" required>
		Review: <input type="text" id="review" required> <br>
		<input type="button" value="Insert Film" id="addFilm"></button>
	</form>
	<span id="filmDetails1"></span>
	
</body>
</html>