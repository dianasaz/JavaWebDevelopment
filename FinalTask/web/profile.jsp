<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
</head>
<body>
<div class="container emp-profile">
    <div style="margin-left: 20%; margin-right: 20%;">
    <table style="margin-bottom: 20px">
        <div class="row">
            <div class="col-md-4">
                <a href="controller?command=edit_profile" class="profile-edit-btn">EDIT PROFILE</a>
            </div>
            <div class="col-md-4">
                <a href="controller?command=home_page" class="profile-edit-btn">HOME</a>
            </div>
            <div class="col-md-4">
                <a href="controller?command=register_pet" class="profile-edit-btn">REGISTER_PET</a>
            </div>
            </div>
    </table>
    </div>
    <form class="form" method="POST" action="controller?command=profile">
        <div class="row">
            <table class="col-md-8" >

                <div class="tab-content" id="myTabContent">
                    <label class="col-md-6 col-md-offset-3 control-label" style="font-size: 20px; text-align: center;"> <b>USER
                        INFO</b> </label>
                    <div class="row">
                        <div class="col-md-6">
                            <label>User login</label>
                        </div>
                        <div class="col-md-6">
                            <p>${login}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>Name</label>
                        </div>
                        <div class="col-md-6">
                            <p>${name}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>Email</label>
                        </div>
                        <div class="col-md-6">
                            <p>${email}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label>Phone</label>
                        </div>
                        <div class="col-md-6">
                            <p>${phone}</p>
                        </div>
                    </div>

                </div>
            </table>
            <table class="col-md-8" >
                <div class="tab-content" id="myTaabContent">
                    <label class="col-md-6 col-md-offset-3 control-label" style="font-size: 20px; text-align: center;"> <b>YOUR PETS</b> </label>
                    <c:forEach var="pet" items="${pets}">
                        <div class="row">
                            <div class="col-md-4">
                                <label>${pet.name}</label>
                            </div>
                            <div class="col-md-4">
                                <p>${pet.kind}</p>
                            </div>
                            <div class="col-md-4">
                                <p>${pet.dateOfBirth}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </table>
        </div>
    </form>

</div>
<style>
    body {
        background: -webkit-linear-gradient(left, #c1d5c7, #00c6ff);
    }

    .emp-profile {
        padding: 3%;
        margin-top: 3%;
        margin-bottom: 3%;
        border-radius: 0.5rem;
        background: #fff;
    }

    .profile-img {
        text-align: center;
    }

    .tab-content {
        margin-left: 20%;
        margin-right: 20%;
        width: 500px;
    }

    .profile-img img {
        width: 70%;
        height: 100%;
    }

    .profile-img .file {
        position: relative;
        overflow: hidden;
        margin-top: -20%;
        width: 70%;
        border: none;
        border-radius: 0;
        font-size: 15px;
        background: #212529b8;
    }

    .profile-img .file input {
        position: absolute;
        opacity: 0;
        right: 0;
        top: 0;
    }

    .profile-head h5 {
        color: #333;
    }

    .profile-head h6 {
        color: #0062cc;
    }

    .profile-edit-btn {
        border: none;
        border-radius: 1.5rem;
        width: 70%;
        padding: 2%;
        font-weight: 600;
        color: #6c757d;
        cursor: pointer;
    }

    .proile-rating {
        font-size: 12px;
        color: #818182;
        margin-top: 5%;
    }

    .proile-rating span {
        color: #495057;
        font-size: 15px;
        font-weight: 600;
    }

    .profile-head .nav-tabs {
        margin-bottom: 5%;
    }

    .profile-head .nav-tabs .nav-link {
        font-weight: 600;
        border: none;
    }

    .profile-head .nav-tabs .nav-link.active {
        border: none;
        border-bottom: 2px solid #0062cc;
    }

    .profile-work {
        padding: 14%;
        margin-top: -15%;
    }

    .profile-work p {
        font-size: 12px;
        color: #818182;
        font-weight: 600;
        margin-top: 10%;
    }

    .profile-work a {
        text-decoration: none;
        color: #495057;
        font-weight: 600;
        font-size: 14px;
    }

    .profile-work ul {
        list-style: none;
    }

    .profile-tab label {
        font-weight: 600;
    }

    .profile-tab p {
        font-weight: 600;
    }
</style>
</body>
</html>