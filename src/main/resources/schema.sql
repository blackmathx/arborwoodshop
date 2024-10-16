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
    created_date datetime,
    roles varchar(100),
    primary key (user_id)
);

create table listing (
    listing_id int unsigned not null auto_increment,
    user_id int unsigned not null,
    title varchar(100),
    description varchar(255),
    price decimal(15,2),
    state varchar(255),
    city varchar(255),
    location varchar(255),
    zipcode varchar(255),
    phone varchar(255),
    email varchar(255),
    delivery_available bit(1),
    shipping_available bit(1),
    created_date datetime,
    updated_date datetime,
    primary key (listing_id),
    foreign key (user_id) references users(id)
);

create table message (
    message_id int unsigned not null auto_increment,
    user_id int unsigned not null,
    contact_email varchar(255),
    contact_phone varchar(255),
    message varchar(255),
    primary key (message_id),
    foreign key (user_id) references users(id)
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



-- changed all id fields to <table>_id
-- added listing_images table
-- rebuilt messages, listing_images from sql
-- location: removed can_email, can_text, dimensions
-- location: added location varchar(255)
-- alter table `arborwoodshop-dev`.listing modify price decimal(15,2);
-- users table. seller_status to seller_active