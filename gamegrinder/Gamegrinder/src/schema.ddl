
    create table gamegrinder.public.game (
        id int8 not null,
        gmname varchar(255),
        setting varchar(255),
        dayDate timestamp,
        locator int4,
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

    alter table gamegrinder.public.player_avail 
        add constraint FK4679611625F9EB9 
        foreign key (game_id) 
        references gamegrinder.public.game;
