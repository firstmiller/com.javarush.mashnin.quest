let context = document.getElementById("modal");
let closeElement = document.querySelector(".modal__close");
let header__stat = document.querySelector(".header__stat");

context.addEventListener("click", e => {
    if (e.target === context || e.target === closeElement) {
        context.classList.remove("modal");
        context.classList.add("modal_close")
    }
})

header__stat.addEventListener("click", e => {
    console.log("ОТкрытие модалки");
    context.classList.add("modal");
    context.classList.remove("modal_close")
})
