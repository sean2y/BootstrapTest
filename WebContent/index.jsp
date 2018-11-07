<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css" />
<script type="text/javascript" src="js/jquery-1.11.0.js" ></script>
<script type="text/javascript" src="js/bootstrap.js" ></script>
<script type="text/javascript">
	function showUser() {
		$.ajax({
			url:'UserServlet',
			type:'POST',
			data:{'type':'list'},
			dataType:'json',
			success:function(data){
				var str1 = "<table class='table table-hover table-bordered'><tr><th>编号</th><th>姓名</th><th>性别</th><th>年龄</th><th>手机号码</th><th>操作</th></tr>";
				for(var i=0; i<data.length; i++){
					var user = data[i];
					var str2 = "<tr><td>"+user.userId+"</td><td>"
								+user.userName+"</td><td>"
								+user.userSex+"</td><td>"
								+user.userAge+"</td><td>"
								+user.userNum+"</td><td>"
								+"<button type='button' class='btn btn-default btn-xs'"
								+"data-toggle='modal' data-target='#updateModal'"
								+" onclick='findUser("
								+user.userId
								+")'>修改</button>"+"&nbsp"
								+"<button type='button' class='btn btn-primary btn-xs' onclick='delUser("
								+user.userId
								+")'>删除</button>"
								+"</td></tr>";
					str1+=str2;
				}
				$("p").html(str1+"</table>");
			},
			error:function(){
				alert("出错了！");
			}
		});
	}
	function addUser(){
		$.ajax({
			url:'UserServlet',
			type:'POST',
			data:{'type':'add', 'userName':uName.value, 'userSex':$("input[name='userSex']:checked").val(), 'userAge':age.value, 'userNum':number.value},
			dataType:'json',
			success:function(data){
				showUser();
			},
			error:function(){
				alert("提交出错了");
			}
		});
	}
	function delUser(userId){
		$.ajax({
			url:'UserServlet',
			type:'POST',
			data:{'type':'del', 'userId':userId},
			dataType:'json',
			success:function(data){
				showUser();
			},
			error:function(){
				alert("出错了");
			}
		});
	}
	function findUser(userId){
		$.ajax({
			url:'UserServlet',
			type:'POST',
			data:{'type':'find', 'userId':userId},
			dataType:'json',
			success:function(data){
				$("#inputUser").val(data.userId);
				$("#uName2").val(data.userName);
				$("#age2").val(data.userAge);
				$("#number2").val(data.userNum);
			},
			error:function(){
				alert("提交出错了");
			}
		});
	}
	function updateUser(){
		$.ajax({
			url:'UserServlet',
			type:'POST',
			data:{'type':'update','userId':inputUser.value ,'userName':uName2.value, 'userSex':$("input[name='userSex2']:checked").val(), 'userAge':age2.value, 'userNum':number2.value},
			dataType:'json',
			success:function(data){
				showUser();
			},
			error:function(){
				alert("提交出错了");
			}
		});
	}
</script>
</head>
<body onload="showUser()">
<div class="container">
	<h3 class="text-center">用户列表显示</h3>
	<a href="#" data-toggle="modal" data-target="#addModal">添加用户</a>
	<p></p>	
  	<div class="modal fade bs-example-modal-sm" id="addModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	  <div class="modal-dialog modal-sm" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title" id="ModalTitle">添加用户</h4>
	      </div>
          <form>
		      <div class="modal-body">
		          <div class="form-group">
		            <label for="name" class="control-label">姓名:</label>
		            <input type="text" class="form-control" name="userName" id="uName">
		          </div>
		          <label class="control-label">性别：</label>
		          <div class="radio">
					  <label>
					    <input type="radio" name="userSex" id="uSex1" value="男" checked>男
					  </label>
					  <label>
					    <input type="radio" name="userSex" id="uSex2" value="女">女
					  </label>
				  </div>
		          <div class="form-group">
		            <label for="age" class="control-label">年龄:</label>
		            <input type="text" class="form-control" name="userAge" id="age">
		          </div>
		          <div class="form-group">
		            <label for="number" class="control-label">手机号码:</label>
		            <input type="text" class="form-control" name="userNum" id="number">
		          </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="addUser()">提交</button>
		      </div>
	      </form>
	    </div>
	  </div>
	</div>
	<div class="modal fade bs-example-modal-sm" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
	  <div class="modal-dialog modal-sm" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title" id="ModalTitle">修改用户</h4>
	      </div>
          <form>
		      <div class="modal-body">
		          <div class="form-group">
		            <label for="name" class="control-label">姓名:</label>
		            <input type="hidden" id="inputUser">
		            <input type="text" class="form-control" name="userName" id="uName2">
		          </div>
		          <label class="control-label">性别：</label>
		          <div class="radio">
					  <label>
					    <input type="radio" name="userSex2" value="男" checked>男
					  </label>
					  <label>
					    <input type="radio" name="userSex2" value="女">女
					  </label>
				  </div>
		          <div class="form-group">
		            <label for="age" class="control-label">年龄:</label>
		            <input type="text" class="form-control" name="userAge" id="age2">
		          </div>
		          <div class="form-group">
		            <label for="number" class="control-label">手机号码:</label>
		            <input type="text" class="form-control" name="userNum" id="number2">
		          </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="updateUser()">提交</button>
		      </div>
	      </form>
	    </div>
	  </div>
	</div>		
</div>
</body>
</html>