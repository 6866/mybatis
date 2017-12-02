/**
 * Created by yangxingrom on 2017/11/22.
 */
function remove(obj) {

    var Row = obj.parentNode; //tr
    while (Row.tagName.toLowerCase() != "tr") {
        Row = Row.parentNode;
    }
    Row.parentNode.removeChild(Row); //删除行
}
function addRow() {

    var tab = document.getElementById("ta1"); //获得表格
    var colsNum = tab.rows.item(0).cells.length; //表格的列数
    //表格当前的行数
    var num = tab.rows.length;
    var rownum = num-1;
    tab.insertRow(rownum);

    tab.rows[rownum].insertCell(0);//插入列
    tab.rows[rownum].cells[0].innerHTML = '<td>内容:<textarea name="newContent" cols="30" rows="4"></textarea></td>';
    tab.rows[rownum].insertCell(1);//插入列
    tab.rows[rownum].cells[1].innerHTML = '<td><a href="#" onclick="remove(this)">删除这行</a></td>';


}