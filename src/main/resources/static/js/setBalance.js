const setBalanceBtn = document.querySelector(".header__set-balance")
const balanceParent = document.querySelector(".header__balance")
const balanceApply = document.querySelector(".header__balance-apply")
const balanceClose = document.querySelector(".header__balance-close")
const balanceInput = document.querySelector(".header__balance-input input")

if(balanceParent && setBalanceBtn) {
    setBalanceBtn.addEventListener("click", e => {
        e.preventDefault()

        balanceParent.classList.remove("value")
    })
}

if(balanceParent && balanceClose) {
    balanceClose.addEventListener("click", e => {
        e.preventDefault()

        if(balanceInput) {
            balanceInput.value = 0;
        }

        balanceParent.classList.add("value")
    })
}

if(balanceParent && balanceApply) {
    balanceApply.addEventListener("click", async e => {
        e.preventDefault()

        if(balanceInput) {
            const value = balanceInput.value

            if(value) {
                await setBalance(value)
            } else {
                console.log("error")
            }
            balanceInput.value = null;
        }

        balanceParent.classList.add("value")
    })
}

async function setBalance(balance) {
    try {
        const accessToken = window.localStorage.getItem("accessToken")

        if(accessToken) {
            const response = await fetch("/update-balance", {
                method: "POST",
                headers: {
                    Authorization: `Bearer ${accessToken}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(balance)
            })

            const data = await response.text();

            console.log(data)
        }
    } catch(error) {
        console.error(error.message)
    }
}