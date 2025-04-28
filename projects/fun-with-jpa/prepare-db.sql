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
  LAST_LOGIN timestamp,
  VERSION int not null default 0
);

alter table USERS add constraint USER_PK
  primary key (ID);


create table ITEM (
  ID bigint auto_increment not null,
  SELLER_ID bigint not null,
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


create table DEPOSIT_WITHDRAWAL (
  ID bigint auto_increment not null,
  USER_ID bigint not null,
  TRANSACTION_DATE timestamp,
  ACCOUNT_PREFIX varchar,
  ACCOUNT_CORE varchar not null,
  BANK_CODE varchar not null,
  AMOUNT decimal(10, 2) not null
);

alter table DEPOSIT_WITHDRAWAL add constraint DEPOSIT_WITHDRAWAL_PK
  primary key (ID);

alter table DEPOSIT_WITHDRAWAL add constraint DEPOSIT_WITHDRAWAL_FK_USER
  foreign key (USER_ID) REFERENCES USERS(ID);


create table USER_ITEM_LIKE (
  USER_ID bigint not null,
  ITEM_ID bigint not null
);

alter table USER_ITEM_LIKE add constraint USER_ITEM_LIKE_PK
  primary key (USER_ID, ITEM_ID);

alter table USER_ITEM_LIKE add constraint USER_ITEM_LIKE_FK_USER
  foreign key (USER_ID) REFERENCES USERS(ID);

alter table USER_ITEM_LIKE add constraint USER_ITEM_LIKE_FK_ITEM
  foreign key (ITEM_ID) REFERENCES ITEM(ID);


insert into USERS (TYPE, LOGIN, NAME, DATE_OF_BIRTH)
  values ('ADMIN', 'pufm', 'Muf Puf', current_timestamp - 5000);

insert into USERS (TYPE, LOGIN, NAME, DATE_OF_BIRTH)
  values ('MODERATOR', 'kosteja', 'Antonin Kostej', '1988-02-29');

insert into USERS (ACTIVE, LOGIN, NAME, DATE_OF_BIRTH, BALANCE)
  values (false, 'barf', 'Foo Bar', '1984-02-29', 147.39);

insert into USERS (ACTIVE, LOGIN, NAME, DATE_OF_BIRTH, BALANCE)
  values (true, 'potterh', 'Harry Potter', '1980-07-31', 2500);


insert into ITEM (SELLER_ID, STATE, TITLE, DESCRIPTION, PRICE)
  values (3, 'SOLD', 'Skripta Vývoj klient/server aplikací v Javě', 'ISBN 8024507919. Jako nová, ani jsem je neotevřel.', 59);

insert into ITEM (SELLER_ID, STATE, TITLE, DESCRIPTION, PRICE)
  values (4, 'AVAILABLE', 'Notebook Lenovo T540p', '2 roky používaný. V dobrém stavu. Váží asi 50 tun.', 10000);

insert into ITEM (SELLER_ID, STATE, TITLE, DESCRIPTION, PRICE)
  values (4, 'AVAILABLE', 'Nástěnná police IKEA Lack', 'Rozměry 110 x 26 cm. Tloušťka 5 cm. Barva bříza.', 125);

insert into ITEM (SELLER_ID, STATE, TITLE, DESCRIPTION, PRICE)
  values (4, 'AVAILABLE', 'Zářivka T4', 'Patice G5. Délka 327mm. Nová, nepoužitá. Zdarma, jen za odnos (koupil jsem ji omylem).', 0);


insert into DEPOSIT_WITHDRAWAL (USER_ID, TRANSACTION_DATE, ACCOUNT_PREFIX, ACCOUNT_CORE, BANK_CODE, AMOUNT)
  values (2, current_timestamp - 15, '1234', '0123456789', '0100', 500);

insert into DEPOSIT_WITHDRAWAL (USER_ID, TRANSACTION_DATE, ACCOUNT_PREFIX, ACCOUNT_CORE, BANK_CODE, AMOUNT)
  values (2, current_timestamp - 14, '1234', '0123456789', '0100', -200);

insert into DEPOSIT_WITHDRAWAL (USER_ID, TRANSACTION_DATE, ACCOUNT_CORE, BANK_CODE, AMOUNT)
  values (4, current_timestamp - 30, '1122334455', '5500', 1200);

insert into DEPOSIT_WITHDRAWAL (USER_ID, TRANSACTION_DATE, ACCOUNT_CORE, BANK_CODE, AMOUNT)
  values (4, current_timestamp - 29, '1122334455', '5500', 850);

insert into DEPOSIT_WITHDRAWAL (USER_ID, TRANSACTION_DATE, ACCOUNT_CORE, BANK_CODE, AMOUNT)
  values (4, current_timestamp - 25, '5566778899', '2010', -400);


commit;
