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
			<div class="col-md-6" id="page_nav_area">
			
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//1、页面加载完成以后，直接去发送ajax请求，要到分页数据
		$(function(){
			to_page(1);
		});
		
		function to_page(pn){
			$.ajax({
				url:"${APP_PATH}/emps",
				data:"pn="+pn,
				type:"GET",
				success:function(result){
					build_emps_table(result);
					
					build_page_info(result);
					
					build_page_nav(result);
				}
			});
		}
			
		//解析员工列表数据
		function build_emps_table(result){
			$("#emps_table tbody").empty();
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
			$("#page_info_area").empty();
			$("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页,总"+result.extend.pageInfo.pages+"页，总"+result.extend.pageInfo.total+"条记录");			
		}
		
		//解析显示分页条
		function build_page_nav(result){
			$("#page_nav_area").empty();
			//page_nav_area
			var ul=$("<ul></ul>").addClass("pagination");
			var firstPageLi=$("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
			var prePageLi=$("<li></li>").append($("<a></a>").append("&laquo;"));
			if(result.extend.pageInfo.hasPreviousPage==false){
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			}
			else{
				firstPageLi.click(function(){
					to_page(1);
				});
				prePageLi.click(function(){
					to_page(result.extend.pageInfo.pageNum-1);
				});
			}	
			
			
			var nextPageLi=$("<li></li>").append($("<a></a>").append("&raquo;").attr("href","#"));
			var lastPageLi=$("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
			if(result.extend.pageInfo.hasNextPage==false){
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			}
			else{
				nextPageLi.click(function(){
					to_page(result.extend.pageInfo.pageNum+1);
				});
				lastPageLi.click(function(){
					to_page(result.extend.pageInfo.pages);
				});
			}			
		
			//添加首页和前一页
			ul.append(firstPageLi).append(prePageLi);
			$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
				var numLi=$("<li></li>").append($("<a></a>").append(item));
				if(result.extend.pageInfo.pageNum==item){
					numLi.addClass("active");
				}
				numLi.click(function(){
					to_page(item);
				});
				ul.append(numLi);				
			});
			//添加下一页和末页
			ul.append(nextPageLi).append(lastPageLi);
			
			var navEle=$("<nav></nav>").append(ul);
			navEle.appendTo("#page_nav_area");			
		}
	</script>

</body>
</html>