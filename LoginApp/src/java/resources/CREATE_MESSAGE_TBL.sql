create table message(
    id int not null generated always as
        identity(start with 1, increment by 1),
    message varchar(512),
    user_id int not null,
    constraint pk_message primary key(id),
    constraint fk_message foreign key(user_id) references usr(id)
)
