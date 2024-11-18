<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="author" content="Duy Hoang Thanh & Phat Tran Tan" />
        <link rel="shortcut icon" href="./img/logo.jpg" type="image/jpg" />
        <title>Welcome Page</title>
        <!-- Bootstrap CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
        <!-- My CSS -->
        <link rel="stylesheet" href="./css/styleSignIn.css" />
        <style>
            body {
                background-image: url('https://hf-ui-assets.s3.eu-west-1.amazonaws.com/assets/factor/icons/Secondary+image+(2).png');
                background-size: cover;
                background-repeat: no-repeat;
                background-position: center;
        </style>
        </head>
        <body>
            <main class="form-signin">
                <form action="MainController" method="post">
                    <h1 class="mb-4 fw-bold">Sign In</h1>

                    <h5 class="text-danger fw-bold mb-4">
                        <%= (request.getAttribute("ERROR") != null) ? request.getAttribute("ERROR") : ""%>
                    </h5>

                    <div class="form-floating mb-3">
                        <input name="txtemail" type="email" class="form-control" id="floatingInput" placeholder="email" />
                        <label for="floatingInput">Email</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input name="txtpassword" type="password" class="form-control" id="floatingPassword" placeholder="password" />
                        <label for="floatingPassword">Password</label>
                    </div>

                    <div class="mb-3">
                        <a href="MainController?action=register">Register Account</a>
                    </div>
                    <button class="btn-submit" type="submit" value="signin" name="action">Sign In</button>
                    <p class="mt-5 mb-3 text-muted">&copy; May 2024</p>
                </form>
            </main>
            <!-- Bootstrap CDN -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        </body>
    </html>
