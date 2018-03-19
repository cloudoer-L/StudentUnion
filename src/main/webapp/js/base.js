/**
 * 抽离出一些公共方法
 * @type {Base}
 */
var bs = new Base();

function Base() {}

/**
 * 路径前缀
 * @type {string}
 */
Base.prototype.base_url = "../";

/**
 * 打开一个小的模态框，主要应用于增加和删除
 * @param _divId
 * @param _url
 * @param _title
 * @param _queryParams
 */
Base.prototype.show_dialog_min = function (_divId, _url, _title, _queryParams) {
    $("<div/>").dialog({
        id: _divId,
        href: _url,
        title: _title,
        height: 500,
        width: 450,
        modal: true,
        queryParams: _queryParams,
        onClose: function () {
            $("#"+_divId).dialog('destroy');  //销毁dialog对象
        }
    });
};

/**
 * 提交treegrid 的ajax ，主要应用于treegrid 的删除
 * @param _url
 * @param _dgId
 * @param _mes
 */
Base.prototype.base_ajax_treegrid = function (_url,_dgId,_mes) {
    $.messager.confirm('确认',_mes,function(r){
        if (r){
            $.ajax({
                url:_url,
                type:"get",
                dataType:"json",
                success: function(data){
                    //var result = JSON.parse(data);
                    if(data[0].status=='success'){
                        $.messager.alert('成功', data[0].description);
                        $('#'+_dgId).treegrid('reload');
                    }else{
                        $.messager.alert('失败', data[0].description);
                    }
                },error :function (){
                    $.messager.alert('警告','请求失败，请检查网络连接');
                }
            });
        }
    });
};

/**
 * 提交treegrid 相关的表单
 * @param _url
 * @param _formId
 * @param dialogId
 * @param dgId
 */
Base.prototype.formSubmit_treegrid = function (_url, _formId, dialogId, dgId) {
    $('#'+_formId).form('submit', {
        url: _url,
        type:"post",
        dataType:"json",
        onSubmit:function (param) {
            if ($(this).form('validate')) {
                return true;
            } else {
                return false;
            }
        },
        success:function (data) {
            var result = JSON.parse(data);
            if(result[0].status === 'success'){
                $.messager.alert('成功', result[0].description);
                $("#"+dialogId).dialog('destroy');
                $('#'+dgId).treegrid('reload');
            } else{
                $.messager.alert('失败', result[0].description);
            }
        }
    });
};

/**
 * 初始化treegrid
 * @param _url
 * @param _tbrId
 * @param _columns
 */
Base.prototype.treegrid_init = function (_url, _tbrId, _columns) {
    $('#menu_index_treegrid').treegrid({
        url: _url,
        idField: 'id',
        treeField: 'name',
        singleSelect: true,
        selectOnCheck: false,
        checkOnSelect: false,
        nowrap: true,
        striped: true,
        columns: _columns,
        loadMsg: false,
        toolbar: '#'+_tbrId,
        loadFilter:function (data,parentId) {
            if (data[0] != null && data[0].rows != null){
                for (var i = 0; i < data[0].rows.length; i++){
                    if (data[0].rows[i]._parentId == ""){
                        data[0].rows[i]._parentId = null;
                    }
                }
            }
            return data[0];
        },
        onLoadSuccess:function () {
            $(".tree-icon,.tree-file").removeClass("tree-icon tree-file");
            $(".tree-icon,.tree-folder").removeClass("tree-icon tree-folder tree-folder-open tree-folder-closed");
        }
    });
};

/**
 * datagride加载
 * @param _tableId
 * @param _columns
 * @param _url
 * @param _tbrId
 * @param _queryParams
 */
Base.prototype.datagrid_load = function (_tableId, _columns, _url, _tbrId, _queryParams) {
    $("#"+_tableId).datagrid({
        url: _url,
        columns: _columns,
        queryParams:_queryParams,
        idField: 'id',
        singleSelect: true,
        selectOnCheck: false,
        checkOnSelect: false,
        striped: true,
        pagination: true,
        //nowrap:false,
        autoRowHeight:false,
        nowrap:true,
        fit:true,
        resizable:false,
        pageSize: 30,
        loadMsg: false,
        rownumbers: true,
        pageList:[10,20,30],
        toolbar: '#'+_tbrId,
        onBeforeLoad:function(param){
            $('#'+_tableId).datagrid('clearSelections');
            $('#'+_tableId).datagrid('clearChecked');
        }
    });
};

/**
 * 提交datagrid有关的表单
 * @param _url
 * @param _formId
 * @param dialogId
 * @param dgId
 */
Base.prototype.formSubmit_datagrid = function (_url, _formId, dialogId, dgId) {
    $('#'+_formId).form('submit', {
        url: _url,
        type:"post",
        dataType:"json",
        onSubmit:function (param) {
            if ($(this).form('validate')) {
                return true;
            } else {
                return false;
            }
        },
        success:function (data) {
            var result = JSON.parse(data);
            if(result[0].status === 'success'){
                $.messager.alert('成功', result[0].description);
                $("#"+dialogId).dialog('destroy');
                $('#'+dgId).datagrid('reload');
            } else{
                $.messager.alert('失败', result[0].description);
            }
        }
    });
};

/**
 * 提交datagrid有关的ajax
 * @param _url
 * @param _dgId
 * @param _mes
 */
Base.prototype.base_ajax_datagrid = function (_url,_dgId,_mes) {
    $.messager.confirm('确认',_mes,function(r){
        if (r){
            $.ajax({
                url:_url,
                type:"get",
                dataType:"json",
                success: function(data){
                    //var result = JSON.parse(data);
                    if(data[0].status=='success'){
                        $.messager.alert('成功', data[0].description);
                        $('#'+_dgId).datagrid('reload');
                    }else{
                        $.messager.alert('失败', data[0].description);
                    }
                },error :function (){
                    $.messager.alert('警告','请求失败，请检查网络连接');
                }
            });
        }
    });
};

Base.prototype.file_import_load = function (_importId, _url, _dialogId, _dgId) {
    layui.use('upload', function(){
        layui.upload({
            elem:'#'+_importId
            ,url: _url
            ,title: '点我导入Excel文件'
            ,type:'file'
            ,ext: 'xls|xlsx'
            , before: function(input){
                layer.msg('文件即将上传,请稍后...');
            }
            ,success: function(result){
                //result.description
                if(result[0].status === 'success'){
                    layer.alert(result[0].description, {
                        area: '300px',
                        skin: 'layui-layer-molv' //样式类名
                        ,closeBtn: 0
                    }, function(index){
                        $("#"+_dialogId).dialog('destroy');
                        $('#'+_dgId).datagrid('reload');
                        layer.close(index);
                    });
                } else{
                    layer.msg(result[0].description, { shade: 0.3, time: 3000, icon: 2 });
                }
            }
            ,error:function(){
                layer.msg('网络异常,上传失败', { shade: 0.3, time: 1000, icon: 2 });
            }
        });
    });
}
