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

insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (1, localtimestamp, 0, null, 0, 'FACTION', 'AUTONOMISTS', 1);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (2, localtimestamp, 0, null, 0, 'GENDER', 'MALE', 1);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (3, localtimestamp, 0, null, 0, 'FACTION', 'HYPERCORP', 2);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (4, localtimestamp, 0, null, 0, 'GENDER', 'MALE', 2);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (5, localtimestamp, 0, null, 0, 'BACKGROUND', 'EVACUEE', 2);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (6, localtimestamp, 0, '1', 0, 'MOTIVATION', 'SECURITY', 2);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (7, localtimestamp, 0, '2', 0, 'MOTIVATION', 'IMMORTALITY', 2);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (8, localtimestamp, 0, '3', 0, 'MOTIVATION', 'INFOMORPH_RIGHTS', 2);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (9, localtimestamp, 0, null, 15, 'APT_COG', null, 2);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (10, localtimestamp, 0, null, 10, 'APT_COO', null, 2);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (11, localtimestamp, 0, null, 15, 'APT_INT', null, 2);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (12, localtimestamp, 1, null, 10, 'APT_SOM', null, 2);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (13, localtimestamp, 1, null, 15, 'APT_WIL', null, 2);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (14, localtimestamp, 2, null, 10, 'APT_REF', null, 2);
insert into charhistory (id, editdate, expensetype, locator, spentamount, targetkey, value, character_id) values (15, localtimestamp, 2, null, 15, 'APT_SAV', null, 2);

