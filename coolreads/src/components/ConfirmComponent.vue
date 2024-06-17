<template>
    <div class="confirm-bookshelfadd">
            <div class="confirm-bookshelfadd-child">
            </div>
        <div class="are-you-sure-container">
            <p class="are-you-sure">{{ header_msg }}</p>
        </div>
        <button @click="sendConfirmation(`yes`)" class="confirm-bookshelfadd-item">YES</button>
        <button @click="sendConfirmation(`no`)" class="confirm-bookshelfadd-inner">NO</button>
    </div>
</template>
<script>
export default {
	props:{
		header_msg:''
	}, 
	methods:{
		sendConfirmation(response){
			this.$emit('confirmation_response', response);
		}
	}
}
</script>
<style>
.confirm-bookshelfadd-child {
	position: absolute;
	top: 0px;
	left: 0px;
	border-radius: 20px;
	background-color: #131313;
	width: 800px;
	height: 500px;
}

.are-you-sure {
  width: 550px;
}

.are-you-sure-container {
  	position: absolute;
  	top: 91px;
  	left: 138px;
  	color: #fff;
}

.confirm-bookshelfadd-item {
	position: absolute;
	top: 330px;
	left: 171px;
	border-radius: 5px;
	background-color: #86ff92;
	width: 159px;
	height: 60px;
	cursor:pointer;
}

.yes {
  	position: absolute;
  	top: 352px;
  	left: 257px;
}

.confirm-bookshelfadd-inner {
	position: absolute;
	top: 330px;
	left: 444px;
	border-radius: 5px;
	background-color: #ff8888;
	width: 159px;
	height: 60px;
	cursor:pointer;
}

.no {
  	position: absolute;
  	top: 352px;
  	left: 691px;
  	font-size: 35px;
  	font-family: Inika;
  	text-align: left;
}

.confirm-bookshelfadd {
	width: 100%;
	position: absolute;
	height: 539px;
	text-align: center;
	font-size: 30px;
	color: #000;
	font-family: Michroma;
	z-index: 22;
	margin-top: 400px;
	margin-left: -1800px;
}
</style>