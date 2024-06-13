function setMinDate() {
    var today = new Date();
    var year = today.getFullYear();
    var month = String(today.getMonth() + 1).padStart(2, '0');
    var day = String(today.getDate()).padStart(2, '0');

    var todayFormatted = year + '-' + month + '-' + day;

    document.getElementById('start').setAttribute('min', todayFormatted);
}

window.onload = setMinDate;

function toggleDetails(event) {
    event.stopPropagation(); // Prevent event from bubbling up
    var details = document.getElementById('passenger-details');
    if (details.style.display === 'none' || details.style.display === '') {
        details.style.display = 'block';
        adjustPosition();
    } else {
        details.style.display = 'none';
    }
}

function adjustPosition() {
    var details = document.getElementById('passenger-details');
    var input = document.getElementById('passenger-count');
    var inputRect = input.getBoundingClientRect();
    var detailsHeight = details.offsetHeight;
    details.style.top = -detailsHeight + 'px'; // Position the details above the input field
}

function updateCount(type, change) {
    var countElement = document.getElementById(type + '-count');
    var count = parseInt(countElement.innerText);
    count += change;
    if (count < 0) {
        count = 0;
    }
    countElement.innerText = count;
    updatePassengerCount();
}

function updatePassengerCount() {
    var adultsCount = parseInt(document.getElementById('adults-count').innerText);
    var childrenCount = parseInt(document.getElementById('children-count').innerText);
    var infantsCount = parseInt(document.getElementById('infants-count').innerText);
    var totalCount = adultsCount + childrenCount + infantsCount;
    document.getElementById('passenger-count').value = 'Number of passengers: ' + totalCount;
}

window.onclick = function(event) {
    var details = document.getElementById('passenger-details');
    if (details.style.display === 'block') {
        details.style.display = 'none';
    }
};