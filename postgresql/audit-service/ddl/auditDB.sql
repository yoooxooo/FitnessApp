\c audits
CREATE SCHEMA audits AUTHORIZATION root;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 235 (class 1259 OID 21264)
-- Name: audits; Type: TABLE; Schema: audit; Owner: root
--

CREATE TABLE audits.audits (
    id uuid NOT NULL,
    action character varying(255),
    dt_create timestamp(6) with time zone,
    essence_id uuid,
    fio character varying(255),
    mail character varying(255),
    role smallint,
    essence smallint,
    user_id uuid
);


ALTER TABLE audits.audits OWNER TO root;

--
-- TOC entry 3224 (class 2606 OID 21270)
-- Name: audits audits_pkey; Type: CONSTRAINT; Schema: audit; Owner: root
--

ALTER TABLE ONLY audits.audits
    ADD CONSTRAINT audits_pkey PRIMARY KEY (id);


-- Completed on 2023-03-19 22:19:11

--
-- PostgreSQL database dump complete
--

