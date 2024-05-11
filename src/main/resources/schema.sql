USE `arborwoodshop-dev`;

drop table if exists config_registry;
drop table if exists message;
drop table if exists listing;
drop table if exists users;

create table users (
    id int unsigned not null auto_increment,
    username varchar(255),
    email varchar(255),
    password varchar(255),
    enabled bit(1),
    seller_status bit(1),
    seller_active_date datetime,
    seller_expire_date datetime,
    created_date datetime,
    roles varchar(100),
    primary key (id)
);

create table listing (
    id int unsigned not null auto_increment,
    user_id int unsigned not null,
    title varchar(100),
    description varchar(255),
    price int,
    state varchar(255),
    city varchar(255),
    zipcode varchar(255),
    dimensions varchar(255),
    can_text bit(1),
    phone varchar(255),
    can_email bit(1),
    email varchar(255),
    delivery_available bit(1),
    created_date datetime,
    updated_date datetime,
    primary key (id),
    foreign key (user_id) references users(id)
);

create table message (
    id int unsigned not null auto_increment,
    message varchar(255),
    contact_email varchar(255),
    contact_phone varchar(255),
    user_id int unsigned not null,
    primary key (id),
    foreign key (user_id) references users(id)
);

create table config_registry (
    id int unsigned not null auto_increment,
    name varchar(40),
    description varchar(100),
    flag bit(1),
    value_one varchar(100),
    primary key (id)
);

