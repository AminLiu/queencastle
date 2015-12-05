function refresh(){
	getUserPageData({});
}
function getPageData(queryObj){
	$("#feedbackTableId").datagrid({
	   	url:'goods/getFeedbackByParams',
	   	method:'get',
	    loadMsg:'数据加载中....',
	    pagination:true,
		toolbar:"#feedbackToolbar",
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'infoId',title:'信息编号',width:100,align:"center"},
			{field:'userId',title:'用户编号',width:100,align:"center"},
			{field:'content',title:'留言信息',width:200,align:"center"},
			{field:'createdAt',title:'创建时间',width:170,align:"center"}
	    ]]
	});
}
$(function(){
	getPageData({});
});


