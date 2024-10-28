import { createStore } from 'vuex';

export default createStore({
    state: {
        userRole: "",
        username: "",
    },
    getters: {
        userRole: (state) => state.userRole,
        username: (state) => state.username,
    },
    mutations: {
        setUserRole(state, role) {
            state.userRole = role;
        },
        setUsername(state, username) {
            state.username = username;
        },
        clearUserData(state) {
            state.userRole = "";
            state.username = "";
        },
    },
    actions: {
        updateUserRole({ commit }, role) {
            commit("setUserRole", role);
        },
        updateUsername({ commit }, username) {
            commit("setUsername", username);
        },
        clearUserData({ commit }) {
            commit("clearUserData");
        },
    },
});

