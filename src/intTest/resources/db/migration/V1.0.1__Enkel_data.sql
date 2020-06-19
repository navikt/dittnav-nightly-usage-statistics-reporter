insert into beskjed(systembruker, eventTidspunkt, fodselsnummer, eventId, grupperingsid, tekst, link, sikkerhetsnivaa, sistOppdatert, aktiv, synligfremtil)
values
    ('DittNAV', NOW(), '12345', '1', '100123', 'Dette er en dialognotifikasjon til brukeren', 'https://nav.no/systemX', 4, NOW(), true, now() + 1),
    ('DittNAV', NOW(), '12345', '2', '100123', 'Dette er en annen notifikasjon', 'https://nav.no/systemX', 4, NOW(), true, now() + 1),
    ('DittNAV', NOW(), '12345', '3', '100123', 'Dette er en en en annen notifikasjon', 'https://nav.no/systemX', 4, NOW(), true, now() - 1),
    ('DittNAV', NOW(), '98765', '4', '100456', 'Dette er en annen bruker', 'https://nav.no/systemX', 4, NOW(), false, now() + 1);

insert into oppgave(systembruker, eventTidspunkt, fodselsnummer, eventId, grupperingsid, tekst, link, sikkerhetsnivaa, sistOppdatert, aktiv)
values
    ('DittNAV', NOW(), '12345', '11', '100123', 'Dette er en dialognotifikasjon til brukeren', 'https://nav.no/systemX', 4, NOW(), true),
    ('DittNAV', NOW(), '12345', '12', '100123', 'Dette er en annen notifikasjon', 'https://nav.no/systemX', 4, NOW(), false),
    ('DittNAV', NOW(), '98765', '13', '100456', 'Dette er en annen bruker', 'https://nav.no/systemX', 4, NOW(), true);

insert into innboks(systembruker, eventTidspunkt, fodselsnummer, eventId, grupperingsid, tekst, link, sikkerhetsnivaa, sistOppdatert, aktiv)
values
    ('DittNAV', NOW(), '74185', '21', '100123', 'Dette er en innboksvarsel til brukeren', 'https://nav.no/systemX', 4, NOW(), true),
    ('DittNAV', NOW(), '74185', '22', '100789', 'Dette er en annen notifikasjon', 'https://nav.no/systemX', 4, NOW(), false),
    ('DittNAV', NOW(), '74185', '23', '100789', 'Dette er samme fyren', 'https://nav.no/systemX', 4, NOW(), true),
    ('DittNAV', NOW(), '74185', '24', '100789', 'Dette er samme fyren igjen men inaktiv', 'https://nav.no/systemX', 4, NOW(), false);

insert into done(systembruker, eventtidspunkt, fodselsnummer, eventid, grupperingsid)
values
    ('DittNAV', now(), '12345', '999', '100123');