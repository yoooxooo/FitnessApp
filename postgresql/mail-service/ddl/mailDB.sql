\c mails
CREATE SCHEMA mail AUTHORIZATION root;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 230 (class 1259 OID 21146)
-- Name: jobrunr_backgroundjobservers; Type: TABLE; Schema: mail; Owner: root
--

CREATE TABLE mail.jobrunr_backgroundjobservers (
    id character(36) NOT NULL,
    workerpoolsize integer NOT NULL,
    pollintervalinseconds integer NOT NULL,
    firstheartbeat timestamp(6) without time zone NOT NULL,
    lastheartbeat timestamp(6) without time zone NOT NULL,
    running integer NOT NULL,
    systemtotalmemory bigint NOT NULL,
    systemfreememory bigint NOT NULL,
    systemcpuload numeric(3,2) NOT NULL,
    processmaxmemory bigint NOT NULL,
    processfreememory bigint NOT NULL,
    processallocatedmemory bigint NOT NULL,
    processcpuload numeric(3,2) NOT NULL,
    deletesucceededjobsafter character varying(32),
    permanentlydeletejobsafter character varying(32)
);


ALTER TABLE mail.jobrunr_backgroundjobservers OWNER TO root;

--
-- TOC entry 228 (class 1259 OID 21127)
-- Name: jobrunr_jobs; Type: TABLE; Schema: mail; Owner: root
--

CREATE TABLE mail.jobrunr_jobs (
    id character(36) NOT NULL,
    version integer NOT NULL,
    jobasjson text NOT NULL,
    jobsignature character varying(512) NOT NULL,
    state character varying(36) NOT NULL,
    createdat timestamp without time zone NOT NULL,
    updatedat timestamp without time zone NOT NULL,
    scheduledat timestamp without time zone,
    recurringjobid character varying(128)
);


ALTER TABLE mail.jobrunr_jobs OWNER TO root;

--
-- TOC entry 231 (class 1259 OID 21169)
-- Name: jobrunr_metadata; Type: TABLE; Schema: mail; Owner: root
--

CREATE TABLE mail.jobrunr_metadata (
    id character varying(156) NOT NULL,
    name character varying(92) NOT NULL,
    owner character varying(64) NOT NULL,
    value text NOT NULL,
    createdat timestamp without time zone NOT NULL,
    updatedat timestamp without time zone NOT NULL
);


ALTER TABLE mail.jobrunr_metadata OWNER TO root;

--
-- TOC entry 229 (class 1259 OID 21139)
-- Name: jobrunr_recurring_jobs; Type: TABLE; Schema: mail; Owner: root
--

CREATE TABLE mail.jobrunr_recurring_jobs (
    id character(128) NOT NULL,
    version integer NOT NULL,
    jobasjson text NOT NULL,
    createdat bigint DEFAULT '0'::bigint NOT NULL
);


ALTER TABLE mail.jobrunr_recurring_jobs OWNER TO root;

--
-- TOC entry 232 (class 1259 OID 21188)
-- Name: jobrunr_jobs_stats; Type: VIEW; Schema: mail; Owner: root
--

CREATE VIEW mail.jobrunr_jobs_stats AS
 WITH job_stat_results AS (
         SELECT jobrunr_jobs.state,
            count(*) AS count
           FROM mail.jobrunr_jobs
          GROUP BY ROLLUP(jobrunr_jobs.state)
        )
 SELECT COALESCE(( SELECT job_stat_results.count
           FROM job_stat_results
          WHERE (job_stat_results.state IS NULL)), (0)::bigint) AS total,
    COALESCE(( SELECT job_stat_results.count
           FROM job_stat_results
          WHERE ((job_stat_results.state)::text = 'SCHEDULED'::text)), (0)::bigint) AS scheduled,
    COALESCE(( SELECT job_stat_results.count
           FROM job_stat_results
          WHERE ((job_stat_results.state)::text = 'ENQUEUED'::text)), (0)::bigint) AS enqueued,
    COALESCE(( SELECT job_stat_results.count
           FROM job_stat_results
          WHERE ((job_stat_results.state)::text = 'PROCESSING'::text)), (0)::bigint) AS processing,
    COALESCE(( SELECT job_stat_results.count
           FROM job_stat_results
          WHERE ((job_stat_results.state)::text = 'FAILED'::text)), (0)::bigint) AS failed,
    COALESCE(( SELECT job_stat_results.count
           FROM job_stat_results
          WHERE ((job_stat_results.state)::text = 'SUCCEEDED'::text)), (0)::bigint) AS succeeded,
    COALESCE(( SELECT ((jm.value)::character(10))::numeric(10,0) AS value
           FROM mail.jobrunr_metadata jm
          WHERE ((jm.id)::text = 'succeeded-jobs-counter-cluster'::text)), (0)::numeric) AS alltimesucceeded,
    COALESCE(( SELECT job_stat_results.count
           FROM job_stat_results
          WHERE ((job_stat_results.state)::text = 'DELETED'::text)), (0)::bigint) AS deleted,
    ( SELECT count(*) AS count
           FROM mail.jobrunr_backgroundjobservers) AS nbrofbackgroundjobservers,
    ( SELECT count(*) AS count
           FROM mail.jobrunr_recurring_jobs) AS nbrofrecurringjobs;


