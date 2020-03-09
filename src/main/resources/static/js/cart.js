$("img")
    .width(220)
    .height(150);

$(".js-delete").click((event) => {
    event.preventDefault();
    const link = $(event.target);
    $.ajax({
        url: link.attr("href"),
        method: "delete",
        success: (data) => {
            if (data == "ok") {
                link.parent().parent().remove();
            } else {
                alert("Что-то пошло не так!");
            }
        },
        error: () => {
            alert("Что-то пошло не так! Ошибка");
        }
    });
});






