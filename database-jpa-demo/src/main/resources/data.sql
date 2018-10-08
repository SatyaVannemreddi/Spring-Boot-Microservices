insert into course (id, name, created_date, last_updated_date, is_deleted) values (10001, 'Spring boot', sysdate, sysdate, false);
insert into course (id, name, created_date, last_updated_date, is_deleted) values (10002, 'Spring Cloud', sysdate, sysdate, false);
insert into course (id, name, created_date, last_updated_date, is_deleted) values (10003, 'Spring JPA', sysdate, sysdate, false);
insert into course (id, name, created_date, last_updated_date, is_deleted) values (10004, 'Spring Security', sysdate, sysdate, false);


insert into passport (id, pass_number) values (30001,'satya1234');
insert into passport (id, pass_number) values (30002,'ambica1234');
insert into passport (id, pass_number) values (30003,'tanuj1234');
insert into passport (id, pass_number) values (30004,'uday1234');
insert into passport (id, pass_number) values (30005,'jay1234');

insert into student (id, name, passport_id) values (20001,'Satya', 30001);
insert into student (id, name, passport_id) values (20002,'Ambica',30002);
insert into student (id, name, passport_id) values (20003,'Tanuj',30003);
insert into student (id, name, passport_id) values (20004,'Uday',30004);
insert into student (id, name, passport_id) values (20005,'Jay',30005);


insert into review (id, rating, description, course_id) values (40001,'FIVE', 'excellent5 course',10001);
insert into review (id, rating, description, course_id) values (40002,'THREE', 'excellent4 course',10001);
insert into review (id, rating, description, course_id) values (40003,'FOUR', 'excellent3 course',10002);
insert into review (id, rating, description, course_id) values (40004,'TWO', 'excellent2 course',10003);
insert into review (id, rating, description, course_id) values (40005,'ONE', 'excellent1 course',10004);

insert into student_course (student_id, course_id) values(20001, 10001);
insert into student_course (student_id, course_id) values(20001, 10002);
insert into student_course (student_id, course_id) values(20002, 10003);
insert into student_course (student_id, course_id) values(20003, 10003);
insert into student_course (student_id, course_id) values(20004, 10002);


