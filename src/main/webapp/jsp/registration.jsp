<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Sign up</title>
  <link href="../css/bootstrap.min.css" rel="stylesheet">
  <link href="../css/signin.css" rel="stylesheet">

</head>
<body>

<div class="container">
  <form class="form-signin" action="/login" method="post">
    <h2 class="form-signin-heading">Sigh up</h2>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
    <p></p>
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <a class="btn-signup" href="/registration">Sign up</a>
  </form>
</div>

</body>
</html>
