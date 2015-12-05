function addUser(){
	
}
function configUserMaster(){
	var selectedRows=$("#userTableId").datagrid('getSelections');
	if(selectedRows.length!=1){
		$.messager.alert('提示','请选择一条要编辑的数据！');
		return;
	}
	var row = $('#userTableId').datagrid('getSelected');
	$("#userInfoFrm").form("clear");
	$("#userInfoDlg").dialog("open").dialog('setTitle','设为地区群主');
	
}
function configUserMember(){
	var selectedRows=$("#userTableId").datagrid('getSelections');
	if(selectedRows.length!=1){
		$.messager.alert('提示','请选择一条要编辑的数据！');
		return;
	}
	var row = $('#userTableId').datagrid('getSelected');
	//从后台拿到群信息
	$.ajax({
		method:"post",
		url:"/relations/getAllUsableGroup",
		success:function(data){
			$('#groupSlect').combobox({
			    data:data,
			    valueField:'id',
			    textField:'cname'
			});
			$("#userMemberFrm").form("clear");
			$("#userMemberDlg").dialog("open").dialog('setTitle','设为地区管理员/成员');
		}
	});
}


function configUserGroup(){
	var selectedRows=$("#userTableId").datagrid('getSelections');
	if(selectedRows.length!=1){
		$.messager.alert('提示','请选择一条要编辑的数据！');
		return;
	}
	var row = $('#userTableId').datagrid('getSelected');
	
	$.ajax({
		method:"post",
		url:"users/configUserGroup",
		data:{"userId":row.id},
		success:function(){
			
		}
	});
	
}


function searchUser(){
	var username=$('#s_username').val();
	if(username){
		getUserPageData({"username":username});
	}
	
}
function configUserRole(){
	var selectedRows=$("#userTableId").datagrid('getSelections');
	if(selectedRows.length!=1){
		$.messager.alert('提示','请选择一条要编辑的数据！');
		return;
	}
	var row = $('#userTableId').datagrid('getSelected');
 
	$.ajax({
		url:"users/getRolesByUserId",
		method:"post",
		data:{"userId":row.id},
		success:function(data){
			if(data){
				 $('#userRoleTree').tree({
						data: data,
						checkbox:true
					});
			} 
			$("#userRoleDlg").dialog("open").dialog('setTitle','设置用户角色 ');
		}
	});
	
	
	
}
function saveUserMasterInfoCfg(){
	var row = $('#userTableId').datagrid('getSelected');
	if(row){
		$.ajax({
			url:"users/configUserMaster",
			method:"post",
			data:{
				"userId":row.id,
//				"type":$("#memberSelect").val(),
				"cname":$("#cname").val(),
				"profile":$("#profile").val()
			},
			success:function(){
				$("#userInfoDlg").dialog('close');
				$("#userTableId").datagrid("reload");
			}
		});
	}
}
function saveUserMemberInfoCfg(){
	var row = $('#userTableId').datagrid('getSelected');
	if(row){
		$.ajax({
			url:"users/configUserMember",
			method:"post",
			data:{
				"userId":row.id,
 				"type":$("#memberSelect").val(),
 				"groupId":$('#groupSlect').combobox("getValue")
			},
			success:function(){
				$("#userMemberDlg").dialog('close');
				$("#userTableId").datagrid("reload");
			}
		});
	}
}

function saveUserRoleCfg(){
	var selectedNodes =[];
	selectedNodes = $('#userRoleTree').tree("getChecked");
	var roleIds=[];
	if(selectedNodes.length>0){
		for(var i=0;i<selectedNodes.length; i++){
			roleIds.push(selectedNodes[i].id);
		}
		 var row = $('#userTableId').datagrid('getSelected');
		 var userId= row.id;
		 $.ajax({
			 method:"post",
			 url:"users/configUserRole",
			 data:{
				 "userId":userId,
				 "roleIds":roleIds.join(",")
			 },
			 success:function(data){
				$("#userRoleDlg").dialog('close');
				$("#userTableId").datagrid("reload");
			 }
			 
		 });
	}else{
		$.messager.alert('提示','请至少选择一条数据！');
		return;
	}
	
}
function setAdmin(userId,flag){
	$.ajax({
		method:'post',
		url:'users/setUserAdmin',
		data:{"userId":userId,"flag":flag},
		success:function(){
			$('#userTableId').datagrid('reload');
		}
	});
}
function getUserPageData(queryObj){
	$("#userTableId").datagrid({
	   	url:'users/getUsersByParams',
	   	method:'get',
	    loadMsg:'数据加载中....',
		toolbar:"#userToolbar",
	    pagination:true,
	    singleSelect:true,
	    queryParams:queryObj,
	    nowrap:false,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'username',title:'用户名',width:100,align:"center"},
			{field:'source',title:'渠道 ',width:100,align:"center"},
			{field:'outNick',title:'渠道中昵称',width:100,align:"center"},
			{field:'weixinNo',title:'微信号',width:100,align:"center"},
			{field:'phone',title:'电话号码',width:100,align:"center"},
			{field:'admin',title:'超级管理员',width:100,formatter:function(value,row,index){
		        	if(value){
		        		return "<a href=\"javascript:void(0);\" onclick=\"setAdmin('"+row.id+"',false);\" >取消超级管理员</a>";
		        	}else{
		        		return "<a href=\"javascript:void(0);\" onclick=\"setAdmin('"+row.id+"',true);\">设为超级管理员</a>";
		        	}
			}},
			{field:'provinceCode',title:'省编码',hidden:true,align:"center"},
			{field:'provinceName',title:'所在省',width:100,align:"center"},
			{field:'cityCode',title:'地区编码',hidden:true,align:"center"},
			{field:'cityName',title:'所在地区',width:170,align:"center"},
			{field:'groupIds',title:'组编号',hidden:true,width:170,align:"center"},
			{field:'memberInfos',title:'成员信息',width:170,align:"center"},
			{field:'createdAt',title:'创建时间',width:170,align:"center"},
			{field:'updateAt',title:'更新时间',width:170,align:"center"}
	    ]]
	});
}
$(function(){
	getUserPageData({});
});