document.addEventListener("DOMContentLoaded", function () {

// script.js
    fetch('navbar.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('navbar-placeholder').innerHTML = data;
            // addClickEventToLinks();  // Call the function after the navbar is loaded
        })
        .catch(error => console.error('Error fetching the navbar:', error));

    // function addClickEventToLinks() {
    //     const navLinks = document.querySelectorAll('#navbar-placeholder .nav-a');
    //
    //     navLinks.forEach(link => {
    //         link.addEventListener('click', function(event) {
    //             // Prevent default action to allow for single-page applications
    //             event.preventDefault();
    //
    //             // Remove the underlined class from all links
    //             navLinks.forEach(navLink => navLink.classList.remove('underlined'));
    //
    //             // Add the underlined class to the clicked link
    //             this.classList.add('underlined');
    //
    //             console.log('jjj');
    //             // Optionally navigate to the new page
    //             window.location.href = this.href;
    //         });
    //     });
    // }

});




