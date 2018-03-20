<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/3/1
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="west_app" class="easyui-accordion" data-options="fit:true" ></div>

<script type="text/javascript">
    var _west_js = {
        init:function () {
            $.ajax({
                url:bs.base_url+'menu/getMenuTree.action',
                type:'post',
                async:false,
                success:function (data) {
                    _west_js.menu_init(JSON.parse(data));
                }
            });
        },
        menu_init:function (_menu) {
            for (var i = 0; i < _menu.length; i++){
                var _button = '';
                for (var j = 0; j < _menu[i].lower.length; j++){
                    _button += '<a id="'+_menu[i].lower[j].id+'" href="javascript:;">'+_menu[i].lower[j].name+'</a><br/>';
                }
                $('#west_app').accordion('add',{
                    title: _menu[i].name,
                    content: _button,
                    selected: false
                });
                for (var j = 0; j < _menu[i].lower.length; j++){
                    $('#'+_menu[i].lower[j].id).linkbutton({
                        iconCls: _menu[i].lower[j].ico
                    });
                    //(function(){})();是匿名自调用函数，因为必须马上调用，所以这里for每次循环都将刷新这个匿名函数
                    (function (_menu) {
                        var _subtitle = _menu.name;
                        var _url = _menu.url;
                        var _icon = _menu.ico;
                        $('#'+_menu.id).on('click', function () {
                            _west_js.index_tabs_add(_subtitle, _url, _icon);
                        });
                    })(_menu[i].lower[j]);
                }
            }
        },
        index_tabs_add:function (_subtitle,_url,_icon) {
            if (!$('#index_tabs').tabs('exists', _subtitle)) {
                $('#index_tabs').tabs('add', {
                    title: _subtitle,
                    href: bs.base_url+_url,
                    iconCls: _icon,
                    closable: true,
                    loadingMessage: '正在加载中......'
                });
            } else {
                $('#index_tabs').tabs('select', _subtitle);
            }
        }
    }

    var west_app = (function () {
        $(function () {
            _west_js.init();
        });
    })();
</script>