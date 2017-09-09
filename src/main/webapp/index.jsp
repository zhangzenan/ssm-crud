<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准（http://localhost:3306）;需要加上项目名称
http://localhost:3306/ssm-crud
 -->
<!-- 引入jquery -->
<script type="text/javascript" src="${APP_PATH }/static/js/jquery-2.1.4.min.js"></script>
<!-- 引入样式 -->
<link href="${APP_PATH }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<script type="text/javascript" src="${APP_PATH }/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 搭建显示页面 -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<h1>SSM-CRUD</h1>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 显示表格数据 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>deptName</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody></tbody>									
				</table>
			</div>
		</div>
		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6" id="page_info_area">				
				
			</div>
			<!-- 分页条信息 -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				  <ul class="pagination">
				  	<li><a href="${APP_PATH }/emps?pn=1">首页</a></li>
				  	<c:if test="${pageInfo.hasPreviousPage }">
				  		<li>				    
					      <a href="${APP_PATH }/emps?pn=${pageInfo.pageNum-1 }" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				  	</c:if>
				  					    
				    <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
				    	<c:if test="${page_Num==pageInfo.pageNum }">
				    		<li class="active"><a href="#">${page_Num }</a></li>	
				    	</c:if>
				    	<c:if test="${page_Num!=pageInfo.pageNum }">
				    		<li><a href="${APP_PATH }/emps?pn=${page_Num }">${page_Num }</a></li>	
				    	</c:if>				    	
				    </c:forEach>    
				   
				   <c:if test="${pageInfo.hasNextPage }">
				     <li>
				      <a href="${APP_PATH }/emps?pn=${pageInfo.pageNum+1 }" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
				   </c:if>
				  <li><a href="${APP_PATH }/emps?pn=${pageInfo.pages }">末页</a></li>
				  </ul>
				</nav>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//1、页面加载完成以后，直接去发送ajax请求，要到分页数据
		$(function(){
			$.ajax({
				url:"${APP_PATH}/emps",
				data:"pn=1",
				type:"GET",
				success:function(result){
					build_emps_table(result);
					
					build_page_info(result);
				}
			});
		});
			
		//解析员工列表数据
		function build_emps_table(result){
			var emps=result.extend.pageInfo.list;
			$.each(emps,function(index,item){
				var empIdTd=$("<td></td>").append(item.empId);
				var empNameTd=$("<td></td>").append(item.empName);
				var genderTd=$("<td></td>").append(item.gender=='M'?'男':'女');
				var emailTd=$("<td></td>").append(item.email);
				var deptNameTd=$("<td></td>").append(item.department.deptName);								
				var editBtn=$("<button></button>").addClass("btn btn-primary").append("编辑");
				var delBtn=$("<button></button>").addClass("btn btn-danger").append("删除");
				var btnTd=$("<td></td>").append(editBtn).append(" ").append(delBtn);
				$("<tr></tr>").append(empIdTd)
				.append(empNameTd)
				.append(genderTd)
				.append(emailTd)
				.append(deptNameTd)
				.append(btnTd)				
				.appendTo("#emps_table tbody");
			});			
		}
		
		//解析分页信息
		function build_page_info(result){			
			$("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页,总"+result.extend.pageInfo.pages+"页，总"+result.extend.pageInfo.total+"条记录");			
		}
		
		//解析显示分页条
		function build_page_nav(){
			
		}
	</script>

</body>
</html>