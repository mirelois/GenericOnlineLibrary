<template>
<div class="ribbon">
    <div class="vector-parent">
        <img class="frame-child" alt="" src="/img/bookmark.svg">
        <div class="frame-item">
    </div>
    <div class="frame-inner">
    </div>
    <div class="add-to-bookshelf">Add to Public Bookshelf</div>
    </div>
</div> 
</template>
<style>
.frame-child {
    position: absolute;
    height: 100%;
    width: 100%;
    top: 0%;
    right: 0%;
    bottom: 0%;
    left: 0%;
    max-width: 100%;
    overflow: hidden;
    max-height: 100%;
}
.frame-item {
    position: absolute;
    height: 45.71%;
    width: 8.33%;
    top: 25.71%;
    right: 41.67%;
    bottom: 28.57%;
    left: 50%;
    border-right: 2px solid #fff;
    box-sizing: border-box;
}
.frame-inner {
    position: absolute;
    height: 5.71%;
    width: 66.67%;
    top: 48.57%;
    right: 16.67%;
    bottom: 45.71%;
    left: 16.67%;
    border-top: 2px solid #fff;
    box-sizing: border-box;
}
.add-to-bookshelf {
    position: absolute;
    font-weight: 800;
    width: 300px;
    left: 200%;
}
.vector-parent {
    position: absolute;
    top: 0px;
    left: 0px;
    width: 24px;
    height: 35px;
}
.ribbon {
    width: auto;
    position: absolute;
    height: 35px;
    text-align: left;
    font-size: 20px;
    color: #888585;
    font-family: Karla;
    margin-top: -2675px;
    margin-left: 1300px;
    cursor:pointer;
}
</style>