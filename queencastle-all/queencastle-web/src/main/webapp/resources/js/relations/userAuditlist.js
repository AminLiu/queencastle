

function auditUser(){
	
	var selectedRows=$("#userAuditTableId").datagrid('getSelections');
	if(selectedRows.length!=1){
		$.messager.alert('提示','请选择一条要审核的数据！');
		return;
	}
	var row = $('#userAuditTableId').datagrid('getSelected');
	if(row.auditStatus == "success"){
		$.messager.alert('提示','无需再次审核！');
		return;
	}
	
	$("#userAuditFrm").form("clear");
	
	$("#userAuditDlg").dialog("open").dialog('setTitle','审核');
}
function saveUserAudit(){
	var result = $("#result").val();
	var reason = $("#reason").val();
	if(!result){
		$.messager.alert('提示','请给出审核结果！');
		return;
	}
	if(!reason){
		$.messager.alert('提示','请给出审核理由！');
		return;
	}
	var row = $('#userAuditTableId').datagrid('getSelected');
	$.ajax({
		method:"post",
		url:"userAudit/audit",
		data:{"id":row.id,"result":result,"reason":reason},
		success:function(){
			$("#userAuditDlg").dialog("close");
			$("#userAuditTableId").datagrid("reload");
		}
	});
}
function getUserAuditPageData(queryObj){
	$("#userAuditTableId").datagrid({
	   	url:'userAudit/getUserAuditsByParam',
	   	method:'get',
	    loadMsg:'数据加载中....',
	    pagination:true,
		toolbar:"#userAuditToolbar",
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'applyUserId',title:'申请人编号',width:200,align:"center"},
			{field:'applyUserName',title:'申请人',width:200,align:"center"},
			{field:'auditUserName',title:'审核人',width:200,align:"center"},
			{field:'auditStatus',title:'审核状态',width:100,align:"center",
				formatter:function(value,row,index){
	        	if(value == "success"){
	        		return "<font color='green'>"+value+"</font>";
	        	}else if(value == "fail"){
	        		return "<font color='red'>"+value+"</font>";
	        	}else{
	        		return "<font color='orange'>"+value+"</font>";
	        	}
			}},
			{field:'reason',title:'审核理由',width:170,align:"center"},
			{field:'createdAt',title:'创建时间',width:170,align:"center"}
	    ]]
	});
}
$(function(){
	getUserAuditPageData({});
	
});


