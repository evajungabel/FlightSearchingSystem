document.addEventListener("DOMContentLoaded", function () {

    const saveBooking = document.getElementById("form-save-booking");

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


    saveBooking.addEventListener("submit", function (event) {
        event.preventDefault();

        document.querySelectorAll('.error-message').forEach(el => el.textContent = '');


        const termsCheckbox = document.getElementById("termsCheckbox");
        if (!termsCheckbox.checked) {
            const errorElement = document.getElementById("termsCheckboxError");
            errorElement.textContent = "You have to accept Privacy & cookies and Terms & conditions.";
            return;
        }

        const flightIndex = sessionStorage.getItem('flightIndex');
        console.log('Received flightIndex:', flightIndex);


        const firstName = document.getElementById("firstName").value;
        const lastName = document.getElementById("lastName").value;
        const email = document.getElementById("email").value;
        const phoneNumber = document.getElementById("phoneNumber").value;
        const dateOfBirth = document.getElementById("dateOfBirth").value;
        const documentNumber = document.getElementById("documentNumber").value;
        const issuingCountry = document.getElementById("issuingCountry").value;
        const expirationDate = document.getElementById("expirationDate").value;
        const nationality = document.getElementById("nationality").value;

        const data = {
            flightId: flightIndex,
            firstName: firstName,
            lastName: lastName,
            email: email,
            phoneNumber: phoneNumber,
            dateOfBirth: dateOfBirth,
            documentNumber: documentNumber,
            issuingCountry: issuingCountry,
            expirationDate: expirationDate,
            nationality: nationality,
            ASZFAndASZ: termsCheckbox.checked
        };

        fetch('http://localhost:8080/api/save-booking', {
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
                sessionStorage.removeItem('rowIndex');
                return response.json();
            })
            .then(data => {
                console.log("Success:", data);
                alert("Booking was successful! Are you ready for an adventure?");
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
                    });
                } else {
                    alert("An unexpected error occurred.");
                }
            });
    });
});


function setMinOrMaxDate() {
    const today = new Date();
    today.setDate(today.getDate() - 1);

    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');

    const maxDate = year + '-' + month + '-' + day;
    document.getElementById('dateOfBirth').setAttribute('max', maxDate);
    document.getElementById('expirationDate').setAttribute('min', maxDate);
}

window.onload = setMinOrMaxDate;