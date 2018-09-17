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

CREATE TYPE role AS ENUM (
    'ROLE_MEMBER',
    'ROLE_ADMIN'
);

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

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE country (
    id integer NOT NULL,
    name character(40) NOT NULL,
    version integer DEFAULT 0 NOT NULL
);

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
    features features[],
    version integer DEFAULT 0 NOT NULL
);

CREATE TABLE review (
    id integer NOT NULL,
    date timestamp(4) with time zone,
    text text,
    user_id integer,
    tour_id integer,
    version integer DEFAULT 0 NOT NULL
);

CREATE TABLE tour (
    id integer NOT NULL,
    photo text,
    date date,
    duration integer,
    description text,
    cost numeric,
    tour_type tour_type,
    hotel_id integer,
    country_id integer,
    version integer DEFAULT 0 NOT NULL
);

CREATE TABLE "user" (
    id integer NOT NULL,
    login text,
    password text,
    role role DEFAULT 'ROLE_MEMBER'::role NOT NULL,
    version integer DEFAULT 0 NOT NULL
);

CREATE TABLE user_tour (
    user_id integer NOT NULL,
    tour_id integer NOT NULL
);

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
