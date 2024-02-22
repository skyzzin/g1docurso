import { createStore } from 'vuex'

export default createStore({
  state: {
    homeView:"home",
    isLoggin:false,
    New:{
      description:false,
      reply:false,
      currentReply:""
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
