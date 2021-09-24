insert into roles (name)
values ('ADMIN');

insert into roles (name)
values ('MASTER');

insert into roles (name)
values ('CLIENT');

insert into roles (name)
values ('GUEST');

insert into users (login, password, role_id, rating, recall_count)
values ('admin@mail.com', '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8', 1, 0.0, 0);

insert into users (login, password, role_id, rating, recall_count)
values ('client@mail.com', '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8', 3, 0.0, 0);

insert into users (login, password, role_id, rating, recall_count)
values ('master1@mail.com', '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8', 2, 9.0, 0);

insert into users (login, password, role_id, rating, recall_count)
values ('master2@mail.com', '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8', 2, 8.0, 0);

insert into users (login, password, role_id, rating, recall_count)
values ('master3@mail.com', '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8', 2, 9.0, 0);

insert into users (login, password, role_id, rating, recall_count)
values ('master4@mail.com', '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8', 2, 8.0, 0);

insert into users (login, password, role_id, rating, recall_count)
values ('master5@mail.com', '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8', 2, 9.0, 0);

insert into users (login, password, role_id, rating, recall_count)
values ('master6@mail.com', '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8', 2, 8.0, 0);

insert into users (login, password, role_id, rating, recall_count)
values ('master7@mail.com', '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8', 2, 9.0, 0);

insert into users (login, password, role_id, rating, recall_count)
values ('master8@mail.com', '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8', 2, 8.0, 0);

insert into users (login, password, role_id, rating, recall_count)
values ('master9@mail.com', '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8', 2, 8.0, 0);

insert into users (login, password, role_id, rating, recall_count)
values ('master10@mail.com', '5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8', 2, 8.0, 0);

insert into procedures(name, duration)
values ('hair_coloring', Interval '1 hour');

insert into procedures(name, duration)
values ('face_massage', Interval '1 hour');

insert into procedures(name, duration)
values ('eyelash_extension', Interval '30 min');

insert into procedures(name, duration)
values ('nail_staining', Interval '30 min');

insert into users_procedures(user_id, procedure_id)
values (3, 1);

insert into users_procedures(user_id, procedure_id)
values (4, 1);

insert into users_procedures(user_id, procedure_id)
values (5, 1);

insert into users_procedures(user_id, procedure_id)
values (6, 2);

insert into users_procedures(user_id, procedure_id)
values (7, 2);

insert into users_procedures(user_id, procedure_id)
values (8, 2);

insert into users_procedures(user_id, procedure_id)
values (9, 3);

insert into users_procedures(user_id, procedure_id)
values (10, 3);

insert into users_procedures(user_id, procedure_id)
values (11, 3);

insert into users_procedures(user_id, procedure_id)
values (12, 4);

insert into appointments(procedure_id, master_id, client_id, start_time, status)
values (1, 3, 2, '2021-09-21 11:00:00', 'DECLARED');

insert into appointments(procedure_id, master_id, client_id, start_time, status)
values (1, 4, 2, '2021-09-21 13:00:00', 'CONFIRMED');

insert into appointments(procedure_id, master_id, client_id, start_time, status)
values (1, 3, 2, '2021-09-21 15:00:00', 'FINISHED');

insert into appointments(procedure_id, master_id, client_id, start_time, status)
values (1, 3, 2, '2021-09-22 13:00:00', 'CONFIRMED');

insert into appointments(procedure_id, master_id, client_id, start_time, status)
values (1, 4, 2, '2021-09-22 15:00:00', 'RATED');


