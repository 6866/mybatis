/**
 * Created by yangxingrom on 2017/11/19.
 * 批量删除
 */
function deleteBatch() {
    alert("  00");
    $("mainForm").attr("action","/deleteBatch.action");
    $("mainForm").submit();
}