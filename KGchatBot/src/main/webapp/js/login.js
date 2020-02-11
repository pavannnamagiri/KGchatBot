$(document).ready(
		function() {

			regCheck();
			loginCheck();
			// Toggle the login screen from register screen
			$('.login').click(function() {
				$('.username').fadeOut(500);
				$('.username').prop('required', false);
				$('.signup').removeClass('active');
				$('.login').addClass('active')
				$('.user-process').val('Login');
				$('.login-content form').attr('action', 'loginServ');
			});
			// Toggle the register screen from login screen
			$('.signup').click(function() {
				$('.username').fadeIn(500);
				$('.username').prop('required', true);
				$('.login').removeClass('active');
				$('.signup').addClass('active')
				$('.user-process').val('Sign Up');
				$('.login-content form').attr('action', 'registerServ');
			});

			// checking registration status
			function regCheck() {
				if (localStorage.getItem('stat') === 'Y') {
					$('.registration-status-container p').text(
							'Registration Successfull!!');

					$('.registration-status')
							.css('background-color', '#a1ffb5').slideDown()
							.delay(2000).slideUp();
					localStorage.setItem('stat', '');
				} else if (localStorage.getItem('stat') === 'N') {
					$('.registration-status-container p').text(
							'Registration Failed!!');
					$('.registration-status')
							.css('background-color', '#fca7af').slideDown()
							.delay(2000).slideUp();
					localStorage.setItem('stat', '');
				}
			}

			// checking login status
			function loginCheck() {
				if (localStorage.getItem('loginStat') === 'N') {
					$('.registration-status-container p').text(
							'Invalid Credentials!!');
					$('.registration-status')
							.css('background-color', '#fca7af').slideDown()
							.delay(2000).slideUp();
					localStorage.setItem('loginStat', '');
				}
			}

		});