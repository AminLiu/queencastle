function renderPageData(type,field,sortOrder,categoryId){
	$.ajax({
	 method:"post",
	 url:"/dsManager/getInfosByParams",
	 data:{"type":type,
		 "field":field,
		 "order":sortOrder,
		 "categoryId":categoryId
		 },
	 success:function(result){
		 if(result){
			 $("#exisitDivOne").empty( );
			 $("#exisitDivTwo").empty( );
			 var len = result.length;
			 var htmlArray=[];
			 for(var i=0;i<len;i++){
				 htmlArray.push('<div class="goodsItem">');
				 htmlArray.push('<div class="otherGoodsImg"><img src="http://images11.app.happyjuzi.com/usericonurl/14372808138692.jpg" /></div>');
				 htmlArray.push('<div class="goodsDetail">');
				 htmlArray.push('<div>产品名称:&nbsp;<label>'+result[i].productName+'</label></div>');
				 htmlArray.push('<div class="textBlue1">价格&nbsp;&nbsp;&nbsp;&nbsp; <label >'+result[i].price+'</label></div>');
				 htmlArray.push('<div class="goodsIntro textGray">');
				 htmlArray.push('<div style="float: left">产品数量:&nbsp;<label>'+result[i].amount+'</label></div>');
				 htmlArray.push('<div style="float: right">点赞数:&nbsp;<label>'+result[i].praiseCnt+'</label></div>');
				 htmlArray.push('</div>');
				 htmlArray.push('<div>开始时间:&nbsp;<label >'+(new Date(result[i].startDate)).format("yyyy-MM-dd")+'</label>&nbsp;&nbsp;结束时间:&nbsp;<label ">'+(new Date(result[i].endDate)).format("yyyy-MM-dd")+'</label></div>');
				 htmlArray.push('</div>');
				 if(result[i].view){
					 htmlArray.push('<div class="watchBtn" ><img src="/resources/images/goods_2.png"  title="'+result[i].id+'"/><label>取消关注</label></div>');
				 }else{
					 htmlArray.push('<div class="watchBtn" ><img src="/resources/images/goods_2_1.png"  title="'+result[i].id+'"/><label>关注</label></div>');
				 }
				 
				 htmlArray.push('</div>');
			 }
			  $("#exisitDivTwo").append(htmlArray.join(' '));
			
			 
				
				
				
		 }
		 
	 }
 });
}
/*function divView(){
	var div=$(this).find("div").attr("title");
	if(div==1){
		$(this).find("img").attr("src","/resources/images/goods_2.png");
		$(this).find("label").text("取消关注");
	}
	if(div==0){
		$(this).find("img").attr("src","/resources/images/goods_2_1.png");
		$(this).find("label").text("关注");
	}
}*/
 function handleClick(){
	 	var div=$(this).find("div").attr("title");
		var id = $(this).find("img").attr("title");
		var value= $(this).find("label").text();
		if(value == "关注"){
			$(this).find("img").attr("src","/resources/images/goods_2.png");
			$(this).find("label").text("取消关注");
			addOrMinus(id,1);
		}
		if(value == "取消关注"){
			$(this).find("img").attr("src","/resources/images/goods_2_1.png");
			$(this).find("label").text("关注");
			addOrMinus(id,-1);
		}
 }
 
 function addOrMinus(infoId,score){
	 $.ajax({
			"method":"post",
			url:"/addOrMinus",
			data:{"infoId":infoId,"score":score},
			success:function(){
				
			}
		});
 }
$(function(){
	var searchParam = location.search;
	if(searchParam.indexOf("demand")>0 || searchParam.indexOf("supply")>0){
		var type = searchParam.substring(1).split("=")[1];
		 
		$('#timeSelect').change(function(){
			var timeValue = $('#timeSelect').val();
			if(timeValue == "timeUp"){
				renderPageData(type,"time","up",'');
			}
			if(timeValue == "timeDown"){
				renderPageData(type,"time","down",'');
			}
		});
		$(document).on("click", "div.watchBtn",handleClick); 
		$('#priceSelect').change(function(){
			var priceValue = $('#priceSelect').val();
			if(priceValue == "priceUp"){
				renderPageData(type,"price","up",'');
			}
			if(priceValue == "priceDown"){
				renderPageData(type,"price","down",'');
			}
		});
		$('#categorySelect').change(function(){
			
		});
		
	}
	
	
});