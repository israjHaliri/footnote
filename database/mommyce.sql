--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 10.0

-- Started on 2017-12-17 17:24:48 WIB

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 16428)
-- Name: barber; Type: SCHEMA; Schema: -; Owner: mommyce
--

CREATE SCHEMA barber;


ALTER SCHEMA barber OWNER TO mommyce;

--
-- TOC entry 8 (class 2615 OID 24798)
-- Name: config; Type: SCHEMA; Schema: -; Owner: mommyce
--

CREATE SCHEMA config;


ALTER SCHEMA config OWNER TO mommyce;

--
-- TOC entry 1 (class 3079 OID 12655)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2481 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = barber, pg_catalog;

SET default_tablespace = mommyce;

SET default_with_oids = false;

--
-- TOC entry 192 (class 1259 OID 16552)
-- Name: attachment; Type: TABLE; Schema: barber; Owner: mommyce; Tablespace: mommyce
--

CREATE TABLE attachment (
    id_attachment bigint NOT NULL,
    content_id integer NOT NULL,
    file text,
    type character varying
);


ALTER TABLE attachment OWNER TO mommyce;

--
-- TOC entry 189 (class 1259 OID 16525)
-- Name: content; Type: TABLE; Schema: barber; Owner: mommyce; Tablespace: mommyce
--

CREATE TABLE content (
    title character varying(100),
    description text,
    create_date date,
    update_date date,
    create_by character varying(100),
    update_by character varying(100),
    type character varying(10),
    price numeric,
    id_content bigint NOT NULL
);


ALTER TABLE content OWNER TO mommyce;

--
-- TOC entry 201 (class 1259 OID 24942)
-- Name: content_id_content_seq; Type: SEQUENCE; Schema: barber; Owner: mommyce
--

CREATE SEQUENCE content_id_content_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE content_id_content_seq OWNER TO mommyce;

--
-- TOC entry 2482 (class 0 OID 0)
-- Dependencies: 201
-- Name: content_id_content_seq; Type: SEQUENCE OWNED BY; Schema: barber; Owner: mommyce
--

ALTER SEQUENCE content_id_content_seq OWNED BY content.id_content;


--
-- TOC entry 186 (class 1259 OID 16433)
-- Name: guest_book; Type: TABLE; Schema: barber; Owner: mommyce; Tablespace: mommyce
--

CREATE TABLE guest_book (
    username character varying(100),
    create_date date,
    id_guest_book integer NOT NULL
);


ALTER TABLE guest_book OWNER TO mommyce;

--
-- TOC entry 193 (class 1259 OID 16564)
-- Name: guest_book_id_guest_book_seq; Type: SEQUENCE; Schema: barber; Owner: mommyce
--

CREATE SEQUENCE guest_book_id_guest_book_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE guest_book_id_guest_book_seq OWNER TO mommyce;

--
-- TOC entry 2483 (class 0 OID 0)
-- Dependencies: 193
-- Name: guest_book_id_guest_book_seq; Type: SEQUENCE OWNED BY; Schema: barber; Owner: mommyce
--

ALTER SEQUENCE guest_book_id_guest_book_seq OWNED BY guest_book.id_guest_book;


--
-- TOC entry 190 (class 1259 OID 16544)
-- Name: images_id_image_seq; Type: SEQUENCE; Schema: barber; Owner: mommyce
--

CREATE SEQUENCE images_id_image_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE images_id_image_seq OWNER TO mommyce;

--
-- TOC entry 2484 (class 0 OID 0)
-- Dependencies: 190
-- Name: images_id_image_seq; Type: SEQUENCE OWNED BY; Schema: barber; Owner: mommyce
--

ALTER SEQUENCE images_id_image_seq OWNED BY attachment.id_attachment;


--
-- TOC entry 191 (class 1259 OID 16548)
-- Name: images_news_and_article_di_seq; Type: SEQUENCE; Schema: barber; Owner: mommyce
--

CREATE SEQUENCE images_news_and_article_di_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE images_news_and_article_di_seq OWNER TO mommyce;

--
-- TOC entry 2485 (class 0 OID 0)
-- Dependencies: 191
-- Name: images_news_and_article_di_seq; Type: SEQUENCE OWNED BY; Schema: barber; Owner: mommyce
--

