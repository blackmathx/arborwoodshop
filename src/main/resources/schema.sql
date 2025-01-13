


USE `arborwoodshop-dev`;

drop table if exists message;
drop table if exists listing_images;
drop table if exists listing;
drop table if exists users;

create table users (
    user_id int unsigned not null auto_increment,
    username varchar(255),
    email varchar(255),
    password varchar(255),
    enabled bit(1),
    seller_active bit(1),
    seller_active_date datetime,
    seller_expire_date datetime,
    created_at datetime,
    roles varchar(100),
    primary key (user_id)
);

create table listing (
    listing_id int unsigned not null auto_increment,
    user_id int unsigned not null,
    title varchar(100),
    description varchar(255),
    category varchar(255),
    price decimal(15,2),
    state varchar(255),
    city varchar(255),
    location varchar(255),
    zipcode varchar(255),
    phone varchar(255),
    email varchar(255),
    delivery_available bit(1),
    shipping_available bit(1),
    created_at datetime,
    updated_at datetime,
    primary key (listing_id),
    foreign key (user_id) references users(user_id)
);

create table listing_images (
    listing_images_id int unsigned not null auto_increment,
    listing_id int unsigned not null,
    image_one varchar(255),
    image_two varchar(255),
    image_three varchar(255),
    primary key (listing_images_id),
    foreign key (listing_id) references listing(listing_id)
);

create table message_user_xref (
    message_user_id int unsigned not null auto_increment,
    from_user_id int unsigned not null,
    to_user_id int unsigned not null,
    message_id int unsigned not null,
    listing_id int unsigned,
    primary key (message_user_id)
);

create table message (
    message_id int unsigned not null auto_increment,
    message varchar(255),
    record_status char(1),
    created_at datetime,
    primary key (message_id)
);
