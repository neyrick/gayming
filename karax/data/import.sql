delete from charhistory;
delete from expgains;
delete from metachars;
delete from games;

insert into games (id, name, setting, edition, ruleset) values (1, 'Eclipse Phase', 'Firewall', '1', 'ECLIPSE_PHASE_1.0');
insert into games (id, name, setting, edition, ruleset) values (2, 'Shadowrun', 'Madrid', '4', 'ECLIPSE_PHASE_1.0');

insert into metachars (id, creationdate, name, playername, game_id) values (1, localtimestamp, 'Moko', 'Neyrick', 1);
insert into metachars (id, creationdate, name, playername, game_id) values (2, localtimestamp, 'Ocho', 'Bruno', 1);

insert into expgains (id, amount, gaindate, character_id) values (1, 5, localtimestamp, 1);
insert into expgains (id, amount, gaindate, character_id) values (2, 10, localtimestamp, 1);

insert into charhistory (id, source, editdate, expensetype, spentamount, targetkey, targetsubkey1, targetsubkey2, targetsubkey3, value, character_id) values
 (1, null, localtimestamp, 0, 0, 'FACTION', null, null, null, 'AUTONOMISTS', 1),
 (2, null, localtimestamp, 0, 0, 'GENDER', null, null, null, 'MALE', 1),
 (3, null, localtimestamp, 0, 0, 'FACTION', null, null, null, 'HYPERCORP', 2), 
 (4, null, localtimestamp, 0, 0, 'GENDER', null, null, null, 'MALE', 2),
 (5, null, localtimestamp, 0, 0, 'BACKGROUND', null, null, null, 'EVACUEE', 2),
 (6, null, localtimestamp, 0, 0, 'MOTIVATION', '1', null, null, 'SECURITY', 2),
 (7, null, localtimestamp, 0, 0, 'MOTIVATION', '2', null, null, 'IMMORTALITY', 2),
 (8, null, localtimestamp, 0, 0, 'MOTIVATION', '3', null, null, 'INFOMORPH_RIGHTS', 2),
 (9, null, localtimestamp, 0, 15, 'APT_COG', null, null, null, null, 2),
 (10, null, localtimestamp, 0, 10, 'APT_COO', null, null, null, null, 2),
 (11, null, localtimestamp, 0, 15, 'APT_INT', null, null, null, null, 2),
 (12, null, localtimestamp, 1, 10, 'APT_SOM', null, null, null, null, 2),
 (13, null, localtimestamp, 1, 15, 'APT_WIL', null, null, null, null, 2),
 (14, null, localtimestamp, 2, 10, 'APT_REF', null, null, null, null, 2),
 (15, null, localtimestamp, 2, 15, 'APT_SAV', null, null, null, null, 2);

 
