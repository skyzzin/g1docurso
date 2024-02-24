import { createStore } from 'vuex'

export default createStore({
  state: {
    homeView:"notify",
    isLoggin:false,
    New:{
      description:false,
      reply:false,
      currentReply:""
    },
    Profile:{
      Posts:false
    }
  },
  getters: {
   
  },
  mutations: {
    replyView(state, newView) {
      state.New.reply = newView;
      state.New.description = !newView
    }
  },
  actions: {
  },
  modules: {
  }
})