ALTER SEQUENCE images_news_and_article_di_seq OWNED BY attachment.content_id;


--
-- TOC entry 187 (class 1259 OID 16484)
-- Name: profile; Type: TABLE; Schema: barber; Owner: mommyce; Tablespace: mommyce
--

CREATE TABLE profile (
    address text,
    email character varying(50),
    create_date timestamp(4) without time zone NOT NULL,
    update_date timestamp(4) without time zone,
    phone character varying(15),
    lat character varying(100),
    lon character varying(100)
);


ALTER TABLE profile OWNER TO mommyce;

--
-- TOC entry 188 (class 1259 OID 16492)
-- Name: testimonial; Type: TABLE; Schema: barber; Owner: mommyce; Tablespace: mommyce
--

CREATE TABLE testimonial (
    subject character varying(100),
    description text,
    age integer,
    create_date date,
    update_date date,
    id_testimonial bigint NOT NULL
);


ALTER TABLE testimonial OWNER TO mommyce;

--
-- TOC entry 200 (class 1259 OID 24929)
-- Name: testimonial_id_testimonial_seq; Type: SEQUENCE; Schema: barber; Owner: mommyce
--

CREATE SEQUENCE testimonial_id_testimonial_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE testimonial_id_testimonial_seq OWNER TO mommyce;

--
-- TOC entry 2486 (class 0 OID 0)
-- Dependencies: 200
-- Name: testimonial_id_testimonial_seq; Type: SEQUENCE OWNED BY; Schema: barber; Owner: mommyce
--

ALTER SEQUENCE testimonial_id_testimonial_seq OWNED BY testimonial.id_testimonial;


SET search_path = config, pg_catalog;

--
-- TOC entry 196 (class 1259 OID 24825)
-- Name: role; Type: TABLE; Schema: config; Owner: mommyce; Tablespace: mommyce
--

CREATE TABLE role (
    id integer NOT NULL,
    role character varying(50)
);


ALTER TABLE role OWNER TO mommyce;

--
-- TOC entry 195 (class 1259 OID 24821)
-- Name: role_id_seq; Type: SEQUENCE; Schema: config; Owner: mommyce
--

CREATE SEQUENCE role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE role_id_seq OWNER TO mommyce;

--
-- TOC entry 2487 (class 0 OID 0)
-- Dependencies: 195
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: config; Owner: mommyce
--

ALTER SEQUENCE role_id_seq OWNED BY role.id;


--
-- TOC entry 199 (class 1259 OID 24908)
-- Name: user_roles; Type: TABLE; Schema: config; Owner: mommyce; Tablespace: mommyce
--

CREATE TABLE user_roles (
    id bigint NOT NULL,
    role_id bigint NOT NULL,
    user_id character varying(100)
);


ALTER TABLE user_roles OWNER TO mommyce;

--
-- TOC entry 197 (class 1259 OID 24902)
-- Name: user_roles_id_seq; Type: SEQUENCE; Schema: config; Owner: mommyce
--

CREATE SEQUENCE user_roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_roles_id_seq OWNER TO mommyce;

--
-- TOC entry 2488 (class 0 OID 0)
-- Dependencies: 197
-- Name: user_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: config; Owner: mommyce
--

ALTER SEQUENCE user_roles_id_seq OWNED BY user_roles.id;


--
-- TOC entry 198 (class 1259 OID 24906)
-- Name: user_roles_role_id_seq; Type: SEQUENCE; Schema: config; Owner: mommyce
--

CREATE SEQUENCE user_roles_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_roles_role_id_seq OWNER TO mommyce;

--
-- TOC entry 2489 (class 0 OID 0)
-- Dependencies: 198
-- Name: user_roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: config; Owner: mommyce
--

ALTER SEQUENCE user_roles_role_id_seq OWNED BY user_roles.role_id;


--
-- TOC entry 194 (class 1259 OID 24804)
-- Name: users; Type: TABLE; Schema: config; Owner: mommyce; Tablespace: mommyce
--

CREATE TABLE users (
    id character varying(100) NOT NULL,
    password character varying(150),
    enable boolean,
    token text,
    username character varying(100)
);


ALTER TABLE users OWNER TO mommyce;

SET search_path = barber, pg_catalog;

