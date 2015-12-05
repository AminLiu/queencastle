function renderPageData(type){
	
	$.ajax({
	 method:"post",
	 url:"/dsManager/getByQueryParams",
	 data:{"type":type,
			"timeSearch":$('#timeSelect').attr("title"),
		 	"priceSearch":$('#priceSelect').attr("title"),
		 	"categoryId":"",
 		 	"attentionSearch":$("#praiseSelect").attr("title")
		 },
	 success:function(result){
		 if(result){
			 $("#exisitDivOne").empty( );
			 $("#exisitDivTwo").empty( );
			 var len = result.length;
			 var htmlArray=[];
			 for(var i=0;i<len;i++){
 
				 
				 htmlArray.push('<div class="goodsItem">');
				 htmlArray.push('<div class="otherGoodsImg"><img src="/resources/images/goods_1.jpg" /></div>');
				 
				 htmlArray.push('<div class="goodsDetail">');
				 htmlArray.push('<input type="hidden" value="'+result[i].id+'"/>');
				 htmlArray.push('<div>产品名称:&nbsp;<label>'+result[i].productName+'</label></div>');
				 htmlArray.push('<div class="textGray">产品类型:&nbsp;<label>'+result[i].productName+'</label></div>');
				 htmlArray.push('<div class="textBlue1">价格&nbsp;&nbsp;&nbsp;&nbsp; <label >'+result[i].price+'</label></div>');
				 htmlArray.push(' <div class="textBlue1">数量 <label >'+result[i].amount+'</label></div>');
				 htmlArray.push(' <div class="textGray">求/供货倒计时：<label >'+result[i].dayGap+'</label>天</div>');
				 htmlArray.push('</div>');
				 
				 htmlArray.push('<div class="watchBtn">');
				 htmlArray.push('<div class="watchImg"><img src="/resources/images/attention1.jpg" title="'+result[i].id+'" /></div>');
				 htmlArray.push('<div><label>'+result[i].praiseCnt+'</label>人关注</div>');
				 htmlArray.push('</div>');
				 
				 htmlArray.push('</div>');
			 }
			  $("#exisitDivTwo").append(htmlArray.join(' '));
			
			 
				
				
				
		 }
		 
	 }
 });
}

 function addOrMinus(infoId,score,thiz){
	 $.ajax({
			"method":"post",
			url:"/addOrMinus",
			data:{"infoId":infoId,"score":score},
			success:function(result){
				if(result){
					var value =$(thiz).parent().find("label").text();
					value = parseInt(value)+1;
					$(thiz).parent().find("label").text(value);
				}
			}
		});
 }
$(function(){
	var searchParam = location.search;
	if(searchParam.indexOf("demand")>0 || searchParam.indexOf("supply")>0){
		var type = searchParam.substring(1).split("=")[1];
		 
	}
	$(document).on("click",	"div.watchImg",function(){
		var id = $(this).find("img").attr("title");
		var value =$(this).parent().find("label").text();
		addOrMinus(id,1,this);
	});
	
	$(document).on("click",	"div.goodsTabItem",function(){
		var spanId = $(this).find(':first-child');
		
		
		var spanIcon = $(this).find(':last-child');
		var clazz = spanIcon.attr("class");
		if(clazz == "actArrow"){
			spanIcon.attr("class","actArrow actArrowChange");
			
			if(spanId.attr("id") == "timeSelect"){
				spanId.attr("title","timeUp");
			}else if(spanId.attr("id") == "praiseSelect"){
				spanId.attr("title","praiseUp");
			}else if(spanId.attr("id") == "priceSelect"){
				spanId.attr("title","priceUp");
			}
			
		}else{
			spanIcon.attr("class","actArrow");
			
			if(spanId.attr("id") == "timeSelect"){
				spanId.attr("title","timeDown");
			}else if(spanId.attr("id") == "praiseSelect"){
				spanId.attr("title","praiseDown");
			}else if(spanId.attr("id") == "priceSelect"){
				spanId.attr("title","priceDown");
			}
		}
		renderPageData(type);
		
	});
	
	
	
	
	$(document).on("click", "div.goodsDetail",function(){
	var dsId=	$(this).find("input").val();
	 	window.location.href="/dsManager/preview?id="+dsId;
	}); 
	
	
	
});
