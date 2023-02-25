create database cartdb;

use cartdb;

CREATE TABLE product (
    id int not null AUTO_INCREMENT,
    name varchar(255) not null,
    description text,
    price float not null default 1,
    created_at timestamp,
    PRIMARY KEY (id)
);

select * from product; -- Use this line to check record

insert into product (name, price) values ("cabbage", 1.0);

-- Update a default value to created_at 
alter table product modify created_at timestamp default current_timestamp;