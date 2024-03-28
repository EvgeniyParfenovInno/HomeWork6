create table user_limit (id bigserial primary key, user_id bigint unique not null, limitation double);

insert into user_limit (user_id, limitation) values (1, 10000), (2, 10000);