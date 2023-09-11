-- 여기는 유저가 채용공고를 스크랩한 상태 --
insert into user_tb (user_email_id, user_name, user_password, user_pic_url, created_at, gubun) values('ssar@nate.com', 'ssar', '1234', 'basic.jpg', now(), 1);
insert into user_tb (user_email_id, user_name, user_password, user_pic_url, created_at, gubun) values('cos@nate.com', 'cos', '1234', 'basic.jpg', now(), 1);
insert into user_tb (user_email_id, user_name, user_password, user_pic_url, created_at, gubun) values('love@nate.com', '러브', '1234', null, now(), 1);
-- 여기는 회사가 유저의 이력서를 스크랩한 상태 --
insert into user_tb (comp_email_id, user_name, user_password, comp_history, comp_intro, comp_pic_url, created_at, gubun) values('toss@nate.com','toss', '1234', '2010-10-25', '세계1위 토스입니다', 'comp_img01.png', now(), 2);
insert into user_tb (comp_email_id, user_name, user_password, comp_history, comp_intro, comp_pic_url, created_at, gubun) values('naver@nate.com', 'naver', '1234','2000-11-15', '세계1위 네이버입니다', 'comp_img01.png', now(), 2);
insert into user_tb (comp_email_id, user_name, user_password, comp_history, comp_intro, comp_pic_url, created_at, gubun) values('kakao@nate.com', 'kakao', '1234','2005-12-01', '세계1위 카카오입니다', 'comp_img01.png', now(), 2);
--
insert into comp_scrap_tb (created_at) values(now());
insert into comp_scrap_tb (created_at) values(now());
insert into comp_scrap_tb (created_at) values(now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('4', '네이버 공고글', '채용절차는 코딩테스트 후 면접입니다', '신입', null, '대졸', '부산 광안리', now(), now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('5', '카카오 공고글', '채용절차는 코딩테스트 후 면접입니다', '경력', '5년차', '고졸', '부산 장산', now(), now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('5', '삼성 공고글', '채용절차는 코딩테스트 후 면접입니다', '신입', null, '고졸', '부산 장산', now(), now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('4', '토스 공고글', '채용절차는 코딩테스트 후 면접입니다', '경력', '1년차',  '초대졸', '부산 서면', now(), now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('6', '다음 공고글', '채용절차는 코딩테스트 후 면접입니다', '경력', '5년차', '고졸', '서울 장산', now(), now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('6', '애플 공고글', '채용절차는 코딩테스트 후 면접입니다', '신입', null, '고졸', '부산 장산', now(), now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('5', '테슬러 공고글', '채용절차는 코딩테스트 후 면접입니다', '경력', '2년차', '고졸', '부산 장산', now(), now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('4', '마이크로소프트 공고글', '채용절차는 코딩테스트 후 면접입니다', '경력', '3년차', '고졸', '부산 장산', now(), now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('5', '아마존 공고글', '채용절차는 코딩테스트 후 면접입니다', '경력', '1년차', '고졸', '제주 장산', now(), now());

insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('4', '네이버 공고글', '채용절차는 코딩테스트 후 면접입니다', '경력', '1년차', '대졸', '부산 광안리', now(), now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('4', '토스 공고글', '채용절차는 코딩테스트 후 면접입니다', '경력', '2년차', '초대졸', '창원 서면', now(), now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('5', '카카오 공고글', '채용절차는 코딩테스트 후 면접입니다', '경력', '5년차', '고졸', '부산 장산', now(), now());
-- insert into user_scrap_tb (created_at, user_id, job_opening_id) values(now(), '1', '1');
-- insert into user_scrap_tb (created_at, user_id, job_opening_id) values(now(), '2', '1');
-- insert into user_scrap_tb (created_at, user_id, job_opening_id) values(now(), '2', '2');
-- insert into user_scrap_tb (created_at, user_id, job_opening_id) values(now(), '2', '3');
insert into skill_tb (skill) values('Java');
insert into skill_tb (skill) values('Spring Boot');
insert into skill_tb (skill) values('JavaScript');
insert into skill_tb (skill) values('MySQL');
insert into skill_tb (skill) values('jQuery');
insert into skill_tb (skill) values('HTML');
insert into skill_tb (skill) values('JSP');
insert into skill_tb (skill) values('React');
insert into skill_tb (skill) values('Vue.js');
insert into skill_tb (skill) values('Oracle');
insert into position_tb (position) values('서버 백엔드 개발자');
insert into position_tb (position) values('프론트엔드 개발자');
insert into position_tb (position) values('웹 풀스택 개발자');
insert into position_tb (position) values('IOS 개발자');
insert into position_tb (position) values('크로스 플랫폼 앱 개발자');
insert into position_tb (position) values('안드로이드 개발자');

insert into required_position_tb (position_id, job_opening_id) values ('1', '1');
insert into required_position_tb (position_id, job_opening_id) values ('2', '1');
insert into required_position_tb (position_id, job_opening_id) values ('3', '1');
insert into required_position_tb (position_id, job_opening_id) values ('4', '1');
insert into required_position_tb (position_id, job_opening_id) values ('5', '1');
-----------------------------------------------------------------------------------
insert into required_position_tb (position_id, job_opening_id) values ('2', '2');
-----------------------------------------------------------------------------------
insert into required_position_tb (position_id, job_opening_id) values ('3', '3');
-----------------------------------------------------------------------------------
insert into required_position_tb (position_id, job_opening_id) values ('4', '4');
-----------------------------------------------------------------------------------
insert into required_position_tb (position_id, job_opening_id) values ('5', '5');
insert into required_skill_tb (skill_id, job_opening_id) values('1', '1');
insert into required_skill_tb (skill_id, job_opening_id) values('2', '1');
insert into required_skill_tb (skill_id, job_opening_id) values('2', '2');
insert into required_skill_tb (skill_id, job_opening_id) values('3', '2');
insert into required_skill_tb (skill_id, job_opening_id) values('3', '3');
insert into required_skill_tb (skill_id, job_opening_id) values('4', '4');
insert into required_skill_tb (skill_id, job_opening_id) values('5', '5');
insert into resume_tb (user_id, title, resume_pic_url, user_name, user_email_id, birth, tel, address, sub_intro, career, career_year, edu, main_intro, created_at) values ('1', 'ssar 이력서입니다', null, 'ssar', 'ssar@nate.com', now(), '010-2222-3333', '부산 서면 전포', '열심히하는 개발자가 되겠습니다', '경력', '2년차', '대졸', '부산/25/남/ssar입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());
insert into resume_tb (user_id, title, resume_pic_url, user_name, user_email_id, birth, tel, address, sub_intro, career, career_year, edu, main_intro, created_at) values ('1', 'ssar2 이력서입니다', null, 'ssar', 'ssar@nate.com', now(), '010-2222-3333', '부산 서면 전포', '열심히하는 개발자가 되겠습니다', '경력', '2년차', '대졸', '부산/25/남/ssar입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());
insert into resume_tb (user_id, title, resume_pic_url, user_name, user_email_id, birth, tel, address, sub_intro, career, career_year, edu, main_intro, created_at) values ('2', 'cos 이력서입니다', null, 'cos', 'cos@nate.com', now(), '010-4444-3333', '부산 광안리', '포기하지않는 개발자가 되겠습니다', '신입', null, '대졸', '부산/20/남/cos입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());
insert into has_skill_tb (skill_id, resume_id) values('1', '1');
insert into has_skill_tb (skill_id, resume_id) values('2', '1');
insert into has_skill_tb (skill_id, resume_id) values('2', '2');
insert into has_skill_tb (skill_id, resume_id) values('3', '2');
insert into has_skill_tb (skill_id, resume_id) values('3', '3');
insert into has_skill_tb (skill_id, resume_id) values('4', '3');

insert into community_tb (title, content, created_at, user_id) values('제목1', '내용1', now(), '1');
insert into community_tb (title, content, created_at, user_id) values('제목2', '내용2', now(), '2');
insert into community_tb (title, content, created_at, user_id) values('제목3', '내용3', now(), '3');
insert into community_tb (title, content, created_at, user_id) values('제목4', '내용4', now(), '4');
insert into community_tb (title, content, created_at, user_id) values('제목5', '내용5', now(), '5');
insert into community_tb (title, content, created_at, user_id) values('제목6', '내용6', now(), '6');

insert into reply_tb (comment, created_at, user_id, community_id) values('댓글1-1', now(), '1', '1');
insert into reply_tb (comment, created_at, user_id, community_id) values('댓글1-2', now(), '1', '1');
insert into reply_tb (comment, created_at, user_id, community_id) values('댓글2-1', now(), '2', '2');
insert into reply_tb (comment, created_at, user_id, community_id) values('댓글3-1', now(), '3', '3');
insert into task_tb (task_content, job_opening_id) values('재생에너지 SaaS 백엔드 개발', '1');
insert into task_tb (task_content, job_opening_id) values('API 개발(재생에너지모니터링 웹 대시보드)', '1');
insert into task_tb (task_content, job_opening_id) values('API 개발(재생에너지모니터링 웹 대시보드)', '2');
insert into task_tb (task_content, job_opening_id) values('서비스 아키텍쳐 설계 및 AWS 인프라 관리 및 운영', '2');
insert into task_tb (task_content, job_opening_id) values('서비스 아키텍쳐 설계 및 AWS 인프라 관리 및 운영', '3');
insert into qualified_tb (qualified_content, job_opening_id) values('백엔드 개발 실무 경력3년이상', '1');
insert into qualified_tb (qualified_content, job_opening_id) values('Java를 기반 개발 경험', '1');
insert into qualified_tb (qualified_content, job_opening_id) values('백엔드 개발 실무 경력5년이상', '2');
insert into qualified_tb (qualified_content, job_opening_id) values('Python 기반 개발 경험', '2');
insert into qualified_tb (qualified_content, job_opening_id) values('CI CD 툴을 활용한 빌드,테스트,배포 자동화 경험', '2');
insert into qualified_tb (qualified_content, job_opening_id) values('Java 기반 실무 경험', '3');
insert into qualified_tb (qualified_content, job_opening_id) values('MySQL 사용 경험 필수', '3');
insert into qualified_tb (qualified_content, job_opening_id) values('CI CD 툴을 활용한 빌드,테스트,배포 자동화 경험', '3');

insert into comp_scrap_tb (user_id, resume_id) values ('1', '1');
insert into user_scrap_tb (user_id, job_opening_id) values ('1', '1');
insert into user_scrap_tb (user_id, job_opening_id) values ('1', '2');
insert into user_scrap_tb (user_id, job_opening_id) values ('1', '3');
