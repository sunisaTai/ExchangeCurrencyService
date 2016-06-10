
var idBankEdit;
var jsondataBankAll;
var bankCode = "";
var bankName = "";
$("#collapOne").on('click',function(){
	clearDataBank();
	loadTableBank();
});

$("#btnSaveBank").on('click',function(){
	idBankEdit == null?idBankEdit = "" : idBankEdit;
	bankCode = $("#inputBankCode").val();
	bankName = $("#inputBankName").val();

	if (bankCode != "" && bankName != "") {

		if(checkDataSame(bankName,bankCode) == true){
			saveBank(idBankEdit);
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

function checkDataSame(name,code){

		var statusSave = true;

		if(jsondataBankAll.length != 0){

			$.each(jsondataBankAll,function(index,item){
				if(item.code == code || item.bank_Name == name){

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
			statusSave = true ;
		}

		return statusSave;

}
function saveBank(id){
	var idBank = (id == "" || id == null ? "" : id);

        var jsondata = $.ajax({
	        type: "POST",
	        headers: {
	            Accept: 'application/json'
	        },
	        url: session['context']+'/banks/saveBanks',
	        data : {
	        	id:idBank,
	        	name:$("#inputBankName").val(),
            	code:$("#inputBankCode").val(),
            	image:valueImage
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
	                    loadTableBank();
	                    clearDataBank();
	                    idBankEdit = "";
	                }else if (xhr.status == 500) {
	                	alert("ERROR");
	                }
	            } else {
	            	alert("ERROR");
	            }
	        },async: false
	    })
}
function editBank(id){
		var jsondataBank = $.ajax({
	        type: "GET",
	        url: session['context']+'/banks/findBankByIdBank',
	        data : {
	        	id : id
	        },
	        async: false
	    }).responseJSON;

		if (jsondataBank != null || jsondataBank != "") {
			idBankEdit = id
			$("#inputBankName").val(jsondataBank.bank_Name);
			$("#inputBankCode").val(jsondataBank.code);
			if (jsondataBank.image_name == "BANGKOK-1.png") {
				$("#test1").prop("checked", true);
				valueImage = "BANGKOK-1.png";
			}else if(jsondataBank.image_name == "KBANK-1.png"){
				$("#test2").prop("checked", true);
				valueImage = "KBANK-1.png";
			}else if(jsondataBank.image_name == "KTB-1.png"){
				$("#test3").prop("checked", true);
				valueImage = "KTB-1.png";
			}else if(jsondataBank.image_name == "KRUNGSRI-1.png"){
				$("#test4").prop("checked", true);
				valueImage = "KRUNGSRI-1.png";
			}else if(jsondataBank.image_name == "SCB-1.png"){
				$("#test5").prop("checked", true);
				valueImage = "SCB-1.png";
			}else if(jsondataBank.image_name == "GSB-1.png"){
				$("#test6").prop("checked", true);
				valueImage = "GSB-1.png";
			}else if(jsondataBank.image_name == "TMB.png"){
				$("#test7").prop("checked", true);
				valueImage = "TMB.png";
			}else if(jsondataBank.image_name == "UOB.png"){
				$("#test8").prop("checked", true);
				valueImage = "UOB.png";
			}else if(jsondataBank.image_name == "CIMB.png"){
				$("#test9").prop("checked", true);
				valueImage = "CIMB.png";
			}else if(jsondataBank.image_name == "THANACHART-1.png"){
				$("#test10").prop("checked", true);
				valueImage = "THANACHART-1.png";
			}
		};
}
function loadTableBank(){

	jsondataBankAll = $.ajax({
	        type: "GET",
	        url: session['context']+'/banks/findAllBank',
	        async: false
	    }).responseJSON;

	$("#tableBank").empty();
    $.each(jsondataBankAll,function(index,item){
        $("#tableBank").append(''+
            '<tr>'+
                '<td>'+'<center>'+(index+1)+'</center>'+'</td>'+
                '<td>'+'<center>'+item.code+'</center>'+'</td>'+
                '<td>'+'<center>'+item.bank_Name+'</center>'+'</td>'+
                '<td>'+'<center>'+'<img class="activator" src=" '+partLoadImage+' / '+item.image_name+' "/>'+'</center>'+'</td>'+
                '<td>'+'<center>'+'<a class="waves-effect waves-light btn blue" onclick="editBank(' + item.id + ')" style="padding-left: 10px; padding-right: 10px;"><i class="fa fa-pencil"></i></a>'+'</center>'+'</td>'+
                '<td>'+'<center>'+'<a class="waves-effect waves-light btn red" onclick="deleteBank(' + item.id + ')" style="padding-left: 10px; padding-right: 10px;"><i class="fa fa-times"></i></a>'+'</center>'+'</td>'+
            '</tr>'
        );
    });
}
function deleteBank(id){
	var jsonData = $.ajax({
	        type: "DELETE",
	        url: session['context']+'/banks/deleteBank/'+id,
	        complete: function (xhr) {
	            if (xhr.readyState == 4) {
	                if (xhr.status == 200) {
	                    $("#modal1").openModal();
				        $("h4[id=h4Modal]").text("ลบข้อมูลสำเร็จ");
				        $("p[id=pModal]").text("");
				        $('#btnOkModal').click(function() {
				            $('#modal1').closeModal();
				        });
	                    loadTableBank();
	                }else if (xhr.status == 500) {
	                	alert("ERROR");
	                }
	            } else {
	            	alert("ERROR");
	            }
	        },async: false
	    });
}
function clearDataBank() {
	$("#inputBankName").val("")
	$("#inputBankCode").val("")
	valueImage = 'BANGKOK-1.png';
	$("#test1").prop("checked", true);
}

