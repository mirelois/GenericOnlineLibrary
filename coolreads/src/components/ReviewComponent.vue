<template>
	<main>
    <div class="my-review">
        <div class="review-box">
        </div>
        <img class="foto-icon" alt="" src="/img/perfil1.png">
        <div class="reviewer">
        <span>review by</span>
        <span class="sophie-mayer"> Sophie Mayer</span>
			<div>
				<div class="rating" style="--rating-value: 2.5;"></div>       
			</div>
		</div>
        <div class="review">"Soul" by Olivia Wilson is a captivating journey into the depths of the human experience. With richly drawn characters and evocative prose, Wilson explores the essence of humanity, touching upon universal truths that resonate deeply. A profound and thought-provoking read that lingers in the mind long after the last page.
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
</style>

