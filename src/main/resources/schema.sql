create table User (
    id varchar(36) primary key,
    login varchar(255) not null unique,
    password varchar(255) not null,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    age INTEGER,
    gender varchar(20),
    interests varchar(255),
    city varchar(100),
    email varchar(255) not null unique
  );
