--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.9
-- Dumped by pg_dump version 10.0

-- Started on 2018-07-18 16:02:19

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
-- TOC entry 2170 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 565 (class 1247 OID 16417)
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
-- TOC entry 587 (class 1247 OID 16614)
-- Name: tour_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE tour_type AS ENUM (
    'safari',
    'rural',
    'mountain',
    'ski',
    'ecotourism',
    'health',
    'cruise',
    'education',
    'adventure',
    'cultural'
);


ALTER TYPE tour_type OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

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
-- TOC entry 187 (class 1259 OID 16403)
-- Name: hotel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE hotel (
    id integer NOT NULL,
    name text NOT NULL,
    stars smallint,
    website text,
    latitude numeric,
    longitude numeric,
    features features[]
);


ALTER TABLE hotel OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16496)
-- Name: review; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE review (
    id integer NOT NULL,
    date date,
    text text,
    user_id integer,
    tour_id integer
);


ALTER TABLE review OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16390)
-- Name: tour; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tour (
    id integer NOT NULL,
    photo text,
    date date,
    duration integer,
    description text,
    cost money,
    tour_type tour_type,
    hotel_id integer,
    country_id integer
);


ALTER TABLE tour OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16469)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "user" (
    id integer NOT NULL,
    login text,
    password text
);


ALTER TABLE "user" OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16481)
-- Name: user_tour; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_tour (
    user_id integer NOT NULL,
    tour_id integer NOT NULL
);


ALTER TABLE user_tour OWNER TO postgres;

--
-- TOC entry 2030 (class 2606 OID 16389)
-- Name: country pk_country_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY country
    ADD CONSTRAINT pk_country_id PRIMARY KEY (id);


--
-- TOC entry 2034 (class 2606 OID 16410)
-- Name: hotel pk_hotel_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hotel
    ADD CONSTRAINT pk_hotel_id PRIMARY KEY (id);


--
-- TOC entry 2040 (class 2606 OID 16503)
-- Name: review pk_review_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY review
    ADD CONSTRAINT pk_review_id PRIMARY KEY (id);


--
-- TOC entry 2032 (class 2606 OID 16397)
-- Name: tour pk_tour_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tour
    ADD CONSTRAINT pk_tour_id PRIMARY KEY (id);


--
-- TOC entry 2036 (class 2606 OID 16473)
-- Name: user pk_user_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT pk_user_id PRIMARY KEY (id);


--
-- TOC entry 2038 (class 2606 OID 16485)
-- Name: user_tour pk_usertour; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_tour
    ADD CONSTRAINT pk_usertour PRIMARY KEY (user_id, tour_id);


--
-- TOC entry 2042 (class 2606 OID 16640)
-- Name: tour fk_country_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tour
    ADD CONSTRAINT fk_country_id FOREIGN KEY (country_id) REFERENCES country(id);


--
-- TOC entry 2041 (class 2606 OID 16635)
-- Name: tour fk_hotel_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tour
    ADD CONSTRAINT fk_hotel_id FOREIGN KEY (hotel_id) REFERENCES hotel(id);


--
-- TOC entry 2046 (class 2606 OID 16509)
-- Name: review fk_tour_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY review
    ADD CONSTRAINT fk_tour_id FOREIGN KEY (tour_id) REFERENCES tour(id);


--
-- TOC entry 2044 (class 2606 OID 16519)
-- Name: user_tour fk_tour_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_tour
    ADD CONSTRAINT fk_tour_id FOREIGN KEY (tour_id) REFERENCES tour(id);


--
-- TOC entry 2045 (class 2606 OID 16504)
-- Name: review fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY review
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "user"(id);


--
-- TOC entry 2043 (class 2606 OID 16514)
-- Name: user_tour fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_tour
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "user"(id);


-- Completed on 2018-07-18 16:02:19

--
-- PostgreSQL database dump complete
--

