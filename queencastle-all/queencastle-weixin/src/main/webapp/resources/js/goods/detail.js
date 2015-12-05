$(function(){
	//让背景填充整个屏幕和内容区
	if($(".pageGoodsSource").height() < $(window).height() ){
		$(".pageGoodsSource").height($(window).height()  );
	}
	 $(document).on("click", "div.provideBtn",handleClick); 
});
function handleClick(){
 	var div=$(this).find("div").attr("title");
 	var id = $(this).find("a").attr("title");
	var value= $(this).find("a").text();
	if(value == "关注"){
		
		$(this).find("a").text("取消关注");
		addOrMinus(id,1);
	}
	if(value == "取消关注"){
	
		$(this).find("a").text("关注");
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
 