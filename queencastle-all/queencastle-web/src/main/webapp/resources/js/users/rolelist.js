function addRole(){
	$.messager.prompt('新增角色', '请输入角色名称:', function(r){
		if (r){
			 $.ajax({
				 url:"users/addRole",
				 method:"post",
				 data:{"cname":r},
				 success:function(){
					 $('#roleTableId').datagrid("reload");
					 $.messager.alert('提示','新增成功!','info');
				 }
			 });
		}else{
			$.messager.alert('提示','您的输入为空!','info');
		}
	});
}
function configRoleMenu(){
	 var row = $('#roleTableId').datagrid('getSelected');
	 if(row){
		 $.ajax({
//				url:"users/getCurrUserMenus",
			 	url:"users/getRoleMenus",
				method:"post",
				data:{"roleId":row.id},
				success:function(data){
					if(data){
						 $('#roleMenuTree').tree({
								data: data,
								checkbox:true
							});
					} 
					$("#roleMenuDlg").dialog("open").dialog('setTitle','设置角色菜单 ');
				}
			});
	 }else{
		 $.messager.alert('提示','请至少选择一条数据！');
		 return;
	 }
	 
}
function saveRoleMenuCfg(){
	var nodes  = $('#roleMenuTree').tree("getChecked");
	var menuIds=[];
	if(nodes.length>0){
		for(var i=0;i<nodes.length; i++){
			menuIds.push(nodes[i].id);
		}
		 var row = $('#roleTableId').datagrid('getSelected');
		 var roleId= row.id;
		 $.ajax({
			 method:"post",
			 url:"users/configRoleMenu",
			 data:{
				 "roleId":roleId,
				 "menuIds":menuIds.join(",")
			 },
			 success:function(data){
				 $("#roleMenuDlg").dialog('close');
			 }
			 
		 });
	}else{
		$.messager.alert('提示','请至少选择一条数据！');
		return;
	}
}
function getRolePageData(queryObj){
	$("#roleTableId").datagrid({
	   	url:'users/getCurrUserRoles',
	   	method:'get',
	    loadMsg:'数据加载中....',
		toolbar:"#roleToolbar",
	    pagination:false,
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'cname',title:'角色名',width:100,align:"center"},
			{field:'createdAt',title:'创建时间',width:170,align:"center"}
	    ]]
	});
}
$(function(){
	getRolePageData({});
});