<template>
	<div class="bookpagecomponent">
    	<div class="canva-brown-rusty-mystery-nove-parent">
    		<img class="canva-brown-rusty-mystery-nove-icon" alt="" src="/img/capa.png">
        		<div class="frame-child"></div>
      	</div>
		<div class="bookpagecomponent-child"></div>
    </div>
	<img class="capa-icon" alt="" src="/img/capa.png">
	<div>
			<div class="titulo">Soul</div>
    		<div class="autor">by Olivia Wilson</div>
    		<div class="descricao"> A  vida que Kim e Krickitt Carpenter conheciam mudou completamente no dia  24 de novembro de 1993, dois meses após o seu casamento, quando a  traseira do seu carro foi atingida por uma caminhonete que transitava em alta velocidade. Um ferimento sério na cabeça deixou Krickitt em coma  por várias semanas. Quando finalmente despertou, parte da sua memória  estava comprometida e ela não conseguia se lembrar de seu marido. Ela  não fazia a menor ideia de quem ele era. Essencialmente, a "Krickitt"  com quem Kim havia se casado morreu no acidente, e naquele momento ele  precisava reconquistar a mulher que amava.</div>
    		<div class="group-stars">
      			<img class="star-icon" alt="" src="/img/Star.svg">
      			
      			<img class="star-icon1" alt="" src="/img/Star.svg">
      			
      			<img class="star-icon2" alt="" src="/img/Star.svg">
      			
      			<img class="star-icon3" alt="" src="/img/Star.svg">
      			
      			<img class="star-icon4" alt="" src="/img/Star.svg">
      			
    		</div>
			<div class="classificacao">3.0/5</div>
    		<div class="nr-rates">20 ratings</div>
    		<div class="nr-reviews">1 reviews</div>
    		<div class="separator">
    		</div>
			<div class="group-categories">
      			<div class="rectangle-parent">
        				<div class="group-child">
							Horror
        				</div>
      			</div>
      			<div class="rectangle-group">
        				<div class="group-child">
        				</div>
						<div>Romance</div>	
      			</div>
    		</div>
	</div>
	<NavComponent></NavComponent>
</template>
<script>
import NavComponent from '../components/NavComponent.vue';
export default {
	components: {
        NavComponent
    }

}
</script>

<style>
@media (min-width: 1024px) {
  #app {
    padding: 0;
  }
}
#app {
  max-width: 3000px;
  font-weight: normal;
}

.canva-brown-rusty-mystery-nove-icon {
	position: absolute;
	top: 0px;
	left: 0px;
	width: 3000px;
	height: 1078px;
	object-fit: cover;
}

.frame-child {
	position: absolute;
	top: 0px;
	left: 0px;
	background: linear-gradient(-0.12deg, #000, rgba(153, 153, 153, 0) 99.98%, rgba(89, 89, 89, 0.59) 99.99%);
	width: 3000px;
	height: 1078px;
	overflow: hidden;
}

.canva-brown-rusty-mystery-nove-parent {
	width: 3000px;
	position: relative;
	height: 1170px;
	overflow: auto;
	flex-shrink: 0;
}

.bookpagecomponent-child {
	width: 3000px;
	position: relative;
	background-color: #000;
	height: 2321px;
	margin-top: -108px;
}

.bookpagecomponent {
	width: 3000px;
	display: flex;
	flex-direction: column;
	align-items: flex-start;
	justify-content: flex-start;
} 

.capa-icon {
  	position: absolute;
  	top: 431px;
  	left: 148px;
  	border-radius: 25px;
  	width: 291px;
  	height: 445px;
  	object-fit: cover;
}
.titulo {
  	position: absolute;
  	top: 400px;
  	left: 485px;
  	font-size: 96px;
  	display: inline-block;
  	width: 273px;
  	height: 185px;
	color: white;
}
.autor {
  	position: absolute;
  	top: 529px;
  	left: 485px;
  	font-size: 48px;
	color: white;
}

.descricao {
  	position: absolute;
  	top: 775px;
  	left: 486px;
  	font-family: 'Jacques Francois';
  	white-space: pre-wrap;
  	display: inline-block;
  	width: 1051px;
  	height: 207px;
	color: white;
}
.star-icon {
  	position: absolute;
  	top: 3px;
  	left: 0px;
  	width: 35px;
  	height: 29.6px;
  	object-fit: cover;
}
.star-icon1 {
  	position: absolute;
  	top: 3px;
  	left: 48px;
  	width: 35px;
  	height: 29.6px;
  	object-fit: cover;
}
.star-icon2 {
  	position: absolute;
  	top: 2px;
  	left: 96px;
  	width: 35px;
  	height: 29.6px;
  	object-fit: cover;
}
.star-icon3 {
  	position: absolute;
  	top: 0px;
  	left: 144px;
  	width: 35px;
  	height: 33.1px;
  	object-fit: cover;
}
.star-icon4 {
  	position: absolute;
  	top: 0px;
  	left: 192px;
  	width: 35px;
  	height: 33.1px;
  	object-fit: cover;
}
.group-stars {
  	position: absolute;
  	top: 625px;
  	left: 485px;
  	width: 227px;
  	height: 33.1px;
}

.classificacao {
  	position: absolute;
  	top: 615px;
  	left: 735px;
  	font-size: 36px;
  	display: inline-block;
  	width: 108px;
  	height: 51px;
	color: white;
}
.nr-rates {
  	position: absolute;
  	top: 629px;
  	left: 859px;
	color: white;
}
.nr-reviews {
  	position: absolute;
  	top: 629px;
  	left: 1009px;
	color: white;
}
.separator {
  	position: absolute;
  	top: 643px;
  	left: 970px;
  	border-radius: 50%;
  	background-color: #d9d9d9;
  	width: 5px;
  	height: 5px;
}
.review-box {
  	position: absolute;
  	top: 25.72px;
  	left: 0px;
  	border-radius: 30px;
  	background-color: #212121;
  	width: 929px;
  	height: 159.3px;
  	mix-blend-mode: normal;
}
.foto-icon {
  	position: absolute;
  	top: 0px;
  	left: 24px;
  	width: 79px;
  	height: 74.1px;
  	object-fit: cover;
}
.sophie-mayer {
  	color: #fff;
}

.group-categories {
	position: absolute;
	height: 4.5%;
  	width: 8%;
  	top: 62%;
	left: 13%;
	color: #e1e1e1;
	font-family: Lato;
}

.rectangle-group {
  	position: absolute;
  	height: 100%;
  	width: 48.66%;
  	top: 0%;
  	right: -0.02%;
  	bottom: 0%;
  	left: 51.36%;
	display: inline-block;
}

body {
	margin-left: -80px !important;
}


</style>