let timer;

function showFeedback(feedbackElement, message, color) {
    feedbackElement.textContent = message;
    feedbackElement.style.color = color;
    feedbackElement.style.visibility = "visible";
}

function hideFeedback(feedbackElement) {
    feedbackElement.textContent = "";
    feedbackElement.style.visibility = "hidden";
}

function validateInput(element) {
    clearTimeout(timer);
    const feedbackElement = document.getElementById(`feedback-${element.id}`);
    const value = element.value;

    const reg_length = /^.{4,16}$/;
    const reg_start = /^[a-zA-Z_]/;
    const reg_character = /[a-zA-Z0-9_]+/;

    if (!reg_start.test(value)) {
        showFeedback(feedbackElement, "첫 글자는 알파벳 또는 언더스코어(_)만 사용할 수 있습니다.", "red");
        element.setAttribute('data-valid', false);
        return;
    }
    if (!reg_character.test(value)) {
        showFeedback(feedbackElement, "아이디는 알파벳, 숫자, 언더스코어(_)만 사용할 수 있습니다.", "red");
        element.setAttribute('data-valid', false);
        return;
    }
    if (!reg_length.test(value)) {
        showFeedback(feedbackElement, "아이디는 4자 이상 16자 이하로만 생성할 수 있습니다.", "red");
        element.setAttribute('data-valid', false);
        return;
    }
    hideFeedback(feedbackElement)

    timer = setTimeout(() => {
        fetch(`/api/members/check-${element.id}?${element.id}=${element.value}`)
            .then(response => response.json())
            .then(data => {
                showFeedback(feedbackElement, data.message, data.duplicate ? "red" : "green");
                element.setAttribute('data-valid', !data.duplicate);
            });
    }, 1000);
}


function validatePassword(element) {
    const feedbackElement = document.getElementById(`feedback-${element.id}`);
    const value = element.value;

    const reg_length = /^.{8,24}$/;
    const reg_character = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\da-zA-Z]).{8,24}$/;

    if (!reg_length.test(value)) {
        showFeedback(feedbackElement, "8글자 이상 24글자 이하로만 생성할 수 있습니다.", "red");
        element.setAttribute('data-valid', false);
        return;
    }
    if (!reg_character.test(value)) {
        showFeedback(feedbackElement, "알파벳 소문자, 알파벳 대문자, 숫자, 특수문자가 한 개 이상 포함되어야 합니다.", "red");
        element.setAttribute('data-valid', false);
        return;
    }
    hideFeedback(feedbackElement)

    element.setAttribute('data-valid', true);
}

function validateAndSubmit() {
    const loginId = document.getElementById("loginId");
    const name = document.getElementById("name");
    const password = document.getElementById("password");

    let message;
    let valid = true;
    if (loginId.getAttribute("data-valid") !== "true") {
        if(loginId.value.length == 0) {
           message = "아이디를 입력해주세요.";
        } else {
            message = "아이디를 다시 확인해주세요.";
        }
        valid = false;
    } else if (name.getAttribute("data-valid") !== "true") {
        if(loginId.value.length == 0) {
            message = "이름을 입력해주세요.";
        } else {
            message = "이름을 다시 확인해주세요.";
        }
        valid = false;
    } else if (password.getAttribute("data-valid") !== "true") {
        if(loginId.value.length == 0) {
            message = "비밀번호를 입력해주세요.";
        } else {
            message = "비밀번호를 다시 확인해주세요.";
        }
        valid = false;
    }

    if (valid) {
        return true;
    } else {
        alert(message);
        return false;
    }
}