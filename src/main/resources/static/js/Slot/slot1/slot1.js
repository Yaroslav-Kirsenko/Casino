(function () {
    "use strict";

    const items = [
        { name: "../static/style/img/game1/apple.png", weight: 6, value: 15 },
        { name: "../static/style/img/game1/cherry.png", weight: 4, value: 50 },
        { name: "../static/style/img/game1/grape.png", weight: 8, value: 10 },
        { name: "../static/style/img/game1/banana.png", weight: 8, value: 10 },
        { name: "../static/style/img/game1/lemon.png", weight: 6, value: 25 },
        { name: "../static/style/img/game1/orange.png", weight: 8, value: 10 },
        { name: "../static/style/img/game1/watermellon.png", weight: 6, value: 20 },
        { name: "../static/style/img/game1/bar.png", weight: 1, value: 100 }
    ];

    const reels = document.querySelectorAll(".reel");

    let lastItems = [null, null, null];
    let intervalBlink;

    const saldoElement = document.getElementById("saldo");
    const valorElement = document.getElementById("valor");
    let saldo = 0; // Баланс будет получен из сервера
    let valor = 10;

    async function fetchBalance() {
        try {
            const accessToken = window.localStorage.getItem("accessToken");
            if (!accessToken) {
                console.error("Access token not available");
                return;
            }

            const response = await fetch("/main", {
                method: "POST",
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
            });

            if (!response.ok) {
                throw new Error("Failed to fetch balance");
            }

            const data = await response.text();
            saldo = parseInt(data);
            saldoElement.textContent = `Saldo: $${saldo}`;
        } catch (error) {
            console.log("Error fetching balance:", error.message);
            saldoElement.textContent = "Error fetching balance";
        }
    }

    document.addEventListener("DOMContentLoaded", fetchBalance);

    valorElement.textContent = `Valor: $${valor}`;

    const winImage = document.getElementById("winimage");
    const startButton = document.querySelector("#start");

    startButton.addEventListener("click", async function () {
        valor = 10;
        clearInterval(intervalBlink);
        winImage.style.display = "none";
        init();
        await spin();

        // Получите новый баланс с сервера
        await fetchBalance();

        // Отправьте новый баланс на сервер для обновления
        await updateBalance(saldo);
    });

    async function spin() {
        init(false, 1, 1);
        for (let i = 0; i < reels.length; i++) {
            const squares = reels[i].querySelector(".squares");
            const duration = parseInt(squares.style.transitionDuration);
            squares.style.transform = "translateY(0)";
            await new Promise((resolve) => setTimeout(resolve, duration * 100));
        }

        if (lastItems[0].name !== null && lastItems.every(item => item.name === lastItems[0].name)) {
            setTimeout(() => {
                intervalBlink = setInterval(blinkLine, 500);
            }, 1000);

            valor = lastItems[0].value;
            saldo += valor;
        } else {
            saldo -= valor;
        }

        saldoElement.textContent = `Saldo: $${saldo}`;
        valorElement.textContent = `Valor: $${valor}`;

        // Отправьте новый баланс на сервер для обновления
        await updateBalance(saldo);
    }

    function blinkLine() {
        if (winImage.style.display === "none") {
            winImage.style.display = "block";
        } else {
            winImage.style.display = "none";
        }
    }

    function init(firstInit = true, groups = 1, duration = 1) {
        for (let i = 0; i < reels.length; i++) {
            const reel = reels[i];

            if (firstInit) {
                reel.dataset.spinned = "0";
            } else if (reel.dataset.spinned === "1") {
                return;
            }

            const squares = reel.querySelector(".squares");
            const squaresClone = squares.cloneNode(false);

            const pool = [
                { name: "../static/style/img/game1/start.png", weight: null }
            ];

            if (!firstInit) {
                const arr = [];
                for (let n = 0; n < (groups > 0 ? groups : 1); n++) {
                    arr.push(...items);
                }

                pool.push(...shuffle(arr));

                squaresClone.addEventListener(
                    "transitionstart",
                    function () {
                        reel.dataset.spinned = "1";
                        this.querySelectorAll(".box").forEach((box) => {
                            box.style.filter = "blur(0.3px)";
                        });
                    },
                    { once: true }
                );

                squaresClone.addEventListener(
                    "transitionend",
                    function () {
                        this.querySelectorAll(".box").forEach((box, index) => {
                            box.style.filter = "blur(0)";
                            if (index > 0) this.removeChild(box);
                        });
                    },
                    { once: true }
                );

            }

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

            squaresClone.style.transitionDuration = `${duration > 0 ? duration : 1}s`;
            squaresClone.style.transform = `translateY(-${reel.clientHeight * (pool.length - 1)}px)`;
            reel.replaceChild(squaresClone, squares);
        }
    }

    function weightedRandom(remainingItems, remainingWeights) {
        const totalWeight = remainingWeights.reduce((acc, weight) => acc + weight, 0);
        const randomNumber = Math.random() * totalWeight;

        let cumulativeProbability = 0;
        for (let i = 0; i < remainingItems.length; i++) {
            cumulativeProbability += remainingWeights[i] / totalWeight;
            if (randomNumber <= cumulativeProbability) {
                return remainingItems[i];
            }
        }
    }

    function shuffle(arr) {
        const shuffledItems = [];
        const weights = arr.map(item => item.weight);
        let remainingItems = [...arr];
        let remainingWeights = [...weights];

        while (remainingItems.length > 0) {
            const selected = weightedRandom(remainingItems, remainingWeights);
            const indexToRemove = remainingItems.findIndex(item => item === selected);

            if (indexToRemove !== -1) {
                shuffledItems.push(selected);
                remainingItems.splice(indexToRemove, 1);
                remainingWeights.splice(indexToRemove, 1);
            } else {
                console.error("Error: Selected item not found in remainingItems array (debugging)...");
            }
        }

        return shuffledItems;
    }

    const playButton = document.querySelector("[data-play]");
    playButton.addEventListener("click", () => {
        playButton.disabled = true;
        setTimeout(() => {
            playButton.disabled = false;
        }, 1000);
    });

    init();

    // Добавленный код для автоматического обновления баланса
    async function updateBalance(newBalance) {
        try {
            const accessToken = window.localStorage.getItem("accessToken");
            if (!accessToken) {
                console.error("Access token not available");
                return;
            }

            const response = await fetch("/update-balance", {
                method: "POST",
                headers: {
                    Authorization: `Bearer ${accessToken}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(newBalance) // Здесь изменение
            });

            if (!response.ok) {
                throw new Error("Failed to update balance");
            }

            const data = await response.text();
            console.log(data); // Logging server response
        } catch (error) {
            console.log("Error updating balance:", error.message);
        }
    }
})();
