let API_URL = 'http://localhost:8080';
export const environment = {
  production: false,
  api: {
    game: {
      start: `${API_URL}/game/start`,
      play: `${API_URL}/game/play`,
    }
  }
};
