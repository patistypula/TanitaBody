document.addEventListener("DOMContentLoaded", function () {

    var maxLength = 11;
    var counter = 0;
    var text = document.getElementById("count");
    var area = document.getElementById("countArea");
    var first = document.getElementById("first");
    var last  = document.getElementById("last");
    var clear  = document.getElementById("clear");
    var create = document.querySelector(".create");
    area.style.color = "green";
    var buttons = document.querySelectorAll(".measur");
     for(var i=0;   i<buttons.length;   i++){
        buttons[i].addEventListener('click', function (event){
            if(this.checked){
                getCount(1);
            } else{
                getCount(-1);
            }
            if(counter!=maxLength){
                first.checked = false;
                last.checked = false;
            }
            fontColor();
            addOrReomveButton();
        });
     }

     first.addEventListener('click', function (event) {
        unCheckedAll();
        var size;
        if(buttons.length<=maxLength){
            size = buttons.length;
        } else{
            size = maxLength;
        }
        for(var i=0;   i<size;   i++){
            buttons[i].checked = true;
        }
        first.checked = true;
        counter = size;
        text.innerText = counter;
        fontColor();
        addOrReomveButton();
     });

     last.addEventListener('click', function (event) {
        unCheckedAll();
        var start;
        if(buttons.length<=maxLength){
            start = 0;
        } else{
            start = buttons.length - maxLength;
        }
        for(var i=start;   i<buttons.length;   i++){
            buttons[i].checked = true;
        }
        last.checked = true;
        counter = (buttons.length-start);
        text.innerText = counter;
        fontColor();
        addOrReomveButton();
     });

     clear.addEventListener('click', function (event) {
        unCheckedAll();
        counter = 0;
        text.innerText = counter;
     });

     function getCount(value){
        counter = counter + value;
        text.innerText = counter;
     }

     function fontColor(){
        if(counter>maxLength){
            area.style.color = "red";
        } else{
            area.style.color = "green";
        }
     }

     function addOrReomveButton(){
        if(counter>maxLength){
            area.style.color = "red";
            if(create.hasChildNodes()){
                var btn = create.firstElementChild;
                btn.parentElement.removeChild(btn);
            }
        } else{
            area.style.color = "green";
            if(!create.hasChildNodes()){
                var newButton = document.createElement("button");
                newButton.innerHTML = "Generuj PDF";
                newButton.type = "submit";
                newButton.name = "createPDF";
                create.appendChild(newButton);
            }
        }
     }

     function unCheckedAll(){
        for(var i=0;   i<buttons.length;   i++){
            buttons[i].checked = false;
        }
        first.checked = false;
        last.checked = false;
        clear.checked = false;
     }



});