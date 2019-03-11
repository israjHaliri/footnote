/**
 * Created by israjhaliri on 1/11/18.
 */
import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        attachments: []
        // attachments: [
            // {rn: 1, file: "C:/test-file-1.txt"},
            // {rn: 2, file: "C:/test-file-2.txt"}
        // ]
    },
    mutations: {
        exMutation: (state, data) => {
            console.log("mutation state ",state);
            console.log("mutation data ",data);
            state.attachments = data;
            // state.attachments = [
            //     {rn: 8, file: "C:/test-file-1.txt"},
            //     {rn: 10, file: "C:/test-file-2.txt"},
            //     {rn: data, file: "C:/test-file-2.txt"},
            // ]
        }
    },
    actions: {
        exAction: (context, data) => {
            console.log("action attachment : ",data);
            context.commit('exMutation', data);
        }
    },
    getters: {
        exGetter: state => {
            console.log("get attachment : ",state.attachments);
            return state.attachments
        }
    }
})

export default store