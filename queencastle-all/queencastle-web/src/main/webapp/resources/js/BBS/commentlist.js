
function addComment(){
	$("#commentFrm").form("clear");
	$("#commentDlg").dialog("open").dialog('setTitle','新增评论');
}
 

function saveComment(){
	$('#commentFrm').form('submit', {
		url:"BBS/saveComment",
		method:"post",
	    onSubmit: function(){
	    	var isValid = $(this).form('validate');
			if (!isValid){
				$.messager.alert('提示','请填写完整');
			}
			return isValid;	
	    },
	    success:function(data){
	    	$("#commentDlg").dialog('close');
	    	$("#CommentTableId").datagrid("reload");
	    }
	});
}

function getPageData(queryObj){
	$("#CommentTableId").datagrid({
	   	url:'BBS/getCommentByParams',
	   	method:'get',
	    loadMsg:'数据加载中....',
	    pagination:true,
		toolbar:"#CommentToolbar",
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'articleId',title:'文章编号',width:100,align:"center"},
			{field:'content',title:'评论内容',width:100,align:"center"},
			{field:'userId',title:'评论留言的人',width:170,align:"center"}
	    ]]
	});
}
$(function(){
	getPageData({});
});


