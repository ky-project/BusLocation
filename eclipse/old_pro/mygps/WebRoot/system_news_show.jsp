<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/myutils.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			type: "POST",
			dataType: "json",
			url: "../servlet/newsGetObjectById",
			cache: false,
			data: {
				"id": $('#id').val()
			},
			success: function(result) {
				if (result!=null) {
					$('#_show_news_title').html(result.newTitle);
					$('#_show_news_createTime').html(result.faTime);
					$('#_show_news_content').html(result.newContent);
				}
			}
		});
	});
</script>
<style type="text/css">

.bodytype {
    margin: 10px;
    width: 700px;
}
</style>
</head>
<body id="lr_body" class="bodytype">
<span style="display:none">
	<input name="id" id="id" type="hidden" value="<%=request.getParameter("id")%>"/>
</span>
		<!--内容中间开始-->
			<div id="system-notice-left">
				<h2>通知公告</h2>
				<div id="system-notice-content">
				    <div id="_show_news_title" class="notice-title">
					</div>
					<div id="notice-top">
						<span id="_show_news_createTime"></span>
					</div>
					<div id="_show_news_content" class="notice-content">
					</div>
				</div>
			</div>
			  <div class="clear"></div>

		 <!--内容中间结束-->
</body>
</html>
