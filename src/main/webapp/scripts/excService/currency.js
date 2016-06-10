var jsondataCurrencyAll;
var idCurrencyEdit;
var currencyCode = "";
var currencyName = "";
$("#collapTwo").on('click',function(){
	clearDataCurrency();
	loadTableCurrency();
});

$("#btnSaveCurrency").on('click',function(){

	currencyCode = $("#inputCurrencyCode").val();
	currencyName = $("#inputCurrencyName").val();

	if (currencyCode != "" && currencyName != "") {

		if(checkDataSameCurrency(currencyName,currencyCode) == true){
			saveCurrency(idCurrencyEdit);
		};

	}else{

		$("#modal1").openModal();
		$("h4[id=h4Modal]").text("กรุณากรอกข้อมูลให้ครบถ้วน");
		$("p[id=pModal]").text("");
		$('#btnOkModal').click(function() {
			$('#modal1').closeModal();
		});
	};
});
function checkDataSameCurrency(name,code){

		var statusSave = true;
		if(jsondataCurrencyAll.length != 0){

			$.each(jsondataCurrencyAll,function(index,item){
				if(item.code == code || item.currency_Name == name){

					$("#modal1").openModal();
					$("h4[id=h4Modal]").text("ข้อมูลซ้ำในระบบ");
					$("p[id=pModal]").text("");
					$('#btnOkModal').click(function() {
						$('#modal1').closeModal();
					});
					statusSave = false ;
				}
			})
		}else{
			statusSave = true;
		}

		return statusSave;

}
function saveCurrency(id){
	var dataSymbol = '';
	if (valueImage == 'au.png') {
		dataSymbol = 'au'
	}else if (valueImage == 'bn.png') {
		dataSymbol = 'bn'
	}else if (valueImage == 'ca.png') {
		dataSymbol = 'ca'
	}else if (valueImage == 'ch.png') {
		dataSymbol = 'ch'
	}else if (valueImage == 'cn.png') {
		dataSymbol = 'cn'
	}else if (valueImage == 'dk.png') {
		dataSymbol = 'dk'
	}else if (valueImage == 'eu.png') {
		dataSymbol = 'eu'
	}else if (valueImage == 'hk.png') {
		dataSymbol = 'hk'
	}else if (valueImage == 'id.png') {
		dataSymbol = 'id'
	}else if (valueImage == 'in.png') {
		dataSymbol = 'in'
	}else if (valueImage == 'jp.png') {
		dataSymbol = 'jp'
	}else if (valueImage == 'my.png') {
		dataSymbol = 'my'
	}else if (valueImage == 'no.png') {
		dataSymbol = 'no'
	}else if (valueImage == 'nz.png') {
		dataSymbol = 'nz'
	}else if (valueImage == 'ph.png') {
		dataSymbol = 'ph'
	}else if (valueImage == 'se.png') {
		dataSymbol = 'se'
	}else if (valueImage == 'sg.png') {
		dataSymbol = 'sg'
	}else if (valueImage == 'uk.png') {
		dataSymbol = 'uk'
	}else if (valueImage == 'us.png') {
		dataSymbol = 'us'
	}else if (valueImage == 'th.png') {
		dataSymbol = 'th'
	};

	var idCurrency = (id == "" || id == null ? "" : id);

        var jsondata = $.ajax({
	        type: "POST",
	        headers: {
	            Accept: 'application/json'
	        },
	        url: session['context']+'/currencys/saveCurrencys',
	        data : {
	        	id:idCurrency,
	        	currency_Name:$("#inputCurrencyName").val(),
	        	symbol:dataSymbol,
				code: $("#inputCurrencyCode").val(),
				image_name :valueImage
	        },
	        complete: function (xhr) {
	            if (xhr.readyState == 4) {
	                if (xhr.status == 201) {
	                    $("#modal1").openModal();
				        $("h4[id=h4Modal]").text("บันทึกข้อมูลสำเร็จ");
				        $("p[id=pModal]").text("");
				        $('#btnOkModal').click(function() {
				            $('#modal1').closeModal();
				        });
	                    loadTableCurrency();
	                    clearDataCurrency();
	                    idCurrencyEdit = "";
	                }else if (xhr.status == 500) {
	                	alert("ERROR");
	                }
	            } else {
	            	alert("ERROR");
	            }
	        },async: false
	    })
}
function editCurrency(id){
		var jsondataCurrency = $.ajax({
	        type: "GET",
	        url: session['context']+'/currencys/findCurrencyByIdCurrency',
	        data : {
	        	id : id
	        },
	        async: false
	    }).responseJSON;

		if (jsondataCurrency != null || jsondataCurrency != "") {
			idCurrencyEdit = id
			$("#inputCurrencyName").val(jsondataCurrency.currency_Name);
			$("#inputCurrencyCode").val(jsondataCurrency.code);
			if (jsondataCurrency.image_name == "au.png") {
				$("#flag1").prop("checked", true);
				valueImage = "au.png";
			}else if(jsondataCurrency.image_name == "bn.png"){
				$("#flag2").prop("checked", true);
				valueImage = "bn.png";
			}else if(jsondataCurrency.image_name == "ca.png"){
				$("#flag3").prop("checked", true);
				valueImage = "ca.png";
			}else if(jsondataCurrency.image_name == "ch.png"){
				$("#flag4").prop("checked", true);
				valueImage = "ch.png";
			}else if(jsondataCurrency.image_name == "cn.png"){
				$("#flag5").prop("checked", true);
				valueImage = "cn.png";
			}else if(jsondataCurrency.image_name == "dk.png"){
				$("#flag6").prop("checked", true);
				valueImage = "dk.png";
			}else if(jsondataCurrency.image_name == "eu.png"){
				$("#flag7").prop("checked", true);
				valueImage = "eu.png";
			}else if(jsondataCurrency.image_name == "hk.png"){
				$("#flag8").prop("checked", true);
				valueImage = "hk.png";
			}else if(jsondataCurrency.image_name == "id.png"){
				$("#flag9").prop("checked", true);
				valueImage = "id.png";
			}else if(jsondataCurrency.image_name == "in.png"){
				$("#flag10").prop("checked", true);
				valueImage = "in.png";
			}else if(jsondataCurrency.image_name == "jp.png"){
				$("#flag11").prop("checked", true);
				valueImage = "jp.png";
			}else if(jsondataCurrency.image_name == "my.png"){
				$("#flag12").prop("checked", true);
				valueImage = "my.png";
			}else if(jsondataCurrency.image_name == "no.png"){
				$("#flag13").prop("checked", true);
				valueImage = "no.png";
			}else if(jsondataCurrency.image_name == "nz.png"){
				$("#flag14").prop("checked", true);
				valueImage = "nz.png";
			}else if(jsondataCurrency.image_name == "ph.png"){
				$("#flag15").prop("checked", true);
				valueImage = "ph.png";
			}else if(jsondataCurrency.image_name == "se.png"){
				$("#flag16").prop("checked", true);
				valueImage = "se.png";
			}else if(jsondataCurrency.image_name == "sg.png"){
				$("#flag17").prop("checked", true);
				valueImage = "sg.png";
			}else if(jsondataCurrency.image_name == "uk.png"){
				$("#flag18").prop("checked", true);
				valueImage = "uk.png";
			}else if(jsondataCurrency.image_name == "us.png"){
				$("#flag19").prop("checked", true);
				valueImage = "us.png";
			}else if(jsondataCurrency.image_name == "th.png"){
				$("#flag20").prop("checked", true);
				valueImage = "th.png";
			}
		};
}
function loadTableCurrency(){

	jsondataCurrencyAll = $.ajax({
	        type: "GET",
	        url: session['context']+'/currencys/findAllCurrency',
	        async: false
	    }).responseJSON;

	$("#tableCurrency").empty();
    $.each(jsondataCurrencyAll,function(index,item){
        $("#tableCurrency").append(''+
            '<tr>'+
                '<td>'+'<center>'+(index+1)+'</center>'+'</td>'+
                '<td>'+'<center>'+item.code+'</center>'+'</td>'+
                '<td>'+'<center>'+item.currency_Name+'</center>'+'</td>'+
                '<td>'+'<center>'+item.symbol+'</center>'+'</td>'+
                '<td>'+'<center>'+'<img class="activator" src=" '+partLoadImageFlag+' / '+item.image_name+' " style="width: 64px; height: 64px;" />'+'</center>'+'</td>'+
                '<td>'+'<center>'+'<a class="waves-effect waves-light btn blue" onclick="editCurrency(' + item.id + ')" style="padding-left: 10px; padding-right: 10px;"><i class="fa fa-pencil"></i></a>'+'</center>'+'</td>'+
                '<td>'+'<center>'+'<a class="waves-effect waves-light btn red" onclick="deleteCurrency(' + item.id + ')" style="padding-left: 10px; padding-right: 10px;"><i class="fa fa-times"></i></a>'+'</center>'+'</td>'+
            '</tr>'
        );
    });
}
function deleteCurrency(id){
	var jsonData = $.ajax({
	        type: "DELETE",
	        url: session['context']+'/currencys/deleteCurrency/'+id,
	        complete: function (xhr) {
	            if (xhr.readyState == 4) {
	                if (xhr.status == 200) {
	                    $("#modal1").openModal();
				        $("h4[id=h4Modal]").text("ลบข้อมูลสำเร็จ");
				        $("p[id=pModal]").text("");
				        $('#btnOkModal').click(function() {
				            $('#modal1').closeModal();
				        });
	                    loadTableCurrency();
	                }else if (xhr.status == 500) {
	                	alert("ERROR");
	                }
	            } else {
	            	alert("ERROR");
	            }
	        },async: false
	    });
}
function clearDataCurrency() {
	$("#inputCurrencyCode").val("")
	$("#inputCurrencyName").val("")
	valueImage = 'au.png';
	$("#flag1").prop("checked", true);
}