CREATE TABLE app.artists
(
    id   bigint NOT NULL,
    name text   NOT NULL
);

CREATE SEQUENCE app.artists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE app.artists_id_seq OWNED BY app.artists.id;

CREATE TABLE app.emails
(
    id           bigint  NOT NULL,
    vote_id      bigint  NOT NULL,
    recipient    text    NOT NULL,
    topic        text    NOT NULL,
    text_message text    NOT NULL,
    departures   integer NOT NULL,
    status       text    NOT NULL
);

CREATE SEQUENCE app.emails_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE app.emails_id_seq OWNED BY app.emails.id;

CREATE TABLE app.genres
(
    id    bigint NOT NULL,
    title text   NOT NULL
);

CREATE SEQUENCE app.genres_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE app.genres_id_seq OWNED BY app.genres.id;

CREATE TABLE app.votes
(
    id            bigint                      NOT NULL,
    artist_id     bigint                      NOT NULL,
    about         text                        NOT NULL,
    creation_time timestamp without time zone NOT NULL,
    email         text                        NOT NULL
);

CREATE TABLE app.votes_genres
(
    vote_id  bigint NOT NULL,
    genre_id bigint NOT NULL
);

CREATE SEQUENCE app.votes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE app.votes_id_seq OWNED BY app.votes.id;

ALTER TABLE ONLY app.artists
    ALTER COLUMN id SET DEFAULT nextval('app.artists_id_seq'::regclass);

ALTER TABLE ONLY app.emails
    ALTER COLUMN id SET DEFAULT nextval('app.emails_id_seq'::regclass);

ALTER TABLE ONLY app.genres
    ALTER COLUMN id SET DEFAULT nextval('app.genres_id_seq'::regclass);

ALTER TABLE ONLY app.votes
    ALTER COLUMN id SET DEFAULT nextval('app.votes_id_seq'::regclass);

SELECT pg_catalog.setval('app.artists_id_seq', 1, false);

SELECT pg_catalog.setval('app.emails_id_seq', 1, false);

SELECT pg_catalog.setval('app.genres_id_seq', 1, false);

SELECT pg_catalog.setval('app.votes_id_seq', 1, false);

ALTER TABLE ONLY app.artists
    ADD CONSTRAINT artists_pkey PRIMARY KEY (id);

ALTER TABLE ONLY app.emails
    ADD CONSTRAINT emails_pkey PRIMARY KEY (id);

ALTER TABLE ONLY app.genres
    ADD CONSTRAINT genres_pkey PRIMARY KEY (id);

ALTER TABLE ONLY app.genres
    ADD CONSTRAINT genres_title_unique UNIQUE (title);

ALTER TABLE ONLY app.votes
    ADD CONSTRAINT votes_emails_unique UNIQUE (email);

ALTER TABLE ONLY app.votes
    ADD CONSTRAINT votes_pkey PRIMARY KEY (id);

ALTER TABLE ONLY app.emails
    ADD CONSTRAINT emails_vote_id_fkey FOREIGN KEY (vote_id) REFERENCES app.votes (id) NOT VALID;

ALTER TABLE ONLY app.votes
    ADD CONSTRAINT "votes_artists_artist_id_fkey" FOREIGN KEY (artist_id) REFERENCES app.artists (id) NOT VALID;

ALTER TABLE ONLY app.votes_genres
    ADD CONSTRAINT votes_genres_genre_id_fkey FOREIGN KEY (genre_id) REFERENCES app.genres (id);

ALTER TABLE ONLY app.votes_genres
    ADD CONSTRAINT votes_genres_vote_id_fkey FOREIGN KEY (vote_id) REFERENCES app.votes (id);