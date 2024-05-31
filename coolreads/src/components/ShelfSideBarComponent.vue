<template>
	<main>
	<div class="shelf-sidebar">
		<b class="title">Your Bookshelves</b>
		<div class="shelves-parent">
			<div class="shelves">Want To Read</div>
			<div class="shelves1">3</div>
		</div>
		<div class="shelves-group">
			<div class="shelves1">1</div>
			<div class="shelves3">Currently Reading </div>
		</div>
		<div class="shelves-container">
			<div class="shelves1">5</div>
			<div class="shelves5">Read</div>
		</div>
		<div class="group-div">
			<div class="shelves1">0</div>
			<div class="shelves7">Did Not Finish</div>
		</div>
		<div class="shelves-parent1">
			<div class="shelves1">1</div>
			<div class="shelves7">Aasimov Rules</div>
		</div>
		<div class="shelves-parent2" id="groupContainer5">
			<div class="shelves10" id="shelvesText">All</div>
			<div class="shelves1">10</div>
		</div>
		<div class="create-bookshelf-btn" id="createBookshelfBtnContainer">
			<div class="create-list-rectangle">
			</div>
			<b class="createbs">Create Bookshelf</b>
			<img class="transfer-icon" alt="" src="/img/transfer.svg">
		</div>
	</div>
	<div id="createBookshelfInfoContainer" class="popup-overlay" style="display:none">
		<div class="create-bookshelf-info">
		<div class="enter-bookshelf-name">Enter Bookshelf Name</div>
		<div class="create-bookshelf-btn1">
			<div class="create-list-rectangle1">
			</div>
		<div class="createbs1">CREATE</div>
		</div>
		</div>
	</div>
	</main>

</template>
<style>

.title {
  	position: absolute;
  	font-size: 26px;
  	letter-spacing: 0.03em;
}
.shelves {
  	position: absolute;
  	top: 0px;
  	left: 0px;
  	display: inline-block;
  	width: 156.7px;
}
.shelves1 {
  	position: absolute;
  	top: 0px;
  	left: 243px;
  	display: inline-block;
  	width: 19px;
}
.shelves-parent {
  	position: absolute;
  	top: 99px;
  	left: 41px;
  	width: 262px;
  	height: 26px;
}
.shelves3 {
  	position: absolute;
  	top: 0px;
  	left: 0px;
}
.shelves-group {
  	position: absolute;
  	top: 131px;
  	left: 41px;
  	width: 262px;
  	height: 26px;
}
.shelves5 {
  	position: absolute;
  	top: 0px;
  	left: 0px;
  	display: inline-block;
  	width: 168px;
}
.shelves-container {
  	position: absolute;
  	top: 163px;
  	left: 41px;
  	width: 262px;
  	height: 26px;
}
.shelves7 {
  	position: absolute;
  	top: 0px;
  	left: 0px;
  	display: inline-block;
  	width: 168px;
  	height: 25px;
}
.group-div {
  	position: absolute;
  	top: 195px;
  	left: 41px;
  	width: 262px;
  	height: 26px;
}
.shelves-parent1 {
  	position: absolute;
  	top: 227px;
  	left: 41px;
  	width: 262px;
  	height: 26px;
}
.shelves10 {
  	position: absolute;
  	top: 0px;
  	left: 0px;
  	display: inline-block;
  	width: 168px;
  	cursor: pointer;
}
.shelves-parent2 {
  	position: absolute;
  	top: 67px;
  	left: 41px;
  	width: 262px;
  	height: 26px;
  	cursor: pointer;
}
.create-list-rectangle {
  	position: absolute;
  	height: 100%;
  	width: 100%;
  	top: 0%;
  	right: 0%;
  	bottom: 0%;
  	left: 0%;
  	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  	border-radius: 6px;
  	background-color: #e1e1e1;
}
.createbs {
  	position: absolute;
  	height: 50%;
  	width: 73.32%;
  	top: 25%;
  	left: 18.57%;
  	letter-spacing: 1px;
  	display: flex;
  	align-items: center;
  	justify-content: center;
}
.transfer-icon {
  	position: absolute;
  	height: 29.23%;
  	width: 6.68%;
  	top: 37.5%;
  	right: 88.08%;
  	bottom: 33.27%;
  	left: 5.24%;
  	max-width: 100%;
  	overflow: hidden;
  	max-height: 100%;
}
.create-bookshelf-btn {
  	position: absolute;
  	top: 269px;
  	left: 41px;
  	width: 262px;
  	height: 39px;
  	text-align: center;
  	color: #000;
}

.shelf-sidebar {
  width: 350px;
  position: relative;
  border-radius: 5px;
  background: #000;
  height: 332px;
  text-align: left;
  font-size: 20px;
  color: #fff;
  font-family: Inika;
  padding: 10px;
  float: left;
  margin-left: -1000px;
  bottom: 170px;
}

.menusclose-line-round-icon {
  	position: absolute;
  	top: 18px;
  	left: 16px;
  	width: 35px;
  	height: 35px;
  	overflow: hidden;
}
.enter-bookshelf-name {
  	position: absolute;
  	top: 20px;
  	left: 64px;
}
.create-list-rectangle1 {
  	position: absolute;
  	height: 100%;
  	width: 100%;
  	top: 0%;
  	right: 0%;
  	bottom: 0%;
  	left: 0%;
  	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  	border-radius: 6px;
  	background-color: #e1e1e1;
}
.createbs1 {
  	position: absolute;
  	height: 50%;
  	width: 100%;
  	top: 25%;
  	left: 0%;
  	display: flex;
  	align-items: center;
  	justify-content: center;
}
.create-bookshelf-btn1 {
  	position: absolute;
  	top: 19px;
  	left: 326px;
  	width: 79px;
  	height: 32px;
  	text-align: center;
  	font-size: 16px;
  	color: #000;
}
.create-bookshelf-info {
  	width: 423px;
  	position: relative;
  	border-radius: 20px;
  	background-color: #0f0f0f;
  	height: 70px;
  	overflow: hidden;
  	max-width: 90%;
  	max-height: 90%;
  	text-align: left;
  	font-size: 24px;
  	color: #bababa;
  	font-family: Inika;
}
</style>