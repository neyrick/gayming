
-- 
-- PostgreSQL database dump
-- 

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

-- 
-- Data for Name: game; Type: TABLE DATA; Schema: public; Owner: devel
-- 

INSERT INTO game VALUES (1, 'Neyrick', 'Eclipse Phase', '2013-01-26 00:00:00', 0);
INSERT INTO game VALUES (2, 'Neyrick', 'Ars Magica - Norvège', '2013-02-02 00:00:00', 0);
INSERT INTO game VALUES (3, 'Jerôme', 'Mage', '2013-02-03 00:00:00', 1);
INSERT INTO game VALUES (4, 'Bruno', 'Ars Magica - Normandie', '2013-02-09 00:00:00', 1);


-- 
-- Data for Name: player_avail; Type: TABLE DATA; Schema: public; Owner: devel
-- 

INSERT INTO player_avail VALUES (1, 'Simon', NULL, NULL, 1);
INSERT INTO player_avail VALUES (2, 'Bruno', NULL, NULL, 1);
INSERT INTO player_avail VALUES (3, 'Jérôme', NULL, NULL, 1);
INSERT INTO player_avail VALUES (4, 'Neyrick', NULL, NULL, 2);
INSERT INTO player_avail VALUES (5, 'Simon', NULL, NULL, 2);
INSERT INTO player_avail VALUES (6, 'Neyrick', NULL, NULL, 3);
INSERT INTO player_avail VALUES (7, 'Neyrick', NULL, NULL, 3);
INSERT INTO player_avail VALUES (8, 'Bruno', NULL, NULL, 3);
INSERT INTO player_avail VALUES (9, 'Simon', '2013-02-16 00:00:00', 1, 4);
INSERT INTO player_avail VALUES (10, 'Neyrick', '2013-02-16 00:00:00', 1, 4);


-- 
-- PostgreSQL database dump complete
-- 