--
-- TOC entry 2318 (class 2604 OID 16555)
-- Name: attachment id_attachment; Type: DEFAULT; Schema: barber; Owner: mommyce
--

ALTER TABLE ONLY attachment ALTER COLUMN id_attachment SET DEFAULT nextval('images_id_image_seq'::regclass);


--
-- TOC entry 2319 (class 2604 OID 16578)
-- Name: attachment content_id; Type: DEFAULT; Schema: barber; Owner: mommyce
--

ALTER TABLE ONLY attachment ALTER COLUMN content_id SET DEFAULT nextval('images_news_and_article_di_seq'::regclass);


--
-- TOC entry 2317 (class 2604 OID 24944)
-- Name: content id_content; Type: DEFAULT; Schema: barber; Owner: mommyce
--

ALTER TABLE ONLY content ALTER COLUMN id_content SET DEFAULT nextval('content_id_content_seq'::regclass);


--
-- TOC entry 2315 (class 2604 OID 16566)
-- Name: guest_book id_guest_book; Type: DEFAULT; Schema: barber; Owner: mommyce
--

ALTER TABLE ONLY guest_book ALTER COLUMN id_guest_book SET DEFAULT nextval('guest_book_id_guest_book_seq'::regclass);


--
-- TOC entry 2316 (class 2604 OID 24938)
-- Name: testimonial id_testimonial; Type: DEFAULT; Schema: barber; Owner: mommyce
--

ALTER TABLE ONLY testimonial ALTER COLUMN id_testimonial SET DEFAULT nextval('testimonial_id_testimonial_seq'::regclass);


SET search_path = config, pg_catalog;

--
-- TOC entry 2320 (class 2604 OID 24828)
-- Name: role id; Type: DEFAULT; Schema: config; Owner: mommyce
--

ALTER TABLE ONLY role ALTER COLUMN id SET DEFAULT nextval('role_id_seq'::regclass);


--
-- TOC entry 2321 (class 2604 OID 24911)
-- Name: user_roles id; Type: DEFAULT; Schema: config; Owner: mommyce
--

ALTER TABLE ONLY user_roles ALTER COLUMN id SET DEFAULT nextval('user_roles_id_seq'::regclass);


--
-- TOC entry 2322 (class 2604 OID 24913)
-- Name: user_roles role_id; Type: DEFAULT; Schema: config; Owner: mommyce
--

ALTER TABLE ONLY user_roles ALTER COLUMN role_id SET DEFAULT nextval('user_roles_role_id_seq'::regclass);


SET search_path = barber, pg_catalog;

--
-- TOC entry 2466 (class 0 OID 16552)
-- Dependencies: 192
-- Data for Name: attachment; Type: TABLE DATA; Schema: barber; Owner: mommyce
--

COPY attachment (id_attachment, content_id, file, type) FROM stdin;
12	2	14b82981491f46f3909bf511a1c3183ajpg	PRICE
15	2	40057a0b93e045cf93431189c3dda996.gif	PRICE
16	2	0c4e2dfeeaed47e4937e4d7d3f0afac9.jpg	PRICE
\.


--
-- TOC entry 2463 (class 0 OID 16525)
-- Dependencies: 189
-- Data for Name: content; Type: TABLE DATA; Schema: barber; Owner: mommyce
--

COPY content (title, description, create_date, update_date, create_by, update_by, type, price, id_content) FROM stdin;
Test update	Testing update	1970-01-01	1970-01-01	admin.barber@gmail.com	admin.barber@gmail.com	PRICE	150000	2
test	from postman	2017-12-12	\N	admin	\N	PRICE	10000	3
test	from postman	2018-11-12	\N	admin	\N	PRICE	10000	4
\.


--
-- TOC entry 2460 (class 0 OID 16433)
-- Dependencies: 186
-- Data for Name: guest_book; Type: TABLE DATA; Schema: barber; Owner: mommyce
--

COPY guest_book (username, create_date, id_guest_book) FROM stdin;
jono	2017-11-23	2
jono	2017-11-23	3
haliri	2017-11-23	4
new jono	2017-12-09	5
\.


--
-- TOC entry 2461 (class 0 OID 16484)
-- Dependencies: 187
-- Data for Name: profile; Type: TABLE DATA; Schema: barber; Owner: mommyce
--

