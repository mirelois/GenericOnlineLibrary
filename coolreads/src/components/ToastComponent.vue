<template>
    <main>
        <div id="popup1" class="overlay">
        <div class="popup">
            <h2>{{ msg }}</h2>
            <a class="close" @click="sendCloseToast" href="#">&times;</a>
        </div>
        </div>
    </main>
</template>
<script>
export default{
  props:{
    msg:'',
  },
  methods:{
    sendCloseToast(){
      this.$emit('close_toast')
    }
  }
}
</script>
<style>
.overlay {
  position: fixed;
  top: 300px;
  bottom: 0;
  left: 0;
  right: 0;
  transition: opacity 500ms;
}
.overlay:target {
  visibility: visible;
  opacity: 1;
}

.popup {
  margin: 70px auto;
  padding: 20px;
  background: #fff;
  border-radius: 5px;
  width: 30%;
  position: relative;
  transition: all 5s ease-in-out;
  z-index:30;
}

.popup h2 {
  margin-top: 0;
  color: #333;
  font-family: Tahoma, Arial, sans-serif;
}
.popup .close {
  position: absolute;
  top: 20px;
  right: 30px;
  transition: all 200ms;
  font-size: 30px;
  font-weight: bold;
  text-decoration: none;
  color: #333;
}
.popup .close:hover {
  color: #111111;
}

</style>