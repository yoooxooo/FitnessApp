\c products
CREATE SCHEMA products AUTHORIZATION root;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 21078)
-- Name: portion_product; Type: TABLE; Schema: products; Owner: root
--

CREATE TABLE products.portion_product (
    id_product uuid,
    id_portion bigint NOT NULL
);


ALTER TABLE products.portion_product OWNER TO root;

--
-- TOC entry 216 (class 1259 OID 21083)
-- Name: portions; Type: TABLE; Schema: products; Owner: root
--

CREATE TABLE products.portions (
    id bigint NOT NULL,
    weight integer
);


ALTER TABLE products.portions OWNER TO root;

--
-- TOC entry 217 (class 1259 OID 21088)
-- Name: products; Type: TABLE; Schema: products; Owner: root
--

CREATE TABLE products.products (
    id uuid NOT NULL,
    calories integer,
    carbohydrates double precision,
    dt_create timestamp(6) with time zone,
    fats double precision,
    proteins double precision,
    title character varying(255),
    dt_update timestamp(6) with time zone,
    weight integer
);


ALTER TABLE products.products OWNER TO root;

--
-- TOC entry 218 (class 1259 OID 21093)
-- Name: recipe_portion; Type: TABLE; Schema: products; Owner: root
--

CREATE TABLE products.recipe_portion (
    id_recipe uuid NOT NULL,
    id_portion bigint NOT NULL
);


ALTER TABLE products.recipe_portion OWNER TO root;

--
-- TOC entry 219 (class 1259 OID 21096)
-- Name: recipes; Type: TABLE; Schema: products; Owner: root
--

CREATE TABLE products.recipes (
    id uuid NOT NULL,
    dt_create timestamp(6) with time zone,
    title character varying(255),
    dt_update timestamp(6) with time zone
);


ALTER TABLE products.recipes OWNER TO root;

--
-- TOC entry 3343 (class 0 OID 21078)
-- Dependencies: 215
-- products for Name: portion_product; Type: TABLE products; Schema: products; Owner: root
--

COPY products.portion_product (id_product, id_portion) FROM stdin;
\.


--
-- TOC entry 3344 (class 0 OID 21083)
-- Dependencies: 216
-- products for Name: portions; Type: TABLE products; Schema: products; Owner: root
--

COPY products.portions (id, weight) FROM stdin;
\.


--
-- TOC entry 3345 (class 0 OID 21088)
-- Dependencies: 217
-- products for Name: products; Type: TABLE products; Schema: products; Owner: root
--

COPY products.products (id, calories, carbohydrates, dt_create, fats, proteins, title, dt_update, weight) FROM stdin;
\.


--
-- TOC entry 3346 (class 0 OID 21093)
-- Dependencies: 218
-- products for Name: recipe_portion; Type: TABLE products; Schema: products; Owner: root
--

COPY products.recipe_portion (id_recipe, id_portion) FROM stdin;
\.


--
-- TOC entry 3347 (class 0 OID 21096)
-- Dependencies: 219
-- products for Name: recipes; Type: TABLE products; Schema: products; Owner: root
--

COPY products.recipes (id, dt_create, title, dt_update) FROM stdin;
\.


--
-- TOC entry 3190 (class 2606 OID 21082)
-- Name: portion_product portion_product_pkey; Type: CONSTRAINT; Schema: products; Owner: root
--

ALTER TABLE ONLY products.portion_product
    ADD CONSTRAINT portion_product_pkey PRIMARY KEY (id_portion);


--
-- TOC entry 3192 (class 2606 OID 21087)
-- Name: portions portions_pkey; Type: CONSTRAINT; Schema: products; Owner: root
--

ALTER TABLE ONLY products.portions
    ADD CONSTRAINT portions_pkey PRIMARY KEY (id);


--
-- TOC entry 3194 (class 2606 OID 21092)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: products; Owner: root
--

ALTER TABLE ONLY products.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- TOC entry 3196 (class 2606 OID 21100)
-- Name: recipes recipes_pkey; Type: CONSTRAINT; Schema: products; Owner: root
--

ALTER TABLE ONLY products.recipes
    ADD CONSTRAINT recipes_pkey PRIMARY KEY (id);


--
-- TOC entry 3197 (class 2606 OID 21106)
-- Name: portion_product fke637a7itpiyldjhug12ia259i; Type: FK CONSTRAINT; Schema: products; Owner: root
--

ALTER TABLE ONLY products.portion_product
    ADD CONSTRAINT fke637a7itpiyldjhug12ia259i FOREIGN KEY (id_portion) REFERENCES products.portions(id);


--
-- TOC entry 3199 (class 2606 OID 21111)
-- Name: recipe_portion fkmsbxn59hq7yvgc9mc17tfqo9v; Type: FK CONSTRAINT; Schema: products; Owner: root
--

ALTER TABLE ONLY products.recipe_portion
    ADD CONSTRAINT fkmsbxn59hq7yvgc9mc17tfqo9v FOREIGN KEY (id_portion) REFERENCES products.portions(id);


--
-- TOC entry 3198 (class 2606 OID 21101)
-- Name: portion_product fkorl43xihef04v7978ivjo6uh8; Type: FK CONSTRAINT; Schema: products; Owner: root
--

ALTER TABLE ONLY products.portion_product
    ADD CONSTRAINT fkorl43xihef04v7978ivjo6uh8 FOREIGN KEY (id_product) REFERENCES products.products(id);


--
-- TOC entry 3200 (class 2606 OID 21116)
-- Name: recipe_portion fktij09hqhcty0yux9tijsrg2q0; Type: FK CONSTRAINT; Schema: products; Owner: root
--

ALTER TABLE ONLY products.recipe_portion
    ADD CONSTRAINT fktij09hqhcty0yux9tijsrg2q0 FOREIGN KEY (id_recipe) REFERENCES products.recipes(id);


-- Completed on 2023-03-17 14:55:24

--
-- rootQL database dump complete
--

