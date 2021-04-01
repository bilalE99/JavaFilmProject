<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Update Films</title>

</head>
<body>
<h1>Update details for Film ID: ${filmID}</h1>

<form action="./updateFilm" method="post">
	ID: <input type="text" name="filmID" value="${filmID}" readonly>
	Title: <input type="text" name="title" value="${filmTitle}">
	Year: <input type="text" name="year" value="${filmYear}">
	Director: <input type="text" name="director" value="${filmDirector}">
	Stars: <input type="text" name="stars" value="${filmStars}">
	Review: <input type="text" name="review" value="${filmReview}">
	<br></br>
	<button type="submit" name="button2" value="updFilm">Update!</button>
</form>
<a href="/AssignmentAJAX/index.jsp">Home Page</a>

</body></html>