--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.9
-- Dumped by pg_dump version 10.0

-- Started on 2018-07-11 15:00:34

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2177 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 569 (class 1247 OID 16417)
-- Name: features; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE features AS ENUM (
    'swimming pool',
    'all inclusive',
    'fresh towel',
    'carpets on the walls',
    'free wifi',
    'close to sea',
    'free animators',
    'free beer',
    'air conditioning',
    'children room'
);


ALTER TYPE features OWNER TO postgres;

--
-- TOC entry 572 (class 1247 OID 16439)
-- Name: tour_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE tour_type AS (
	safari text,
	rural text,
	mountain text,
	ski text,
	ecotourism text,
	health text,
	cruise text,
	education text,
	adventure text,
	cultural text
);


ALTER TYPE tour_type OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 187 (class 1259 OID 16403)
-- Name: Hotel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "Hotel" (
    id integer NOT NULL,
    name text NOT NULL,
    stars smallint,
    website text,
    latitude numeric(10,8),
    longitude numeric(10,8),
    features features
);


ALTER TABLE "Hotel" OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 16496)
-- Name: Review; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "Review" (
    id integer NOT NULL,
    date date,
    text text,
    user_id integer,
    tour_id integer
);


ALTER TABLE "Review" OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16390)
-- Name: Tour; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "Tour" (
    id integer NOT NULL,
    photo path,
    date date,
    duration integer,
    description text,
    cost money,
    tour_type tour_type,
    hotel_id integer,
    country_id integer
);


ALTER TABLE "Tour" OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16469)
-- Name: User; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "User" (
    id integer NOT NULL,
    login text,
    password text
);


ALTER TABLE "User" OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16481)
-- Name: UserTour; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "UserTour" (
    user_id integer NOT NULL,
    tour_id integer NOT NULL
);


ALTER TABLE "UserTour" OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16385)
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE country (
    id integer NOT NULL,
    name character(80) NOT NULL
);


ALTER TABLE country OWNER TO postgres;

--
-- TOC entry 2167 (class 0 OID 16403)
-- Dependencies: 187
-- Data for Name: Hotel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Hotel" (id, name, stars, website, latitude, longitude, features) FROM stdin;
\.


--
-- TOC entry 2170 (class 0 OID 16496)
-- Dependencies: 191
-- Data for Name: Review; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Review" (id, date, text, user_id, tour_id) FROM stdin;
\.


--
-- TOC entry 2166 (class 0 OID 16390)
-- Dependencies: 186
-- Data for Name: Tour; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Tour" (id, photo, date, duration, description, cost, tour_type, hotel_id, country_id) FROM stdin;
\.


