const jwt = require('jsonwebtoken');
const token = 'eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IiQyYSQxMCQ1RXo0emdHZW9EREhvb3l5czJLaFYuQThrSXFuTVhrMUZQaFVYVU40ZU9uaURGLmpDOU56TyIsInJvbGUiOiJST0xFX1VTRVIiLCJiYWxhbmNlIjowLjAsInllYXIiOjIwLCJpZCI6MSwiZW1haWwiOiJ1c2VyMUBnbWFpbC5jb20iLCJzdWIiOiJVc2VyMSIsImlhdCI6MTcxMzExODEyNCwiZXhwIjoxNzEzMjYyMTI0fQ.vnX5NkS9Jx4H18HurHUYYNaCW0QAyNeg1HXVB8zrdwE';

jwt.verify(token, 'секретный_ключ', (err, decoded) => {
    if (err) {
        console.error('Ошибка при верификации токена:', err);
        return;
    }

    const balance = decoded.balance;
    console.log('Баланс:', balance);
});