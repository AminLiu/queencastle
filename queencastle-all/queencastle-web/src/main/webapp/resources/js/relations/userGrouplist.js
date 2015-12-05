function editUserGroup(){

	 row = $('#userGroupTableId').datagrid('getSelected');
		if(row){

    		$.ajax({
    			url:"relations/getUserGroupById",
    			method:"post",
    			data:{"groupId":row.id},
    			success:function(result){
    				$("#editFrm").form("clear");
    				$("#editDlg").dialog("open").dialog('setTitle','修改群信息');
    				$("#editFrm").form("load",result);
    				
    			}
    			
    		});
    		
    		
		}	else{
			$.messager.alert('提示', '请选择一条数据');
		}
			    		
	
}

function buildGroup(){
	$("#editFrm").form("clear");
	
	$("#editDlg").dialog("open").dialog('setTitle','自建群');
}
function saveUserGroup() {
	var groupId =$("#groupId").val();
	if(groupId){
		$('#editFrm').form('submit',{
			url : "relations/editUserGroup",
			method : "post",
			onSubmit: function(){
		    	var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.alert('提示','请填写完整');
				}
				return isValid;	
			},
			success : function(data) {
				if (data) {
					$("#editDlg").dialog('close');
					$("#userGroupTableId").datagrid("reload");
				} else {
					$.messager.alert('提示', '服务端出现错误！');
					return;
				}

			}
		});
	}else{
		$('#editFrm').form('submit',{
			url : "relations/saveUserGroup",
			method : "post",
			onSubmit: function(){
		    	var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.alert('提示','请填写完整');
				}
				return isValid;	
			},
			success : function(data) {
				if (data) {
					$("#editDlg").dialog('close');
					$("#userGroupTableId").datagrid("reload");
				} else {
					$.messager.alert('提示', '服务端出现错误！');
					return;
				}

			}
		});
	}
		

}

function getUserGroupPageData(queryObj){
	$("#userGroupTableId").datagrid({
	   	url:'relations/getUserGroupsByParams',
	   	method:'get',
	    loadMsg:'数据加载中....',
	    pagination:true,
		toolbar:"#userGroupToolbar",
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'cname',title:'群名称',width:200,align:"center"},
			{field:'code',title:'群编号',width:170,align:"center"},
			{field:'profile',title:'群简介',width:100,align:"center"},
			{field:'img',title:'群二维码',width:170,align:"center",formatter:function(value,row,index){
	        	if(!value){
	        		return "";
	        	}else{
	        		return "<a href='"+value+"' target='blank'><img src='"+value+"' width='50px' height='50px'/></a>";
	        	}
			}
				
			
			
			
			},
			{field:'createdAt',title:'创建时间',width:170,align:"center"}
	    ]]
	});
}
$(function(){
	getUserGroupPageData({});
	$('#img').filebox({
	    buttonText: '选择二维码图片',
	    buttonAlign: 'right'
	})
	
	
});