--
-- TOC entry 2168 (class 0 OID 16469)
-- Dependencies: 189
-- Data for Name: User; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "User" (id, login, password) FROM stdin;
1	bluegoose681	angel
2	brownbutterfly205	hello1
3	orangegoose293	putter
4	ticklishlion744	kong
5	whitepanda913	redsox1
6	happypeacock362	sailor
7	redbutterfly724	sherri
8	lazygoose982	quest1
9	greenzebra720	arturo
10	goldenbird994	kris
11	heavymeercat419	europa
12	angryfrog369	adam25
13	heavymouse685	ttttttt
14	ticklishcat364	liquid
15	ticklishbutterfly225	kkkkkkk
16	angrypeacock516	book
17	purplemouse260	alanis
18	goldenlion776	hooters
19	heavywolf758	abcdefg
20	ticklishkoala139	speakers
21	crazyelephant893	backbone
22	beautifulgoose390	oilers
23	yellowmouse813	zong
24	redostrich841	wendy
25	beautifulladybug109	jordan1
26	silverlion900	bbbb
27	happycat176	stephan
28	bluefrog188	therock
29	organicgoose309	adrienne
30	whitepanda978	menace
31	bigtiger143	backbone
32	blackmouse380	haha
33	sadmeercat294	andrea1
34	orangeostrich703	notredam
35	heavypanda275	timber
36	sadelephant502	mickey
37	silverduck288	sang
38	smallwolf911	picasso
39	purplekoala886	mooses
40	heavydog241	double
41	beautifulmouse912	underdog
42	ticklishwolf878	beech
43	redtiger977	chico
44	organicbird902	saturn
45	goldenladybug891	alberto
46	brownpeacock888	5rxypn
47	goldenfish615	goose1
48	organicrabbit968	bryant
49	redwolf768	focus
50	lazymeercat288	jjjjj
51	organiclion568	reckless
52	blackgorilla961	list
53	blackgoose942	nikita
54	lazyduck506	cumm
55	brownladybug419	nong
56	heavyswan909	hurricane
57	sadmouse301	buddha
58	redfish354	fandango
59	greengorilla209	cubs
60	orangeostrich124	real
61	reddog897	womans
62	silvermeercat606	irish1
63	angrycat977	aberdeen
64	organicmouse602	andreas
65	redsnake779	danielle
66	yellowfish220	slinky
67	goldenbear447	sssss
68	crazykoala414	pirates
69	greenelephant315	blueberr
70	bluelion826	1013
71	smallbutterfly789	eagles1
72	heavygoose821	thong
73	bigbird192	sunrise
74	bigbear891	barley
75	redzebra537	annie
76	sadmeercat357	gadget
77	redbutterfly579	gunners
78	ticklishkoala633	newport
79	crazyfish306	strat
80	yellowladybug766	147147
81	smallwolf625	paris1
82	goldenbutterfly873	irish
83	goldenelephant345	starship
84	smalllion436	1111
85	whitewolf362	rebel
86	biggoose963	louise
87	blackgoose959	kisskiss
88	yellowtiger965	boating
89	greenfish637	darkman
90	angrygoose891	trout
91	beautifulbear761	shun
92	organicostrich442	swinging
93	whitebear377	wwwwwww
94	goldenleopard934	333333
95	organicgoose662	callum
96	crazyleopard523	mariner
97	orangemouse918	hydro
98	blackzebra579	frosch
99	silvergoose415	maxime
100	goldenswan189	meister
\.


--
-- TOC entry 2169 (class 0 OID 16481)
-- Dependencies: 190
-- Data for Name: UserTour; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "UserTour" (user_id, tour_id) FROM stdin;
\.


--
-- TOC entry 2165 (class 0 OID 16385)
-- Dependencies: 185
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY country (id, name) FROM stdin;
1	Canada                                                                          
2	Egypt                                                                           
3	Uruguay                                                                         
4	Iraq                                                                            
5	Switzerland                                                                     
6	Cyprus                                                                          
7	India                                                                           
8	Azerbaijan                                                                      
9	Ireland                                                                         
10	Moldova                                                                         
11	Christmas Island                                                                
12	Serbia                                                                          
13	Malaysia                                                                        
14	Thailand                                                                        
15	Cuba                                                                            
16	Mauritius                                                                       
17	Norway                                                                          
18	Reunion                                                                         
19	Tuvalu                                                                          
20	Mali                                                                            
21	Nepal                                                                           
22	Taiwan                                                                          
23	Austria                                                                         
24	Bahamas                                                                         
25	Central African Republic                                                        
26	Myanmar                                                                         
27	Curaçao                                                                         
28	Romania                                                                         
29	Guinea                                                                          
30	Moldova                                                                         
31	Christmas Island                                                                
32	Japan                                                                           
33	Haiti                                                                           
34	Antarctica                                                                      
35	Mozambique                                                                      
36	Malta                                                                           
37	Bhutan                                                                          
38	Kiribati                                                                        
39	Spain                                                                           
40	Trinidad and Tobago                                                             
41	Argentina                                                                       
42	Samoa                                                                           
43	Russian Federation                                                              
44	Saint Martin                                                                    
45	Brazil                                                                          
46	Bonaire, Sint Eustatius and Saba                                                
47	Latvia                                                                          
48	Tuvalu                                                                          
49	United States Minor Outlying Islands                                            
50	Monaco                                                                          
51	Tanzania                                                                        
52	American Samoa                                                                  
53	Burundi                                                                         
54	Cuba                                                                            
55	French Southern Territories                                                     
56	Hong Kong                                                                       
57	Cayman Islands                                                                  
58	Sao Tome and Principe                                                           
59	Niue                                                                            
60	South Sudan                                                                     
61	Somalia                                                                         
62	Falkland Islands                                                                
63	Kiribati                                                                        
64	Bahrain                                                                         
65	Curaçao                                                                         
66	Bahrain                                                                         
67	Norway                                                                          
68	Turkmenistan                                                                    
69	Central African Republic                                                        
70	Belgium                                                                         
71	Mayotte                                                                         
72	Afghanistan                                                                     
73	Senegal                                                                         
74	Niger                                                                           
75	Côte D'Ivoire (Ivory Coast)                                                     
76	Macedonia                                                                       
77	Slovakia                                                                        
78	United Arab Emirates                                                            
79	San Marino                                                                      
80	Latvia                                                                          
81	Pakistan                                                                        
82	Trinidad and Tobago                                                             
83	Saint Lucia                                                                     
84	Antigua and Barbuda                                                             
85	India                                                                           
86	Andorra                                                                         
87	Seychelles                                                                      
88	Guinea                                                                          
89	Myanmar                                                                         
90	Tunisia                                                                         
91	Liechtenstein                                                                   
92	Albania                                                                         
93	Niue                                                                            
94	Saint Vincent and The Grenadines                                                
95	Tajikistan                                                                      
96	Dominican Republic                                                              
97	Liberia                                                                         
98	Bonaire, Sint Eustatius and Saba                                                
99	Iraq                                                                            
100	Mayotte                                                                         
\.


