set search_path to 'public';

drop table if exists EP_MorphEdit;
drop table if exists MorphDefinition;
drop table if exists charhistory;
drop table if exists expgains;
drop table if exists metachars;
drop table if exists games;

drop sequence if exists hibernate_sequence;

    create table EP_MorphEdit (
        id int8 not null,
        amount int4 not null,
        amountType varchar(255),
        multiplier float4 not null,
        targetKey varchar(255),
        targetSubKey1 varchar(255),
        targetSubKey2 varchar(255),
        targetSubKey3 varchar(255),
        value varchar(255),
        cause_id int8,
        morph_id int8,
        primary key (id)
    );

    create table EP_MorphDefinition (
        id int8 not null,
        description varchar(255),
        key varchar(255),
        type varchar(255),
        primary key (id)
    );

    create table games (
    	ruleset varchar(255) not null,
        id int8 not null,
        edition varchar(255),
        name varchar(255),
        setting varchar(255),
        stylesheet varchar(255),
        primary key (id)
    );

    create table metachars (
        id int8 not null,
        creationDate timestamp,
        name varchar(255),
        playerName varchar(255),
        game_id int8,
        primary key (id)
    );

    create table charhistory (
        id int8 not null,
        cause_id int8,
        editDate timestamp,
        amountType varchar(32),
        amount int4 not null,
        multiplier float(16) not null default 1,
        targetKey varchar(255),
        targetSubkey1 varchar(255),
        targetSubkey2 varchar(255),
        targetSubkey3 varchar(255),
        value varchar(255),
        character_id int8,
        primary key (id)
    );

    create table expgains (
        id int8 not null,
        amount int4 not null,
        gaindate timestamp,
        character_id int8,
        primary key (id)
    );

    alter table EP_MorphEdit 
        add constraint FK3851D87E9AE7FF46 
        foreign key (cause_id) 
        references EP_MorphEdit;

    alter table EP_MorphEdit 
        add constraint FK3851D87E6A5D7D50 
        foreign key (morph_id) 
        references EP_MorphDefinition;

    alter table metachars 
        add constraint FKC076BCF83A5077B4 
        foreign key (game_id) 
        references games;

    alter table charhistory 
        add constraint FK79BB047ECDEEB78A 
        foreign key (cause_id) 
        references charhistory;

    alter table charhistory 
        add constraint FK79BB047E5027529B 
        foreign key (character_id) 
        references metachars;

    alter table expgains 
        add constraint FK8CBA64D75027529B 
        foreign key (character_id) 
        references metachars;

    create sequence hibernate_sequence;
