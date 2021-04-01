<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Info</title>
<script rel="javascript" type="text/javascript" href="js/jquery-3.5.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$('#getFilmID').click(function(){
			var filmID = $('#filmID').val();
			var searchBy = $('#searchBy').val();
			var format = $('#format').val();
			$.ajax({
				type: "POST",
				data: {
					filmID: filmID,
					searchBy: searchBy,
					format: format,
					action: 'test1'
				},
				url: "getFilmID",
				success: function(result){
					$('#filmDetails1').html(result);
				}
			});
		});
		
		$('#delFilmBtn').click(function(){
			var filmID = $('#filmID').val();
			$.ajax({
				type: "POST",
				data: {
					filmID: filmID,
					action: 'test2'
				},
				url: "deleteFilm",
				success: function(result){
					$('#filmDetails2').html(result);
				}
			});
		});
		
		
	});
</script>

<h4>Enterprise Programming - : Bilal El-Fitouri, 18017385 - Unit Code: 6G6Z1103, 6G6Z1903</h4>
<h4>An Ajax based web front end to retrieve the data and display in a suitable format using
library based routines for an enhanced user interface</h4>

</head>
<body>
	<p />	
			<form action="./getAllFilms" method="post">
    			<button type="submit" name="button" value="getAllInfo">Show all Films</button>
			</form>
			<br>
			<form>
				<input type="text" id="filmID" placeholder="search..."> 
				<input type="text" id="searchBy" placeholder="search by..name/id/year"><input type="text" id="format" placeholder="format...">
	  			<input type="button"  value="Search film" id="getFilmID"></input>
	  			<br><br>
	  			<span id="filmDetails1"></span>
			</form>
			<form>
				Delete Film ID: <input type="text" id="filmID"><input type="button"  value="Delete Film Record" id="delFilmBtn"></button>
				<br><br>
	  			<span id="filmDetails2"></span>
			</form>
			<br>
				<form action="./updateFilm" method="post">
				<label for="id">Update Film ID: </label><input type="text" name="userInp"><button type="submit" name="button" value="updateFilm">Show Film Data</button>
			</form>
			<br>
			<form action="./addFilm" method="post">
	  			<button type="submit" name="button" value="redirectPage">Add new Film!</button>
			</form>	
		<p />
	

</body>





</html>