import { createStore } from 'vuex';

export default createStore({
    state: {
        userRole: "",
    },
    getters: {
        userRole: (state) => state.userRole,
    },
    mutations: {
        setUserRole(state, role) {
            state.userRole = role;
        },
    },
    actions: {
        updateUserRole({ commit }, role) {
            commit("setUserRole", role);
        },
    },
});
