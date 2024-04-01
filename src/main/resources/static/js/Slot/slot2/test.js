var global = 0;
const symbols = ['🍒', '🍋', '🍊', '🍇', '🍉', '🔔', '💎', '🎰'];

function startSpin() {
    var takeImage = document.getElementById("element");
    var changeImage = -10;

    for (let i = 0; i < 8; i++) {
        (function(index) {
            setTimeout(function() {
                for (let j = 0; j < 200; j++) {
                    setTimeout(function() {
                        takeImage.innerHTML = "<div style='top:" + changeImage + "px; position: relative'>" + symbols[index] + "</div>";
                        changeImage += 1;
                    }, j * 5); // Установка таймаута на j * 5 миллисекунд
                }

                // Сброс позиции перед началом следующей анимации
                changeImage = -10;

                // Удаление элемента после завершения анимации
                setTimeout(function() {
                    takeImage.innerHTML = ""; // Удаление содержимого элемента
                }, 1500);
            }, i * 1200); // Установка задержки перед началом анимации
        })(i);
    }
}
