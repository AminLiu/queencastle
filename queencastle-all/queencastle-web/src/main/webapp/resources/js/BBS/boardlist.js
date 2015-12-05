
function addBoard(){
	$("#boardFrm").form("clear");
	$("#boardDlg").dialog("open").dialog('setTitle','新增板块类型');
}
 
function saveBoard(){
	$('#boardFrm').form('submit', {
		url:"BBS/saveBoard",
		method:"post",
	    onSubmit: function(){
	    	var isValid = $(this).form('validate');
			if (!isValid){
				$.messager.alert('提示','请填写完整');
			}
			return isValid;	
	    },
	    success:function(data){
	    	$("#boardDlg").dialog('close');
	    	$("#BoardTableId").datagrid("reload");
	    }
	});
}

function getPageData(queryObj){
	$("#BoardTableId").datagrid({
	   	url:'BBS/getBoardByParams',
	   	method:'get',
	    loadMsg:'数据加载中....',
	    pagination:true,
		toolbar:"#BoardToolbar",
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'title',title:'标题',width:100,align:"center"},
			{field:'img',title:'图片',width:100,align:"center"},
	    ]]
	});
}
$(function(){
	getPageData({});
	
	$(".sysFile").filebox({
	    buttonText: '选择板块图片',
	    buttonAlign: 'right'
	})
});


