document.addEventListener("DOMContentLoaded", function() {
    const slides = document.querySelector(".slides");
    const prevBtn = document.querySelector(".prev-btn");
    const nextBtn = document.querySelector(".next-btn");

    let slideIndex = 0;
    let intervalId;

    function showSlide(index) {
        slides.style.transform = `translateX(-${index * 100}%)`;
    }

    function startSlider() {
        intervalId = setInterval(function() {
            slideIndex = (slideIndex + 1) % slides.children.length;
            showSlide(slideIndex);
        }, 3000); // Измените время между слайдами (в миллисекундах) по вашему усмотрению
    }

    function stopSlider() {
        clearInterval(intervalId);
    }

    prevBtn.addEventListener("click", function() {
        slideIndex = (slideIndex - 1 + slides.children.length) % slides.children.length;
        showSlide(slideIndex);
        stopSlider();
        startSlider();
    });

    nextBtn.addEventListener("click", function() {
        slideIndex = (slideIndex + 1) % slides.children.length;
        showSlide(slideIndex);
        stopSlider();
        startSlider();
    });

    startSlider(); // Запустить слайдер автоматически при загрузке страницы
});