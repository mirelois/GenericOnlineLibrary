<template>
    <main>
       <button @click="openCommentSection" class="comment-button">
        <label class="react-comment" style="--r:50px">
            <i data-icon="🗨️"></i>
        </label>
       </button> 
       <div v-if="openComments==true" :style="{backgroundColor: backgroundColor,borderRadius: borderRadius,width: width,height: height,top: top,left: left,position: position,zIndex: zIndex}">
        <section class="comment-module">
            <input v-model="mycomment" type="text" class="mycommentarea"/> 
            <button @click="publishComment" class="publish-comment">Publish</button>
            <ul>
                <li v-for="comentario in comentarios">
                    <div class="comment">
                        <div class="comment-img">
                            <img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png" alt="">
                        </div>
                        <div class="comment-content">
                            <div class="comment-details">
                                <h4 class="comment-name">{{ comentario.customerUserName }}</h4>
                            </div>
                            <div class="comment-desc">
                                <p>{{  comentario.comment }}</p>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </section>
        <div v-show="showMoretxt==true" @click="increaseArea" :style="{'margin-left':moreleft,'margin-top':moreheight}">more</div>

       </div>
    </main>
</template>
<script>
import axios from "axios";
import authHeader from '@/services/auth.header';
export default {
    props:{
        isbn:'',
        reviewer:'',
        canInteract:Boolean
    },
    data(){
        return{
            backgroundColor:"#212121",
            borderRadius: "20px",
            width: "700px",
            height: "360px",
            top: "160px",
            left: "0px",
            position: "absolute",
            zIndex: "0",
            moreheight:"-210px",
            moreleft:"50px",
            openComments:false,
            comentarios:[],
            mycomment:'',
            morepage:0,
            showMoretxt:true
        }
    },
    created(){
        this.getComments();
    },
    methods:{
        openCommentSection(){
            this.$emit("comment_opened");
            this.openComments=!this.openComments;
        },
        increaseArea(){
            this.$emit("comment_opened");
            this.morepage = this.morepage + 1; 
            this.getComments();
            if(this.morepage!==1){
              let h = this.height.replace('px','');
              h=parseInt(h)+140+"px";
              this.height=h;
              let more = this.moreheight.replace('px','');
              more=parseInt(more)+140+"px";
              this.moreheight=more;
            }
        },
        publishComment(){
            if(this.mycomment!="" && this.canInteract===true){
                let header = authHeader();
                let config = {headers:header}
                header['Content-Type']='application/json';
                axios.post("http://localhost:8080/api/book/"+this.isbn+"/review/"+this.reviewer+"/comment",
                this.mycomment,
                config
                ).then(resp=>{
                    if(resp.status==200){
                        this.increaseArea();
                        this.comentarios.push({"customerUserName":this.reviewer,"comment":this.mycomment});
                      }
                    console.log(resp);
                }).catch(error=>{
                    console.log(error);
                })
            }else handle_logout();
        },
        getComments(){
            //let header = authHeader();
            //let config = {headers:header}
            //header['Content-Type']='application/json';
            axios.get("http://localhost:8080/api/book/"+this.isbn+"/review/"+this.reviewer+"/comment?page="+this.morepage+"&size=1"
            ).then(resp=>{
                if(resp.data.length==0){
                    this.showMoretxt=false;
                    this.morepage = -1;
                    return 1;
                }
                console.log(resp.data)
                this.comentarios = resp.data;
                return 0;
            }).catch(error=>{
                console.log(error);
            })
        },
        handle_logout(){
            this.$store.dispatch('auth/logout').then(
            () => {
                router.go()
            },
            error => {
              this.message =
                (error.response && error.response.data && error.response.data.message) ||
                error.message ||
                error.toString();
            }
          );
        }
    }
}
</script>
<style scoped>

.mycommentarea{
    width: 500px;
    position: relative;
    border-radius: 20px;
    background-color: #5b5b5b;
    height: 50px;
    left: 10%;
    top:-90px;
}

.publish-comment{
    position: absolute;
    top: 120px;
    left: 580px;
    width: 100px;
    height: 49px;
    text-align: center;
    font-size: 20px;
    color: #afafaf;
    background-color: #313030;
    border-radius: 30px;
    border-width: 0px 0px 0px 0px;
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19);
    cursor:pointer;
}
.comment-button {
    background-color: #31363F;
    border-radius: 30px;
    position: absolute;
    top: 150px;
    left: 500px;
    font-weight: 300;
    color: #fff;
    width: auto;
    height: 80px;
    z-index: 1;
}
.react-comment {
  display:inline-grid;
  vertical-align:middle;
  cursor:pointer;
  width: var(--r);
  height: var(--r);
  border-radius:50%;
  line-height: var(--r);
  transform:rotate(0);
  margin:20px;
}

