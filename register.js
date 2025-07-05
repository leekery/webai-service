document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.getElementById('register-form');
    const usernameInput = document.getElementById('username');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirm-password');
    const messageDiv = document.getElementById('message');

    registerForm.addEventListener('submit', (event) => {
        event.preventDefault();
        
        const username = usernameInput.value.trim();
        const email = emailInput.value.trim();
        const password = passwordInput.value;
        const confirmPassword = confirmPasswordInput.value;

        // --- Клиентская валидация ---
        if (!username || !email || !password || !confirmPassword) {
            showMessage('Все поля обязательны для заполнения', 'error');
            return;
        }
        if (password.length < 6) {
            showMessage('Пароль должен содержать не менее 6 символов', 'error');
            return;
        }
        if (password !== confirmPassword) {
            showMessage('Пароли не совпадают', 'error');
            return;
        }
        
        // --- Имитация отправки данных на сервер ---
        showMessage('Аккаунт успешно создан! Перенаправляем на страницу входа...', 'success');
        localStorage.setItem('isLoggedIn', 'true');
        localStorage.setItem('username', username);

        // В реальном приложении здесь будет fetch() к вашему Java-бэкенду
        // fetch('/api/register', { method: 'POST', body: JSON.stringify({username, email, password}) ... })

        setTimeout(() => {
            window.location.href = 'login.html'; // Переадресация на страницу входа
        }, 2000);
    });

    function showMessage(text, type) {
        messageDiv.textContent = text;
        messageDiv.className = type; // 'error' или 'success'
    }
});