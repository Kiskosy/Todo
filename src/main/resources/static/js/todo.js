$(document).ready(function(){



    $(".plus-sign").on("click", function(event){
        let clickedElement;
        if(event.target.tagName === 'SPAN'){
            clickedElement = event.target.parentElement.parentElement.parentElement;
        } else {
            clickedElement = event.target.parentElement.parentElement;
        }
        
        if(!(document.getElementById('tasks'))){
        
        console.log(event.target.tagName);
        let html = '<tr id="tasks"><td><h6 >én egy altaszk vagyok</h6></td></tr>';
        let template = document.createElement('template');
        template.innerHTML = html;
        clickedElement.parentNode.insertBefore(template.content,clickedElement.nextSibling);
        console.log(event);
        var temp = /*[[${testmsg}]]*/{};
        
        }
        else {
            $('#tasks').remove();
        }
    });



});