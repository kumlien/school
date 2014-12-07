
create table User_roles (
    user_id int not null,
    role int not null,
    primary key (user_id, role)
);
