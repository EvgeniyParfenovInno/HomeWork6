create table user_limit (
    id bigserial primary key,
    user_id bigint unique not null,
    limitation double);

create table payment(
    id bigserial primary key,
    user_id bigint not null,
    payment_id varchar(64) unique not null,
    amount double not null,
    status int not null);
