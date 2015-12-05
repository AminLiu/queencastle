

function addPropertyDict(){

	 row = $('#domainTableId').datagrid('getSelected');
		if(row){

   		$.ajax({
   			url:"shop/addPropertyDict",
   			method:"post",
   			data:{"id":row.id},
   			success:function(result){
   				$("#addFrm").form("clear");
   				$("#addDlg").dialog("open").dialog('setTitle','增加子类');
   				$("#addFrm").form("load",result);
   				
   			}
   			
   		});
   		
   		
		}	else{
			$.messager.alert('提示', '请选择一条数据');
		}
}


function savePropertyDict() {
	var id = $("#id").val();
	if(id){
		$('#addFrm').form('submit',{
			url : "shop/savePropertyDict",
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
					$("#addDlg").dialog('close');
					getPropertyPageData({});
				} else {
					$.messager.alert('提示', '服务端出现错误！');
					return;
				}

			}
		});
		}else{
			$.messager.alert('提示', '服务端出现错误！');
		}
			

	}

function getPropertyPageData(queryObj){
	var  id = $("#id").val();
	$("#propertyTableId").datagrid({
	   	url:'shop/getPropertyByParam?id='+id,
	   	method:'post',
	    loadMsg:'数据加载中....',
		toolbar:"#propertyToolbar",
	    pagination:true,
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'domainId',title:'子类ID',width:80,align:"center"},
			{field:'cname',title:'介绍',width:80,align:"center"},
			{field:'createdAt',title:'创建时间',width:170,align:"center"}
	    ]]
	});
}


function getDomainPageData(queryObj){
	$("#domainTableId").datagrid({
	   	url:'shop/getDomainByParam',
	   	method:'post',
	    loadMsg:'数据加载中....',
		toolbar:"#domainToolbar",
	    pagination:true,
	    singleSelect:true,
	    queryParams:queryObj,
	    onClickRow:function seePropertyDict(index,row){
	    	if(row){
				var id=row.id;
					$("#propertyTableId").datagrid({
					   	url:'shop/getPropertyByParam?id='+id,
					   	method:'post',
					    loadMsg:'数据加载中....',
						toolbar:"#propertyToolbar",
					    pagination:true,
					    singleSelect:true,
					    sortOrder:"desc",
					    sortName:"id",
					    columns:[[
							{field:'id',title:'编号',width:100,hidden:true},
							{field:'domainId',title:'子类ID',width:80,align:"center"},
							{field:'cname',title:'介绍',width:80,align:"center"},
							{field:'createdAt',title:'创建时间',width:170,align:"center"}
					    ]]
					});
					
	}else{
		$.messager.alert('提示', '请选择一条数据');
	}
	    },
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'cname',title:'介绍',width:80,align:"center"},
			{field:'createdAt',title:'创建时间',width:170,align:"center"}
	    ]]
	});
}

$(function(){
	getDomainPageData({});
	getPropertyPageData({});
});