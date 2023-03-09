<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/member/login.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<link rel="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">  
</head>
<body>

<div class="wrapper">
	
	<div class="wrap">
	    <form id="login_form" method="post">

		<div class="logo_wrap">

			<span>Login Page</span>

		</div>
		<div class="login_wrap"> 
			<div class="id_wrap">
					<div class="id_input_box">
					<input class="id_input" name="memberId">
				</div>
			</div>
			<div class="pw_wrap">
				<div class="pw_input_box">
					<input type="password" class="pw_iput" name="memberPw">
					 <i class="fa fa-eye fa-lg"></i>
				</div>
			</div>			
            	<c:if test = "${result == 0 }">
                	<div class = "login_warn">사용자 ID 또는 비밀번호를 잘못 입력하셨습니다.</div>
            	</c:if>			
			<div class="login_button_wrap">
				<input type="button" class="login_button" value="로그인">
			</div>			
		</div>
		
			<div class="join_button_wrap">
				<a href="join" class="join_button">회원 가입</a>			
			</div>
			 
    </form>	
	</div>

</div>


<script>

$(document).ready(function(){
    $('.pw_wrap i').on('click',function(){
        $('input').toggleClass('active');
        if($('input').hasClass('active')){
            $(this).attr('class',"fa fa-eye-slash fa-lg")
            .prev('input').attr('type',"text");
        }else{
            $(this).attr('class',"fa fa-eye fa-lg")
            .prev('input').attr('type','password');
        }
    });
});
 
    /* 로그인 버튼 클릭 메서드 */
    $(".login_button").click(function(){
        
        //alert("로그인 버튼 작동");
        
        /* 로그인 메서드 서버 요청 */
        $("#login_form").attr("action", "/member/login.do");
        $("#login_form").submit();
        
    });
    
$(".Logo_wrap").on("click",function(e){
	
	history.back();
	
});
 
</script>
</body>
</html>