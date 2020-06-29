CREATE TABLE IF NOT EXISTS beskjed (
    id serial primary key,
    systembruker character varying(100),
    eventtidspunkt timestamp without time zone,
    fodselsnummer character varying(50),
    eventid character varying(50),
    grupperingsid character varying(100),
    tekst character varying(500),
    link character varying(200),
    sikkerhetsnivaa integer,
    sistoppdatert timestamp without time zone,
    aktiv boolean,
    synligfremtil timestamp without time zone,
    uid character varying(100)
);

CREATE TABLE IF NOT EXISTS oppgave (
    id serial primary key,
    systembruker character varying(100),
    eventtidspunkt timestamp without time zone,
    fodselsnummer character varying(50),
    eventid character varying(50),
    grupperingsid character varying(100),
    tekst character varying(500),
    link character varying(200),
    sikkerhetsnivaa integer,
    sistoppdatert timestamp without time zone,
    aktiv boolean
);

CREATE TABLE IF NOT EXISTS innboks (
    id serial primary key,
    systembruker character varying(100),
    eventtidspunkt timestamp without time zone,
    fodselsnummer character varying(50),
    eventid character varying(50),
    grupperingsid character varying(100),
    tekst character varying(500),
    link character varying(200),
    sikkerhetsnivaa integer,
    sistoppdatert timestamp without time zone,
    aktiv boolean
);

CREATE TABLE IF NOT EXISTS done (
    id serial primary key,
    systembruker character varying(100),
    eventtidspunkt timestamp without time zone,
    fodselsnummer character varying(50),
    eventid character varying(50),
    grupperingsid character varying(100)
);

ALTER TABLE BESKJED ADD CONSTRAINT beskjedEventIdSystembruker UNIQUE (eventid, systembruker);
ALTER TABLE OPPGAVE ADD CONSTRAINT oppgaveEventIdSystembruker UNIQUE (eventid, systembruker);
ALTER TABLE INNBOKS ADD CONSTRAINT innboksEventIdSystembruker UNIQUE (eventid, systembruker);

CREATE OR REPLACE VIEW brukernotifikasjon_view AS
SELECT eventId, systembruker, 'beskjed' as type, fodselsnummer, aktiv FROM BESKJED
UNION
SELECT eventId, systembruker, 'oppgave' as type, fodselsnummer, aktiv FROM OPPGAVE
UNION
SELECT eventId, systembruker, 'innboks' as type, fodselsnummer, aktiv FROM INNBOKS;