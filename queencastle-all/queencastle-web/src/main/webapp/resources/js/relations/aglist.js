function searchAreaGroupInfo(){
	getAreaInfoPageData(
			{"type":$('#areaSelect').combobox('getValue'),
				"cname":$("#areaName").val()
			});
}
function provinceCfg(){
	var pcode = $("#pcode").val();
	
	if(pcode){
		pcode = $("#a_type").val()+pcode;
		$.ajax({
			url:"areaGroups/setAreaGroupCode",
			method:"post",
			data:{"pcode":pcode,
				"areaId":$("#areaId").val(),
				"type":$('#areaSelect').combobox('getValue')
				},
			success:function(){
				$("#provinceCfgDlg").dialog('close');
				getAreaInfoPageData({"type":"province"});
				getAreaGroupPageData({});
			}
		});
	}else{
		$.messager.alert('提示','请填写编码！');
		return;
	}
}

function configAreaCode(){
	var selectedRows=$("#areaTableId").datagrid('getSelections');
	if(selectedRows.length != 1){
		$.messager.alert('提示','请选择一条要编辑的数据！');
		return;
	}
	var row = $('#areaTableId').datagrid('getSelected');
	var areaId=row.id;
	var areaType = $('#areaSelect').combobox('getValue');
	$("#pcfgFrm").form("clear");
	$("#areaId").val(row.id);
	if(areaType == "province"){
		$("#a_type").css("display","none");
		$("#provinceCfgDlg").dialog("open").dialog('setTitle','配置省群编号 ');
	}else{
		
		$.ajax({
			"url":"areaGroups/getPrefix",
			data:{"cityId":areaId},
			method:"post",
			success:function(data){
				if(data){
					$("#a_type").val(data);
					$("#a_type").css("display","block");
					$("#a_type").attr("readonly","true");
					$("#provinceCfgDlg").dialog("open").dialog('setTitle','配置地区群编号 ');
				}else{
					$.messager.alert('提示','该地区所从属的省/直辖市还没有设置！');
					return;
				}
			}
			
			
		});
	}
}

function getAreaInfoPageData(queryObj){
	$("#areaTableId").datagrid({
	   	url:'areaGroups/getAreaInfoByParams',
	   	method:'post',
	    loadMsg:'数据加载中....',
		toolbar:"#areaToolbar",
	    pagination:true,
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'cname',title:'区域名称',width:80,align:"center"},
			{field:'ename',title:'全拼',width:120,align:"center"},
			{field:'config',title:'是否配置',width:120,align:"center",formatter:function(value,row,index){
	        	if(value){
	        		return "<font color='green'>已配置</font>";
	        	}else{
	        		return "<font color='red'>未配置</font>";
	        	}
			}}
			
	    ]]
	});
}





function getAreaGroupPageData(queryObj){
	$("#agTableId").datagrid({
	   	url:'areaGroups/getByParams',
	   	method:'post',
	    loadMsg:'数据加载中....',
		toolbar:"#agToolbar",
	    pagination:true,
	    singleSelect:true,
	    queryParams:queryObj,
	    sortOrder:"desc",
	    sortName:"id",
	    columns:[[
			{field:'id',title:'编号',width:100,hidden:true},
			{field:'areaId',title:'区域ID',width:100,hidden:true},
			{field:'type',title:'区域类型',width:80,align:"center"},
			{field:'cname',title:'区域名称',width:80,align:"center"},
			{field:'code',title:'区域编号',width:120,align:"center"},
			{field:'createdAt',title:'创建时间',width:170,align:"center"}
	    ]]
	});
}
 
$(function(){

	$('#areaSelect').combobox({
	    data:[{
	    	"label":"省",
	    	"value":"province"
	    },{
	    	"label":"地区",
	    	"value":"city"
	    }],
	    valueField:'value',
	    textField:'label',
	    onSelect: function(rec){
	    	getAreaInfoPageData({"type":rec.value});
        }
	});
	$('#areaSelect').combobox('setValue', 'province');
	
	getAreaInfoPageData({"type":"province"});
	getAreaGroupPageData({});
});