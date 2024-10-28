import { createStore } from 'vuex';

export default createStore({
    state: {
        userRole: "",
        username: "",
        id: null,
    },
    getters: {
        userRole: (state) => state.userRole,
        username: (state) => state.username,
        id: (state) => state.id,
    },
    mutations: {
        setUserRole(state, role) {
            state.userRole = role;
        },
        setUsername(state, username) {
            state.username = username;
        },
        setId(state, id) {
            state.id = id;
        },
        clearUserData(state) {
            state.userRole = "";
            state.username = "";
            state.id = null;
        },
    },
    actions: {
        updateUserRole({ commit }, role) {
            commit("setUserRole", role);
        },
        updateUsername({ commit }, username) {
            commit("setUsername", username);
        },
        updateId({ commit }, id) {
            commit("setId", id);
        },
        clearUserData({ commit }) {
            commit("clearUserData");
        },
    },
});
