'use strict'

// Show projects correspond to the clicked category
const categories = document.querySelector('.categories');
const projects = document.querySelectorAll('.project');
const projectsContainer = document.querySelector('.projects');
categories.addEventListener('click', (event) => {
    let target = event.target;
    if (target.className == 'category__count') {
        target = target.parentElement;  // catrygory button
    }

    const category = target.dataset.category;
    if (category == null) {
        return;
    }

    handleActiveSelection(target);
    filterProjects(category);
});

function handleActiveSelection(target) {
    const active = document.querySelector('.category--selected');
    active.classList.remove('category--selected');
    target.classList.add('category--selected');
}

function filterProjects(category) {
    projectsContainer.classList.add('anim-out');
    setTimeout(() => {
        new Promise(function (resolve) {
            projects.forEach(project => {
                if (project.dataset.type == category || category == 'all') {
                    project.style.display = 'block';
                }
                else {
                    project.style.display = 'none';
                }
            });
            resolve;
        }).then(projectsContainer.classList.remove('anim-out'));
    }, 250);  // animation time is 250ms in CSS
}