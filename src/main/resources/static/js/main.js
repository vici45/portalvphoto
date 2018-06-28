baguetteBox.run('.baguetteBoxOne');

$(function () {

    var speed = 800;//滚动速度


    //回到顶部

    $("#toTop").click(function () {

        $('html,body').animate({

                scrollTop: '0px'

            },

            speed);

    })
});



var a = window.innerWidth / 16 + "px";
document.documentElement.style.fontSize = a;

function refresh() {
    window.location.reload();
}

function toPage() {
    window.location = "http://www.baidu.com";
}

function toTop() {
    $('html,body').animate({scrollTop: '0px'}, 800);
}

function qrcode() {
    document.getElementById('vCode').style.display = "block"

}

function closeVCode() {
    document.getElementById('vCode').style.display = "none"

}

