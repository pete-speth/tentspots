drop database if exists TentSpots;
create database TentSpots;
use TentSpots;

-- CREATE SCHEMA

create table Campsite (
	Id int auto_increment primary key,
    `Name` varchar(50) not null,
    LocationId int not null
    
);

create table Location (
	Id int auto_increment primary key,
    Latitude fixed(8,6) not null,
    Longitude fixed(9,6) not null,
    ParkId int null,
    StateAbbr char(2) not null
    
);

alter table Campsite
	add constraint fk_Campsite_Location
	foreign key (LocationId) references Location(Id);

create table Park (
	Id int auto_increment primary key,
    `Name` varchar(60) not null,
    GoverningOrg varchar(60) null
);

create table State (
	Abbr char(2) primary key,
    `Name` varchar(25) not null
);

alter table Location
	add constraint fk_Location_Park
    foreign key (ParkId) references Park(Id);
    
alter table Location
	add constraint fk_Location_State
    foreign key (StateAbbr) references State(Abbr);

create table Feature (
	Id int auto_increment primary key,
    `Name` varchar(50) not null
);

create table CampsiteFeature (
	CampsiteId int not null,
    FeatureId int not null
);

alter table CampsiteFeature
	add constraint fk_CampsiteFeature_Campsite
    foreign key (CampsiteId) references Campsite(Id);
    
alter table CampsiteFeature
	add constraint fk_CampsiteFeature_Feature
    foreign key (FeatureId) references Feature(Id);

create table Visit (
	Id int auto_increment primary key,
    GroupLeader varchar(60) not null,
    StartDate date not null,
    EndDate date not null,
    GroupSize int not null,
    CampsiteId int not null,
    Notes varchar (500) null
);

alter table Visit
	add constraint fk_Visit_Campsite
	foreign key (CampsiteId) references Campsite(Id);
    
create table Login (
	Username varchar(30) not null primary key,
    PasswordHash varchar(256) not null,
    Role varchar(30) not null
);