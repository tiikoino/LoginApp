create table usr(
    id int not null generated always as
        identity(start with 1, increment by 1),
    name varchar(512),
    mail varchar(512),
    constraint pk_usr primary key(id)
)
