function getSalePageData(queryObj){
	$("#saleDataTableId").datagrid({
	   	url:'data/getDatasByParam',
	   	method:'get',
	    loadMsg:'数据加载中....',
		toolbar:"#saleDataToolbar",
	    pagination:true,
	    singleSelect:true,
	    queryParams:queryObj,
	    nowrap:false,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'userName',title:'用户名',width:100,align:"center"},
			{field:'phone',title:'电话号码',width:100,align:"center"},
//			{field:'orderNo',title:'订单编号',width:170,align:"center"},
			{field:'province',title:'省',width:100,align:"center"},
			{field:'city',title:'市',width:100,align:"center"},
			{field:'area',title:'地区',width:170,align:"center"},
			{field:'address',title:'详细地址',width:170,align:"center"},
			{field:'createdAt',title:'创建时间',width:170,align:"center"}
	    ]]
	});
}
$(function(){
	getSalePageData({});
});