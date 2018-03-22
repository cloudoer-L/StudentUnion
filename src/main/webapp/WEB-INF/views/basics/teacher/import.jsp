<%--
  Created by IntelliJ IDEA.
  User: 李小东
  Date: 2018/3/15
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="teacher_dialog" class="import_div">
    <fieldset class="layui-elem-field">
        <legend>导入过程:</legend>
        <div class="layui-field-box">
            1.请先<a href="../excel/getExcel.action?downPath=教师导入模板.xls"  target="_blank" style="font-size: 15px;color: red;">单位导入模板</a><br/>
            2.按照模板格式录入数据<br/>
            3.上传文件<br/>
        </div>
    </fieldset>
    <fieldset class="layui-elem-field">
        <legend>注意事项:</legend>
        <div class="layui-field-box">
            说明<br/>
            1.程序只读取.xls格式数据;<br/>
            2.程序只导入sheet1数据,第一行作为表头，不导入;<br/>
            3.由于Excel限制，每个sheet最多只能有65535行，所有一个sheet最多导入65534条数据,多于65534的数据请分批导入;<br/>
            4.学年:2016年情况就写2016。<br/>
            5.学号:是学校对每个学生的编号，用于后台检索数据，没有此需求的空着不填。<br/>
            6.单位类别、办别、地区编号、城乡性质必须使用程序内数据，否则将影响导入。<br/>
            7.时间的格式为“YYYY.MM.DD hh:mm:ss”，位数不足需补0，如“2017.01.01 00:00:00”。<br/>
        </div>
    </fieldset>
    <fieldset class="layui-elem-field">
        <legend>上传文件:</legend>
        <div class="layui-field-box">
            <form enctype="multipart/form-data" action="#" method="post">
                <input type="file"  id="teacher_import_file" name="importExcel" class="layui-upload-file">
            </form>
        </div>
    </fieldset>
    <script type="text/javascript">
        $(bs.file_import_load('teacher_import_file',bs.base_url+'teacher/importFile.action','teacher_dialog','teacher_index_dg'));
    </script>

</div>