COPY profile (address, email, create_date, update_date, phone, lat, lon) FROM stdin;
jln goal para new	israj.haliri@gmail.comnew	2017-12-09 14:46:22.2833	2017-12-09 14:46:22.2833	85862624140	-6.14349985	106.723999
\.


--
-- TOC entry 2462 (class 0 OID 16492)
-- Dependencies: 188
-- Data for Name: testimonial; Type: TABLE DATA; Schema: barber; Owner: mommyce
--

COPY testimonial (subject, description, age, create_date, update_date, id_testimonial) FROM stdin;
israj	test post man	24	2017-12-09	2017-12-09	7
\.


SET search_path = config, pg_catalog;

--
-- TOC entry 2470 (class 0 OID 24825)
-- Dependencies: 196
-- Data for Name: role; Type: TABLE DATA; Schema: config; Owner: mommyce
--

COPY role (id, role) FROM stdin;
1	ROLE_BARBER_ADMIN
2	ROLE_SUPER_ADMIN
\.


--
-- TOC entry 2473 (class 0 OID 24908)
-- Dependencies: 199
-- Data for Name: user_roles; Type: TABLE DATA; Schema: config; Owner: mommyce
--

COPY user_roles (id, role_id, user_id) FROM stdin;
1	2	barber.admin@gmail.com
2	1	israj.haliri@gmail.com
3	2	israj.haliri@gmail.com
\.


--
-- TOC entry 2468 (class 0 OID 24804)
-- Dependencies: 194
-- Data for Name: users; Type: TABLE DATA; Schema: config; Owner: mommyce
--

COPY users (id, password, enable, token, username) FROM stdin;
barber.admin@gmail.com	$2a$12$JEdDATwXzSanYkSNNlfcYuOsHcHSH9dQNjCRlUlZOqfUrYXyR7gw6	t	eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYXJiZXIuYWRtaW5AZ21haWwuY29tIiwiYXVkIjoiaXNyYWpIYWxpcmlTaXRlIiwiZXhwIjoxNTEzNTA2NDk5fQ.5IJoio6ams_1An1nPg8LdboXnIUgI_GHx844GyOwCYc	\N
israj.haliri@gmail.com	$2a$12$3aJYGZNTDKXUeDruTBxYVeBVyTBcM0mpG/BF/.DezaUSI/N4yzMIO	t	eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpc3Jhai5oYWxpcmlAZ21haWwuY29tIiwiYXVkIjoiaXNyYWpIYWxpcmlTaXRlIiwiZXhwIjoxNTEzNTA2NTI0fQ.fMkrSeRuhcDmpfohsNhxraozXThYdDofRl8BQcqUWgg	jono
\.


SET search_path = barber, pg_catalog;

--
-- TOC entry 2490 (class 0 OID 0)
-- Dependencies: 201
-- Name: content_id_content_seq; Type: SEQUENCE SET; Schema: barber; Owner: mommyce
--

SELECT pg_catalog.setval('content_id_content_seq', 5, true);


--
-- TOC entry 2491 (class 0 OID 0)
-- Dependencies: 193
-- Name: guest_book_id_guest_book_seq; Type: SEQUENCE SET; Schema: barber; Owner: mommyce
--

SELECT pg_catalog.setval('guest_book_id_guest_book_seq', 5, true);


--
-- TOC entry 2492 (class 0 OID 0)
-- Dependencies: 190
-- Name: images_id_image_seq; Type: SEQUENCE SET; Schema: barber; Owner: mommyce
--

SELECT pg_catalog.setval('images_id_image_seq', 16, true);


--
-- TOC entry 2493 (class 0 OID 0)
-- Dependencies: 191
-- Name: images_news_and_article_di_seq; Type: SEQUENCE SET; Schema: barber; Owner: mommyce
--

SELECT pg_catalog.setval('images_news_and_article_di_seq', 1, false);


--
-- TOC entry 2494 (class 0 OID 0)
-- Dependencies: 200
-- Name: testimonial_id_testimonial_seq; Type: SEQUENCE SET; Schema: barber; Owner: mommyce
--

SELECT pg_catalog.setval('testimonial_id_testimonial_seq', 7, true);


SET search_path = config, pg_catalog;

