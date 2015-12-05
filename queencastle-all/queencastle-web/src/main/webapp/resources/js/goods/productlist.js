function addProduct(){
	$("#productFrm").form("clear");
	$("#productDlg").dialog("open").dialog('setTitle','新增产品');
}
function saveProduct(){
	$('#productFrm').form('submit', {
		url:"goods/saveProduct",
		method:"post",
	    onSubmit: function(){
	    	var isValid = $(this).form('validate');
			if (!isValid){
				$.messager.alert('提示','请填写完整');
			}
			return isValid;	
	    },
	    success:function(data){
	    	$("#productDlg").dialog('close');
	    	$("#productTableId").datagrid("reload");
	    }
	});
}
function getCategoryPageData(queryObj){
	$("#productTableId").datagrid({
	   	url:'goods/getProductsByParams',
	   	method:'get',
	    loadMsg:'数据加载中....',
	    toolbar:"#productToolbar",
	    pagination:true,
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'cname',title:'产品名称',width:100,align:"center"},
			{field:'intro',title:'产品介绍',width:100,align:"center"},
			{field:'createdAt',title:'创建时间',width:170,align:"center"}
	    ]]
	});
}
$(function(){
	
	getCategoryPageData({});
	
	$(".sysFile").filebox({
	    buttonText: '选择产品图片',
	    buttonAlign: 'right'
	})
	
	
	
	
	
});