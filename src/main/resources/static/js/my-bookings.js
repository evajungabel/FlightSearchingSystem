document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("my-bookings");


    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const email = document.getElementById("email").value;

        const params = new URLSearchParams({
            sortDir: 'asc',
            sort: 'departureTime',
            page: 0,
            size: 10,
            email: email
        });

        const url = 'http://localhost:8080/api/customusers/bookings?' + params.toString();

        fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
        })
            .then(response => {
                console.log(response);
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                populateTable(data);
            })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
            });
    });
});

function populateTable(data) {
    const table = document.getElementById("booking-table");
    const tableBody = table.getElementsByTagName('tbody')[0];
    tableBody.innerHTML = '';

    data.forEach((info) => {
        const row = tableBody.insertRow();
        const cell1 = row.insertCell(0);
        const cell2 = row.insertCell(1);
        const cell3 = row.insertCell(2);
        const cell4 = row.insertCell(3);
        const cell5 = row.insertCell(4);
        const cell6 = row.insertCell(5);
        const cell7 = row.insertCell(6);
        cell1.textContent = info.placeFrom;
        cell2.textContent = info.placeTo;
        cell3.textContent = info.departureTime;
        cell4.textContent = info.arrivalTime;
        cell5.textContent = info.duration;
        cell6.textContent = info.numberOfPassengers;
        cell7.textContent = info.numberOfAvailableSeats;

    });


    document.getElementById("table-of-bookings").style.display = 'block';
    table.style.display = 'table';
}
