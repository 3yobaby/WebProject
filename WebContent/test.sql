drop table cafe;
create table cafe(
  pk number primary key,
  title varchar2(40) not null,
  uri varchar2(20) not null,
  manager_id varchar2(20),
  detail varchar2(400),
  created date,
  search_words varchar2(200),
  join_rule varchar2(20),
  is_valid varchar2(5),
  is_search varchar2(5),
  is_organization varchar2(5)
);
drop sequence seq_cafe;
create sequence seq_cafe;
insert into cafe values(seq_cafe.nextval, '동성학원', 'dongsung', '동성학원장', '동성학원 기관입니다', sysdate, '동성|자바' ,'organization','true','true', 'true');
insert into cafe values(seq_cafe.nextval, '자바스터디', 'javastudy', '김희택', '자바스터디 모임', sysdate, '자바' ,'cafe','true','true', 'false');
select * from cafe where search_words like '%동%';
select * from cafe;
commit;
-- cafe_category
drop table category;
create table category(
  pk number primary key,
  fk_cafe number,
  title varchar2(30),
  is_sub varchar2(5),
  ref number
);
drop sequence seq_category;
create sequence seq_category;
insert into category values(seq_category.nextval, 1, '자유게시판', 'false', seq_category.nextval);
insert into category values(seq_category.nextval, 2, '가입인사', 'false', seq_category.nextval);
insert into category values(seq_category.nextval, 2, '자유게시판', 'false', seq_category.nextval);
insert into category values(seq_category.nextval, 2, '강의자료', 'false', seq_category.nextval);
insert into category values(seq_category.nextval, 2, '자바', 'true', seq_category.nextval);
select * from category;

commit;
-- cafe_board
drop table board;
create table board(
  pk number primary key,
  fk_category number,
  fk_member number,
  name varchar2(20),
  title varchar2(100),
  content varchar2(4000),
  created date,
  ref number
);
drop sequence seq_board;
create sequence seq_board;
insert into board values(seq_board.nextval, 2, 2, '김희택', '가입인사 납겨주세요', '냉무', sysdate, seq_board.nextval);
insert into board values(seq_board.nextval, 2, 2, '김희택', '반갑습니다', 'ㅎㅎ', sysdate, seq_board.nextval);
select * from board;
commit;
-- member
drop table member;
create table member(
  pk number primary key,
  id varchar2(20) not null,
  pass varchar2(20) not null,
  name varchar2(40) not null,
  email varchar2(40),
  tel varchar2(20),
  joined date,
  is_valid varchar2(5)
);
drop sequence seq_member;
create sequence seq_member;
insert into member values(seq_member.nextval, 'dongsung', '1234', '동성학원장', 
  'dongsung@naver.com', '051-111-1111', sysdate, 'true');
insert into member values(seq_member.nextval, 'kht', '1234', '제작자', 
  'minionofdiablo@nate.com', '051-111-1111', sysdate, 'true');
select * from member;
commit;
-- cafe_members
drop table cafe_members;
create table cafe_members(
  pk number primary key,
  fk_cafe number,
  fk_member number,
  member_type number
);
drop sequence seq_cafe_members;
create sequence seq_cafe_members;
insert into cafe_members values(seq_cafe_members.nextval, 1, 1, 4); -- 동성학원, 동성학원장
insert into cafe_members values(seq_cafe_members.nextval, 2, 2, 3);
select * from cafe_members;
commit;
--
drop table organization_cafe;
create table organization_cafe(
pk number primary key,
fk_organization number,
fk_cafe number,
cafe_type number
);
drop sequence seq_organization_cafe;
create sequence seq_organization_cafe;
insert into organization_cafe values(seq_organization_cafe.nextval, 1, 2, 1);
select * from organization_cafe;
commit;
--