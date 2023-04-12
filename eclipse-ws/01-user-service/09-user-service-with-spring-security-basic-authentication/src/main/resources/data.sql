/* This script would be executed automatically only if in-memory database is configured. Otherwise it will not be executed. */

insert into user_details(id, birth_date, name) values(10001, current_date(), 'Deepak');
insert into user_details(id, birth_date, name) values(10002, current_date(), 'Ravi');
insert into user_details(id, birth_date, name) values(10003, current_date(), 'Ranga');

insert into post(id, description, user_id) values(20001, 'I want to learn AWS', 10001);
insert into post(id, description, user_id) values(20002, 'I want to learn DevOps', 10001);
insert into post(id, description, user_id) values(20003, 'I want to Get Cloud certified', 10002);
insert into post(id, description, user_id) values(20004, 'I want to learn Kubernetes', 10003);
