function buildManager(){
	 $("#addManagerFrm").form("clear");
	$("#addManagerDlg").dialog("open").dialog('setTitle','新建地区管理员');
} 

function dispatcherManager(){
	var row = $("#userManageTableId").datagrid('getSelected');
	if(row){
		if(row.level == "province"){
			$.messager.alert('提示', '无需为省级管理员分配');
			return;
		}
		if(row.level == "area"){
			$.messager.alert('提示', '无需为县区级管理员分配');
			return;
		}
		if(row.level == "city"){
			$.ajax({
				url:"relations/getCityGroups",
				method:"post",
				data:{"areaId":row.areaId},
				success:function(result){
					$("#usableCityGroupSelect").combobox({
						 data:result
					});
					$("#citysSelectDlg").dialog("open").dialog('setTitle','分配管理员');
				}
			});
		}
		
		
	}else{
		$.messager.alert('提示', '请选择一行数据');
		return;
	}
	
}

function configUserForManager(){
	var groupId = $("#usableCityGroupSelect").combobox("getValue");
	var row = $("#userManageTableId").datagrid('getSelected');
	var userId = row.userId;
	
	$.ajax({
		method:"post",
		url:"userAdmin/configMasterForUserGroup",
		data:{"userId":userId,"groupId":groupId},
		success:function(){
			$("#citysSelectDlg").dialog("close");
		}
	});
	
}

function selectUserForManager(){
	var row = $("#managerUserTableId").datagrid('getSelected');
	$("#managerUserSelectDlg").dialog("close");
	if(row){
		var userId = row.id;
		 var aLevel = $("#areaLevel").val();
		var areaId= getAreaId();
		var cname =$("#cname").val();
		if(userId && aLevel && areaId && cname){
			$.ajax({
				url:"userAdmin/saveUserManager",
				data:{"userId":userId,"level":aLevel,"areaId":areaId,"cname":cname},
				method:"post",
				success:function(){
					$("#addManagerDlg").dialog("close");
					$("#userManageTableId").datagrid("reload");
				}
			});
		}else{
			$.messager.alert('提示', '请填写完整');
		}
	}
}
function getAreaUserPageData(queryObj){
	$("#managerUserTableId").datagrid({
	   	url:'userAdmin/getAreaUserByParam',
	   	method:'get',
	    loadMsg:'数据加载中....',
	    pagination:true,
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'userName',title:'用户名',width:200,align:"center"},
	    ]]
	});
}
function getUserManagePageData(queryObj){
	$("#userManageTableId").datagrid({
	   	url:'userAdmin/getByParams',
	   	method:'get',
	    loadMsg:'数据加载中....',
	    pagination:true,
		toolbar:"#userManageToolbar",
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'userName',title:'用户名',width:200,align:"center"},
			{field:'cname',title:'管理员名称',width:200,align:"center"},
			{field:'areaName',title:'所在地区',width:200,align:"center"},
			{field:'level',title:'管理员级别',width:170,align:"center",formatter:function(value,row,index){
				if(value == "province"){
					return "省级";
				}else if(value == "city"){
					return "地区级";
				}else if(value == "area"){
					return "县级";
				}else{
					return "未知";
				}
			}},
			{field:'createdAt',title:'创建时间',width:170,align:"center"},
			{field:'updateAt',title:'更新时间',width:170,align:"center"}
	    ]]
	});
}

function getAreaId(){
	var aLevel = $("#areaLevel").val();
	if(aLevel == "province"){
		areaId = $("#provinceId").combobox("getValue");
	}
	if(aLevel == "city"){
		areaId = $("#cityId").combobox("getValue");
	}
	if(aLevel == "area"){
		areaId = $("#areaId").combobox("getValue");
	}
	return areaId;
}
$(function(){
	getUserManagePageData({});
	
	
	$("#areaLevel").change(function(){
		var aLevel = $("#areaLevel").val();
		if(aLevel == "province"){
			$("#provinceDiv").css("display","block");
			$("#cityDiv").css("display","none");
			$("#areaDiv").css("display","none");
		}
		if(aLevel == "city"){
			$("#provinceDiv").css("display","block");
			$("#cityDiv").css("display","block");
			$("#areaDiv").css("display","none");
		}
		if(aLevel == "area"){
			$("#provinceDiv").css("display","block");
			$("#cityDiv").css("display","block");
			$("#areaDiv").css("display","block");
		}
	});
	
	$("#usableCityGroupSelect").combobox({
		 valueField:'id',
		 textField:'cname',
	});
	
	
	$("#selectUserBtn").click(function(){
		 var aLevel = $("#areaLevel").val();
		
		getAreaUserPageData({"level":aLevel,"areaId":getAreaId()});
		$("#managerUserSelectDlg").dialog("open").dialog('setTitle','根据地区查询用户');
	});
	$("#provinceId").combobox({
		url:"area/getAllProvince",
	    valueField:'id',
	    textField:'cname',
	    onSelect:function(record){
	    	$.ajax({
	    		url:"area/getByProvinceCode",
	    		data:{"provinceCode":record.code},
	    		success:function(data){
	    			var len=data.length;
	    			if(len>0){
	    				$("#cityDiv").css("display","block");
	    				$("#cityId").combobox({
		    				data:data,
		    				valueField:'id',
		    		    	textField:'cname',
	    		    	    onSelect:function(record){
	    		    		  $.ajax({
	    		    			  url:"area/getAreasByCityCode",
	    		  	    		  data:{"cityCode":record.code},
	    		  	    		  success:function(data){
	    		  	    			  var len = data.length;
	    		  	    			  if(len>0){
	    		  	    				$("#areaDiv").css("display","block");
	    		  	    				  $("#areaId").combobox({
	    		  	    					  data:data,
	    		  	    					  valueField:'id',
	    		  	    					  textField:'cname'
	    		  	    				  });
	    		  	    			  }else{
	    		  	    				$("#areaDiv").css("display","none");
	    		  	    				$("#areaId").val("");
	    		  	    			  }
	    		  	    		  }	
	    		    		  });
	    		    	   }
		    			});
	    			}else{
	    				$("#cityDiv").css("display","none");
	    				$("#cityId").val("");
						$("#areaDiv").css("display","none");
						$("#areaId").val("");
	    			}
	    			
	    		}
	    	});
	    }
	    
	});
});


