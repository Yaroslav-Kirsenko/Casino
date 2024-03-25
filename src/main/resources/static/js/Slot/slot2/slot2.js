const symbols = ['🍒', '🍋', '🍊', '🍇', '🍉', '🔔', '💎', '🎰'];

function spin() {
    const slots = document.querySelectorAll('.slot');
    slots.forEach(slot => {
        const randomIndex = Math.floor(Math.random() * symbols.length);
        slot.textContent = symbols[randomIndex];
    });
}

document.getElementById('spinBtn').addEventListener('click', spin);
