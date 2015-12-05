
function addArticle(){
	$("#articleFrm").form("clear");
	$("#articleDlg").dialog("open").dialog('setTitle','新增文章');
}
 
function saveArticle(){
	$('#articleFrm').form('submit', {
		url:"BBS/saveArticle",
		method:"post",
	    onSubmit: function(){
	    	var isValid = $(this).form('validate');
			if (!isValid){
				$.messager.alert('提示','请填写完整');
			}
			return isValid;	
	    },
	    success:function(data){
	    	$("#articleDlg").dialog('close');
	    	$("#ArticleTableId").datagrid("reload");
	    }
	});
}

function getPageData(queryObj){
	$("#ArticleTableId").datagrid({
	   	url:'BBS/getArticByParams',
	   	method:'get',
	    loadMsg:'数据加载中....',
	    pagination:true,
		toolbar:"#ArticleToolbar",
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'author',title:'作者',width:100,align:"center",formatter:function(value,row,index){
	        	if(value != null){
	        		return value=value.id;
	        	}
	        	return "无";
			}},
			{field:'boardId',title:'板块',width:100,align:"center"},
			{field:'createdAt',title:'创建时间',width:170,align:"center"}
	    ]]
	});
}
$(function(){
	getPageData({});
	
	
});