--
-- TOC entry 2031 (class 2606 OID 16389)
-- Name: country pk_country_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY country
    ADD CONSTRAINT pk_country_id PRIMARY KEY (id);


--
-- TOC entry 2035 (class 2606 OID 16410)
-- Name: Hotel pk_hotel_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Hotel"
    ADD CONSTRAINT pk_hotel_id PRIMARY KEY (id);


--
-- TOC entry 2041 (class 2606 OID 16503)
-- Name: Review pk_review_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Review"
    ADD CONSTRAINT pk_review_id PRIMARY KEY (id);


--
-- TOC entry 2033 (class 2606 OID 16397)
-- Name: Tour pk_tour_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Tour"
    ADD CONSTRAINT pk_tour_id PRIMARY KEY (id);


--
-- TOC entry 2037 (class 2606 OID 16473)
-- Name: User pk_user_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "User"
    ADD CONSTRAINT pk_user_id PRIMARY KEY (id);


--
-- TOC entry 2039 (class 2606 OID 16485)
-- Name: UserTour pk_usertour; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "UserTour"
    ADD CONSTRAINT pk_usertour PRIMARY KEY (user_id, tour_id);


--
-- TOC entry 2042 (class 2606 OID 16524)
-- Name: Tour fk_country_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Tour"
    ADD CONSTRAINT fk_country_id FOREIGN KEY (country_id) REFERENCES country(id);


--
-- TOC entry 2043 (class 2606 OID 16529)
-- Name: Tour fk_hotel_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Tour"
    ADD CONSTRAINT fk_hotel_id FOREIGN KEY (hotel_id) REFERENCES "Hotel"(id);


--
-- TOC entry 2047 (class 2606 OID 16509)
-- Name: Review fk_tour_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Review"
    ADD CONSTRAINT fk_tour_id FOREIGN KEY (tour_id) REFERENCES "Tour"(id);


--
-- TOC entry 2045 (class 2606 OID 16519)
-- Name: UserTour fk_tour_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "UserTour"
    ADD CONSTRAINT fk_tour_id FOREIGN KEY (tour_id) REFERENCES "Tour"(id);


--
-- TOC entry 2046 (class 2606 OID 16504)
-- Name: Review fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Review"
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "User"(id);


--
-- TOC entry 2044 (class 2606 OID 16514)
-- Name: UserTour fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "UserTour"
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "User"(id);


-- Completed on 2018-07-11 15:00:34

--
-- PostgreSQL database dump complete
--

