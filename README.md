# dittnav-event-cache-metrics-reporter

App som henter ut diverse statistikk fra dittnavs brukernotifikasjonscache. I miljø raporteres data til influx.
Appen er ment å kjøres som et program som kjører til completion og så termineres. I miljø sparkes appen i gang hver natt
kl. 01:00 UTC.

# Kom i gang
1. Bygge dittnav-event-aggregator:
    * bygge og kjøre tester: `gradle clean build`
2. Kjør henting av statistikk mot lokal database `gradle run`

# Feilsøking
Appen eier ikke tabellene, og har en implisitt avhengighet mot aggregator. Pass på at aggregator har opprettet tabellene først.

# Henvendelser

Spørsmål knyttet til koden eller prosjektet kan rettes mot https://github.com/orgs/navikt/teams/personbruker
