function addMember(){
	
}
function searchUserMembers(){
	var userCode =$("#s_usercode").val();
	if(userCode){
		getUserPageData({"userCode":userCode});
	}
}
function getUserPageData(queryObj){
	$("#memberTableId").datagrid({
	   	url:'relations/getMyMembersByParams',
	   	method:'get',
	    loadMsg:'数据加载中....',
		toolbar:"#memberToolbar",
	    pagination:true,
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'groupId',title:'用户组编号',width:100,hidden:true},
			{field:'groupName',title:'用户组名',width:100},
			{field:'userId',title:'用户编号',width:100,hidden:true},
			{field:'username',title:'用户名',width:100},
			{field:'phone',title:'手机号码',width:100},
			{field:'code',title:'成员编号',width:100},
			{field:'type',title:'成员类别',width:100},
			{field:'createdAt',title:'创建时间',width:170,align:"center"}
	    ]]
	});
}

$(function(){
	getUserPageData({});
});