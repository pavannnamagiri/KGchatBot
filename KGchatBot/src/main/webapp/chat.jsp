<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Lobster&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="css/chat.css">
</head>

<body>
	<main class="main-chat">
	<div class="main-chat-header">
		<!--Header of the chat page  -->
		<div class="chat-header-title container">
			<h2>KG Chatbot</h2>
			<img class="logo-image" src="images/robot-svgrepo-com.svg"> <span
				class="user-name"><i class="fa fa-user-circle"></i> Welcome <%=session.getAttribute("USERNAME")%></span>
		</div>

	</div>
	<!-- Shows the list of items in s3 bucket -->
	<div class="bucketListContainer">

		<ul class="bucketObjectList">
			<h4>List of PDFs uploaded</h4>

		</ul>
	</div>
	<!--  main chat space of the application-->
	<div class="container">
		<div class="main-chat-content"></div>
		<div class="main-chat-typespace">
			<div class="main-chat-user-text" contenteditable="true"></div>
			<div class="main-chat-send">
				<i class="fa fa-paper-plane"></i>
			</div>

		</div>
	</div>
	<i class="fa fa-times close-btn-chat btn"></i> </main>
	<!-- chat rating shows when clicked on close button  -->
	<div class="chat-rating">
		<div class="rating-stars">
			<i class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star"
				aria-hidden="true"></i><i class="fa fa-star" aria-hidden="true"></i><i
				class="fa fa-star" aria-hidden="true"></i><i class="fa fa-star"
				aria-hidden="true"></i>
		</div>
		<!-- different span is shown based on user rating -->
		<div class="rating-reaction">
			<span class="reaction reaction-1">&#128532</span> <span
				class="reaction reaction-2">&#128556</span> <span
				class="reaction reaction-3">&#128528</span> <span
				class="reaction reaction-4">&#128578</span> <span
				class="reaction reaction-5">&#129321</span>
		</div>
		<p>Rate Your Experince</p>
		<a class="btn btn-success submit-btn" href="redirectHome">Submit</a>
	</div>
	<!-- PDF upload button -->
	<button type="button" class="btn btn-info btn-lg pdf-upload-btn"
		data-toggle="modal" data-target="#myModal">Click Here to
		Upload PDF!!</button>

	<!-- Modal  to open PDF upload section-->
	<!-- Modal -->
	<div class="uploadModal">
		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">PDF Upload</h4>
					</div>
					<div class="modal-body">
						<form id="uploadForm" method="POST" action="test/fileUpload"
							enctype='multipart/form-data'>
							<input type="file" name="pdf" accept="application/pdf" /> <input
								type="submit" class="btn btn-primary"></input>

						</form>
						<h5 class="upload-status"></h5>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<script src="https://apis.google.com/js/api.js"></script>
	<script src="js/chat.js"></script>


</body>

</html>