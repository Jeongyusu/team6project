insert into user_tb (user_email_id, user_name, user_password, user_pic_url, created_at, gubun) values('ssar@nate.com', 'ssar', '1234', 'basic.jpg', now(), 1);
insert into user_tb (user_email_id, user_name, user_password, user_pic_url, created_at, gubun) values('cos@nate.com', 'cos', '1234', 'basic.jpg', now(), 1);
insert into user_tb (user_email_id, user_name, user_password, user_pic_url, created_at, gubun) values('love@nate.com', '러브', '1234', null, now(), 1);

insert into user_tb (comp_email_id, user_name, user_password, comp_pic_url, created_at, gubun, comp_history, comp_intro) values('toss@nate.com', '토스', '1234', 'comp_img01.png', now(), 2, now(), '토스입니다.');
insert into user_tb (comp_email_id, user_name, user_password, comp_pic_url, created_at, gubun, comp_history, comp_intro) values('kakao@nate.com', '카카오', '1234', 'comp_img01.png', now(), 2, now(), '카카오입니다.');

insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('2', '토스 공고글', '채용절차는 코딩테스트 후 면접입니다', '경력', '1년차', '대졸', '부산 광안리', now(), now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('3', '토스 공고글2', '채용절차는 코딩테스트 후 면접입니다', '신입', null, '대졸', '부산 광안리', now(), now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('4', '토스 공고글3', '채용절차는 코딩테스트 후 면접입니다', '신입', null, '대졸', '부산 광안리', now(), now());
insert into job_opening_tb (user_id, title, process, career, career_year, edu, comp_address, dead_line, created_at) values ('5', '카카오 공고글', '채용절차는 코딩테스트 후 면접입니다', '경력', '3년차', '대졸', '부산 서면', now(), now());

insert into resume_tb (user_id, title, resume_pic_url, open_check, user_name, user_email_id, birth, tel, address, sub_intro, career, edu, main_intro, created_at) values ('1', '남은혜 이력서입니다', 'n_img.png', '1', 'ssar', 'ssar@nate.com', now(), '010-2222-3333', '부산 서면 전포', '열심히하는 개발자가 되겠습니다', '신입', '대졸', '부산/25/남/ssar입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());
insert into resume_tb (user_id, title, resume_pic_url, open_check, user_name, user_email_id, birth, tel, address, sub_intro, career, edu, main_intro, created_at) values ('2', '이은지 이력서입니다', 'i_img.png', '1', 'ssar', 'ssar@nate.com', now(), '010-2222-3333', '부산 서면 전포', '열심히하는 개발자가 되겠습니다', '경력 2년차','대졸', '부산/25/남/ssar입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());
insert into resume_tb (user_id, title, resume_pic_url, open_check, user_name, user_email_id, birth, tel, address, sub_intro, career, edu, main_intro, created_at) values ('1', '최봉준 이력서입니다', 'c_img.png', '1', 'cos', 'cos@nate.com', now(), '010-4444-3333', '부산 광안리', '포기하지않는 개발자가 되겠습니다', '경력 3년차', '대졸', '부산/20/남/cos입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());
insert into resume_tb (user_id, title, resume_pic_url, open_check, user_name, user_email_id, birth, tel, address, sub_intro, career, edu, main_intro, created_at) values ('2', '정유수 이력서입니다', 'j_img.png', '1', 'cos', 'cos@nate.com', now(), '010-4444-3333', '부산 광안리', '포기하지않는 개발자가 되겠습니다', '신입', '대졸', '부산/20/남/cos입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());
insert into resume_tb (user_id, title, resume_pic_url, open_check, user_name, user_email_id, birth, tel, address, sub_intro, career, edu, main_intro, created_at) values ('3', '최봉준 이력서입니다', 'i_img.png', '2', 'cos', 'cos@nate.com', now(), '010-4444-3333', '부산 광안리', '포기하지않는 개발자가 되겠습니다', '신입', '대졸', '부산/20/남/cos입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());
insert into resume_tb (user_id, title, resume_pic_url, open_check, user_name, user_email_id, birth, tel, address, sub_intro, career, edu, main_intro, created_at) values ('3', '최봉준 이력서입니다', 'i_img.png', '2', 'cos', 'cos@nate.com', now(), '010-4444-3333', '부산 광안리', '포기하지않는 개발자가 되겠습니다', '신입', '대졸', '부산/20/남/cos입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());
insert into resume_tb (user_id, title, resume_pic_url, open_check, user_name, user_email_id, birth, tel, address, sub_intro, career, edu, main_intro, created_at) values ('3', '최봉준 이력서입니다', 'i_img.png', '2', 'cos', 'cos@nate.com', now(), '010-4444-3333', '부산 광안리', '포기하지않는 개발자가 되겠습니다', '신입', '대졸', '부산/20/남/cos입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());
insert into resume_tb (user_id, title, resume_pic_url, open_check, user_name, user_email_id, birth, tel, address, sub_intro, career, edu, main_intro, created_at) values ('3', '최봉준 이력서입니다', 'i_img.png', '2', 'cos', 'cos@nate.com', now(), '010-4444-3333', '부산 광안리', '포기하지않는 개발자가 되겠습니다', '신입', '대졸', '부산/20/남/cos입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());

insert into apply_tb (apply_state, job_opening_id, resume_id) values(null, '2', '1');
insert into apply_tb (apply_state, job_opening_id, resume_id) values(null, '3', '1');
insert into apply_tb (apply_state, job_opening_id, resume_id) values(null, '2', '3');
insert into apply_tb (apply_state, job_opening_id, resume_id) values(null, '3', '3');

insert into suggest_tb (sug_state, resume_id, user_id, job_opening_id) values(null, '1', '4', '1');
insert into suggest_tb (sug_state, resume_id, user_id, job_opening_id) values(null, '3', '5', '3');

insert into comp_scrap_tb (user_id, resume_id) values ('4', '1');
insert into comp_scrap_tb (user_id, resume_id) values ('4', '2');

insert into user_scrap_tb (user_id, job_opening_id) values ('1', '1');
insert into user_scrap_tb (user_id, job_opening_id) values ('1', '2');

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

insert into has_skill_tb (skill_id, resume_id) values('1', '1');
insert into has_skill_tb (skill_id, resume_id) values('2', '1');
insert into has_skill_tb (skill_id, resume_id) values('2', '2');
insert into has_skill_tb (skill_id, resume_id) values('3', '2');
insert into has_skill_tb (skill_id, resume_id) values('3', '3');
insert into has_skill_tb (skill_id, resume_id) values('4', '3');

insert into required_skill_tb (skill_id, job_opening_id) values('1', '1');
insert into required_skill_tb (skill_id, job_opening_id) values('2', '1');
insert into required_skill_tb (skill_id, job_opening_id) values('2', '2');
insert into required_skill_tb (skill_id, job_opening_id) values('3', '2');
insert into required_skill_tb (skill_id, job_opening_id) values('4', '2');
insert into required_skill_tb (skill_id, job_opening_id) values('5', '3');

insert into resume_tb (user_id, title, resume_pic_url, user_name, user_email_id, birth, tel, address, sub_intro, career, career_year, edu, main_intro, created_at) values ('1', 'ssar 이력서입니다','i_img.png', 'ssar', 'ssar@nate.com', now(), '010-2222-3333', '부산 서면 전포', '열심히하는 개발자가 되겠습니다', '경력', '2년차', '대졸', '부산/25/남/ssar입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());
insert into resume_tb (user_id, title, resume_pic_url, user_name, user_email_id, birth, tel, address, sub_intro, career, career_year, edu, main_intro, created_at) values ('1', 'ssar2 이력서입니다', 'i_img.png', 'ssar', 'ssar@nate.com', now(), '010-2222-3333', '부산 서면 전포', '열심히하는 개발자가 되겠습니다', '경력', '2년차', '대졸', '부산/25/남/ssar입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());
insert into resume_tb (user_id, title, resume_pic_url, user_name, user_email_id, birth, tel, address, sub_intro, career, career_year, edu, main_intro, created_at) values ('2', 'cos 이력서입니다', 'i_img.png', 'cos', 'cos@nate.com', now(), '010-4444-3333', '부산 광안리', '포기하지않는 개발자가 되겠습니다', '신입', null, '대졸', '부산/20/남/cos입니다 어렸을때부터 메타코딩 개발자님 영상을 보며 개발자를 꿈꿧습니다.', now());

insert into has_skill_tb (skill_id, resume_id) values('1', '1');
insert into has_skill_tb (skill_id, resume_id) values('2', '1');
insert into has_skill_tb (skill_id, resume_id) values('3', '1');
insert into has_skill_tb (skill_id, resume_id) values('1', '2');
insert into has_skill_tb (skill_id, resume_id) values('2', '2');
insert into has_skill_tb (skill_id, resume_id) values('2', '3');
insert into has_skill_tb (skill_id, resume_id) values('3', '3');
