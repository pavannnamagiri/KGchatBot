$(document)
		.ready(
				function() {
					var rep;

					// call to display PDF list on load
					getBucketList();

					// reading user input sending to the google api call
					// function
					function sendMessage() {
						var curTime = new Date;
						curTime = curTime.getHours() + ':'
								+ curTime.getMinutes();
						var userCurrentMessage = $('.main-chat-user-text')
								.html();
						var userCurrentMessageHTML = '<p class="message message-client">'
								+ userCurrentMessage
								+ '</p><span class="message-time-client">'
								+ curTime + '</span>';
						$('.main-chat-content').append(userCurrentMessageHTML);
						$('.main-chat-user-text').text('');
						// setTimeout(gapiCall,, userCurrentMessage);
						gapiCall(userCurrentMessage);

					}

					// process of understanding user message and sending the
					// appropriate message
					function botReply() {

						while (rep != undefined) {
							var botCurrentMessageHTML;
							var curTime = new Date;
							curTime = curTime.getHours() + ':'
									+ curTime.getMinutes();

							if (rep.itemListElement.length != 0) {
								botCurrentMessageHTML = '<p class="message message-bot">'
										+ rep.itemListElement[0].result.description;
								+'</p>' + '<span class="message-time-bot">'
										+ curTime + '</span>';
								$('.main-chat-content').append(
										botCurrentMessageHTML);
								rep = undefined;
							} else {
								botCurrentMessageHTML = '<p class="message message-bot">Sorry I could not find the answer to that! 😢</p>'
										+ '<span class="message-time-bot">'
										+ curTime + '</span>';
								$('.main-chat-content').append(
										botCurrentMessageHTML);
								rep = undefined;
							}

						}

						$('.main-chat-user-text').text('');

						$('.main-chat-user-text').focus();

					}

					// Google API call with user message as search string
					function gapiCall(userCurrentMessage) {
						var service_url = 'https://kgsearch.googleapis.com/v1/entities:search';
						var params = {

							'query' : userCurrentMessage,
							'limit' : 1,
							'indent' : true,
							'key' : '<<api key>>',
						};
						$.getJSON(service_url + '?callback=?', params,
								function(response) {
									console.log(response);
									rep = response;
									botReply();

								});

					}

					// send button for chat
					$('.main-chat-send').click(function() {
						if ($('.main-chat-user-text').html() != '') {
							sendMessage();
						}

					});

					$('.main-chat-user-text').keypress(function(event) {

						if ($('.main-chat-user-text').html() != '') {
							if (event.which === 13) {
								event.preventDefault();
								sendMessage();

							}
						}

					});

					// closes chat and brings up the rating screen
					$('.close-btn-chat').click(function() {
						$('.main-chat-typespace').remove();
						$('.main-chat-content').remove();
						$('.chat-rating').show();
						$('.close-btn-chat').hide();
						$('.pdf-upload-btn').hide();
						$('.bucketObjectList').hide();
					});

					// reading the number of stars selected in rating
					$('.rating-stars i').click(function() {
						$('.yellow-bg').removeClass('yellow-bg');
						var clickIndex = $(this).index();
						userRating(clickIndex);

					});

					// change emojis based on number of stars selected
					function userRating(likeCount) {
						for (var i = 0; i <= likeCount; i++) {
							$('.rating-stars i').eq(i).addClass('yellow-bg');
						}
						likeCount = likeCount + 1;
						$('.reaction').hide();
						var reactionClass = 'reaction-' + likeCount;

						$('.' + reactionClass).show();

					}
					// call to list s3 bucket items
					function getBucketList() {
						$('.bucketObjectList').empty();
						$
								.ajax({
									url : 'test/s3List',
									async : false,
									success : function(data) {
										console.log(data);
										$(
												'.bucketListContainer .bucketObjectList')
												.append(
														'<h4>List of PDFs uploaded</h4>');
										for (var i = 0; i < data.length; i++) {
											var listItem = '<li>' + data[i]
													+ '</li>'
											$(
													'.bucketListContainer .bucketObjectList')
													.append(listItem);
										}
									},
									fail : function(error) {
										console.log(error);
									}
								});
					}

					// asset upload to AWS s3, makes a ajax call to java servlet
					$('#uploadForm')
							.submit(
									function(e) {

										e.preventDefault();

										var pdfCheck = $('#uploadForm input')[0].files[0].type;

										if (pdfCheck === "application/pdf") {
											alert("Upload in progess");
											$
													.ajax({
														url : "test/fileUpload",
														type : "POST",
														data : new FormData(
																document
																		.getElementById('uploadForm')),
														enctype : 'multipart/form-data',
														async : false,
														processData : false,
														contentType : false,
														success : function(
																response) {
															console
																	.log('success');
															$('.upload-status')
																	.css(
																			'color',
																			'green')
																	.text(
																			response
																					+ ' uploaded...!');

															getBucketList();
														},
														error : function(err) {
															$('.upload-status')
																	.css(
																			'color',
																			'red')
																	.text(
																			'Upload Failed!!');
															console.log(err);
														}
													})
										}

										else {
											alert('Only pdf upload is allowed');
										}

									});
				});