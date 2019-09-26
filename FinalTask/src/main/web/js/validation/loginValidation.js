function check() {
    var input = document.getElementById("login");
    if(!/^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$/.test(input.value)){
        input.style.border = "2px solid #ed1d12";
    }else{
        input.style.border = "none";
    }
}

function checkPassword() {
    var input = document.getElementById("password");
    if(!/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]*)(?!.*\s).{8,33}$/.test(input.value)){
        input.style.border = "2px solid #ed1d12";
    }else{
        input.style.border = "none";
    }
}


function checkForRegistrationAnyData(){
    var loginRegisterInput = document.getElementById("login_register");
    if(loginRegisterInput.value==="" || (!/^[a-zA-Z][\w\d-_.]{2,20}$/.test(loginRegisterInput.value))){
        var loginRegisterTitle = loginRegisterInput.getAttribute("title");
        loginRegisterInput.setCustomValidity(loginRegisterTitle);
    }else{
        loginRegisterInput.setCustomValidity('');
    }

    var passwordRegisterInput = document.getElementById("password_register");
    if(passwordRegisterInput.value==="" || (!/[\w\d-_\.]{3,20}/.test(passwordRegisterInput.value))){
        var passwordRegisterTitle = passwordRegisterInput.getAttribute("title");
        passwordRegisterInput.setCustomValidity(passwordRegisterTitle);
    }else{
        passwordRegisterInput.setCustomValidity('');
    }

    var nameRegisterInput = document.getElementById("name_register");
    if(nameRegisterInput.value==="" || (!/^[a-zA-Z]{3,20}$/.test(nameRegisterInput.value))){
        var nameRegisterTitle = nameRegisterInput.getAttribute("title");
        nameRegisterInput.setCustomValidity(nameRegisterTitle);
    }else{
        nameRegisterInput.setCustomValidity('');
    }

    var surnameRegisterInput = document.getElementById("surname_register");
    if(surnameRegisterInput.value==="" || (!/^[a-zA-Z]{3,20}$/.test(surnameRegisterInput.value))){
        var surnameRegisterTitle = surnameRegisterInput.getAttribute("title");
        surnameRegisterInput.setCustomValidity(surnameRegisterTitle);
    }else{
        surnameRegisterInput.setCustomValidity('');
    }

}