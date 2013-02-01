delete from charhistory;
delete from expgains;
delete from metachars;
delete from games;

insert into games (id, name, setting, edition) values (1, 'Eclipse Phase', 'Firewall', '1');
insert into games (id, name, setting, edition) values (2, 'Shadowrun', 'Madrid', '4');

insert into metachars (id, creationdate, name, playername, game_id) values (1, localtimestamp, 'Moko', 'Neyrick', 1);
insert into metachars (id, creationdate, name, playername, game_id) values (2, localtimestamp, 'Ocho', 'Bruno', 1);

insert into expgains (id, amount, dategain, character_id) values (1, 5, localtimestamp, 1);
insert into expgains (id, amount, dategain, character_id) values (2, 10, localtimestamp, 1);

insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (1, localtimestamp, 0, null, 0, 'FACTION', 'AUTONOMISTS', 1);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (2, localtimestamp, 0, null, 0, 'GENDER', 'MALE', 1);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (3, localtimestamp, 0, null, 0, 'FACTION', 'CORPORATIONS', 2);
