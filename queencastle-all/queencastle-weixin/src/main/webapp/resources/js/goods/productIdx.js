function getObjectURL(file) {
var url = null ; 
if (window.createObjectURL!=undefined) { // basic
url = window.createObjectURL(file) ;
} else if (window.URL!=undefined) { // mozilla(firefox)
url = window.URL.createObjectURL(file) ;
} else if (window.webkitURL!=undefined) { // webkit or chrome
url = window.webkitURL.createObjectURL(file) ;
}
return url ;
}

function showFirstPage(){
	$(".step").hide();
	$(".step").eq(0).show();
	$(".stepItem").removeClass("curStepTip");
	$(".step").eq(0).find(".first").addClass("curStepTip");
}
function showSecondPage(){
	$(".step").hide();
	$(".step").eq(1).show();
	$(".stepItem").removeClass("curStepTip");
	$(".step").eq(1).find(".second").addClass("curStepTip");
}
$(function(){
	$(".boxOne").height($(".fileMask").height());
	$(".boxOne input").height($(".fileMask").height());
	
	$(".realFile").change(function(){
		var objUrl = getObjectURL(this.files[0]) ;
		if (objUrl) {
			$(this).parent().find(".previewIMg").attr("src", objUrl);
			$(this).parent().find(".emptyImg").css("display","none");
		}
	});
	 
	//第一步和第二步切换
	$(".first").on("click", function(){
		showFirstPage();
	});
	$("#nextBtn").on("click",function(){
		showSecondPage();
	});
	$(".second").on("click", function(){
		showSecondPage();
	});
	$("#preBtn").on("click",function(){
		showFirstPage();
	});
	 
	$("#provinceSelect").change(function(){
		var provinceCode = $("#provinceSelect").val();
		if(provinceCode){
			$.ajax({
				method:"post",
				url:"/getByProvinceCode",
				data:{"provinceCode":provinceCode},
				success:function(data){
					if(data){
						var len=data.length;
						if(len>0){
							$("#citySelect").css("display","inline-block");
							$("#citySelect").empty();
							$("#areaSelect").empty();
							for(var i=0;i<len;i++){
								$("#citySelect").append('<option value="'+data[i].code+'">' + data[i].cname+ '</option>');
							}
						}else{
							$("#citySelect").css("display","none");
							$("#areaSelect").css("display","none");
						}
						
					}
					
				}
			});
		}
	});
	$("#citySelect").change(function(){
		var cityCode = $("#citySelect").val();
		if(cityCode){
			$.ajax({
				method:"post",
				url:"/getAreasByCityCode",
				data:{"cityCode":cityCode},
				success:function(data){
					if(data){
						var len=data.length;
						if(len>0){
							$("#areaSelect").css("display","inline-block");
							$("#areaSelect").empty();
							for(var i=0;i<len;i++){
								$("#areaSelect").append('<option value="'+data[i].code+'">' + data[i].cname+ '</option>');
							}
						}else{
							$("#areaSelect").css("display","none");
						}
					}
					
				}
			});
		}
	});
	$("#addCategoryBtn").click(function(){
		var cname = $("#categoryCnameIpt").val();
		if (cname) {
			$.ajax({
				method : "post",
				url : "/addCategory",
				data : {
					"cname" : cname
				},
				success : function(data) {
					$("#categoryId").append(
							'<option value="'+data.id+'">' + data.cname
									+ '</option>');
					$("#categoryId").val(data.id);
					$("#categoryId").focus();
					$("#categoryCnameIpt").val("");
					$('#categoryDiv').css('display',"none");
					$("#addCategory").text("新增类目?");
				},
				error : function() {

				}
			});
		} else {
			alert("您的输入为空");
		}
	});
	$("#addProductAndDsFrm").validate({
		 focusCleanup:true,focusInvalid:false,
		    errorClass: "unchecked",
		    validClass: "checked",
		    errorElement: "span",
		  rules:{
			  cname:{required:true},
			  categoryId:{required:true},
			  intro:{required:true},
			  imgs:{required:true},
			  amount:{required:true},
			  price:{required:true},
			  startDate:{required:true},
			  endDate:{required:true},
			  provinceCode:{required:true},
//			  cityCode:{required:true},
//			  areaCode:{required:true},
		      memo:{required:true}

		    }
	});
$("#submitAllBtn").click(function(){
	//检查输入内容
		$("#addProductAndDsFrm").submit();});

	
});