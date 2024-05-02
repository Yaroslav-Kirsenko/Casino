$(document).ready(function() {
    $('#signUpForm').submit(function(event) {
        event.preventDefault();

        var formData = {
            username: $('#username').val(),
            email: $('#email').val(),
            password: $('#password').val(),
            year: $('#year').val(),
        };

        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: '/auth/sign-up',
            data: JSON.stringify(formData),
            success: function(response) {
                console.log('Registration successful:', response);
                alert('Registration successful!');

                // Сохраняем токен в локальное хранилище
                localStorage.setItem('accessToken', response.token);

                // Перенаправляем на страницу входа или другую страницу по вашему выбору
                window.location.href = '/main';
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                alert('An error occurred. Please try again later.');
            }
        });
    });
});
