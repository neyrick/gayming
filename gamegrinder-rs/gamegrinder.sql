--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: role_type; Type: TYPE; Schema: public; Owner: devel
--

CREATE TYPE role_type AS ENUM (
    'GM',
    'PLAYER'
);


ALTER TYPE public.role_type OWNER TO devel;

--
-- Name: tf_type; Type: TYPE; Schema: public; Owner: devel
--

CREATE TYPE tf_type AS ENUM (
    'AFTERNOON',
    'EVENING'
);


ALTER TYPE public.tf_type OWNER TO devel;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: comment; Type: TABLE; Schema: public; Owner: devel; Tablespace: 
--

CREATE TABLE comment (
    id integer NOT NULL,
    player character varying NOT NULL,
    message character varying NOT NULL,
    dayid integer NOT NULL,
    setting integer NOT NULL,
    timeframe tf_type NOT NULL
);


ALTER TABLE public.comment OWNER TO devel;

--
-- Name: comment_id_seq; Type: SEQUENCE; Schema: public; Owner: devel
--

CREATE SEQUENCE comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comment_id_seq OWNER TO devel;

--
-- Name: comment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: devel
--

ALTER SEQUENCE comment_id_seq OWNED BY comment.id;


--
-- Name: game; Type: TABLE; Schema: public; Owner: devel; Tablespace: 
--

CREATE TABLE game (
    id integer NOT NULL,
    masterschedule integer NOT NULL
);


ALTER TABLE public.game OWNER TO devel;

--
-- Name: game_id_seq; Type: SEQUENCE; Schema: public; Owner: devel
--

CREATE SEQUENCE game_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.game_id_seq OWNER TO devel;

--
-- Name: game_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: devel
--

ALTER SEQUENCE game_id_seq OWNED BY game.id;


--
-- Name: schedule; Type: TABLE; Schema: public; Owner: devel; Tablespace: 
--

CREATE TABLE schedule (
    id integer NOT NULL,
    dayid integer NOT NULL,
    setting integer NOT NULL,
    game integer,
    player character varying NOT NULL,
    timeframe tf_type NOT NULL,
    role role_type NOT NULL
);


ALTER TABLE public.schedule OWNER TO devel;

--
-- Name: schedule_id_seq; Type: SEQUENCE; Schema: public; Owner: devel
--

CREATE SEQUENCE schedule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.schedule_id_seq OWNER TO devel;

--
-- Name: schedule_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: devel
--

ALTER SEQUENCE schedule_id_seq OWNED BY schedule.id;


--
-- Name: setting; Type: TABLE; Schema: public; Owner: devel; Tablespace: 
--

CREATE TABLE setting (
    id integer NOT NULL,
    code character varying(3) NOT NULL,
    name character varying NOT NULL,
    creationdate timestamp without time zone DEFAULT now() NOT NULL,
    mode smallint NOT NULL,
    status smallint DEFAULT 0 NOT NULL
);


ALTER TABLE public.setting OWNER TO devel;

--
-- Name: setting_id_seq; Type: SEQUENCE; Schema: public; Owner: devel
--

CREATE SEQUENCE setting_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.setting_id_seq OWNER TO devel;

--
-- Name: setting_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: devel
--

ALTER SEQUENCE setting_id_seq OWNED BY setting.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: devel
--

