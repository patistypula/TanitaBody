document.addEventListener("DOMContentLoaded", function () {

console.log("test");
    var buttons = document.getElementsByClassName("measur");
     for(var i=0;   i<buttons.length;   i++){
        buttons[i].addEventListener('click', function (event){
            if(buttons[i].checked){
                console.log("ha ha ha");
            }
        });
     }


});