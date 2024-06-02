<template>
	<main>
    <div class="my-review">
        <div class="review-box">
        </div>
        <img class="foto-icon" alt="" src="/img/perfil1.png">
        <div class="reviewer">
			<div>
				<div class="rating" style="--rating-value: 0;"></div>       
			</div>
		</div>
        <input type="text" class="review-area"></input> 
        <div class="publish">
            <div class="publish-child">
            </div>
            <b class="publicar">Publicar</b>
        </div> 
    </div> 
	</main>
</template>
<style>
.review-box {
  	position: absolute;
  	top: 25.72px;
  	left: 0px;
  	border-radius: 30px;
  	background-color: #212121;
  	width: 929px;
	height: 180px;
	mix-blend-mode: normal;
}
.review-area {
    width: 500px;
    position: relative;
    border-radius: 20px;
    background-color: #5b5b5b;
    height: 50px;
    top: 50%;
    left: 50%;
}

.foto-icon {
  	position: absolute;
  	top: 0px;
  	left: 24px;
  	width: 79px;
  	height: 74.1px;
  	object-fit: cover;
	border-radius: 50%;
}
.sophie-mayer {
  	color: #fff;
}
.reviewer {
  	position: absolute;
  	top: 37.41px;
  	left: 137.7px;
  	font-weight: 600;
  	display: inline-block;
  	width: 196.4px;
  	height: 21.9px;
}
.review {
  	position: absolute;
	top: 110px;
	left: 93.33px;
  	font-weight: 300;
  	color: #fff;
  	display: inline-block;
  	width: 827.8px;
  	height: 72.9px;
}

.my-review {
	width: 100%;
	position: relative;
	height: 185px;
	text-align: left;
	font-size: 16px;
	color: #a9a0a0;
	font-family: 'Open Sans';
}
.publish {
    position: absolute;
    top: 72px;
    left: 874px;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    width: 121.8px;
    height: 49px;
    text-align: center;
    font-size: 20px;
    color: #afafaf;
}  
</style>

