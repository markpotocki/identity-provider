<!doctype html>

<html>

<head>
	<link rel="stylesheet" href="lib/style.css">
	<script src="lib/script.js">

	</script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	 crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	 crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	 crossorigin="anonymous">

	</script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	 crossorigin="anonymous">

	</script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	 crossorigin="anonymous">

	</script>

	<style>
		body {
			background: #660566;
			/* Old browsers */
			background: -moz-linear-gradient(-45deg, #660566 0%, #ff7f04 100%);
			/* FF3.6-15 */
			background: -webkit-linear-gradient(-45deg, #660566 0%, #ff7f04 100%);
			/* Chrome10-25,Safari5.1-6 */
			background: linear-gradient(135deg, #660566 0%, #ff7f04 100%);
			/* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
			filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#660566', endColorstr='#ff7f04', GradientType=1);
			/* IE6-9 fallback on horizontal gradient */
			height: 100%;
			margin: 0;
			background-repeat: no-repeat;
			background-attachment: fixed;
		}

		.login-box {
			background-color: rgba(0, 0, 0, 0.9);
			border-radius: 5px;
		}

		.centered {
			position: fixed;
			top: 50%;
			left: 50%;
			/* bring your own prefixes */
			transform: translate(-50%, -50%);
		}

		.logo {
			width: 40px;
		}

		.navbar-bg {
			background-color: rgba(0, 0, 0, 0.9);
			padding: 0;
		}

		.bg-black {
			background-color: rgba(0, 0, 0, 0.9);
			border-color: rgba(0, 0, 0, 0.9);
		}
	</style>
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-dark navbar-bg">
		<a class="navbar-brand mx-auto" href="#">
			<img src="http://www.thelockinmovie.com/wp-content/uploads/2018/09/beagle-clipart-cute-beagle-puppy-lying-down-clipart-vector-toons-space-clipart-1024x1024.jpg" class="logo" alt="MEP" />
      
    </a>
  </nav>

	<div class="container">
		<div class="h-100 p-3 m-3 login-box">
			<h3 class="text-center text-light">Login</h3>
			<div class="px-3 text-light">
				<form>
					<div class="form-group">
						<label class="d-none" for="username">Username</label>
            <div class="input-group">
            <div class="input-group-prepend">
              <div class="input-group-text bg-black"><i class="text-light fas fa-user"></i></div>
            </div>
          <input type="text" class="form-control" id="username" aria-describedby="usernameHelp" placeholder="Enter username" />
            </div>
          <small id="usernameHelp" class="d-none form-text text-muted">Enter the username you used when registering. If your account is newer, it will be your email.</small>
        </div>

        <div class="form-group">
          <label for="pwd" class="d-none">Password</label>
          <div class="input-group">
            <div class="input-group-prepend">
              <div class="input-group-text bg-black"><i class="text-light fas fa-key"></i></div>
            </div>
          <input type="text" class="form-control" id="pwd" aria-describedby="pwdHelp" placeholder="Password" />
          </div>
          <small id="pwdHelp" class="form-text text-muted">Forgot Password</small>
        </div>

        <div class="form-group form-check">
          <input type="checkbox" class="form-check-input" id="remember">
          <label class="form-check-label" for="remember">Stay logged in</label>
        </div>
        <div>
          <a href="#" class="btn btn-dark bg-black btn-light btn-block">Login</a>
			</form>

			<div class="text-right">
        <small><a href="#" class="text-muted">New User</a></small>
		</div>
  </div>
</div>
	</div>
</body>

</html>
