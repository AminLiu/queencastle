function buildUserGroup(){
	var id = $("#id").val();
	if(id){
		$('#uglDlgFrm').form('submit',{
			url : "relations/editAreaUserGroup",
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
					$("#uglDlg").dialog('close');
					$("#userGLevelTableId").datagrid("reload");
				} else {
					$.messager.alert('提示', '服务端出现错误！');
					return;
				}
				
			}
		});

	}else{
		$('#uglDlgFrm').form('submit',{
			url : "relations/buildAreaUserGroup",
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
					$("#uglDlg").dialog('close');
					$("#userGLevelTableId").datagrid("reload");
				} else {
					$.messager.alert('提示', '服务端出现错误！');
					return;
				}
				
			}
		});
		
	}
}
function buildNewGroup(){
	$("#uglDlgFrm").form("clear");
	$("#uglDlg").dialog("open").dialog('setTitle','新建群');
}
function editGroupLevel( ){
	var row = $('#userGLevelTableId').datagrid('getSelected');
	if(row){
		$("#id").val(row.id);
		$("#cGname").val(row.cname);
		$("#profile").val(row.profile);
		var code = row.code;
		var perfix = code.substring(0,3);
		var suffix = code.substring(3);
		$("#areaPrefix").val(perfix);
		$("#areaSuffix").val(suffix);
		$("#uglDlg").dialog("open").dialog('setTitle','修改群信息');
	}else{
		$.messager.alert('提示','请选择一条要审核的数据！');
		return;
	}
}
function getUserGroupLevelPageData(queryObj){
	$("#userGLevelTableId").datagrid({
	   	url:'relations/getUGroupLevelsByParams',
	   	method:'get',
	    loadMsg:'数据加载中....',
	    pagination:true,
		toolbar:"#userGLevelToolbar",
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	//    sortName:"id",
	    columns:[[
 			{field:'id',title:'编号',width:100,hidden:true},
			{field:'cname',title:'群名称',width:200,align:"center"},
			{field:'code',title:'群编号',width:170,align:"center"},
			{field:'profile',title:'群简介',width:100,align:"center"},
			{field:'glevel',title:'群类型',width:100,align:"center",formatter:function(value,row,index){
				if(value == "province"){
					return "省级群";
				}else if(value == "city"){
					return "地区级群";
				}else if(value == "area"){
					return "县级群";
				}else{
					return "未知";
				}
			}},
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
	getUserGroupLevelPageData({});
	$("#groupLSelect").change(function(){
		var level = $("#groupLSelect").val();
		getUserGroupLevelPageData({"glevel":level});
	});
	$('#img').filebox({
	    buttonText: '选择二维码图片',
	    buttonAlign: 'right'
	})
	$("#areaProvinceId").combobox({
		url:"area/getAllProvince",
	    valueField:'id',
	    textField:'cname',
	    onSelect:function(record){
	    	$("#areaId").val(record.id);
	    	$.ajax({
	    		url:"areaGroups/getByProvinceIdAndType",
	    		method:"post",
	    		data:{"provinceId":record.id},
	    		success:function(code){
	    			$("#areaPrefix").val(code+"00");
	    		}
	    	});
	    	$.ajax({
				url:"area/getByProvinceId",
				method:"post",
				data:{"provinceId":record.id},
				success:function(data){
					$("#areaCityId").combobox({
						 valueField:'id',
						 textField:'cname',
						 data:data,
						 onSelect:function(record){
							 $("#areaId").val(record.id);
							 $.ajax({
						    		url:"areaGroups/getByCityIdAndType",
						    		method:"post",
						    		data:{"cityId":record.id},
						    		success:function(code){
						    			$("#areaPrefix").val(code.substring(0,3));
						    		}
						    	});
							 
						 }
					});
				}
			});
	    }
	});
	
});


