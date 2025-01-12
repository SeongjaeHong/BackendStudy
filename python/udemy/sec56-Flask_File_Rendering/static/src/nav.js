'use strict'

function handleActiveNav(menu) {
    const activeMenu = document.querySelector('.header__menu__item.active');
    if (menu != activeMenu.id) {
        const target = document.querySelector(`.header__menu__item[data-menu="${menu}"]`);
        activeMenu.classList.remove('active');
        target.classList.add('active');
    }
}

function changeNavbyScroll(entries) {
    let nav;
    let idx;
    let selectLastOne = false;
    for (let entry of entries) {
        idx = sectionIds.indexOf(entry.target.id);
        visibles[idx] = entry.isIntersecting;
        selectLastOne = idx == sectionIds.length - 1 &&
            entry.isIntersecting &&
            entry.intersectionRatio > 0.9;
    }

    if (visibles[visibles.length - 1] && selectLastOne) {
        nav = sections[sections.length - 1].id;
    }
    else {
        try{
            nav = sections[visibles.indexOf(true)].id;
        }
        catch{
            nav = null;
        }
    }
    if (nav) {
        handleActiveNav(nav);
    }
}
const observerOption = {
    rootMargin: '-20% 0px -10px 0px',
    threshold: [0.3, 0.9]
};
const observer = new IntersectionObserver(changeNavbyScroll, observerOption);
const sections = document.querySelectorAll('.section');
const sectionIds = Array.from(sections).map((section) => section.id);
const visibles = Array.from(sections).map(() => false);
window.addEventListener('scroll', () => {
    sections.forEach(target => {
        observer.observe(target);
    });
});