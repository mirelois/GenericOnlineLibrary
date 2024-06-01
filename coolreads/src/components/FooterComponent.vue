<template>
   	<footer>
        <div class="footer">
        <div class="background"></div>
        <div class="footer-ui">
        <div class="footer-ui1">
        <div class="linearity-gmb">&copy; 2024 CoolReads. All rights reserved. </div>
        </div>
        <div class="thelogo">C</div>
        </div>
    </div> 
    </footer>
</template>
<style>
.background {
    position: absolute;
    top: 0px;
    left: -3px;
    background-color: #6b6b6b;
    width: 3000px;
    height: 300px;
}
.linearity-gmb {
    position: absolute;
    width: 100%;
    top: calc(50% - 10.55px);
    left: 0%;
    line-height: 26px;
    display: inline-block;
    height: 21.1px;
    mix-blend-mode: normal;
}
.footer-ui1 {
    position: absolute;
    width: 100%;
    top: calc(50% + 70px);
    right: 0%;
    left: 0%;
    height: 21.1px;
}
.thelogo {
    position: absolute;
    top: 0px;
    left: 200px;
    font-size: 96px;
    font-family: Inika;
    color: #c48930;
    text-align: left;
    display: inline-block;
    width: 57px;
    height: 101.5px;
}
.footer-ui {
    position: absolute;
    width: 16.2%;
    top: calc(50% - 62px);
    right: 40.69%;
    left: 43.11%;
    height: 124px;
}
.footer {
    width: 3000px;
    position: absolute;
    background-color: #fff;
    height: 300px;
    overflow: hidden;
    text-align: center;
    font-size: 16px;
    color: #fff;
    font-family: Inter;
}
</style>