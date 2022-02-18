drop table if exists Person;
drop table if exists User;
drop table if exists Event;
drop table if exists Authtoken;

create table User (
    username varchar(255) not null primary key,
    password varchar(255) not null,
    email varchar(255) not null,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    gender char(1) not null,
    personID varchar(255) not null,
    foreign key(personID) references Person(personID),
    check(gender == 'f' OR gender == 'm')
);

create table Person (
    personID varchar(255) not null primary key,
    associatedUsername varchar(255) not null,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    fatherID varchar(255),
    motherID varchar(255),
    spouseID varchar(255),
    foreign key(associatedUsername) references User(username)
    foreign key(fatherID) references Person(personID),
    foreign key(motherID) references Person(personID),
    foreign key(spouseID) references Person(personID)
);

create table Event(
    eventID varchar(255) not null primary key,
    associatedUsername varchar(255) not null,
    personID varchar(255) not null,
    latitude integer not null,
    longitude integer not null,
    country varchar(255) not null,
    city varchar(255) not null,
    eventType varchar(255) not null,
    year integer not null,
    foreign key(associatedUsername) references User(username),
    foreign key(personID) references Person(personID)
);

create table Authtoken (
    authtoken varchar(255) not null primary key,
    username varchar(255) not null,
    foreign key(username) references User(username)
);