$("img").click(function (event) {
    var img = $(event.target); //выбрать текущий элемент
    var sub = img.parent().find("section"); // в родительском элементе текущего найти section

    if (sub.is(":visible")) { // если элемент видимый -
        sub.slideUp(); // делает невидимым
    } else {
        sub.slideDown(); // делает видимым
    }
});
