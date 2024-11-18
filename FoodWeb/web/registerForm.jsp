<%-- Document : registerForm 
     Created on : Jun 3, 2024, 7:25:57 PM 
     Author : Duy Hoang Thanh & Phat Tran Tan
--%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="author" content="Duy Hoang Thanh & Phat Tran Tan" />
        <link rel="shortcut icon" href="./img/logo.jpg" type="image/jpg" />
        <title>Register Now</title>
        <!-- Bootstrap CDN -->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
            />

        <!-- Custom CSS -->
        <link rel="stylesheet" href="./css/styleRegister.css" />
        <style>
            body {
                background-image: url('https://hf-ui-assets.s3.eu-west-1.amazonaws.com/assets/factor/icons/Secondary+image+(2).png');
                background-size: cover;
                height: 100vh;
                background-repeat: no-repeat;
                background-position: center;
            }
            .btn-submit {
                background-color: #02489d;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                font-size: 1.1rem;
            }
            .btn-submit:hover {
                background-color: #013377;
            }
            .card-registration {
                background: rgba(255, 255, 255, 0.9);
            }
            .form-select {
                height: calc(3.5rem + 2px);
            }
        </style>
    </head>
    <body>
        <section>
            <div class="container py-5">
                <div class="row d-flex justify-content-center align-items-center">
                    <div class="col-12">
                        <div class="card card-registration" style="border-radius: 15px">
                            <div class="card-body p-0">
                                <form action="MainController" method="post">
                                    <div class="row g-0">
                                        <div class="col-6 p-5">
                                            <h3 class="mb-5" style="color: #02489d">
                                                General Information
                                            </h3>

                                            <div class="form-floating mb-4 pb-2">
                                                <input
                                                    name="txtname"
                                                    type="text"
                                                    class="form-control"
                                                    id="floatingName"
                                                    placeholder="Full Name"
                                                    required
                                                    />
                                                <label for="floatingName">Full Name</label>
                                            </div>

                                            <div class="form-floating mb-4 pb-2">
                                                <input
                                                    name="txtpassword"
                                                    type="password"
                                                    class="form-control"
                                                    id="floatingPassword"
                                                    placeholder="Password"
                                                    required
                                                    />
                                                <label for="floatingPassword">Password</label>
                                            </div>

                                            <div class="form-floating mb-4 pb-2">
                                                <input
                                                    name="txtemail"
                                                    type="email"
                                                    class="form-control"
                                                    id="floatingEmail"
                                                    placeholder="Email"
                                                    required
                                                    />
                                                <label for="floatingEmail">Email</label>
                                            </div>

                                            <div class="form-floating mb-4">
                                                <input
                                                    name="txtphone"
                                                    type="tel"
                                                    class="form-control"
                                                    id="floatingPhone"
                                                    placeholder="Phone Number"
                                                    required
                                                    />
                                                <label for="floatingPhone">Phone Number</label>
                                            </div>

                                            <div class="mb-4 mx-1">
                                                <a href="MainController?action=welcome" style="text-decoration: none;">Sign In</a>
                                            </div>

                                            <p class="mb-4 mx-1 text-muted">&copy; May 2024</p>
                                        </div>

                                        <div class="col-6 p-5 bg-indigo">
                                            <h3 class="mb-5 text-white">Details Information</h3>

                                            <div class="form-floating mb-4 pb-2">
                                                <input
                                                    name="txtaddress"
                                                    type="text"
                                                    class="form-control"
                                                    id="floatingAddress"
                                                    placeholder="Address"
                                                    required
                                                    />
                                                <label for="floatingAddress">Address</label>
                                            </div>

                                            <div class="mb-4 pb-2">
                                                <select
                                                    class="form-select form-select-lg"
                                                    name="txtward"
                                                    required
                                                    >
                                                    <option value="" disabled selected>Select Ward</option>
                                                    <option value="1">Ward 1</option>
                                                    <option value="2">Ward 2</option>
                                                    <option value="3">Ward 3</option>
                                                    <option value="4">Ward 4</option>
                                                    <option value="5">Ward 5</option>
                                                </select>
                                            </div>

                                            <div class="mb-4 pb-2">
                                                <select
                                                    class="form-select form-select-lg"
                                                    name="txtdistrict"
                                                    required
                                                    >
                                                    <option value="" disabled selected>Select District</option>
                                                    <option value="1">District 1</option>
                                                    <option value="2">District 2</option>
                                                    <option value="3">District 3</option>
                                                    <option value="4">District 4</option>
                                                    <option value="5">District 5</option>
                                                </select>
                                            </div>

                                            <div class="mb-4 pb-2">
                                                <select
                                                    class="form-select form-select-lg"
                                                    name="txtcity"
                                                    required
                                                    >
                                                    <option value="" disabled selected>Select City (Province)</option>
                                                    <option value="1">Ho Chi Minh City</option>
                                                    <option value="2">Binh Duong</option>
                                                    <option value="3">Dong Nai</option>
                                                    <option value="4">Ba Ria - Vung Tau</option>
                                                    <option value="5">Long An</option>
                                                </select>
                                            </div>

                                            <button
                                                type="submit"
                                                class="btn-submit"
                                                value="saveuser"
                                                name="action"
                                                >
                                                Register
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Bootstrap CDN -->
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
        ></script>
    </body>
</html>
