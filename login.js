document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const messageDiv = document.getElementById('message');
    

    loginForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const email = emailInput.value.trim();
        const password = passwordInput.value;

        if (!email || !password) {
            showMessage('Пожалуйста, введите email и пароль', 'error');
            return;
        }

        // --- Имитация запроса на сервер ---
        showMessage('Успешный вход! Перенаправляем на главную...', 'success');
        localStorage.setItem('isLoggedIn', 'true');
        localStorage.setItem('username', email);

        // В реальном приложении здесь будет fetch() для логина
        // fetch('/api/login', { method: 'POST', body: JSON.stringify({email, password}) ... })

        setTimeout(() => {
            window.location.href = 'index.html'; // Переадресация на главную страницу
        }, 2000);
    });

    function showMessage(text, type) {
        messageDiv.textContent = text;
        messageDiv.className = type;
    }
});