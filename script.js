function updateNav() {
    const nav = document.getElementById('nav-buttons');
    nav.innerHTML = ''; // очистим

    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    const username = localStorage.getItem('username') || 'Пользователь';

    if (isLoggedIn) {
        // Кнопка "История"
        const historyBtn = document.createElement('button');
        historyBtn.className = 'btn btn-secondary';
        historyBtn.textContent = 'История';
        historyBtn.onclick = () => alert('Пока нет истории 😅');
        nav.appendChild(historyBtn);

        // Подпись с ником
        const userLabel = document.createElement('span');
        userLabel.textContent = username;
        userLabel.style.marginLeft = '1rem';
        userLabel.style.fontWeight = 'bold';
        userLabel.style.color = '#ccc';
        nav.appendChild(userLabel);

        const logoutBtn = document.createElement('button');
        logoutBtn.className = 'btn btn-secondary';
        logoutBtn.textContent = 'Выйти';
        logoutBtn.style.marginLeft = '1rem';
        logoutBtn.onclick = () => {
            localStorage.removeItem('isLoggedIn');
            localStorage.removeItem('username');
            updateNav();
        };
        nav.appendChild(logoutBtn);

    } else {
        // Кнопка "Войти"
        const loginBtn = document.createElement('button');
        loginBtn.className = 'btn btn-secondary';
        loginBtn.textContent = 'Войти';
        loginBtn.onclick = () => window.location.href = 'login.html';
        nav.appendChild(loginBtn);

        // Кнопка "Регистрация"
        const registerBtn = document.createElement('button');
        registerBtn.className = 'btn btn-primary';
        registerBtn.textContent = 'Регистрация';
        registerBtn.onclick = () => window.location.href = 'register.html';
        nav.appendChild(registerBtn);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    updateNav();

    // Находим все нужные элементы на странице
    const searchForm = document.getElementById('search-form');
    const songInput = document.getElementById('song-input');
    const resultsContainer = document.getElementById('results-container');
    const recommendationsList = document.getElementById('recommendations-list');

    // Добавляем обработчик события на отправку формы
    searchForm.addEventListener('submit', async (event) => {
        // Предотвращаем стандартное поведение формы (перезагрузку страницы)
        event.preventDefault();

        const query = songInput.value.trim();
        if (!query) {
            alert('Пожалуйста, введите название песни.');
            return;
        }

        // Показываем контейнер с результатами и сообщение о загрузке
        resultsContainer.classList.remove('hidden');
        recommendationsList.innerHTML = '<p style="text-align:center;">Подбираем музыку для вас...</p>';

        try {
            // Имитируем запрос к бэкенду.
            // В реальности здесь будет fetch('/api/recommendations', { ... })
            const recommendations = await getMockRecommendations(query);
            
            // Отображаем полученные рекомендации
            displayRecommendations(recommendations);

        } catch (error) {
            // В случае ошибки показываем сообщение
            console.error('Ошибка при получении рекомендаций:', error);
            recommendationsList.innerHTML = '<p style="text-align:center; color: #e74c3c;">Не удалось загрузить рекомендации. Попробуйте позже.</p>';
        }
    });

    /**
     * Отображает список рекомендаций на странице.
     * @param {Array<Object>} tracks - Массив объектов с треками.
     */
    function displayRecommendations(tracks) {
        // Очищаем предыдущие результаты
        recommendationsList.innerHTML = '';

        if (tracks.length === 0) {
            recommendationsList.innerHTML = '<p style="text-align:center;">К сожалению, мы ничего не нашли. Попробуйте другой трек.</p>';
            return;
        }

        // Создаем и добавляем каждый трек в список
        tracks.forEach(track => {
            const trackElement = document.createElement('div');
            trackElement.className = 'recommendation-item';

            trackElement.innerHTML = `
                <div class="song-info">
                    <div class="title">${track.title}</div>
                    <div class="artist">${track.artist}</div>
                </div>
                <div class="song-genre">
                    <span>${track.genre}</span>
                </div>
            `;
            
            recommendationsList.appendChild(trackElement);
        });
    }

    /**
     * ИМИТАЦИЯ ЗАПРОСА К БЭКЕНДУ.
     * Эта функция притворяется, что отправляет запрос на сервер и получает ответ.
     * @param {string} songName - Название песни для поиска.
     * @returns {Promise<Array<Object>>} - Промис с массивом треков.
     */
    function getMockRecommendations(songName) {
        console.log(`Ищем рекомендации для песни: "${songName}"`);

        // Имитация задержки сети (1.5 секунды)
        return new Promise(resolve => {
            setTimeout(() => {
                // Заглушка с фейковыми данными
                const mockData = [
                    { title: 'Come As You Are', artist: 'Nirvana', genre: 'Grunge' },
                    { title: 'Even Flow', artist: 'Pearl Jam', genre: 'Grunge' },
                    { title: 'Black Hole Sun', artist: 'Soundgarden', genre: 'Alternative Rock' },
                    { title: 'Plush', artist: 'Stone Temple Pilots', genre: 'Alternative Rock' },
                    { title: 'Under the Bridge', artist: 'Red Hot Chili Peppers', genre: 'Funk Rock' },
                ];
                resolve(mockData);
            }, 1500);
        });
    }
});
