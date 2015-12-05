function refeUser() {
	var msg = "请等待群主审核"
	var row = $('#urTableId').datagrid('getSelected')
	if (!row) {
		$.messager.alert('提示', '请至少选择一条数据！');
		return;
	} else {
		$.messager.confirm('确认', msg, function(r) {
			if (r) {
				$.ajax({
					url : "ur/getMaster",
					method : "post",
					data : {
						"parentId" : parentId,
						"userId" : userId
					},
					success : function(data) {

					}
				});
				alert("ok");
			}
		});
	}
}
	
function getAgencyPageData(queryObj){
	$("#urTableId").datagrid({
	   	url:'ur/getAgencyByParams',
	   	method:'get',
	    loadMsg:'数据加载中....',
		toolbar:"#urToolbar",
	    pagination:true,
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'parentId',title:'parentId',width:100,hidden:true},
			{field:'userId',title:'userId',width:100,hidden:true},
			{field:'parentName',title:'推荐人',width:100,align:"center"},
			{field:'username',title:'成员 ',width:100,align:"center"},
			{field:'createdAt',title:'开始时间',width:100,align:"center"}		
			
	    ]]
	});
}
$(function(){
	getAgencyPageData({});
});