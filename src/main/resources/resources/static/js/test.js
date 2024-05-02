(async function () {
    "use strict";


    const reels = document.querySelectorAll(".reel");
    let lastItems = [null, null, null];
    let intervalBlink;
    const saldoElement = document.getElementById("saldo");

    // Убираем жестко заданное значение баланса
    let saldo;

    // Функция для получения баланса с сервера
    async function fetchBalance() {
        try {
            const response = await fetch("/main", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                }
            });

            if (response.ok) {
                const balance = await response.text();
                if (balance) {
                    saldo = parseFloat(balance); // Преобразуем в число
                }
            } else {
                throw new Error("Failed to fetch balance");
            }
        } catch (error) {
            console.error("Error fetching balance:", error.message);
            saldoElement.innerHTML = "Error fetching balance";
        }
    }

    // Инициализация игры
    async function initGame() {
        await fetchBalance(); // Получаем текущий баланс с сервера
        saldoElement.innerHTML = `Saldo: <span>$${saldo}</span>`;

        // Добавляем обработчик нажатия на кнопку "Play"
        const playButton = document.querySelector("[data-play]");
        playButton.addEventListener("click", () => {
            playButton.disabled = true;
            setTimeout(() => {
                playButton.disabled = false;
            }, 1000); // Задержка в 1000 мс (1 секунда)
        });

        initReels(); // Инициализируем барабаны
    }

    // Инициализация барабанов
    // Функция для инициализации барабанов
    function initReels() {
        reels.forEach(reel => {
            const squares = reel.querySelector(".squares");
            const squaresClone = squares.cloneNode(false);
            const pool = [
                { name: "../static/style/img/game1/apple.png", weight: 6, value: 15 },
                { name: "../static/style/img/game1/cherry.png", weight: 4, value: 50 },
                { name: "../static/style/img/game1/grape.png", weight: 8, value: 10 },
                { name: "../static/style/img/game1/banana.png", weight: 8, value: 10 },
                { name: "../static/style/img/game1/lemon.png", weight: 6, value: 25 },
                { name: "../static/style/img/game1/orange.png", weight: 8, value: 10 },
                { name: "../static/style/img/game1/watermellon.png", weight: 6, value: 20 },
                { name: "../static/style/img/game1/bar.png", weight: 1, value: 100 }
                // Добавьте другие изображения для барабанов по аналогии
            ];

            lastItems[i] = pool[pool.length - 1];

            for (let j = pool.length - 1; j >= 0; j--) {
                const box = document.createElement("div");
                box.classList.add("box");
                box.style.width = reel.clientWidth + "px";
                box.style.height = reel.clientHeight + "px";

                const img = document.createElement("img");
                img.src = pool[j].name;
                box.appendChild(img);

                squaresClone.appendChild(box);
            }

            squaresClone.style.transitionDuration = "1s"; // Примерно 1 секунда для примера
            squaresClone.style.transform = `translateY(-${reel.clientHeight * (pool.length - 1)}px)`;
            reel.replaceChild(squaresClone, squares);
        });
    }

    // Функция для запуска вращения барабанов
    async function spinReels() {
        // Примерно вращаем каждый барабан в течение 3 секунд для примера
        for (let i = 0; i < reels.length; i++) {
            const squares = reels[i].querySelector(".squares");
            squares.style.transitionDuration = "3s"; // Примерно 3 секунды для примера
            squares.style.transform = `translateY(-${reels[i].clientHeight * 3}px)`;

            await new Promise(resolve => setTimeout(resolve, 3000)); // Ожидание 3 секунды (3000 мс)
        }

        // После вращения барабанов можно обновить баланс
        // В этом месте можно добавить логику для проверки выигрыша и обновления баланса
    }

    // Инициализация игры при загрузке страницы
    document.addEventListener("DOMContentLoaded", initGame);

})();
