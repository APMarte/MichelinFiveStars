drop database if exists codecadets;

create database codecadets;
  use codecadets;

  create table User(
    user_id integer AUTO_INCREMENT,
    username char(15) UNIQUE,
    email char(30) UNIQUE,
    password char(15) NOT NULL,
    primary key (user_id)
);


insert into client(username, email, password) values ('Manuel', 'maneldascouves@youporn.com', '123abc');
insert into client(username, email, password) values ('Nuno', 'nunogostadecenoura@redtube.com', '456def');
