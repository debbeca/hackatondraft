/**
 * Created by avdovichenko on 31/05/2017.
 */
$(document).ready(function(){
    var $form = $('#chatbot-form'),
        $btnOK = $('#submit-user-text'),
        $userInput = $('#chatbot-input input'),
        $contentScroll = $('.content-scroll');

    window.responseString = null;
    window.selectedProducts = null;

    function appendUserBlock(text) {
        var blockHtml = '<div class="row row-user"><div class="col-md-12 text-right"><span class="user-text">'+text+'</span> </div> </div>';
        $('#content-body .row:last').after(blockHtml);
        $contentScroll.mCustomScrollbar('scrollTo','bottom');
        $userInput.val('');
    }

    function appendBotBlock(text) {
        var blockHtml = '<div class="row row-bot"> <div class="col-md-1 col-sm-1 col-xs-1 text-center"> <img src="img/icon-gl.jpg" alt="Icon Galeries Lafayette"> </div> <div class="col-md-11 col-sm-11 col-xs-11"> <span class="bot-text">'+text+' </span> </div> </div>';
        $('#content-body .row:last').after(blockHtml);
        $contentScroll.mCustomScrollbar('scrollTo','bottom');
    }

    function appendProductsBlock(url, imgUrl, name, price) {
        var blockHtml = '<div class="col-md-3 col-sm-4 col-xs-4 product-item"> <a href="'+url+'" target="_blank"> <img src="'+imgUrl+'" alt="'+name+'"> <div> <p class="product-name">'+name+' </p> <p class="product-price text-right"> <b>'+price+'</b> </p> </div> </a> </div>';
        $('#content-body .row-products:last').append(blockHtml);
        window.setTimeout(function () {
            $contentScroll.mCustomScrollbar('scrollTo','bottom');
        },1000);

    }

    function showError() {
        var blockHtml = '<div class="row row-error"> <div class="col-md-12 col-sm-12 col-xs-12"> <div class="alert alert-danger" role="alert">Désolé, il y a une erreur. Essayez redémarrer la page </div> </div> </div>';
        $('#content-body .row:last').after(blockHtml);
        $contentScroll.mCustomScrollbar('scrollTo','bottom');

    }

    function startConversation(userText) {
        var data = {
            'userText':userText,
            'responseString':window.responseString
        };

        $.ajax({
            url : './api/conversation',
            method : 'POST',
            contentType : 'application/json',
            data : JSON.stringify(data),
            success : function(data) {
                window.responseString = data;
                var response = JSON.parse(data);
                for(i=0; i<response.output.text.length; i++){
                    var botText = response.output.text[0];
                    var blockHtml = '<div class="row row-bot"><div class="col-md-1 col-sm-1 col-xs-1 text-center"> <img src="img/icon-gl.jpg" alt="Icon Galeries Lafayette"> </div> <div class="col-md-11 col-sm-11 col-xs-11"> <span class="bot-text">'+botText+' </span> </div></div>';
                    $('#content-body .container').append(blockHtml);
                }
                $contentScroll.mCustomScrollbar('scrollTo','bottom');
            },
            error : function() {
                showError();
            }
        });
    }

    // init dialog
    startConversation('');



    //submit user text and get bot response
    $form.on('submit', function (e) {
        e.preventDefault();
        var userText = $userInput.val();

        if(userText === ''){
            return;
        }

        appendUserBlock(userText);

        var data = {
          'userText':userText,
          'responseString':window.responseString
        };



        $.ajax({
            url : $form.attr('action'),
            method : 'POST',
            contentType : 'application/json',
            data : JSON.stringify(data),
            beforeSend:function () {
                //disable multiple sending of the same question
                $btnOK.prop('disabled', true);
                $userInput.prop('disabled', true);
            },
            success : function(data) {
                window.responseString = data;
                var response = JSON.parse(data);
                for(i=0; i<response.output.text.length; i++){
                    var botText = response.output.text[i];
                    appendBotBlock(botText);
                }

                if(response.context.checkAvailability){
                    makeQuery(response.context.searchParam);
                }

            },
            error : function() {
                showError();
            },
            complete: function(){
                $btnOK.prop('disabled', false);
                $userInput.prop('disabled', false);
                if(!/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
                    $userInput.focus();
                }
            }
        });

    });

    //restart conversation
    $('.restart-conversation span').click(function () {
        $('#content-body .container .row').fadeOut();
        $('#content-body .container').empty();
        startConversation('restart');
    });


    //show selected products
    function fillProductBlock(data) {
        var blockHtml = '<div class="row row-products equal-cols"></div>';
        $('#content-body .row:last').after(blockHtml);

        for(i=0; i<data.length; i++){
            var imgUrl = data[i].imageUrl;
            var url = data[i].url;
            var name = data[i].product_name;
            var price = data[i].price;
            appendProductsBlock(url, imgUrl, name, price.toFixed(2));
        }
    }

    //change conversation context item to show products
    function manageShowProducts(show) {
        var data = {
            'userText':'',
            'responseString':window.responseString,
            'showProducts':show
        };

        $.ajax({
            url : './api/conversation',
            method : 'POST',
            contentType : 'application/json',
            data : JSON.stringify(data),
            success : function(data) {
                window.responseString = data;
                var response = JSON.parse(data);
                for(i=0; i<response.output.text.length; i++){
                    var botText = response.output.text[i];
                    appendBotBlock(botText);
                }

                if(response.context.showProducts){
                    fillProductBlock(window.selectedProducts);
                }

            },
            error : function() {
                showError();
            }
        });

    }

    //make products selecting
    function makeQuery(parameters) {
        var data = {};
        for(key in parameters){
            data[key] = parameters[key];
        }

        $.ajax({
            url:'./api/products',
            method:'POST',
            contentType:'application/json',
            data:JSON.stringify(data),
            success:function (data) {
                if(data.length > 0){
                    window.selectedProducts = data;
                    manageShowProducts(true);
                } else {
                    manageShowProducts(false);
                }
            },
            error:function () {
                showError();
            }

        });

    }

});