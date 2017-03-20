drop database if exists users;

create database users;
  use users;

  create table User(
    user_id integer AUTO_INCREMENT,
    username char(15) UNIQUE,
    email char(30) UNIQUE,
    password char(15) NOT NULL,
    primary key (user_id)
);


insert into User(username, email, password) values ('tome', 'xxx@youporn.com', '1234');
insert into User(username, email, password) values ('antonio', 'xxxa@youporn.com', '1234');
insert into User(username, email, password) values ('paulo', 'xxxp@youporn.com', '1234');
insert into User(username, email, password) values ('ricardo', 'xxxr@youporn.com', '1234');
insert into User(username, email, password) values ('luis', 'xxxl@youporn.com', '1234');
