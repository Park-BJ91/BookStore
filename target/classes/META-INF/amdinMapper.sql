  	<insert id="bookEnroll">
  	
  	  	<selectKey resultType="int" keyProperty="bookId" order="BEFORE">
  		
  			SELECT MAX(bookId)+1 FROM vam_book
  		
  		</selectKey>
  	
  		insert into vam_book(bookName, authorId, publeYear, publisher, cateCode, bookPrice, bookStock, bookDiscount, bookIntro, bookContents)
		values(#{bookName},#{authorId}, #{publeYear},#{publisher},#{cateCode},#{bookPrice},#{bookStock},#{bookDiscount},#{bookIntro},#{bookContents})

  	</insert>
  	
  	 Mapper 단계의 bookEnroll() 메서드 수행 후, 등록한 'bookId' 컬럼(Column)값을 파라미터로 전달된 BookVO객체의 'bookId' 변수에
  	 반환되도록 해주록 하기 위해 AdminMapper.xml 파일의 bookEnroll() 메서드가 수행하는 <insert> 태그에 <selectkey> 태그를 적용
  	 
  	 selectkey>는 자동생성키 컬럼(autoincrement(mysql), IDENTITY(Orace))을 지원하지 않는 데이터베이스에 자동생성키 기능과
  	 비슷한 효과를 구현하기 위해서 사용하거나, 쿼리(예를 들어 insert)에서 수행된 특정 칼럼 값을 반환받기 위해 사용합니다.
  	 <selectKey>태그 내부의 쿼리문은 vam_book 테이블의 'bookId' 칼럼 값 중 가장 큰 값(MAX(bookId))을 가져와서 
  	 거기에 +1을 해준 결과를 반환해줍니다. 이는 insert문의 결과로 삽입될 'bookId' 칼럼(Column)의 값이 기존 'bookId'값들 중 가장 큰 값에서 +1 된 값이기 때문입니다
  	  
  	 이를 <selectkey>에 적용을 해보겠습니다. MySQL을 사용하는 프로젝트의 AdminMapper.xml 경우 아래와 같이 수정을 하시면 됩니다.
  	   
  	 <selectKey resultType="int" keyProperty="bookId" order="AFTER">
  		
  		SELECT LAST_INSERT_ID()
  		
  	 </selectKey>
  		
  	  LAST_INSERT_ID()는 가장 최근에 실행된 INSERT의 열 값을 반환해주기 때문에 
  	  <selectkey>의 order 속성 성 값을 "AFTER"로 지정해주었습니다. 
  		 
  		
  	  <!--	Oracle	-->
  		 
  	  아쉽게도 Oracle에서는 LAST_INSERT_ID와 같은 명령어를 제공하지 않습니다. 
  	  현재 Oracle 프로젝트에서 생성한 vam_book 테이블의 'bookId' 컬럼(Column)은 IDENTITY 기능을 통해 +1된 값이 삽입되고 있습니다.
  	  IDENTITY 기능은 오라클 12c 버전부터 제공하는 기능인데 내부적으로 시퀀스와 default value(테이블 생성 시 부여해주는 기본값)를 사용합니다.
  	  따라서 시퀀스(Sequence)에서 현재의 시퀀스 값을 얻기 위해 사용하는 CURRVAL 명령을 IDENTITY가 생성하는 현재의 값을 얻을 수 있습니다.
  		  
  	  SELECT 시퀀스객체이름.CURRVAL FROM DUAL;
  	  
     CURRVAL을 사용하기 위해선 먼저 시퀀스 객체 이름에 대한 정보를 얻어야 합니다. 
     다음의 명령을 통해서 얻을 수 있습니다. 
     아래 명령어에서 주의할 점은 '테이블 이름'을 대문자로 작성해주어야 한다는 점입니다.
     
     Select TABLE_NAME, COLUMN_NAME, DATA_DEFAULT from USER_TAB_COLUMNS
	 where TABLE_NAME = '대상 테이블 이름(대문자)';
	 
	   	<selectKey resultType="int" keyProperty="bookId" order="AFTER">
  		
  			SELECT ISEQ$$_74040.currval from dual
  		
  		</selectKey>  	