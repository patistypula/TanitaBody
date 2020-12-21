document.addEventListener("DOMContentLoaded", function () {
    var sex = document.getElementById("sex");
    var age = document.getElementById("age");
    var h   = document.getElementById("height");


    var bodyFat = document.getElementById("bodyFat");
    var row1  = document.getElementById("row1");
    var row1p = document.getElementById("row1p");
    var bodyWeight = document.getElementById("bodyWeight");
    var row2 = document.getElementById("row2");
    var bodyWater = document.getElementById("bodyWater");
    var row3 = document.getElementById("row3");
    var row3p = document.getElementById("row3p");
    var visceralFat = document.getElementById("visceralFat");
    var row4 = document.getElementById("row4");
    var row4p = document.getElementById("row4p");

    var bodyBuildingIndex = document.getElementById("bodyBuildingIndex");
    var row6 = document.getElementById("row6");
    var row6p = document.getElementById("row6p");

    var boneMass = document.getElementById("boneMass");
    var row7 = document.getElementById("row7");
    var row7p = document.getElementById("row7p");
    
    var bmi   = document.getElementById("bmi");
    var row8  = document.getElementById("row8");
    var row8p = document.getElementById("row8p");


    bodyFat.addEventListener('keyup', function (event){
        bodyFlatRanges();
    });

    bodyWater.addEventListener('keyup', function(event){
        bodyWaterPercentage();
    });

    visceralFat.addEventListener('keyup', function(event){
        visceralFatRange();
    });

    bodyBuildingIndex.addEventListener('keyup', function (event){
        bodyBuildingIndexRange();
    });

    boneMass.addEventListener('keyup', function (event){
        boneMassRange();
    });
    
    bmi.addEventListener('keyup', function (event){
        bmiRange();
    });
    
    bodyWeight.addEventListener('keyup', function(event){
        if(h.className==""){
            bmi.value = "nie podano wzrostu";
        } else{
            var bmiCount = bodyWeight.value/Math.pow((h.className/100), 2);
            //console.log("dokaldnosc: "+precision(bmiCount));
            bmi.value = bmiCount.toPrecision(precision(bmiCount));
            bmiRange();
        }
    });

    function bodyFlatRanges(){
        if(sex.className=="F"){
            if(age.className>=18 && age.className<= 39){
                if(bodyFat.value>=0  && bodyFat.value<21){
                    row1.style.color = "blue";
                    row1p.innerText = "UNDERFAT";
                }else if(bodyFat.value>=21 && bodyFat.value<=33){
                    //console.log("gren: "+bodyFat.value);
                    row1.style.color = "green";
                    row1p.innerText = "HEALTHY";
                } else if(bodyFat.value>=34 && bodyFat.value<=39){
                    //console.log("red: "+bodyFat.value);
                    row1.style.color = "orange";
                    row1p.innerText = "OVERFAT";
                } else if(bodyFat.value>=40){
                    row1.style.color = "red";
                    row1p.innerText = "OBESE";
                }
            } else if(age.className>=40 && age.className<=59){
                if(bodyFat.value>=0  && bodyFat.value<23){
                    row1.style.color = "blue";
                    row1p.innerText = "UNDERFAT";
                }else if(bodyFat.value>=23 && bodyFat.value<34){
                    //console.log("gren: "+bodyFat.value);
                    row1.style.color = "green";
                    row1p.innerText = "HEALTHY";
                } else if(bodyFat.value>=34 && bodyFat.value<=40){
                    //console.log("red: "+bodyFat.value);
                    row1.style.color = "orange";
                    row1p.innerText = "OVERFAT";
                } else if(bodyFat.value>=41){
                    row1.style.color = "red";
                    row1p.innerText = "OBESE";
                }
            } else if(age.className>=60 && age.className<=99){
                if(bodyFat.value>=0  && bodyFat.value<24){
                    row1.style.color = "blue";
                    row1p.innerText = "UNDERFAT";
                }else if(bodyFat.value>=24 && bodyFat.value<=36){
                    //console.log("gren: "+bodyFat.value);
                    row1.style.color = "green";
                    row1p.innerText = "HEALTHY";
                } else if(bodyFat.value>36 && bodyFat.value<=41){
                    //console.log("red: "+bodyFat.value);
                    row1.style.color = "orange";
                    row1p.innerText = "OVERFAT";
                } else if(bodyFat.value>41){
                    row1.style.color = "red";
                    row1p.innerText = "OBESE";
                }
            }
        } else{
            if(age.className>=18 && age.className<= 39){
                if(bodyFat.value>=0  && bodyFat.value<9){
                    row1.style.color = "blue";
                    row1p.innerText = "UNDERFAT";
                }else if(bodyFat.value>=9 && bodyFat.value<=20){
                    //console.log("gren: "+bodyFat.value);
                    row1.style.color = "green";
                    row1p.innerText = "HEALTHY";
                } else if(bodyFat.value>20 && bodyFat.value<=25){
                    //console.log("red: "+bodyFat.value);
                    row1.style.color = "orange";
                    row1p.innerText = "OVERFAT";
                } else if(bodyFat.value>25){
                    row1.style.color = "red";
                    row1p.innerText = "OBESE";
                }
            } else if(age.className>=40 && age.className<=59){
                if(bodyFat.value>=0  && bodyFat.value<11){
                    row1.style.color = "blue";
                    row1p.innerText = "UNDERFAT";
                }else if(bodyFat.value>=11 && bodyFat.value<=22){
                    //console.log("gren: "+bodyFat.value);
                    row1.style.color = "green";
                    row1p.innerText = "HEALTHY";
                } else if(bodyFat.value>22 && bodyFat.value<=28){
                    //console.log("red: "+bodyFat.value);
                    row1.style.color = "orange";
                    row1p.innerText = "OVERFAT";
                } else if(bodyFat.value>28){
                    row1.style.color = "red";
                    row1p.innerText = "OBESE";
                }
            } else if(age.className>=60 && age.className<=99){
                if(bodyFat.value>=0  && bodyFat.value<13){
                    row1.style.color = "blue";
                    row1p.innerText = "UNDERFAT";
                }else if(bodyFat.value>=13 && bodyFat.value<=25){
                    //console.log("gren: "+bodyFat.value);
                    row1.style.color = "green";
                    row1p.innerText = "HEALTHY";
                } else if(bodyFat.value>25 && bodyFat.value<=30){
                    //console.log("red: "+bodyFat.value);
                    row1.style.color = "orange";
                    row1p.innerText = "OVERFAT";
                } else if(bodyFat.value>30){
                    row1.style.color = "red";
                    row1p.innerText = "OBESE";
                }
            }
        }
    }

    function bodyWaterPercentage(){
        console.log(sex.className);
        if(sex.className=="F"){
            console.log("F");
            if(bodyWater.value>=45 && bodyWater.value<=60){
                row3.style.color = "green";
                row3p.innerText = "HEALTHY";
            } else{
                row3.style.color = "#ff0000";
                row3p.innerText = "NOT HEALTHY";
            }
        }
        else{
            console.log("M");
            if(bodyWater.value>=50 && bodyWater.value<=65){
                row3.style.color = "green";
                row3p.innerText = "HEALTHY";
            } else{
                row3.style.color = "#ff0000";
                row3p.innerText = "NOT HEALTHY";
            }
        }
    }
//
    function visceralFatRange(){
        if(visceralFat.value>=1 && visceralFat.value<=12){
            row4.style.color = "green";
            row4p.innerText = "HEALTHY LEVEL";
        } else{
            row4.style.color = "#ff0000";
            row4p.innerText = "EXCESS LEVEL";
        }
    }

    function bodyBuildingIndexRange(){
        if(bodyBuildingIndex.value==1){
            row6p.innerText = "HIDDEN OBESE";
            row6.style.color = "#ff0000";
        } else if(bodyBuildingIndex.value==2){
            row6p.innerText = "OBESE";
            row6.style.color = "#ff0000";
        } else if(bodyBuildingIndex.value==3){
            row6p.innerText = "SOLIDLY-BUILT";
            row6.style.color = "green";
        } else if(bodyBuildingIndex.value==4){
            row6p.innerText = "UNDER EXERCISED";
            row6.style.color = "green";
        } else if(bodyBuildingIndex.value==5){
            row6p.innerText = "STANDARD";
            row6.style.color = "green";
        } else if(bodyBuildingIndex.value==6){
           row6p.innerText = "STANDARD MUSCULAR";
           row6.style.color = "green";
        } else if(bodyBuildingIndex.value==7){
           row6p.innerText = "THIN";
           row6.style.color = "green";
        } else if(bodyBuildingIndex.value==8){
           row6p.innerText = "THIN AND MUSCULAR";
           row6.style.color = "green";
        } else if(bodyBuildingIndex.value==9){
           row6p.innerText = "VERY MUSCULAR";
           row6.style.color = "green";
        }
    }

    function boneMassRange(){
        if(sex.className=="F"){
            if(bodyWeight.value<50 && bodyWeight.value>0){
                if(boneMass.value>=1.95){
                    row7.style.color = "green";
                    row7p.innerText = "HEALTHY";
                } else{
                    row7.style.color = "#ff0000";
                    row7p.innerText = "NOT HEALTHY";
                }
            } else if(bodyWeight.value>=50 && bodyWeight.value<75){
                if(boneMass.value>=2.40){
                    row7.style.color = "green";
                    row7p.innerText = "HEALTHY";
                } else{
                    row7.style.color = "#ff0000";
                    row7p.innerText = "NOT HEALTHY";
                }
            } else if(bodyWeight.value>=75){
                if(boneMass.value>=2.95){
                    row7.style.color = "green";
                    row7p.innerText = "HEALTHY";
                } else{
                    row7.style.color = "#ff0000";
                    row7p.innerText = "NOT HEALTHY";
                }
            }
        } else{
            if(bodyWeight.value<65 && bodyWeight.value>0){
                if(boneMass.value>=2.65){
                    row7.style.color = "green";
                    row7p.innerText = "HEALTHY";
                } else{
                    row7.style.color = "#ff0000";
                    row7p.innerText = "NOT HEALTHY";
                }
            } else if(bodyWeight.value>=65 && bodyWeight.value<95){
                if(boneMass.value>=3.29){
                    row7.style.color = "green";
                    row7p.innerText = "HEALTHY";
                } else{
                    row7.style.color = "#ff0000";
                    row7p.innerText = "NOT HEALTHY";
                }
            } else if(bodyWeight.value>=95){
                if(boneMass.value>=3.69){
                    row7.style.color = "green";
                    row7p.innerText = "HEALTHY";
                } else{
                    row7.style.color = "#ff0000";
                    row7p.innerText = "NOT HEALTHY";
                }
            }
        }
    }

    function bmiRange(){
        if(bmi.value<18.5){
            row8.style.color = "blue";
            row8p.innerText = "UNDERWEIGHT";
        } else if(bmi.value>=18.5 && bmi.value<25){
            row8.style.color = "green";
            row8p.innerText = "NORMAL";
        } else if(bmi.value>=25 && bmi.value<30){
            row8.style.color = "yellow";
            row8p.innerText = "OVERWEIGHT";
        } else if(bmi.value>=30 && bmi.value<35){
            row8.style.color = "orange";
            row8p.innerText = "OBESE";
        } else if(bmi.value>=35){
            row8.style.color = "red";
            row8p.innerText = "EXTREMELY OBESE";
        }
    }
    
    function precision(how){
        var hm;
        if(how<1){
            hm = 2;
        } else{
            var serch = true;
            var divider = 1;
            var ite = 3;
            while (how/divider>1){
                hm = ite;
                //console.log("bmi = "+how+"divider = "+divider+" wynosi = "+how/divider);
                divider = divider * 10;
                ite ++;
                
            }
        }
        return hm;
    }

});
