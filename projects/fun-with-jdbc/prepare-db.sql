create schema APP;

create user APP_USER password 'APP_USER';

grant all on schema APP to APP_USER;

use APP;

create table USERS (
  ID bigint auto_increment not null,
  ACTIVE boolean not null default true,
  LOGIN varchar not null,
  NAME varchar not null,
  DATE_OF_BIRTH date not null,
  BALANCE decimal(10, 2) not null default 0,
  PHOTO blob,
  LAST_LOGIN timestamp
);

insert into USERS (LOGIN, NAME, DATE_OF_BIRTH)
  values ('mufpuf', 'Muf Puf', current_timestamp - 5000);

insert into USERS (ACTIVE, LOGIN, NAME, DATE_OF_BIRTH, BALANCE)
  values (false, 'foobar', 'Foo Bar', '1984-02-29', 147.39);

commit;
