<script>
import {defineComponent} from "vue";
import MenuComponent from "./components/MenuComponent.vue";

export default defineComponent({
  components: {MenuComponent}
})
</script>

<template>
  <div class="body">
    <menu-component class="menu"/>
    <router-view class="view"/>
  </div>
</template>

<style scoped>
.body{
  display: flex;
  flex-direction: row;
}
.menu{
  display: flex;
  flex: 1;
}
.view{
  display: flex;
  flex-direction: ;
}
</style>
