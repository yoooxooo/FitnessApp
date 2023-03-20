\c users
CREATE SCHEMA users AUTHORIZATION root;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 21026)
-- Name: users; Type: TABLE; Schema: users; Owner: root
--

CREATE TABLE users.users (
    id uuid NOT NULL,
    dt_create timestamp(6) with time zone,
    fio character varying(255),
    mail character varying(255),
    password character varying(255),
    role character varying(255),
    status character varying(255),
    dt_update timestamp(6) with time zone
);


ALTER TABLE users.users OWNER TO root;

--
-- TOC entry 3174 (class 2606 OID 21034)
-- Name: users uk_jhck7kjdogc7yia7qamc89ypv; Type: CONSTRAINT; Schema: users; Owner: root
--

ALTER TABLE ONLY users.users
    ADD CONSTRAINT uk_jhck7kjdogc7yia7qamc89ypv UNIQUE (mail);


--
-- TOC entry 3176 (class 2606 OID 21032)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: users; Owner: root
--

ALTER TABLE ONLY users.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


-- Completed on 2023-03-17 14:44:42

--
-- rootQL database dump complete
--

