/**
 * Created by israjhaliri on 1/11/18.
 */
import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        attachments: [
            {rn: 1 ,file : "C:/test-file-1.txt"},
            {rn: 2 ,file : "C:/test-file-2.txt"}
        ]
    },
    mutations: {
        exMutation: (state, data) => {
            state.attachments = [
                {rn: 8 ,file : "C:/test-file-1.txt"},
                {rn: 10 ,file : "C:/test-file-2.txt"},
                {rn: data ,file : "C:/test-file-2.txt"},
            ]
        }
    },
    actions:{
        exAction : (context, data) => {
            setTimeout(function () {
                context.commit('exMutation',data);
            },2000)
        }
    },
    getters: {
        exGetter: state => {
            var attachments = state.attachments.map(attachment => {
                return {
                    rn: attachment.rn,
                    file: "test-file-"+attachment.file+".txt"
                }
            })
            return attachments
        }
    }
})

export default store