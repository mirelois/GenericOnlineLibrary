<template>
<main>
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.0/css/all.css">
    </head>    
    <div class="reaction-bar">
    <input type="checkbox" class="hide-input" id="r1">
    <label for="r1" class="react" style="--r:35px">
    <i class="fas fa-thumbs-up" style="color:blue"></i>
    </label>
    
    <input type="checkbox"  class="hide-input" id="r2">
    <label for="r2" class="react" style="--r:35px">
      <i class="fas fa-laugh-squint" style="color:gold"></i>
    </label>
    <input type="checkbox" class="hide-input" id="r3">
    <label for="r3" class="react" style="--r:35px">
        <i class="fas fa-sad-tear" style="color:gold"></i>
    </label>
    
    <input type="checkbox"  class="hide-input" id="r4">
    <label for="r4" class="react" style="--r:35px">
        <i class="fas fa-surprise" style="color:gold"></i>
    </label>

    <input type="checkbox" id="r5" class="hide-input">
    <label for="r5" class="react" style="--r:35px">
    <i data-icon="🤡"></i>
    </label>
    </div>
</main>
</template>
<style>
.react {
  --r:100px;
  display:inline-grid;
  vertical-align:middle;
  cursor:pointer;
  width: var(--r);
  height: var(--r);
  border-radius:50%;
  margin: calc(var(--r)/3);
  line-height: var(--r);
  text-align:center;
  transform:rotate(0);
}

.react i[data-icon]:before {
  content:attr(data-icon);
  font-style:normal;
  font-size:calc(0.6*var(--r));
}

.react i:before {
  grid-area:1/1;
  font-size:calc(0.8*var(--r));
}

.react i{
  filter:grayscale(100%);
  transform:scale(1);
  grid-area:1/1;
  display:inherit;
  line-height:inherit;
  transition: transform .6s cubic-bezier(0.5,-12,1,-12);
}
.react:hover{
  transform:rotate(0.1deg);
  transition:transform 0.25s cubic-bezier(0.5,400,0.5,-400);
}
.react:before {
  content:"";
  grid-area:1/1;
  border-radius:50%;
  border:solid orange calc(var(--r)/2);
  transform:scale(0);
}
.react:after,
.react i:after{
  content:"";
  grid-area:1/1;
  margin: calc(var(--r)/-2);
  --c1:radial-gradient(red    50%,#0000 60%);
  --c2:radial-gradient(orange 50%,#0000 60%);
  background:
    var(--c1),var(--c1),var(--c1),var(--c1),
    var(--c2),var(--c2),var(--c2),var(--c2); 
  background-size:calc(var(--r)/6) calc(var(--r)/6); 
  background-position:
    calc(50% - var(--r)/2) calc(50% - var(--r)/2),
    calc(50% + var(--r)/2) calc(50% - var(--r)/2),
    calc(50% - var(--r)/2) calc(50% + var(--r)/2),
    calc(50% + var(--r)/2) calc(50% + var(--r)/2),
    calc(50% +  0px) calc(50% + var(--r)*0.707),
    calc(50% + var(--r)*0.707) calc(50% +  0px),
    calc(50% - var(--r)*0.707) calc(50% +  0px),
    calc(50% +  0px) calc(50% - var(--r)*0.707);
  background-repeat:no-repeat;
  transform:scale(0);
}
.react i:after {
  background-size:calc(var(--r)/8) calc(var(--r)/8);
  transform:rotate(60deg) scale(0);
}

input:checked + .react i{
  filter:grayscale(0);
  transform:scale(0.95);
  transition:
     filter .5s .5s,
     transform 1s cubic-bezier(0,26.67,.5,26.67);
}
input:checked + .react:before {
  border-width:0;
  transform:scale(1);
  transition:
    transform 0.5s,
    border-width 0.5s 0.5s;
}
input:checked + .react:after,
input:checked + .react i:after{
  transform:scale(1);
  opacity:0;
  background-size:0 0;
  transition:
    transform 0.5s 0.5s,
    opacity   0.4s 0.9s,
    background-size 0.5s 0.9s;
}
input:checked + .react i:after {
  transform:rotate(55deg) scale(1);
}


.hide-input {
  position:absolute;
  visibility:hidden;
  clip-path:inset(50%)
}

.reaction-bar {
  background-color: #31363F;
  border-radius: 30px;
  position: absolute;
  top: 150px;
  left: 30px;
  font-weight: 300;
  color: #fff;
  display: inline-flex;
  width: auto;
  height: 80px;
  padding-top: 12px;
}

</style>
