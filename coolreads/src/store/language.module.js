const state = () => ({
    selectedLanguage: localStorage.getItem('selectedLanguage') || 'english',
    translations: {
      english: {
        selectLanguage: 'Select your preferred language',
        email: 'Email:',
        password: 'Password:',
        changePassword: 'Change Password',
        privacyTitle: 'Default Privacy Settings for Bookshelf',
        public: 'Public',
        friends: 'Friends Only',
        private: 'Private',
        books: 'Books',
        profile: 'Profile',
        viewProfile: 'View Profile',
        settings: 'Settings',
        home: 'Home',
        searchPlaceholder: 'Search for books, readers and writers...',
        notifications: 'Notifications',
        viewAll: 'View All',
        bookshelf: 'Bookshelf',
        allnotifications: 'All Notifications',
        delete: 'Delete',
        currentPassword: 'Current Password',
        newPassword: 'New Password',
        confirm: 'Confirm',
      },
      portuguese: {
        selectLanguage: 'Selecione a linguagem pretendida',
        email: 'Email:',
        password: 'Palavra-Passe:',
        changePassword: 'Alterar Senha',
        privacyTitle: 'Definições Padrão para Privacidade da Estante',
        public: 'Pública',
        friends: 'Apenas Amigos',
        private: 'Privada',
        books: 'Livros',
        profile: 'Perfil',
        viewProfile: 'Ver Perfil',
        settings: 'Definições',
        home: 'Início',
        searchPlaceholder: 'Pesquisar por livros, leitores e escritores...',
        notifications: 'Notificações',
        viewAll: 'Ver Todos',
        bookshelf: 'Estante',
        allnotifications: 'Todas as Notificações',
        delete: 'Apagar',
        currentPassword: 'Palavra-Passe Atual',
        newPassword: 'Palavra-Passe Nova',
        confirm: 'Confirmar',
      },
    },
  });
  
  const mutations = {
    setSelectedLanguage(state, language) {
        state.selectedLanguage = language;
        localStorage.setItem('selectedLanguage', language);
    },
};;
  
  const actions = {
    setLanguage({ commit }, language) {
      commit('setSelectedLanguage', language);
    },
  };
  
  const getters = {
    currentTranslations(state) {
      return state.translations[state.selectedLanguage];
    },
  };
  
  export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters,
  };
  