drop table cafe;
create table cafe(
  pk number primary key,
  title varchar2(40) not null,
  uri varchar2(20) not null,
  manager_id varchar2(20),
  detail varchar2(400),
  created date,
  is_valid varchar2(5)
);
drop sequence seq_cafe;
create sequence seq_cafe;
insert into cafe values(seq_cafe_info.nextval, '동성 학원', 'dongsung.org', '동성학원장', '동성학원입니다', sysdate, 'true');
insert into cafe values(seq_cafe_info.nextval, '자바스터디', 'javastudy.org', '김희택', '자바스터디 모임', sysdate, 'true');
select * from cafe;
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