ALTER TABLE ONLY comment ALTER COLUMN id SET DEFAULT nextval('comment_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: devel
--

ALTER TABLE ONLY game ALTER COLUMN id SET DEFAULT nextval('game_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: devel
--

ALTER TABLE ONLY schedule ALTER COLUMN id SET DEFAULT nextval('schedule_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: devel
--

ALTER TABLE ONLY setting ALTER COLUMN id SET DEFAULT nextval('setting_id_seq'::regclass);


--
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: devel
--

COPY comment (id, player, message, dayid, setting, timeframe) FROM stdin;
\.


--
-- Name: comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: devel
--

SELECT pg_catalog.setval('comment_id_seq', 1, false);


--
-- Data for Name: game; Type: TABLE DATA; Schema: public; Owner: devel
--

COPY game (id, masterschedule) FROM stdin;
\.


--
-- Name: game_id_seq; Type: SEQUENCE SET; Schema: public; Owner: devel
--

SELECT pg_catalog.setval('game_id_seq', 4, true);


--
-- Data for Name: schedule; Type: TABLE DATA; Schema: public; Owner: devel
--

COPY schedule (id, dayid, setting, game, player, timeframe, role) FROM stdin;
43	20140131	1	\N	MJD1	AFTERNOON	GM
45	20140131	2	\N	MJD1	AFTERNOON	GM
46	20140131	3	\N	MJD1	AFTERNOON	GM
\.


--
-- Name: schedule_id_seq; Type: SEQUENCE SET; Schema: public; Owner: devel
--

SELECT pg_catalog.setval('schedule_id_seq', 46, true);


--
-- Data for Name: setting; Type: TABLE DATA; Schema: public; Owner: devel
--

COPY setting (id, code, name, creationdate, mode, status) FROM stdin;
1	EP	Eclipse Phase	2014-01-25 14:15:39.371732	0	0
3	COP	COPS - Ombres & Lumières	2014-01-25 15:40:10.125482	0	0
4	TB	Terres Balafreés	2014-01-25 15:40:10.14555	0	0
5	INS	INS - Rémy	2014-01-25 15:40:10.154438	0	0
6	TFC	Trône de Fer - Conflans	2014-01-25 15:40:10.162259	0	0
7	TFG	Trône de Fer - Garde de Nuit	2014-01-25 15:40:10.169177	0	0
8	PLC	Projet Pélican	2014-01-25 15:40:10.179301	0	0
9	SRS	Shadowrun - Seattle	2014-01-25 15:40:10.188572	0	0
10	SRB	Shadowrun - Bretagne	2014-01-25 15:40:10.195393	0	0
12	SW	Star Wars - Ordre Jedi	2014-01-25 15:40:10.210223	0	0
13	AM	Ars Magica - La hottée du Diable	2014-01-25 15:52:04.730348	1	0
15	NM	Neuromancer	2014-01-25 15:52:04.762494	1	0
16	PFH	Pathfinder - Hero or not Hero	2014-01-25 15:52:04.769873	1	0
17	PFA	Pathfinder - Chroniques d'Alseta	2014-01-25 15:52:04.777814	1	0
18	7SC	7th Sea - Commedia dell'arte	2014-01-25 15:52:04.785924	1	0
19	7SD	7th Sea - Dix ans avant	2014-01-25 15:52:04.806457	1	0
2	7SP	7th Sea - Pirates!	2014-01-25 14:16:22.134889	0	0
14	DL	Deadlands - Soupe aux Pruneaux	2014-01-25 15:52:04.754837	1	0
11	SRQ	Shadowrun - Québec	2014-01-25 15:40:10.202928	0	0
\.


--
-- Name: setting_id_seq; Type: SEQUENCE SET; Schema: public; Owner: devel
--

SELECT pg_catalog.setval('setting_id_seq', 19, true);


--
-- Name: comment_pk; Type: CONSTRAINT; Schema: public; Owner: devel; Tablespace: 
--

ALTER TABLE ONLY comment
    ADD CONSTRAINT comment_pk PRIMARY KEY (id);


--
-- Name: game_pk; Type: CONSTRAINT; Schema: public; Owner: devel; Tablespace: 
--

ALTER TABLE ONLY game
    ADD CONSTRAINT game_pk PRIMARY KEY (id);


--
-- Name: schedule_pk; Type: CONSTRAINT; Schema: public; Owner: devel; Tablespace: 
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_pk PRIMARY KEY (id);


--
-- Name: setting_pk; Type: CONSTRAINT; Schema: public; Owner: devel; Tablespace: 
--

ALTER TABLE ONLY setting
    ADD CONSTRAINT setting_pk PRIMARY KEY (id);


--
-- Name: comment_dayid_idx; Type: INDEX; Schema: public; Owner: devel; Tablespace: 
--

CREATE INDEX comment_dayid_idx ON comment USING btree (dayid, setting, timeframe);


--
-- Name: comment_player_idx; Type: INDEX; Schema: public; Owner: devel; Tablespace: 
--

CREATE INDEX comment_player_idx ON comment USING btree (player);


--
-- Name: schedule_player_idx; Type: INDEX; Schema: public; Owner: devel; Tablespace: 
--

CREATE INDEX schedule_player_idx ON schedule USING btree (player);


--
-- Name: schedule_setting_idx; Type: INDEX; Schema: public; Owner: devel; Tablespace: 
--

CREATE INDEX schedule_setting_idx ON schedule USING btree (setting);


--
-- Name: schedule_timeframe_idx; Type: INDEX; Schema: public; Owner: devel; Tablespace: 
--

CREATE INDEX schedule_timeframe_idx ON schedule USING btree (timeframe, dayid, setting);


--
-- Name: comment_setting_fk; Type: FK CONSTRAINT; Schema: public; Owner: devel
--

ALTER TABLE ONLY comment
    ADD CONSTRAINT comment_setting_fk FOREIGN KEY (setting) REFERENCES setting(id) ON DELETE CASCADE;


--
-- Name: game_schedule_fk; Type: FK CONSTRAINT; Schema: public; Owner: devel
--

ALTER TABLE ONLY game
    ADD CONSTRAINT game_schedule_fk FOREIGN KEY (masterschedule) REFERENCES schedule(id) ON DELETE CASCADE;


--
-- Name: schedule_game_fk; Type: FK CONSTRAINT; Schema: public; Owner: devel
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_game_fk FOREIGN KEY (game) REFERENCES game(id) ON DELETE SET NULL;


--
-- Name: schedule_setting_fk; Type: FK CONSTRAINT; Schema: public; Owner: devel
--

ALTER TABLE ONLY schedule
    ADD CONSTRAINT schedule_setting_fk FOREIGN KEY (setting) REFERENCES setting(id) ON DELETE CASCADE;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

