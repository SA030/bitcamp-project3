/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
////////////////////////////// LONGIN ///////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////



##Main 
[1] LogIN
[2] Join
	=>[1] 회원/root계정


	=>Join
		좀 더 찾아보고 수정할게용


/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
//////////////////////////// User TUI ///////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////

##[1] 회원 로그인
	'______'님 환영합니다!

	대출도서
	+----+--------------------------+-------------------+-------------------+-------------------+
	| no | 제목						| 대출일				| 반납일				| 남은 기간			|
	+----+--------------------------+-------------------+-------------------+-------------------+
	| 1  | 해커스 토익 기술 VOCA(보카)	| 2024-07-08		| 2024-07-15		| D-4				|
	| 2  | 나는 도대체 왜 피곤할까		| 2024-07-10		| 2024-07-20		| D-9				|
	+----+--------------------------+-------------------+-------------------+-------------------+

	[1] 대출
	[2] 반납
	[3] 회원 정보 수정
	[0] 종료





	=>#[1] 대출
		이런 도서는 어떠신가요?
		'______'님의 추천 도서
		+----+--------------------------+-------------------+
		| no | 제목						| 저자				|
		+----+--------------------------+-------------------+
		| 1  | 모순						| 양귀자				|
		| 2  | 마녀와의 7일				| 히가시노 게이고		|
		| 3  | 리틀 라이프 1				| 한야 야나기하라		|
		| 4  | 아이가 없는 집				| 알렉스 안도릴		|
		| 5  | 나의 돈키호테				| 김호연				|
		+----+--------------------------+-------------------+
		
		[1] 추천 도서 대출
		[2] 도서 검색
		[0] 이전 메뉴
		
		
		
		
		
		
		=>[1] 추천 도서 대출
			[SYSTEM] 도서 번호?
			 >1
			[SYSTEM] '모순' 책이 대출되었습니다.
			 >7
			[SYSTEM] 올바른 번호를 입력해주세요.
		
		
		
		
		
		=>[2] 도서 검색
			[1] 제목
			[2] 저자
			[0] 이전 메뉴
			
			
			=>[1] 제목/[2] 저자?
			[SYSTEM] 제목?/저자?
			 >모순/양귀자
			
			+----+--------------------------+-------------------+
			| no | 제목						| 저자				|
			+----+--------------------------+-------------------+
			| 1  | 모순						| 양귀자				|
			+----+--------------------------+-------------------+
			
			
			
			
		
	=>#[2] 반납
		[SYSTEM] 반납할 책 번호?(메인: 0)
		 >1
		[SYSTEM] '해커스 토익 기출 VOCA(보카)'책이 반납되었습니다.
		 >3
		[SYSTEM] 올바른 번호를 입력해주세요.
		
		
		
		
		
		
		
	=>#[3] 회원 정보 수정
		[SYSTEM] PW?
		 >0000
		
		
		
		=> '______'님의 회원 정보
			ID: ______
			PW: 0000
			MBTI: BPSH

			[1] PW 수정
			[2] 독서 MBTI 재검사
			[0] 이전 메뉴
			
			
			=>[1] PW 수정
				[SYSTEM] 새 PW?(이전: 0000)
				 >1234
				[SYSTEM] 변경되었습니다.
			
			
			=>[2] 독서 MBTI 재검사
			
				찾아보고 수정할게용
				
				
				
			
				
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
///////////////////////////// ROOT TUI //////////////////////////////
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
				
				
##root계정 로그인

	[1] 도서관리
	[2] 대출관리
	[3] 유저관리
	[0] 종료
	
	
	
	
	
	=>#[1] 도서관리
		[1] 도서 조회
		[2] 도서 등록
		[3] 도서 수정
		[3] 도서 삭제
		[0] 이전 메뉴
		
		
		
		
		=>[1] 도서 조회
			+----+--------------------------+-------------------+
			| no | 제목						| 저자				|
			+----+--------------------------+-------------------+
			| 1  | 모순						| 양귀자				|
			| 2  | 마녀와의 7일				| 히가시노 게이고		|
			| 3  | 리틀 라이프 1				| 한야 야나기하라		|
			| 4  | 아이가 없는 집				| 알렉스 안도릴		|
			| 5  | 나의 돈키호테				| 김호연				|
			+----+--------------------------+-------------------+
			
			
			
			
		=>[2] 도서 등록
			[SYSTEM] 제목?
			 >퀸의 대각선 1
			[SYSTEM] 저자?
			 >베르나르 베르베르
			[SYSTEM] 추천 MBTI?
			 >BPSH
			[SYSTEM] 도서 수?
			 >2
			
			[SYSTEM] '퀸의 대각선 1'이 등록되었습니다.
			
			
			
			
		=>[3] 도서 수정
			[SYSTEM] 도서 번호?
			 >1
			 
			[SYSTEM] 제목?(이전: '퀸의 대각선1)
			 >퀸의 대각선 2
			[SYSTEM] 저자?(이전: 베르나르 베르베르)
			 >베르나르 베르베르
			[SYSTEM] 추천 MBTI?(이전: BPSH)
			 >EWOM
			[SYSTEM] 도서 수?(이전: 2)
			 >2
			
			[SYSTEM] '퀸의 대각선 2'가 수정되었습니다.
			
		

		
	=>#[2] 대출관리	
		+----+--------------+-------------------+-----------+-------------------+-------------------+
		| no | 도서 번호		| 제목				| 대출자		| 대출일				| 반납일				|
		+----+--------------+-------------------+-----------+-------------------+-------------------+
		| 1  | 가01083		| 모순 				| 선아		| 2024-07-08		| 2024-07-15		|
		| 2  | 나01083		| 마녀와의 7일		| 선아		| 2024-07-10		| 2024-07-20		|
		| 3  | 다01083		| 리틀 라이프 1		| 재욱		| 2024-05-11		| 2024-05-18		|
		| 4  | 라01083		| 아이가 없는 집		| 재욱		| 2024-07-05		| 2024-07-12		|
		| 5  | 마01083		| 나의 돈키호테		| 재욱		| 2024-07-06		| 2024-07-13		|
		+----+--------------+-------------------+-----------+-------------------+-------------------+
		
		
		
		
	=>#[3] 유저 관리
		+----+--------------+-------------------+-----------+
        | no | 이름			| 아이디				| MBTI		|
        +----+--------------+-------------------+-----------+
        | 1  | 선아			| 선아 				| BPSH		|
        | 2  | 재욱			| 재욱				| EWOM		|
		+----+--------------+-------------------+-----------+
		[1] 유저 삭제
		[0] 이전 메뉴
		
		
		=> [1] 유저 삭제
			[SYSTEM] 삭제할 유저 번호?
			 >1
			[SYSTEM] '선아'님이 삭제되셨습니다.
			 >4
			[SYSTEM] 올바른 번호를 입력해주세요.