<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MEP -- Register</title>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>

    <div class="top-bar">
        <h2>MEP -- Registration Page</h2>
    </div>

    <div class="register-block">
        <h3>New User</h3>
        <form class="register-form" name="reg-form" id="reg-form" method="post" action="/identity/register">

            <div>
                <label for="username">Username</label>
                <input type="text" id="username" name="username">
            </div>

            <div>
                <label for="password">Password</label>
                <input type="password" id="password" name="password">
            </div>

            <div>
                <label for="conf-password">Confirm Password</label>
                <input type="password" id="conf-password" name="conf-password">
            </div>

            <h4>Identity Information</h4>

            <div>
                <label for="name">Name</label>
                <input type="text" id="name" name="name">
            </div>

            <div>
                <label for="email">Email</label>
                <input type="email" id="email" name="email">
            </div>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div>
                <button for="reg-form" type="submit">Register</button>
            </div>
        </form>
    </div>

</body>
</html>