$("section").children().find("section").hide();

$(".js-image")
    .width(220)
    .height(150)
    .click(function (event) {
    let img = $(event.target); //выбрать текущий элемент
    let sub = img.parent().find("section"); // в родительском элементе текущего найти section

    if (sub.is(":visible")) { // если элемент видимый -
        sub.slideUp(); // делает невидимым
    } else {
        sub.slideDown(); // делает видимым
        sub.children().find("section").hide();
    }
});








