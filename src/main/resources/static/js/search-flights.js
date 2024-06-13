document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("search-form");


    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const placeFrom = document.getElementById("placeFrom").value;
        const placeTo = document.getElementById("placeTo").value;
        const departureTime = document.getElementById("departureTime").value;
        const numberOfPassengers = document.getElementById("passenger-count").value.match(/\d+/)[0];

        const params = new URLSearchParams({
            sortDir: 'asc',
            sort: 'departureTime',
            page: 0,
            size: 10,
            placeFrom: placeFrom,
            placeTo: placeTo,
            departureTime: departureTime,
            numberOfPassengers: numberOfPassengers
        });

        const url = 'http://localhost:8080/api/flights/requests?' + params.toString();

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
    const table = document.getElementById("flight-table");
    const tableBody = table.getElementsByTagName('tbody')[0];
    tableBody.innerHTML = '';

    data.forEach((info, index) => {
        const row = tableBody.insertRow();
        const cell1 = row.insertCell(0);
        const cell2 = row.insertCell(1);
        const cell3 = row.insertCell(2);
        const cell4 = row.insertCell(3);
        const cell5 = row.insertCell(4);
        const cell6 = row.insertCell(5);
        const cell7 = row.insertCell(6);
        const cell8 = row.insertCell(7);
        cell1.textContent = info.placeFrom;
        cell2.textContent = info.placeTo;
        cell3.textContent = info.departureTime;
        cell4.textContent = info.arrivalTime;
        cell5.textContent = info.duration;
        cell6.textContent = info.numberOfPassengers;
        cell7.textContent = info.numberOfAvailableSeats;

        const button = document.createElement('button');

        button.dataset.rowIndex = index;
        button.textContent = 'Book';
        button.style.backgroundColor = 'blue';
        button.style.color = 'white';
        button.style.padding = '10px';
        button.style.border = 'none';
        button.style.cursor = 'pointer';
        button.style.borderRadius = '20px';
        button.addEventListener('click', () => {
            const rowIndex = event.target.dataset.rowIndex;
            sessionStorage.setItem('flightIndex', data[rowIndex].flightId);
            window.location.href = './save-booking.html';
        });
        cell8.appendChild(button);
    });


    document.getElementById("table-of-results").style.display = 'block';
    table.style.display = 'table';
}


function setMinDate() {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');

    const todayFormatted = year + '-' + month + '-' + day;

    document.getElementById('departureTime').setAttribute('min', todayFormatted);
}

window.onload = setMinDate;

function toggleDetails(event) {
    event.stopPropagation();
    const details = document.getElementById('passenger-details');
    if (details.style.display === 'none' || details.style.display === '') {
        details.style.display = 'block';
        adjustPosition();
    } else {
        details.style.display = 'none';
    }
}

function adjustPosition() {
    const details = document.getElementById('passenger-details');
    const input = document.getElementById('passenger-count');
    const inputRect = input.getBoundingClientRect();
    const detailsHeight = details.offsetHeight;
    details.style.top = -detailsHeight + 'px';
}

function updateCount(type, change) {
    const countElement = document.getElementById(type + '-count');
    let count = parseInt(countElement.innerText);
    count += change;
    if (count < 0) {
        count = 0;
    }
    countElement.innerText = count;
    updatePassengerCount();
}

function updatePassengerCount() {
    const adultsCount = parseInt(document.getElementById('adults-count').innerText);
    const childrenCount = parseInt(document.getElementById('children-count').innerText);
    const infantsCount = parseInt(document.getElementById('infants-count').innerText);
    const totalCount = adultsCount + childrenCount + infantsCount;
    document.getElementById('passenger-count').value = 'Number of passengers: ' + totalCount;
}

window.onclick = function (event) {
    const details = document.getElementById('passenger-details');
    if (details.style.display === 'block') {
        details.style.display = 'none';
    }
};