.react-comment i[data-icon]:before {
  content:attr(data-icon);
  font-style:normal;
  font-size:calc(0.6*var(--r));
}

.react-comment i:before {
  grid-area:1/1;
  font-size:calc(0.8*var(--r));
}

.react-comment i{
  filter:grayscale(100%);
  transform:scale(1);
  grid-area:1/1;
  display:inherit;
  line-height:inherit;
  transition: transform .6s cubic-bezier(0.5,-12,1,-12);
}
.react-comment:hover{
  transform:rotate(0.1deg);
  transition:transform 0.25s cubic-bezier(0.5,400,0.5,-400);
}
.react-comment:before {
  content:"";
  grid-area:1/1;
  border-radius:50%;
  border:solid orange calc(var(--r)/2);
  transform:scale(0);
}
.react-comment:after,
.react-comment i:after{
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
.react-comment i:after {
  background-size:calc(var(--r)/8) calc(var(--r)/8);
  transform:rotate(60deg) scale(0);
}
* ul {
  list-style: none;
}
* a {
  text-decoration: none;
}

body, .cd__main {
  background: #abd1c6 !important;
}

main {
  width: 100%;
  height: 100%;
  display: grid;
  place-items: center;
}
main section.comment-module {
  width: 50%;
  height: auto;
  margin: 30px 0px;
  border-radius: 5px;
  padding: 50px 75px;
}
main section.comment-module ul {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  row-gap: 50px;
  margin-top: -55px;
}
main section.comment-module ul li {
  width: 100%;
  position: relative;
}
main section.comment-module ul li .comment {
  width: 100%;
  display: flex;
  column-gap: 20px;
}
main section.comment-module ul li .comment .comment-img {
  width: 7%;
}
main section.comment-module ul li .comment .comment-img img {
  width: 50px;
  height: 50px;
}
main section.comment-module ul li .comment .comment-content {
  width: 93%;
  display: flex;
  flex-direction: column;
  row-gap: 12px;
}
main section.comment-module ul li .comment .comment-content .comment-details {
  width: 100%;
  display: flex;
  column-gap: 15px;
  align-items: center;
  justify-content: flex-start;
}
main section.comment-module ul li .comment .comment-content .comment-details .comment-name {
  text-transform: capitalize;
}
main section.comment-module ul li .comment .comment-content .comment-details .comment-log {
  color: #7a7a7a;
  font-size: 14px;
}
main section.comment-module ul li .comment .comment-content .comment-data {
  width: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  column-gap: 20px;
}
main section.comment-module ul li .comment .comment-content .comment-data .comment-likes {
  display: flex;
  align-items: center;
  column-gap: 12px;
}
main section.comment-module ul li .comment .comment-content .comment-data .comment-likes > div {
  display: flex;
  column-gap: 4px;
  align-items: center;
}
main section.comment-module ul li .comment .comment-content .comment-data .comment-likes > div img {
  cursor: pointer;
}
main section.comment-module ul li .comment .comment-content .comment-data .comment-likes > div span {
  font-weight: 600;
}
main section.comment-module ul li .comment .comment-content .comment-data .comment-reply a, main section.comment-module ul li .comment .comment-content .comment-data .comment-report a {
  color: #272727;
  font-weight: 400;
}
main section.comment-module ul li::before {
  content: "";
  position: absolute;
  top: 60px;
  left: 50px;
  transform: translateX(-25px);
  width: 2px;
  height: calc(100% - 60px);
  background: #c5c5c5;
}
main section.comment-module ul li ul {
  margin-top: 35px;
  margin-left: 70px;
  width: calc(100% - 70px);
}

main section.comment-module {
    width: 80%;
}

main section.comment-module {
    width: 96%;
    padding: 180px 10px;
}
main section.comment-module ul li .comment {
    column-gap: 12px;
}

main section.comment-module ul li .comment .comment-img {
    width: 15%;
}
main section.comment-module ul li .comment .comment-img img {
    width: 40px;
    height: 40px;
    border-radius: 50%;
}
main section.comment-module ul li .comment .comment-content {
    width: 85%;
}

main section.comment-module ul li .comment .comment-content .comment-details {
    flex-direction: column;
    align-items: flex-start;
}

main section.comment-module ul li .comment .comment-content .comment-data {
    column-gap: 12px;
}

main section.comment-module ul li::before {
    top: 50px;
    left: 50px;
    transform: translateX(-30px);
    height: calc(100% - 60px);
}

main section.comment-module ul li ul {
    margin-top: 25px;
    margin-left: 50px;
    width: calc(100% - 50px);
}

</style>