alter table Users add column encrypted_pwd varchar(1024);
alter table Users add column first_name varchar(1024);
alter table Users add column last_name varchar(1024);
ALTER TABLE Users ADD UNIQUE (username);