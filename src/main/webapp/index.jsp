<html>
<body>
	<h2>Hello World!</h2>
	<!-- <img alt="home" src="https://www.dropbox.com/s/8925pjxsqvz2hgv/feijao-carioca%20kikaldo.jpg?dl=0"> -->
	<img alt="" height="1000px" width="1000px" src="https://www.dropbox.com/s/wsp382x4g95vwzr/feijao-carioca%20kikaldoPNG.png?dl=0">

	<div class="fb-like" data-share="true" data-width="450"
		data-show-faces="true"></div>

	<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/pt_BR/sdk.js#xfbml=1&version=v2.7&appId=1150699848342269";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>

	<div class="fb-login-button" data-max-rows="1" data-size="large"
		data-show-faces="true" data-auto-logout-link="false"></div>

	<script>
		window.fbAsyncInit = function() {
			FB.init({
				appId : '1150699848342269',
				xfbml : true,
				version : 'v2.7'
			});
		};

		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {
				return;
			}
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
</body>
</html>