ALTER TABLE mail.jobrunr_jobs_stats OWNER TO root;

--
-- TOC entry 227 (class 1259 OID 21122)
-- Name: jobrunr_migrations; Type: TABLE; Schema: mail; Owner: root
--

CREATE TABLE mail.jobrunr_migrations (
    id character(36) NOT NULL,
    script character varying(64) NOT NULL,
    installedon character varying(29) NOT NULL
);


ALTER TABLE mail.jobrunr_migrations OWNER TO root;

--
-- TOC entry 3238 (class 2606 OID 21150)
-- Name: jobrunr_backgroundjobservers jobrunr_backgroundjobservers_pkey; Type: CONSTRAINT; Schema: mail; Owner: root
--

ALTER TABLE ONLY mail.jobrunr_backgroundjobservers
    ADD CONSTRAINT jobrunr_backgroundjobservers_pkey PRIMARY KEY (id);


--
-- TOC entry 3231 (class 2606 OID 21133)
-- Name: jobrunr_jobs jobrunr_jobs_pkey; Type: CONSTRAINT; Schema: mail; Owner: root
--

ALTER TABLE ONLY mail.jobrunr_jobs
    ADD CONSTRAINT jobrunr_jobs_pkey PRIMARY KEY (id);


--
-- TOC entry 3242 (class 2606 OID 21175)
-- Name: jobrunr_metadata jobrunr_metadata_pkey; Type: CONSTRAINT; Schema: mail; Owner: root
--

ALTER TABLE ONLY mail.jobrunr_metadata
    ADD CONSTRAINT jobrunr_metadata_pkey PRIMARY KEY (id);


--
-- TOC entry 3225 (class 2606 OID 21126)
-- Name: jobrunr_migrations jobrunr_migrations_pkey; Type: CONSTRAINT; Schema: mail; Owner: root
--

ALTER TABLE ONLY mail.jobrunr_migrations
    ADD CONSTRAINT jobrunr_migrations_pkey PRIMARY KEY (id);


--
-- TOC entry 3236 (class 2606 OID 21145)
-- Name: jobrunr_recurring_jobs jobrunr_recurring_jobs_pkey; Type: CONSTRAINT; Schema: mail; Owner: root
--

ALTER TABLE ONLY mail.jobrunr_recurring_jobs
    ADD CONSTRAINT jobrunr_recurring_jobs_pkey PRIMARY KEY (id);


--
-- TOC entry 3239 (class 1259 OID 21151)
-- Name: jobrunr_bgjobsrvrs_fsthb_idx; Type: INDEX; Schema: mail; Owner: root
--

CREATE INDEX jobrunr_bgjobsrvrs_fsthb_idx ON mail.jobrunr_backgroundjobservers USING btree (firstheartbeat);


--
-- TOC entry 3240 (class 1259 OID 21152)
-- Name: jobrunr_bgjobsrvrs_lsthb_idx; Type: INDEX; Schema: mail; Owner: root
--

CREATE INDEX jobrunr_bgjobsrvrs_lsthb_idx ON mail.jobrunr_backgroundjobservers USING btree (lastheartbeat);


--
-- TOC entry 3226 (class 1259 OID 21136)
-- Name: jobrunr_job_created_at_idx; Type: INDEX; Schema: mail; Owner: root
--

CREATE INDEX jobrunr_job_created_at_idx ON mail.jobrunr_jobs USING btree (createdat);


--
-- TOC entry 3227 (class 1259 OID 21168)
-- Name: jobrunr_job_rci_idx; Type: INDEX; Schema: mail; Owner: root
--

CREATE INDEX jobrunr_job_rci_idx ON mail.jobrunr_jobs USING btree (recurringjobid);


--
-- TOC entry 3228 (class 1259 OID 21138)
-- Name: jobrunr_job_scheduled_at_idx; Type: INDEX; Schema: mail; Owner: root
--

CREATE INDEX jobrunr_job_scheduled_at_idx ON mail.jobrunr_jobs USING btree (scheduledat);


--
-- TOC entry 3229 (class 1259 OID 21135)
-- Name: jobrunr_job_signature_idx; Type: INDEX; Schema: mail; Owner: root
--

CREATE INDEX jobrunr_job_signature_idx ON mail.jobrunr_jobs USING btree (jobsignature);


--
-- TOC entry 3232 (class 1259 OID 21193)
-- Name: jobrunr_jobs_state_updated_idx; Type: INDEX; Schema: mail; Owner: root
--

CREATE INDEX jobrunr_jobs_state_updated_idx ON mail.jobrunr_jobs USING btree (state, updatedat);


--
-- TOC entry 3234 (class 1259 OID 21187)
-- Name: jobrunr_recurring_job_created_at_idx; Type: INDEX; Schema: mail; Owner: root
--

CREATE INDEX jobrunr_recurring_job_created_at_idx ON mail.jobrunr_recurring_jobs USING btree (createdat);


--
-- TOC entry 3233 (class 1259 OID 21134)
-- Name: jobrunr_state_idx; Type: INDEX; Schema: mail; Owner: root
--

CREATE INDEX jobrunr_state_idx ON mail.jobrunr_jobs USING btree (state);


-- Completed on 2023-03-18 22:41:13

--
-- PostgreSQL database dump complete
--

