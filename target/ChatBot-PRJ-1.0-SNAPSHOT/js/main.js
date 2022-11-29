/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Intl */

const popup = document.querySelector('.chat-popup');
const chatBtn = document.querySelector('.chat-btn');
const closeBtn = document.querySelector('.close-btn');
const submitBtn = document.querySelector('.submit-btn');
const chatArea = document.querySelector('.chat-area');
const txtMsg = document.querySelector('#txtmsg');
const searchBtn = document.querySelector('.searchBtn');
const inputTxt = document.querySelector('.inputTxt');
src = "https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js";

//Display chat box
$(popup).hide();

$(chatBtn).click(function () {
    $(popup).toggle("swing");
    $(txtMsg).select();
    eraseText();
});

$(closeBtn).click(function () {
    $(popup).hide("swing");
    eraseText();
});

//Send messages
function eraseText() {
    txtMsg.value = "";
}

$(txtMsg).keyup(function (event) {
    if (event.keyCode === 13) {
        $(submitBtn).click();
    }
});

$(submitBtn).click(function () {
    var userInput = $(txtMsg).val();
    if (!userInput.trim()) {
        eraseText();
        return;
    } else {
        var myMsg = `
        <div class="out-msg">
        <span class="my-msg">${userInput}</span>
        </div>`;
        chatArea.insertAdjacentHTML("beforeend", myMsg);

        $.ajax({
            url: "/chatbot-test/chatbot",
            data: {
                uInput: userInput.trim()
            },
            cache: false,
            type: "GET",
            success: function (content) {
                chatArea.insertAdjacentHTML("beforeend", content);
            },
            error: function (xhr) {

            }
        });
        this.scrollIntoView(false);
        userInput = "";
        eraseText();
        chatArea.scrollTop = chatArea.scrollHeight;
    }
});



