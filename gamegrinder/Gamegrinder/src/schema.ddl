
    create table gamegrinder.public.Setting (
        id int8 not null,
        color varchar(255),
        name varchar(255),
        open bool not null,
        primary key (id)
    );

    create table gamegrinder.public.game (
        id int8 not null,
        gmname varchar(255),
        dayDate timestamp,
        locator int4,
        setting_id int8,
        primary key (id)
    );

    create table gamegrinder.public.notes (
        id int8 not null,
        author varchar(255),
        postDate timestamp,
        text varchar(255),
        primary key (id)
    );

    create table gamegrinder.public.player_avail (
        id int8 not null,
        playerName varchar(255),
        dayDate timestamp,
        locator int4,
        game_id int8,
        primary key (id)
    );

    alter table gamegrinder.public.game 
        add constraint FK304BF21DF3691B 
        foreign key (setting_id) 
        references gamegrinder.public.Setting;

    alter table gamegrinder.public.player_avail 
        add constraint FK4679611625F9EB9 
        foreign key (game_id) 
        references gamegrinder.public.game;
