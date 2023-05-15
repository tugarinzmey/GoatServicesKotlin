--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-05-15 17:01:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 3349 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 24577)
-- Name: Clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Clients" (
    id integer NOT NULL,
    "firstName" text NOT NULL,
    "lastName" text NOT NULL,
    email text NOT NULL,
    phone text,
    status text
);


ALTER TABLE public."Clients" OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 24576)
-- Name: Clients_is_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Clients_is_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Clients_is_seq" OWNER TO postgres;

--
-- TOC entry 3350 (class 0 OID 0)
-- Dependencies: 215
-- Name: Clients_is_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Clients_is_seq" OWNED BY public."Clients".id;


--
-- TOC entry 220 (class 1259 OID 24595)
-- Name: Payments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Payments" (
    id integer NOT NULL,
    "clientId" integer NOT NULL,
    "serviceId" integer NOT NULL,
    date date NOT NULL,
    quantity integer DEFAULT 1 NOT NULL,
    total money NOT NULL
);


ALTER TABLE public."Payments" OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24594)
-- Name: Payments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Payments_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Payments_id_seq" OWNER TO postgres;

--
-- TOC entry 3351 (class 0 OID 0)
-- Dependencies: 219
-- Name: Payments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Payments_id_seq" OWNED BY public."Payments".id;


--
-- TOC entry 218 (class 1259 OID 24586)
-- Name: Services; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Services" (
    id integer NOT NULL,
    name text NOT NULL,
    price money NOT NULL
);


ALTER TABLE public."Services" OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24585)
-- Name: Services_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Services_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Services_id_seq" OWNER TO postgres;

--
-- TOC entry 3352 (class 0 OID 0)
-- Dependencies: 217
-- Name: Services_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Services_id_seq" OWNED BY public."Services".id;


--
-- TOC entry 3184 (class 2604 OID 24580)
-- Name: Clients id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Clients" ALTER COLUMN id SET DEFAULT nextval('public."Clients_is_seq"'::regclass);


--
-- TOC entry 3186 (class 2604 OID 24598)
-- Name: Payments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Payments" ALTER COLUMN id SET DEFAULT nextval('public."Payments_id_seq"'::regclass);


--
-- TOC entry 3185 (class 2604 OID 24589)
-- Name: Services id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Services" ALTER COLUMN id SET DEFAULT nextval('public."Services_id_seq"'::regclass);


--
-- TOC entry 3339 (class 0 OID 24577)
-- Dependencies: 216
-- Data for Name: Clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Clients" (id, "firstName", "lastName", email, phone, status) FROM stdin;
\.


--
-- TOC entry 3343 (class 0 OID 24595)
-- Dependencies: 220
-- Data for Name: Payments; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Payments" (id, "clientId", "serviceId", date, quantity, total) FROM stdin;
\.


--
-- TOC entry 3341 (class 0 OID 24586)
-- Dependencies: 218
-- Data for Name: Services; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Services" (id, name, price) FROM stdin;
\.


--
-- TOC entry 3353 (class 0 OID 0)
-- Dependencies: 215
-- Name: Clients_is_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Clients_is_seq"', 1, false);


--
-- TOC entry 3354 (class 0 OID 0)
-- Dependencies: 219
-- Name: Payments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Payments_id_seq"', 1, false);


--
-- TOC entry 3355 (class 0 OID 0)
-- Dependencies: 217
-- Name: Services_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Services_id_seq"', 1, false);


--
-- TOC entry 3189 (class 2606 OID 24584)
-- Name: Clients Clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Clients"
    ADD CONSTRAINT "Clients_pkey" PRIMARY KEY (id);


--
-- TOC entry 3193 (class 2606 OID 24601)
-- Name: Payments Payments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT "Payments_pkey" PRIMARY KEY (id);


--
-- TOC entry 3191 (class 2606 OID 24593)
-- Name: Services Services_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Services"
    ADD CONSTRAINT "Services_pkey" PRIMARY KEY (id);


--
-- TOC entry 3194 (class 2606 OID 24602)
-- Name: Payments clientId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT "clientId" FOREIGN KEY ("clientId") REFERENCES public."Clients"(id);


--
-- TOC entry 3195 (class 2606 OID 24607)
-- Name: Payments serviceId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Payments"
    ADD CONSTRAINT "serviceId" FOREIGN KEY ("serviceId") REFERENCES public."Services"(id);


-- Completed on 2023-05-15 17:01:42

--
-- PostgreSQL database dump complete
--