--
-- TOC entry 2495 (class 0 OID 0)
-- Dependencies: 195
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: config; Owner: mommyce
--

SELECT pg_catalog.setval('role_id_seq', 4, true);


--
-- TOC entry 2496 (class 0 OID 0)
-- Dependencies: 197
-- Name: user_roles_id_seq; Type: SEQUENCE SET; Schema: config; Owner: mommyce
--

SELECT pg_catalog.setval('user_roles_id_seq', 3, true);


--
-- TOC entry 2497 (class 0 OID 0)
-- Dependencies: 198
-- Name: user_roles_role_id_seq; Type: SEQUENCE SET; Schema: config; Owner: mommyce
--

SELECT pg_catalog.setval('user_roles_role_id_seq', 1, false);


SET search_path = barber, pg_catalog;

SET default_tablespace = '';

--
-- TOC entry 2328 (class 2606 OID 24952)
-- Name: content content_pkey; Type: CONSTRAINT; Schema: barber; Owner: mommyce
--

ALTER TABLE ONLY content
    ADD CONSTRAINT content_pkey PRIMARY KEY (id_content);


SET default_tablespace = mommyce;

--
-- TOC entry 2324 (class 2606 OID 16571)
-- Name: guest_book guest_book_pkey; Type: CONSTRAINT; Schema: barber; Owner: mommyce; Tablespace: mommyce
--

ALTER TABLE ONLY guest_book
    ADD CONSTRAINT guest_book_pkey PRIMARY KEY (id_guest_book);


--
-- TOC entry 2331 (class 2606 OID 16563)
-- Name: attachment images_pkey; Type: CONSTRAINT; Schema: barber; Owner: mommyce; Tablespace: mommyce
--

ALTER TABLE ONLY attachment
    ADD CONSTRAINT images_pkey PRIMARY KEY (id_attachment);


SET default_tablespace = '';

--
-- TOC entry 2326 (class 2606 OID 24940)
-- Name: testimonial testimonial_pkey; Type: CONSTRAINT; Schema: barber; Owner: mommyce
--

ALTER TABLE ONLY testimonial
    ADD CONSTRAINT testimonial_pkey PRIMARY KEY (id_testimonial);


SET search_path = config, pg_catalog;

--
-- TOC entry 2335 (class 2606 OID 24831)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: config; Owner: mommyce
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 2339 (class 2606 OID 24915)
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: config; Owner: mommyce
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2333 (class 2606 OID 24808)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: config; Owner: mommyce
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


SET search_path = barber, pg_catalog;

--
-- TOC entry 2329 (class 1259 OID 16584)
-- Name: fki_news_and_article_fkey; Type: INDEX; Schema: barber; Owner: mommyce
--

CREATE INDEX fki_news_and_article_fkey ON attachment USING btree (content_id);


SET search_path = config, pg_catalog;

--
-- TOC entry 2336 (class 1259 OID 24922)
-- Name: fki_role_fkey; Type: INDEX; Schema: config; Owner: mommyce
--

CREATE INDEX fki_role_fkey ON user_roles USING btree (role_id);


--
-- TOC entry 2337 (class 1259 OID 24928)
-- Name: fki_user_fkey; Type: INDEX; Schema: config; Owner: mommyce
--

CREATE INDEX fki_user_fkey ON user_roles USING btree (user_id);


SET search_path = barber, pg_catalog;

--
-- TOC entry 2340 (class 2606 OID 24953)
-- Name: attachment content_fkey; Type: FK CONSTRAINT; Schema: barber; Owner: mommyce
--

ALTER TABLE ONLY attachment
    ADD CONSTRAINT content_fkey FOREIGN KEY (content_id) REFERENCES content(id_content);


SET search_path = config, pg_catalog;

--
-- TOC entry 2341 (class 2606 OID 24917)
-- Name: user_roles role_fkey; Type: FK CONSTRAINT; Schema: config; Owner: mommyce
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT role_fkey FOREIGN KEY (role_id) REFERENCES role(id);


--
-- TOC entry 2342 (class 2606 OID 24923)
-- Name: user_roles user_fkey; Type: FK CONSTRAINT; Schema: config; Owner: mommyce
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES users(id);


-- Completed on 2017-12-17 17:24:48 WIB

--
-- PostgreSQL database dump complete
--

