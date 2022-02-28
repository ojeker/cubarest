create table CUBAREST_UPDATE_UNIT (
    ID uuid,
    --
    LAST_UPDATE timestamp not null,
    IDENTIFIER varchar(255) not null,
    DATA_THEME_ID uuid not null,
    --
    primary key (ID)
);