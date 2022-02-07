<template>
  <transition name="modal-fade">
    <div id="buzaModal" class="buza-modal">
      <div class="buza-modal-mask">

        <div class="buza-modal-wrapper">
          <div class="buza-modal-container">
            <span class="buza-modal-close_button las la-times-circle"
                  style="font-size:32px;"
                  v-on:click="buzaModalClose"
            ></span>
            <div class="buza-modal-header">
              <slot name="modal-header">
                <h3> {{ title }} </h3>
              </slot>
            </div> <!-- // buza-modal-header -->

            <div class="buza-modal-body">
              <slot name="modal-body"></slot>
            </div> <!-- // buza-modal-body -->

            <div class="buza-modal-footer">
              <slot name="modal-footer"></slot>
            </div> <!-- // buza-modal-footer -->

          </div> <!-- //buza-modal-container -->
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: "BuzaModal",
  props: [
    'title'
  ],
  data() {
    return {

    }
  },
  methods: {
    buzaModalClose() {
      this.$emit('buzaModalClose');
    }
  }
}
</script>

<style scoped>
.buza-modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.buza-modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.buza-modal-container {
  width: 700px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 20px;
  box-shadow: var(--shadow);
  transition: all 0.3s ease;
  font-family: Helvetica, Arial, sans-serif;
  position: relative;
}

.buza-modal-close_button {
  position: absolute;
  top:10px;
  right: 10px;
  cursor: pointer;
}

.buza-modal-header h3 {
  margin-top: 0;
  color: #42b983;
}

.buza-modal-body {
  margin: 20px 0;
}

.modal-fade-enter,
.modal-fade-leave-to {
  opacity: 0;
}

.modal-fade-enter-active,
.modal-fade-leave-active {
  transition: opacity .3s ease;
}

</style>