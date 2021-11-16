-- begin CUBAREST_DATA_THEME
create table CUBAREST_DATA_THEME (
    ID varchar(36) not null,
    --
    IDENTIFIER varchar(255) not null,
    LAST_UPDATE timestamp,
    --
    primary key (ID)
)^
-- end CUBAREST_DATA_THEME
-- begin CUBAREST_UPDATE_UNIT
create table CUBAREST_UPDATE_UNIT (
    ID varchar(36) not null,
    --
    LAST_UPDATE timestamp not null,
    IDENTIFIER varchar(255) not null,
    DATA_THEME_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end CUBAREST_UPDATE_UNIT
