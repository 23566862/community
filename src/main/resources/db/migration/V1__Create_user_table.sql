create table user
(
    id  int     auto_increment primary key,
    accountid   varchar(30)  ,
    name        varchar(30)  ,
    token       varchar(100),
    gmtcreate   bigint,
    gmtmodified bigint
);