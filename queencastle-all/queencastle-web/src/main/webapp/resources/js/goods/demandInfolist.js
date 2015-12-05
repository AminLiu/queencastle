
function doAdd(){
	$.ajax({
		method:"post",
		url:"goods/getAllProducts",
		success:function(data){
			$('#productId').combobox({
				data:data,
			    valueField:'id',
			    textField:'cname'
			});
		}
	});
	
	
	$("#dsAddFrm").form("clear");
	$("#dsAddDlg").dialog("open").dialog('setTitle','新增供求信息');
}

function saveDsInfo(){
	$('#dsAddFrm').form('submit',{
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
				$("#dsAddDlg").dialog('close');
				$("#demandInfoTableId").datagrid("reload");
			} else {
				$.messager.alert('提示', '服务端出现错误！');
				return;
			}

		}
	});
}




function auditDemandInfo(value) {
	
	var row = $('#demandInfoTableId').datagrid('getSelected');
	if(row){
		var msg ="";
		if(value == "1"){
			msg='你确定审核通过吗？';
		}else{
			msg='你确定不通过吗？';
		}
		$.messager.confirm('确认', msg, function(r){
			if(r){
				$.ajax({
					url : "goods/auditDemandInfo",
					method : "post",
					data:{"check":value,"id":row.id},
					success : function(data) {
						if(data){
							$("#demandInfoTableId").datagrid("reload");
						}else{
							$.messager.alert('提示', '服务端出现错误！');
							return;
						}
						
					}
				});
			}else{
				$.messager.alert('提示', '服务端出现错误！');
				return;
			}
		});
		
	}else{
		$.messager.alert('提示', '请至少选择一条数据！');
		return;
	}
	
}

function doForSearch(){
	$("#dsSearchFrm").form("clear");
	$("#dsSearchDlg").dialog("open").dialog('setTitle','查询供求信息 ');
}
function searchDsInfo(){
	var pname = $("#pname").val();
	var startDate = $('#startDate').datebox('getValue'); 
	var endDate =  $('#endDate').datebox('getValue');
	var dsType = $("#dsType").val();
	 
	getDemandSupplyPageData({
		"pname":pname,
		"startDate":startDate,
		"endDate":endDate,
		"dsType":dsType
	});
	
	
}
function getDemandSupplyPageData(queryObj){
	$("#demandInfoTableId").datagrid({
	   	url:'goods/getDemandSupplysByParams',
	   	method:'post',
	    loadMsg:'数据加载中....',
	    pagination:true,
		toolbar:"#demandInfoToolbar",
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'cname',title:'产品名称',width:100,align:"center"},
			{field:'startDate',title:'开始时间',width:170,align:"center"},
			{field:'endDate',title:'结束时间',width:170,align:"center"},
			{field:'amount',title:'数量',width:100,align:"center"},
			{field:'price',title:'价格',width:100,align:"center"},
			{field:'dsType',title:'供需类型',width:100,align:"center",formatter:function(value,row,index){
	        	if(value == "supply"){
	        		return "<font color='blue'>供货</font>";
	        	}else if(value == "demand"){
	        		return "<font color='#EE00EE'>求货</font>";
	        	}
	        	return "未知";
			}
				
			},
			{field:'check',title:'审核结果',width:100,align:'center',formatter:function(value,row,index){
	        	if(value == "0"){
	        		return "<font color='orange'>审核中</font>";
	        	}else if(value == "1"){
	        		return "<font color='green'>通过</font>";
	        	}else if(value == "2"){
	        		return "<font color='red'>未通过</font>";
	        	}
	        	return "未知";
			}},
			{field:'memo',title:'备注',width:170,align:"center"},
			{field:'createdAt',title:'创建时间',width:170,align:"center"}
	    ]]
	});
}
$(function(){
	getDemandSupplyPageData({});
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


