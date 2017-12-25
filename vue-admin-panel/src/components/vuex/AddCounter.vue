<template>
  <div class="container">
    <div class="field is-grouped">
      <div class="control">
        <button class="button is-primary" v-on:click="addCounter()">Add</button>
      </div>
  </div>
  </div>
</template>

<script>
  import * as type from '../../store/mutation/types';

  export default {
    methods: {
        addCounter(){
        	alert("alow");
          store.dispatch({
            type: type.Increment,
            amount: 20
          })
        }
    }
  }
</script>