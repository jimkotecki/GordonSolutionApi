-- Sequence: account_id_seq

DROP SEQUENCE account_id_seq CASCADE;

CREATE SEQUENCE account_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1
  CYCLE;
ALTER TABLE account_id_seq OWNER TO developer;

-- Sequence: message_id_seq

DROP SEQUENCE message_id_seq CASCADE;

CREATE SEQUENCE message_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE message_id_seq OWNER TO developer;

-- Sequence: contact_info_id_seq

DROP SEQUENCE contact_info_id_seq CASCADE;

CREATE SEQUENCE contact_info_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1
  CYCLE;
ALTER TABLE contact_info_id_seq OWNER TO developer;

-- Sequence: link_info_id_seq

DROP SEQUENCE link_info_id_seq CASCADE;

CREATE SEQUENCE link_info_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE link_info_id_seq OWNER TO developer;

-- Sequence: user_id_seq

DROP SEQUENCE user_id_seq CASCADE;

CREATE SEQUENCE user_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE user_id_seq OWNER TO developer;

-- Sequence: ranking_id_seq

DROP SEQUENCE ranking_id_seq CASCADE;

CREATE SEQUENCE ranking_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE ranking_id_seq OWNER TO developer;

-- Sequence: convo_id_seq

DROP SEQUENCE convo_id_seq CASCADE;

CREATE SEQUENCE convo_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE convo_id_seq OWNER TO developer;

-- Table: contact_info

DROP TABLE contact_info CASCADE;

CREATE TABLE contact_info
(
  id integer NOT NULL DEFAULT nextval('contact_info_id_seq'::regclass),
  contact_info_line1 character varying(100),
  contact_info_line2 character varying(100),
  contact_info_line3 character varying(100),
  contact_info_line4 character varying(100),
  contact_info_line5 character varying(100),
  contact_info_city character varying(50),
  contact_info_state character(2),
  contact_info_zip character varying(10),
  contact_info_description character varying(25),
  contact_info_phone character varying(20),
  contact_info_phone2 character varying(20),
  contact_info_phone3 character varying(20),
  contact_info_email character varying(100),
  contact_info_email2 character varying(100),
  contact_info_seq int NOT NULL DEFAULT 1,
  update_date timestamp with time zone,
  create_date timestamp with time zone,
  active boolean DEFAULT true,
  deleted boolean DEFAULT false,
  delete_date timestamp with time zone,
  CONSTRAINT pk_contact_info PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contact_info OWNER TO developer;

-- Table: account

DROP TABLE account CASCADE;

CREATE TABLE account
(
  id integer NOT NULL DEFAULT nextval('account_id_seq'::regclass),
  account_name character varying(100),
  account_first_name character varying(40),
  account_last_name character varying(75),
  account_contact_info_id integer NOT NULL,
  create_date timestamp with time zone,
  update_date timestamp with time zone,
  active boolean DEFAULT true,
  deleted boolean DEFAULT false,
  delete_date timestamp with time zone,
  CONSTRAINT pk_account PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE account OWNER TO developer;

-- Table: "user"

DROP TABLE "user" CASCADE;

CREATE TABLE "user"
(
  user_name character varying(100) NOT NULL,
  user_password character varying(25) NOT NULL,
  user_account_id int,
  id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
  user_contact_info_id integer,
  user_email character varying(150),
  create_date timestamp with time zone,
  update_date timestamp with time zone,
  login_date timestamp with time zone,
  active boolean DEFAULT true,
  deleted boolean DEFAULT false,
  delete_date timestamp with time zone,
  CONSTRAINT pk_user PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "user" OWNER TO developer;


-- Table: link_info

DROP TABLE link_info CASCADE;

CREATE TABLE link_info
(
  id integer NOT NULL DEFAULT nextval('link_info_id_seq'::regclass),
  link_type character varying(15),
  mini_url character varying(75),
  long_url character varying(10000) NOT NULL,
  internal_url character varying(10000),
  description character varying(300),
  user_id int NOT NULL,
  parent_id int DEFAULT 0,
  crawl boolean DEFAULT false,
  last_crawl_date timestamp with time zone,
  update_date timestamp with time zone,
  create_date timestamp with time zone,
  active boolean DEFAULT true,
  deleted boolean DEFAULT false,
  delete_date timestamp with time zone,
  CONSTRAINT pk_link_info PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE link_info OWNER TO developer;

-- Table: "message"

DROP TABLE "message" CASCADE;

CREATE TABLE "message"
(
  id integer NOT NULL DEFAULT nextval('message_id_seq'::regclass),
  "message" character varying(10000) NOT NULL,
  user_id int NOT NULL,
  convo_id int,
  update_date timestamp with time zone,
  create_date timestamp with time zone,
  active boolean DEFAULT true,
  deleted boolean DEFAULT false,
  delete_date timestamp with time zone,
  CONSTRAINT pk_message PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "message" OWNER TO developer;

-- Table: ranking

DROP TABLE ranking CASCADE;

CREATE TABLE ranking
(
  id integer NOT NULL DEFAULT nextval('ranking_id_seq'::regclass),
  ranking_type character varying(15) NOT NULL,
  ranking integer DEFAULT 0,
  score integer DEFAULT 0,
  update_date timestamp with time zone,
  create_date timestamp with time zone,
  active boolean DEFAULT true,
  deleted boolean DEFAULT false,
  delete_date timestamp with time zone,
  CONSTRAINT pk_ranking PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ranking OWNER TO developer;

-- Table: convo

DROP TABLE convo CASCADE;

CREATE TABLE convo
(
  id integer NOT NULL DEFAULT nextval('convo_id_seq'::regclass),
  convo_type character varying(15) NOT NULL,
  original_link_id integer,
  short_description character varying(75),
  long_description character varying(1000),
  creator_user_id int,
  last_contributed_user_id int,
  update_date timestamp with time zone,
  create_date timestamp with time zone,
  active boolean DEFAULT true,
  deleted boolean DEFAULT false,
  delete_date timestamp with time zone,
  CONSTRAINT pk_convo PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ranking OWNER TO developer;

-- Start Insert of seed data

-- Users

INSERT INTO "user"(
       id,user_name, user_password, user_email)
VALUES (1, 'SYSTEM', 'SYSTEM', 'SYSTEM');  

INSERT INTO "user"(
       id,user_name, user_password, user_email)
VALUES (2, 'test', 'test', 'test@test.com');

-- Links

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (1, 'SEED', 'http://www.cbssports.com', 1, true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (2, 'SEED', 'http://www.cnn.com', 1, true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id,crawl)
VALUES (3, 'SEED', 'http://www.reddit.com', 1,true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id,crawl)
VALUES (4, 'SEED', 'http://www.google.com', 1,true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (5, 'SEED', 'http://www.foxnews.com', 1, true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (6, 'SEED', 'http://www.huffingtonpost.com', 1, true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (7, 'SEED', 'http://www.computerworld.com', 1, true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (8, 'SEED', 'http://www.cnet.com', 1, true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (9, 'SEED', 'http://www.chicagotribune.com', 1, true);
 
INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (10, 'SEED', 'http://messages.yahoo.com/yahoo/Recreation_%26_Sports/Sports/Football_%28American%29/index.html', 1, true);



