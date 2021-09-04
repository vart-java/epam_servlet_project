INSERT INTO role (name) VALUES ('administrator'), ('master'), ('client'), ('guest');

INSERT INTO procedures (name, duration) VALUES ('hair_coloring', 10800000);
INSERT INTO procedures (name, duration) VALUES ('nail_staining', 7200000);
INSERT INTO procedures (name, duration) VALUES ('eyelash_extension', 7200000);
INSERT INTO procedures (name, duration) VALUES ('face_massage', 3600000);

INSERT INTO users (role_name, login, password) VALUES ('administrator','admin@google.com','8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918');
INSERT INTO users (role_name, login, password, rating, recall_count, specialization) VALUES ('master','master@google.com','FC613B4DFD6736A7BD268C8A0E74ED0D1C04A959F59DD74EF2874983FD443FC9', 10, 1, 'hair_coloring');
INSERT INTO users (role_name, login, password, rating, recall_count, specialization) VALUES ('master','master1@google.com','B07F3FC75999732D068489FAD851944E09AA103288130173555C0EF2A6C13DD6', 0, 0,  'hair_coloring');
INSERT INTO users (role_name, login, password, rating, recall_count, specialization) VALUES ('master','master2@google.com','EE33BACB9843D3B4BAC37E425B203D8AAD4E08C6773D21A10533D66CD6BB7C02', 10, 1, 'nail_staining');
INSERT INTO users (role_name, login, password, rating, recall_count, specialization) VALUES ('master','master3@google.com','A01086B50F9EABA6D93AC031EDBE965E5AF9642AEFD669DCFF954D950367C837', 6, 1,  'hair_coloring');
INSERT INTO users (role_name, login, password, rating, recall_count, specialization) VALUES ('master','master4@google.com','CC9FD30698B7AFF71EF1D1F65D9CEBF41393C642D130BDF6BCFD8EFFBD93D999', 9, 1, 'nail_staining');
INSERT INTO users (role_name, login, password, rating, recall_count, specialization) VALUES ('master','master5@google.com','5D15F0AABD7261EE4342BAC88C735A509DA132F1A733280A0DE9EA455A3AC689', 10, 1, 'eyelash_extension');
INSERT INTO users (role_name, login, password, rating, recall_count, specialization) VALUES ('master','master6@google.com','66CB32C99A4A03CE988C2C189DF6235E1882F1B3A8BA521B292A66B10A5FDE6C', 6, 1,  'eyelash_extension');
INSERT INTO users (role_name, login, password, rating, recall_count, specialization) VALUES ('master','master7@google.com','51BCB11A440720C170BEE0A263E8FD6FB7FA123484BAE0517395A1A1354C65EB', 3, 1, 'eyelash_extension');
INSERT INTO users (role_name, login, password, rating, recall_count, specialization) VALUES ('master','master8@google.com','7B7C19144FAAC29844E6B95B71B04EA3B8B91ABA65AA280B5B6ACF587FD3F3D4', 9, 1, 'face_massage');
INSERT INTO users (role_name, login, password, rating, recall_count, specialization) VALUES ('master','master9@google.com','C55C378F9A9DAD092771437931EB25C8A1B31CEAE4218530B312A3BAC5453D4C', 5, 1, 'face_massage');
INSERT INTO users (role_name, login, password, rating, recall_count, specialization) VALUES ('master','master10@google.com','C4F95807B968010270AAC9FDD25C90BFE4738E79686F00802D489E1827DFF2AC', 7, 1, 'face_massage');
INSERT INTO users (role_name, login, password) VALUES ('client','client@google.com','948FE603F61DC036B5C596DC09FE3CE3F3D30DC90F024C85F3C82DB2CCAB679D');
INSERT INTO users (role_name, login, password) VALUES ('guest','guest@google.com','84983C60F7DAADC1CB8698621F802C0D9F9A3C3C295C810748FB048115C186EC');


INSERT INTO appointments (procedure_name, master_login, client_login, start_time, is_confirmed, is_paid_up, is_finished, is_rated) VALUES ('hair_coloring', 'master@google.com', 'client@google.com', '2021-08-27 11:00:00', true, false, false, false);
INSERT INTO appointments (procedure_name, master_login, client_login, start_time, is_confirmed, is_paid_up, is_finished, is_rated) VALUES ('hair_coloring', 'master@google.com', 'client@google.com', '2021-08-28 12:00:00', true, false, false, false);
INSERT INTO appointments (procedure_name, master_login, client_login, start_time, is_confirmed, is_paid_up, is_finished, is_rated) VALUES ('hair_coloring', 'master@google.com', 'client@google.com', '2021-08-29 13:30:00', false, false, false, false);
INSERT INTO appointments (procedure_name, master_login, client_login, start_time, is_confirmed, is_paid_up, is_finished, is_rated) VALUES ('hair_coloring', 'master@google.com', 'client@google.com', '2021-08-30 14:30:00', false, false, false, false);
INSERT INTO appointments (procedure_name, master_login, client_login, start_time, is_confirmed, is_paid_up, is_finished, is_rated) VALUES ('hair_coloring', 'master@google.com', 'client@google.com', '2021-08-31 17:30:00', false, false, false, false);
INSERT INTO appointments (procedure_name, master_login, client_login, start_time, is_confirmed, is_paid_up, is_finished, is_rated) VALUES ('hair_coloring', 'master@google.com', 'client@google.com', '2021-08-26 11:00:00', false, false, false, false);
INSERT INTO appointments (procedure_name, master_login, client_login, start_time, is_confirmed, is_paid_up, is_finished, is_rated) VALUES ('hair_coloring', 'master@google.com', 'client@google.com', '2021-08-26 14:00:00', false, false, false, false);
INSERT INTO appointments (procedure_name, master_login, client_login, start_time, is_confirmed, is_paid_up, is_finished, is_rated) VALUES ('hair_coloring', 'master@google.com', 'client@google.com', '2021-08-26 18:00:00', false, false, false, false);

