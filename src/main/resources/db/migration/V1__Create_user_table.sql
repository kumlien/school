
create table Users (
    id serial primary key,
    username varchar(100) not null unique,
    encrypted_pwd varchar(1024),
    salt varchar(1024),
	first_name varchar(1024),
	last_name varchar(1024),
	created_date timestamp,
	modified_date timestamp,
	email varchar(1024)
);
