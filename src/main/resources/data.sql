insert into role(role_name) values('ROLE_ADMIN') ON CONFLICT DO NOTHING;
insert into role(role_name) values('ROLE_USER') ON CONFLICT DO NOTHING;

insert into account(username,email,password) values ('gshahrza','gshahrza@gmail.com','$2a$12$uXuElswXFXgKXEwQbt5IjehzubfWgMTIHdfjCasBxVCyRPN611U7K') ON CONFLICT DO NOTHING;
insert into account_role(account_id,role_id) values(1,1) ON CONFLICT DO NOTHING;