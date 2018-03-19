/**
 * 
 */

//时间输入框
$.extend($.fn.datagrid.defaults.editors, {
	birth:{
		init: function(container, optins){
			var input = $('<input class="easyui-datebox textbox" data-options="required:true" />').appendTo(container);
			return input;
		},
		getValue: function(target){    
			return $(target).val();
        },    
        setValue: function(target, value){    
        	$(target).val(value);    
        },
        resize: function(target, width){    
            
        }
	}
});
