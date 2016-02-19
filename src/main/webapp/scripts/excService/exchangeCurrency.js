
$("#collapThree").on('click',function(){
	$("#tableAddCurrency").hide();
	$("#btnSaveExchangeCurrency").hide();
	var date = new Date();
	var dateExchangeCurrency = date.toISOString().split("T")[0].split("-");
	var day = dateExchangeCurrency[2];
	var month = dateExchangeCurrency[1];
	var year = dateExchangeCurrency[0];
	$("#inputDateForExchangeCurrency").val(day+"/"+month+"/"+year);
	loadDataBank();
});

function loadDataBank(){
	var jsonBankAll = $.ajax({
	        type: "GET",
	        url: session['context']+'/banks/findAllBank',
	        async: false
	    }).responseJSON;
	
	if (jsonBankAll != null) {
		$("#selectBank").empty();
		$("#selectBank").append(''+
			'<option value=""  disabled selected >Choose your Bank</option>'
		)
		$.each(jsonBankAll,function(index,item){
			
			$("#selectBank").append(''+
				'<option value="" data-icon="'+partLoadImage+'/'+item.image_name+'"  class="left circle">'+item.bank_Name+'</option>'
			);
		});
		$('#selectBank').material_select();
	};

}
var jsondataCurrencyAll;
var nameBank;
$("#selectBank").on('change',function(){
	$("#tableAddCurrency").show();
	$("#btnSaveExchangeCurrency").show();
	nameBank = $(".select-dropdown").val();
	loadTableAddCurrency();
})

function loadTableAddCurrency(){
	jsondataCurrencyAll = $.ajax({
	        type: "GET",
	        url: session['context']+'/currencys/findAllCurrency',
	        async: false
	    }).responseJSON;

	$("#addCurrency").empty();
	if (jsondataCurrencyAll != null) {
		$.each(jsondataCurrencyAll,function(index,item){
			$("#addCurrency").append(''+
				 '<tr>'+
	                '<td>'+'<center>'+(index+1)+'</center>'+'</td>'+
	                '<td>'+'<center>'+'<img class="activator" src=" '+partLoadImageFlag+' / '+item.image_name+' " style="width: 64px; height: 64px;" />'+'</center>'+'</td>'+
	                '<td>'+'<center>'+item.currency_Name+'</center>'+'</td>'+
	                '<td>'+'<center>'+item.symbol+'</center>'+'</td>'+
	                '<td style="width: 150px;">'+'<center>'+
	                	'<div class="input-field col s12">'+
							'<input placeholder="Sell" id="inputSell'+item.id+'" type="text" class="validate" />'+
						'</div>'+
	                '</center>'+'</td>'+
	                '<td style="width: 150px;">'+'<center>'+
	                	'<div class="input-field col s12">'+
							'<input placeholder="Buy" id="inputBuy'+item.id+'" type="text" class="validate" />'+
						'</div>'+
	                '</center>'+'</td>'+
	            '</tr>'
				)
		});
	};
}
$("#btnSaveExchangeCurrency").on('click',function(){
	saveExchangeCurrency();
})
function saveExchangeCurrency(){
	$.each(jsondataCurrencyAll,function(index,item){
		 var jsondata = $.ajax({
	        type: "POST",
	        headers: {
	            Accept: 'application/json'
	        },
	        url: session['context']+'/exchangeCurrencys/saveExchangeCurrency',
	        data : {
	        	name_Bank:nameBank,
            	currency:item.id,
            	sell:$("#inputSell"+item.id).val(),
            	buy:$("#inputBuy"+item.id).val()
	        },
	        complete: function (xhr) {
	            if (xhr.readyState == 4) {
	                if (xhr.status == 201) {
	                   
	                }else if (xhr.status == 500) {
	                	alert("ERROR");
	                }
	            } else {
	            	alert("ERROR");
	            }
	        },async: false
	    })
	});
}

