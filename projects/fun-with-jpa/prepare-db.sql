create schema ONE_TABLE;

create schema TABLE_PER_CLASS;

create schema JOINED;

create user APP_USER password 'APP_USER';

grant all on schema ONE_TABLE to APP_USER;

grant all on schema TABLE_PER_CLASS to APP_USER;

grant all on schema JOINED to APP_USER;

commit;
