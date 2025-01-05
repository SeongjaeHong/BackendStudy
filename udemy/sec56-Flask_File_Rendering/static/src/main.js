'use strict'

// Apply dark mode when scrolloing.
function headerToggle(scrollY) {
    if (scrollY > header.offsetHeight) {
        header.classList.add('header--dark');
    }
    else {
        header.classList.remove('header--dark');
    }
}

// Apply dark mode when scrolling.
const header = document.getElementsByClassName('header')[0];
window.addEventListener('scroll', () => {
    headerToggle(scrollY);
});

// For the case when the page is refreshed.
// It must be shown when the scroll is not at the top.
headerToggle(scrollY);

const home_section = document.getElementById('home');
window.addEventListener('scroll', () => {
    // Change opacity of Home section
    home_section.style.opacity = 1 - window.scrollY / home_section.offsetHeight;
});

const arrowEl = document.getElementsByClassName('scroll-up')[0];
window.addEventListener('scroll', () => {
    // Show/Hide scroll-up button
    if (window.scrollY > home_section.offsetHeight / 2) {
        arrowEl.style.display = 'block';
    }
    else {
        arrowEl.style.display = 'none';
    }
    home_section.style.opacity = 1 - window.scrollY / home_section.offsetHeight;
});

// Open/Close menu tab when the menu-toggle is clicked
const navbarToggle = document.getElementsByClassName('header__toggle')[0];
const navbarMenu = document.getElementsByClassName('header__menu')[0];
navbarToggle.addEventListener('click', () => {
    navbarMenu.classList.toggle('open');
});

// Close menu when a menu item is clicked
navbarMenu.addEventListener('click', () => {
    navbarMenu.classList.remove('open');
})