create schema APP;

create user APP_USER password 'APP_USER';

grant all on schema APP to APP_USER;

use APP;


create table USERS (
  ID bigint auto_increment not null,
  ACTIVE boolean not null default true,
  TYPE varchar not null default 'USER',
  LOGIN varchar not null,
  NAME varchar not null,
  DATE_OF_BIRTH date not null,
  BALANCE decimal(10, 2) not null default 0,
  PHOTO blob,
  LAST_LOGIN timestamp
);

alter table USERS add constraint USER_PK
  primary key (ID);


create table ITEM (
  ID bigint auto_increment not null,
  SELLER_ID bigint,
  STATE varchar not null,
  TITLE varchar not null,
  DESCRIPTION varchar,
  PRICE decimal(10, 2) not null,
  PHOTO blob
);

alter table ITEM add constraint ITEM_PK
  primary key (ID);

alter table ITEM add constraint ITEM_FK_SELLER
  foreign key (SELLER_ID) REFERENCES USERS(ID);


insert into USERS (TYPE, LOGIN, NAME, DATE_OF_BIRTH)
  values ('ADMIN', 'mufpuf', 'Muf Puf', current_timestamp - 5000);

insert into USERS (TYPE, LOGIN, NAME, DATE_OF_BIRTH)
  values ('MODERATOR', 'kosteja', 'Antonin Kostej', '1988-02-29');

insert into USERS (ACTIVE, LOGIN, NAME, DATE_OF_BIRTH, BALANCE)
  values (false, 'foobar', 'Foo Bar', '1984-02-29', 147.39);

insert into USERS (ACTIVE, LOGIN, NAME, DATE_OF_BIRTH, BALANCE)
  values (true, 'potterh', 'Harry Potter', '1980-07-31', 2500);


insert into ITEM (SELLER_ID, STATE, TITLE, DESCRIPTION, PRICE)
  values (3, 'SOLD', 'Skripta Vývoj klient/server aplikací v Javě', 'ISBN 8024507919. Jako nová, ani jsem je neotevřel.', 59);

insert into ITEM (SELLER_ID, STATE, TITLE, DESCRIPTION, PRICE)
  values (4, 'AVAILABLE', 'Notebook Lenovo T540p', '2 roky používaný. V dobrém stavu. Váží asi 50 tun.', 10000);

insert into ITEM (SELLER_ID, STATE, TITLE, DESCRIPTION, PRICE)
  values (4, 'AVAILABLE', 'Nástěnná police IKEA Lack', 'Rozměry 110 x 26 cm. Tloušťka 5 cm. Barva bříza.', 125);

insert into ITEM (SELLER_ID, STATE, TITLE, DESCRIPTION, PRICE)
  values (4, 'AVAILABLE', 'Zářivka T4', 'Patice G5. Délka 327mm. Nová, nepoužitá. Zdarma, jen za odnos.', 0);


commit;
