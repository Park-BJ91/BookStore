  	<insert id="bookEnroll">
  	
  	  	<selectKey resultType="int" keyProperty="bookId" order="BEFORE">
  		
  			SELECT MAX(bookId)+1 FROM vam_book
  		
  		</selectKey>
  	
  		insert into vam_book(bookName, authorId, publeYear, publisher, cateCode, bookPrice, bookStock, bookDiscount, bookIntro, bookContents)
		values(#{bookName},#{authorId}, #{publeYear},#{publisher},#{cateCode},#{bookPrice},#{bookStock},#{bookDiscount},#{bookIntro},#{bookContents})

  	</insert>
  	
  	 Mapper �ܰ��� bookEnroll() �޼��� ���� ��, ����� 'bookId' �÷�(Column)���� �Ķ���ͷ� ���޵� BookVO��ü�� 'bookId' ������
  	 ��ȯ�ǵ��� ���ַ� �ϱ� ���� AdminMapper.xml ������ bookEnroll() �޼��尡 �����ϴ� <insert> �±׿� <selectkey> �±׸� ����
  	 
  	 selectkey>�� �ڵ�����Ű �÷�(autoincrement(mysql), IDENTITY(Orace))�� �������� �ʴ� �����ͺ��̽��� �ڵ�����Ű ��ɰ�
  	 ����� ȿ���� �����ϱ� ���ؼ� ����ϰų�, ����(���� ��� insert)���� ����� Ư�� Į�� ���� ��ȯ�ޱ� ���� ����մϴ�.
  	 <selectKey>�±� ������ �������� vam_book ���̺��� 'bookId' Į�� �� �� ���� ū ��(MAX(bookId))�� �����ͼ� 
  	 �ű⿡ +1�� ���� ����� ��ȯ���ݴϴ�. �̴� insert���� ����� ���Ե� 'bookId' Į��(Column)�� ���� ���� 'bookId'���� �� ���� ū ������ +1 �� ���̱� �����Դϴ�
  	  
  	 �̸� <selectkey>�� ������ �غ��ڽ��ϴ�. MySQL�� ����ϴ� ������Ʈ�� AdminMapper.xml ��� �Ʒ��� ���� ������ �Ͻø� �˴ϴ�.
  	   
  	 <selectKey resultType="int" keyProperty="bookId" order="AFTER">
  		
  		SELECT LAST_INSERT_ID()
  		
  	 </selectKey>
  		
  	  LAST_INSERT_ID()�� ���� �ֱٿ� ����� INSERT�� �� ���� ��ȯ���ֱ� ������ 
  	  <selectkey>�� order �Ӽ� �� ���� "AFTER"�� �������־����ϴ�. 
  		 
  		
  	  <!--	Oracle	-->
  		 
  	  �ƽ��Ե� Oracle������ LAST_INSERT_ID�� ���� ��ɾ �������� �ʽ��ϴ�. 
  	  ���� Oracle ������Ʈ���� ������ vam_book ���̺��� 'bookId' �÷�(Column)�� IDENTITY ����� ���� +1�� ���� ���Եǰ� �ֽ��ϴ�.
  	  IDENTITY ����� ����Ŭ 12c �������� �����ϴ� ����ε� ���������� �������� default value(���̺� ���� �� �ο����ִ� �⺻��)�� ����մϴ�.
  	  ���� ������(Sequence)���� ������ ������ ���� ��� ���� ����ϴ� CURRVAL ����� IDENTITY�� �����ϴ� ������ ���� ���� �� �ֽ��ϴ�.
  		  
  	  SELECT ��������ü�̸�.CURRVAL FROM DUAL;
  	  
     CURRVAL�� ����ϱ� ���ؼ� ���� ������ ��ü �̸��� ���� ������ ���� �մϴ�. 
     ������ ����� ���ؼ� ���� �� �ֽ��ϴ�. 
     �Ʒ� ��ɾ�� ������ ���� '���̺� �̸�'�� �빮�ڷ� �ۼ����־�� �Ѵٴ� ���Դϴ�.
     
     Select TABLE_NAME, COLUMN_NAME, DATA_DEFAULT from USER_TAB_COLUMNS
	 where TABLE_NAME = '��� ���̺� �̸�(�빮��)';
	 
	   	<selectKey resultType="int" keyProperty="bookId" order="AFTER">
  		
  			SELECT ISEQ$$_74040.currval from dual
  		
  		</selectKey>  	