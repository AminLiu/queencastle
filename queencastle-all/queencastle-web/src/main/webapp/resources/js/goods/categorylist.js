
function addCategory(){
	$("#categoryFrm").form("clear");
	$("#categoryDlg").dialog("open").dialog('setTitle','新增产品类别');
}
 
function saveCateogry(){
	$('#categoryFrm').form('submit', {
		url:"goods/saveCategory",
		method:"post",
	    onSubmit: function(){
	    	var isValid = $(this).form('validate');
			if (!isValid){
				$.messager.alert('提示','请填写完整');
			}
			return isValid;	
	    },
	    success:function(data){
	    	$("#categoryDlg").dialog('close');
	    	$("#categoryTableId").datagrid("reload");
	    }
	});
}
function getPageData(queryObj){
	$("#categoryTableId").datagrid({
	   	url:'goods/getCategorysByParams',
	   	method:'get',
	    loadMsg:'数据加载中....',
	    pagination:true,
		toolbar:"#categoryToolbar",
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'cname',title:'类别名称',width:100,align:"center"},
			{field:'createdAt',title:'创建时间',width:170,align:"center"}
	    ]]
	});
}
$(function(){
	getPageData({});
});


