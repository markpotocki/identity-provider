<html>

<head>
    <meta charset="UTF-8" />
    <title>MEP -- Register</title>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <link rel="stylesheet" href="style.css" />
    <script src="script.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

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
      .reg-box {
			background-color: rgba(0, 0, 0, 0.9);
			border-radius: 5px;
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
        <img src="" class="logo" alt="MEP" />

    </a>
</nav>

<div class="container">

    <div class="p-3 m-3 reg-box">
        <h3 class="text-light">New User</h3>
        <hr />

        <form class="text-light" name="reg-form" id="reg-form" method="post" action="/register">

            <h4>Login Information</h4>

            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" name="username" class="form-control" id="username" aria-describedby="usernameHelp" placeholder="Enter username"/>
                <small id="usernameHelp" class="form-text text-muted">Choose a unique username you will use to login.</small>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" class="form-control" id="password" aria-describedby="passwordHelp" placeholder="Password"/>
                <small id="passwordHelp" class="form-text text-muted">Create a secure password. Must be larger than 8 characters and contain atleast one letter, number, and symbol.</small>
            </div>

            <div class="form-group">
                <label for="conf_password">Confirm Password</label>
                <input type="password" class="form-control" id="conf_password" aria-desribedby="confPasswordHelp" placeholder="Reenter password"/>
                <small id="confPasswordHelp" class="form-text text-muted d-none">Reenter your chosen password.</small>
            </div>

            <h4>Account Information</h4>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" name="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email"/>
                <small id="emailHelp" class="form-text text-muted">Your email is used for 2 step authentication if enabled and to recover your password if forgotten. We do not share your email information with any third parties.</small>
            </div>

            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" name="name" class="form-control" id="name" aria-describedby="nameHelp" placeholder="Enter name"/>
                <small id="nameHelp" class="form-text text-muted">Enter your name here.</small>
            </div>

            <div class="form-group">
                <label for="2faOption">Authentication Level</label>
                <select class="form-control" id="2faOption">
                    <option>Basic</option>
                    <option>Email Verification</option>
                    <option>QR Code Verification</option>
                    <option>Biometric Verification</option>
                </select>
            </div>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-block btn-large bg-black btn-dark">Register</button>

        </form>

    </div>

</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>