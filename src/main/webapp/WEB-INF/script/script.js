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
    context.classList.add("modal");
    context.classList.remove("modal_close")
})

function removeSession() {
    fetch('http://localhost:8092/quest', {
        method: 'DELETE',
    }).then(e => {
        location.href = "/quest";
    })
}

function makeANewAttempt() {
    const formData = new URLSearchParams();

    formData.append("isNewAttempt", "true");

    fetch("/quest", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: formData.toString()
    }).then(() => {
        location.href = "/quest";
    });
}