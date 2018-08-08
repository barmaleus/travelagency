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

CREATE TABLE country (
    id integer NOT NULL,
    name character(40) NOT NULL
);

ALTER TABLE country OWNER TO postgres;

CREATE SEQUENCE gpa_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

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

CREATE TABLE review (
    id integer NOT NULL,
    date timestamp,
    text text,
    user_id integer,
    tour_id integer
);

ALTER TABLE review OWNER TO postgres;

CREATE TABLE tour (
    id integer NOT NULL,
    photo text,
    date date,
    duration integer,
    description text,
    cost numeric,
    tour_type tour_type,
    hotel_id integer,
    country_id integer
);

ALTER TABLE tour OWNER TO postgres;

CREATE TABLE "user" (
    id integer NOT NULL,
    login text,
    password text
);

ALTER TABLE "user" OWNER TO postgres;

CREATE TABLE user_tour (
    user_id integer NOT NULL,
    tour_id integer NOT NULL
);

ALTER TABLE user_tour OWNER TO postgres;

ALTER TABLE ONLY country
    ADD CONSTRAINT pk_country_id PRIMARY KEY (id);

ALTER TABLE ONLY hotel
    ADD CONSTRAINT pk_hotel_id PRIMARY KEY (id);

ALTER TABLE ONLY review
    ADD CONSTRAINT pk_review_id PRIMARY KEY (id);

ALTER TABLE ONLY tour
    ADD CONSTRAINT pk_tour_id PRIMARY KEY (id);

ALTER TABLE ONLY "user"
    ADD CONSTRAINT pk_user_id PRIMARY KEY (id);

ALTER TABLE ONLY user_tour
    ADD CONSTRAINT pk_usertour PRIMARY KEY (user_id, tour_id);

ALTER TABLE ONLY tour
    ADD CONSTRAINT fk_country_id FOREIGN KEY (country_id) REFERENCES country(id);

ALTER TABLE ONLY tour
    ADD CONSTRAINT fk_hotel_id FOREIGN KEY (hotel_id) REFERENCES hotel(id);

ALTER TABLE ONLY review
    ADD CONSTRAINT fk_tour_id FOREIGN KEY (tour_id) REFERENCES tour(id) ON UPDATE CASCADE ON DELETE CASCADE;;

ALTER TABLE ONLY user_tour
    ADD CONSTRAINT fk_tour_id FOREIGN KEY (tour_id) REFERENCES tour(id);

ALTER TABLE ONLY review
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "user"(id) ON UPDATE CASCADE ON DELETE CASCADE;;

ALTER TABLE ONLY user_tour
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "user"(id);
