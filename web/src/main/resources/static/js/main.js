/**
 * Created by avdovichenko on 31/05/2017.
 */
$(document).ready(function(){
	var counter=0;
    var $form = $('#chatbot-form'),
        $btnOK = $('#submit-user-text'),
        $userInput = $('#chatbot-input input'),
        $contentScroll = $('#content-scroll');

    window.responseString = null;
    window.selectedProducts = null;

    function appendUserBlock(text) {
        var blockHtml = '<div class="row row-user"><div class="col-md-12 text-right"><span class="user-text">'+text+'</span> </div> </div>';
        $('#content-body .row:last').after(blockHtml);

      $contentScroll.mCustomScrollbar('scrollTo','bottom');
        $userInput.val('');
    }

    function appendBotBlock(text) {
        var blockHtml = '<div class="row row-bot"> <div class="col-md-1 col-sm-1 col-xs-1 text-center">  </div> <div class="col-md-11 col-sm-11 col-xs-11"> <span class="bot-text">'+text+' </span> </div> </div>';
        $('#content-body .row:last').after(blockHtml);
        $contentScroll.mCustomScrollbar('scrollTo','bottom');
    }

    function appendProductsBlock(url, imgUrl, name, price) {
        var blockHtml = '<div class="col-md-3 col-sm-4 col-xs-4 product-item"> <a href="'+url+'" target="_blank"> <img src="'+imgUrl+'" alt="'+name+'"> <div> <p class="product-name">'+name+' </p> <p class="product-price text-right"> <b>'+price+'</b> </p> </div> </a> </div>';
        $('#content-body .row-products:last').append(blockHtml);
        $contentScroll.mCustomScrollbar('scrollTo','bottom');
    }

    function showError() {
        var blockHtml = '<div class="row row-error"> <div class="col-md-12 col-sm-12 col-xs-12"> <div class="alert alert-danger" role="alert">Désolé, il y a une erreur. Essayez redémarrer la page </div> </div> </div>';
        $('#content-body .row:last').after(blockHtml);
        $contentScroll.mCustomScrollbar('scrollTo','bottom');
    }

    function startConversation(userText) {
        var data = {
            'query':userText,
        };

        $.ajax({
            url : './api/conversation',
            method : 'POST',
            contentType : 'application/json',
            data : JSON.stringify(data),
            success : function(data) {
                console.log(data);
              
                var botText = data.text;
                var blockHtml = '<div class="row row-bot"><div class="col-md-1 col-sm-1 col-xs-1 text-center">  </div> <div class="col-md-11 col-sm-11 col-xs-11"> <span class="bot-text">'+botText+' </span> </div></div>';
                $('#content-body .container').append(blockHtml);
                $contentScroll.mCustomScrollbar('scrollTo','bottom');
            },
            error : function() {
                showError();
            }
        });
    }

    // init dialog
    startConversation('');


    function queryConversation(userText) {
    	
    }
    
    $('.input').keypress(function (e) {
    	  if (e.which == 13) {
    	    $('form#chatbot-form').submit();
    	    return false;    //<---- Add this line
    	  }
    	});
    
    //submit user text and get bot response
    $form.on('submit', function (e) {
        e.preventDefault();
        var userText = $userInput.val();

        if(userText === ''){
            return;
        }

        appendUserBlock(userText);

        var data = {
          'query':userText,
        };



        $.ajax({
            url :'./api/conversation',
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
                console.log(data);
                switch(data.intention) {
                case "BEST":
                    text = "BEST is good!";
                    afficherBest(data);
                    break;
                case "TENDENCY":
                    text = "TENDENCY";
                    afficherTendancy(data);
                    break;
                case "LAST":
                	console.log('LAST!')
                	afficherLast(data);
                    break;
                case "CONSEIL":
                	console.log('Conseil!')
                	afficherConseil(data);
                    break;    
                default:
                    text = "Not implemented yet";
            }
                 var botText = data.text;
                    
                    appendBotBlock(botText);


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
    function appendBotChart() {
        var blockHtml = ' <div  class="col-md-11 col-sm-11 col-xs-11 bot-chart"> <div class="bot-chart" id="chartContainer'+counter+'""  style="height: 400px; width: 85%;"></div></div> <div  class="row"> </div>';
        $('#content-body .row:last').after(blockHtml);
        $contentScroll.mCustomScrollbar('scrollTo','bottom');
    }
    
    function appendBotRow() {
        var blockHtml = ' <div  class="row"> </div>';
        $('#content-body .bot-chart:last').after(blockHtml);
        $contentScroll.mCustomScrollbar('scrollTo','bottom');
    }
    //<div class="row row-bot"> <div class="col-md-1 col-sm-1 col-xs-1 text-center">  </div> <div class="col-md-11 col-sm-11 col-xs-11"> <span class="bot-text">'+text+' </span> </div> </div>
    function afficherBest(data){
    	
    	 $.ajax({
             url :'./api/bestBuyer',
             method : 'GET',
             success : function(data) {
            	 console.log(data);
            	 resp = JSON.stringify(data);
            	 console.log(resp);
            	 appendBotChart();
         			var chart = new CanvasJS.Chart("chartContainer"+counter, {
         				
         				data: [{
         					type: "column",
         					dataPoints: data
         				}]
         			});
         			chart.render();
         			 appendBotRow();
         			counter++;
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
    }
    
    function isNullAndUndef(variable) {

        return (variable !== null && variable !== undefined);
    }
    
    function afficherTendancy(val){
    	var urlpath='./api/tendancy';
    	if (val.intervalle !== null  &&  val !== undefined){
    		urlpath = urlpath +'?intervalle='+val.intervalle;
    	}
   	 $.ajax({
            url :urlpath,
            method : 'GET',
            success : function(data) {
           	 console.log(data);
           	 resp = JSON.stringify(data);
           	 console.log(resp);
           	 if(data.length != 0){
           		 	appendBotChart();
        			var chart = new CanvasJS.Chart("chartContainer"+counter, {
        				axisX: {
        					labelFormatter: function (e) {
        						return CanvasJS.formatDate( e.value, "DD MMM");
        					},
        				},
        				data: [{
        					 type: "line",
        					dataPoints: data
        				}]
        			});
        			chart.render();
        			 appendBotRow();
        			counter++;
           	 }else{
           		appendBotBlock("Désolé. Nous n''avons pas assez de données à vous fournir. Veuillez reformulez votre demande ou aggrandir votre intervalle de recherche");
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
   }
    
    function afficherConseil(val){
    	var urlpath='./api/conseil';
   	 $.ajax({
            url :urlpath,
            method : 'GET',
            success : function(data) {
           	 console.log(data);
           	 resp = JSON.stringify(data);
           	 console.log(resp);
           	 if(data != null || data != undefined){
           		appendBotBlock("Je vous recommande de vendre l'action "+data.sell+" et d'acheter l'action " +data.buy + " et de garder l'action "+ data.keep );
              	 
           	 }else{
           		appendBotBlock("Désolé. Nous n''avons pas assez de données à vous fournir. Veuillez reformulez votre demande ou aggrandir votre intervalle de recherche");
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
   }
    
    function afficherLast(val){
    	console.log('we are looking for last transactions')
      	 $.ajax({
             url :'./api/transaction?sort=-tradeDate',
             method : 'GET',
             success : function(data) {
            	 console.log(data);
            	 resp = JSON.stringify(data);
            	 console.log(resp);
            	 if(data.length != 0){
            		 appendBotChart();
            		 trans =[];
            		 for(i in data._embedded.transaction){
            			 console.log(i);
            			 trans[i] = data._embedded.transaction[i];
            			 if(data._embedded.transaction[i].product.length > 10){
            				 trans[i].product = data._embedded.transaction[i].product.substring(0,9)+".";
            			 }
            			 if(data._embedded.transaction[i].seller.length > 10){
            				 trans[i].seller = data._embedded.transaction[i].seller.substring(0,9)+".";
            			 }
            			 if(data._embedded.transaction[i].buyer.length > 10){
            				 trans[i].buyer = data._embedded.transaction[i].buyer.substring(0,9)+".";
            			 }
            			 trans[i].tradeDate = $.format.date(data._embedded.transaction[i].tradeDate, "dd-MM-yyyy")
            		 }
            		 $("#chartContainer"+counter).jsGrid({
            			 	height: "50%",
            		        width: "85%",
            		        sorting: true,
            		        paging: true,
            		        pageSize: 5,
            		        data: trans,
            		        fields: [
            		            { title: "Buyer",name: "buyer", type: "text", width : 59 },
            		            { title: "Slr.",name: "seller", type: "text" ,  width : 45 },
            		            { title: "Prod.",name: "product", type: "text", width : 59 },
            		            { title: "Price",name: "unitPrice", type: "number", width : 35},
            		            { title: "Qty.",name: "quantity", type: "number",  width : 35 },
            		            { title: "Fees",name: "fees", type: "number", width : 35 },
            		            { title: "Tot.",title: "Tot.", name: "totalAmount", type: "number", width : 52},
            		            { title: "Date",name: "tradeDate", type: "date",width : 62 },
            		            { title: "Cur.",name: "currency", type: "text", width : 40 }
            		        ]
            		    });
            	     $("#chartContainer"+counter).jsGrid("sort", 'tradeDate');
            	     $("#chartContainer"+counter).jsGrid("sort", 'tradeDate');
         			 appendBotRow();
         			counter++;
            	 }else{
            		appendBotBlock("Désolé. Nous n''avons pas assez de données à vous fournir. Veuillez reformulez votre demande ou aggrandir votre intervalle de recherche");
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
    	
    	
    }
    
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