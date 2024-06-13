//Register and login
document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("form-box");
    const modal = document.getElementById("errorModal");
    const modalErrorMessage = document.getElementById("modalErrorMessage");
    const closeButton = document.getElementsByClassName("close-button")[0];
    const modalOkButton = document.getElementById("modalOkButton");

    function showModal(message) {
        modalErrorMessage.textContent = message;
        modal.style.display = "block";
    }

    function closeModal() {
        modal.style.display = "none";
    }

    closeButton.addEventListener("click", closeModal);
    modalOkButton.addEventListener("click", closeModal);

    window.addEventListener("click", function (event) {
        if (event.target == modal) {
            closeModal();
        }
    });

    function clearErrorMessage(event) {
        const errorElement = document.getElementById(`${event.target.id}Error`);
        if (errorElement) {
            errorElement.textContent = '';
        }
    }

    const inputFields = document.querySelectorAll('input');
    inputFields.forEach(input => {
        input.addEventListener('input', clearErrorMessage);
    });

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        document.querySelectorAll('.error-message').forEach(el => el.textContent = '');

        const termsCheckbox = document.getElementById("termsCheckbox");
        if (!termsCheckbox.checked) {
            const errorElement = document.getElementById("termsCheckboxError");
            errorElement.textContent = "You have to accept Privacy & cookies and Terms & conditions.";
            return;
        }

        const firstName = document.getElementById("firstName").value;
        const lastName = document.getElementById("lastName").value;
        const username = document.getElementById("username").value;
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;
        const phoneNumber = document.getElementById("phoneNumber").value;
        const hasNewsletter = document.getElementById("hasNewsletter");

        const data = {
            firstName: firstName,
            lastName: lastName,
            username: username,
            email: email,
            password: password,
            phoneNumber: phoneNumber,
            hasNewsletter: hasNewsletter.checked.valueOf(),
            ASZFAndASZ: termsCheckbox.checked
        };

        fetch('http://localhost:8080/api/customusers/registration', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(errors => {
                        throw errors;
                    });
                }
                return response.json();
            })
            .then(data => {
                console.log("Success:", data);
                alert("Registration successful");
            })
            .catch(errors => {
                console.error('Errors:', errors);
                if (errors.fieldErrors && Array.isArray(errors.fieldErrors)) {
                    errors.fieldErrors.forEach(error => {
                        const errorField = error.errorCode;
                        const errorMessage = error.details;
                        const errorElement = document.getElementById(`${errorField}Error`);

                        if (errorElement) {
                            errorElement.textContent = errorMessage;
                        }

                        if (errorField === 'username') {
                            showModal(errorMessage);
                        }
                    });
                } else {
                    alert("An unexpected error occurred.");
                }
            });


        const identifier = document.getElementById("identifier").value;

        const dataLogin = {
            username: identifier,
            email: identifier,
            password: password,
        };

        fetch('http://localhost:8080/api/customusers/login/me', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dataLogin)
        })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(errors => {
                        throw errors;
                    });
                }
                return response.json();
            })
            .then(dataLogin => {
                console.log("Success:", dataLogin);
                alert("Login successful");
            })
            .catch(errors => {
                console.error('Errors:', errors);
                if (errors.fieldErrors && Array.isArray(errors.fieldErrors)) {
                    errors.fieldErrors.forEach(error => {
                        const errorField = error.errorCode;
                        const errorMessage = error.details;
                        if (errorField === 'identifier' || errorField === 'password') {
                            showModal(errorMessage);
                        }
                    });
                } else {
                    alert("An unexpected error occurred.");
                }
            });
    });
});

