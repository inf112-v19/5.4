# Oblig 3
Innleveringsfrist: 08.03.2019 kl. 16:00

## Prosjekt og prosjektstruktur

### Roller i teamet

Jarle er teamlead og Marie er kundekontakt.

Rollene fungerer greit.

Teamlead har hatt mer kontroll på prosjektet og har det siste ordet på
hvordan ting skal være. Han dirigerer teamet og passer på at alle har
noe å jobbe med.

Kundekontakt har kommunikasjonen opp mot kunden. Det har vært lite
bruk for kundekontakt, men Marie har tatt litt mer ansvar for å passe
på at vi får på plass det som manglet fra forrige innlevering.

### Erfaringer team-messig

Vi lager litt for store oppgaver til hver person. Dette fører til at
det blir litt for omfattende og mye for en enkelt person å jobbe med.

Vi kan bli flinkere til å legge mer planer og klarere planer for hva
som skal gjøres fremover.

### Gruppedynamikk

God gruppedynamikk, alle føler de kan si det de tenker på og komme med
tanker og ideer. Gruppedynamikken har blitt bedre jo mer vi har jobbet
sammen og blitt kjent med hverandre.

### Kommunikasjon

I og med at vi bruker Discord, er det lett å få tak i noen eller få
hjelp når du står fast. Man får raskt svar fra de man kommuniserer med.

Vi har også 2 gruppemøter i uken, hvor vi snakker om hva vi har jobbet
med, hva vi sliter med og tar opp ting hvis det er noe vi trenger
hjelp med eller lurer på.

### Retropekt

Vi har delt opp i litt for store oppgaver til hver person. Dette er
noe vi må bli flinkere på, og klare å stykke opp oppgavene i små biter
som gjør det enklere for hver enkelt å føle at de blir ferdig med noe
og får en mestringsfølelse.

Vi er litt dårlige på å oppdatere projectboard. Her må vi bli flinkere
til å lage en TODO hivs det er noe vi oppdager at vi må fikse eller
noe som må gjøres.

Alle får tildelt forskjellige oppgaver som de sitter og jobber med
hver for seg, men til en viss grad. Der vi ser det er nødvendig at
flere sitter og jobber sammen, gjør de det.


### Forskjell på commits

Jarle har nok en del flere commits enn de andre. Men ellers er det
ganske jevnt fordelt. Dette er fordi vi har hatt en del problemer med
filer som han har fikset opp i.

### Møtereferat

Vi ble bevisstgjort på møtereferatene 27. Feburar. Siden denne dagen
har vi skrevet møtereferat for alle våre møter. Disse ligger under
mappen Minutes-of-meeting.

### Tre forbedringspunkter til neste sprint

- Bli flinkere til å dele opp oppgavene i enda mindre biter

- Oppdatere projectboard med hensyn på å lage små oppgaver

- Jarle fikse alarmen slik at han dukker opp på møte ;)

## Krav

### Faktiske oppgaver

#### Mål for perioden

- Videreutvikle en smart spill-logikk som er effektiv og god å jobbe med framover.

- Videreutvikle GUI og introdusere ny grafikk. Lære mer om LibGdx.

- Kunne kjøre spillet og teste hvordan grafikken og lyden ser ut i praksis.

(Logikken og GUI jobber enda i stor grad individuelt, da vi som gruppe har tenkt at det er bedre å få på plass god funksjonalitet individuelt før vi får dem til å kommunisere sammen. Vi har som mål til neste periode/innlevering å få på plass dette og å få kjørt ordentlige runder etc.)

#### Vi har hatt fokus på:

- Spill struktur, komme i gang med alle fasene.

- Board, JSON generator som lager en board array fra en json fil som
  skal inneholde startbrettet.

- Actions, kortene har actions og at det går an å utføre disse

- GUI, få på plass grafikk og lyd, design av elementer fra spillet som feks. kort

- Oppdatere Wiki: Vi har hatt fokus på å oppdatere informasjoen om hver fase.

#### Krav fra kunde

- Kunne få alle typene bevegelseskort:
  - Klart
  - Tester er laget - `PlayerActionTest`
    - Tester at actions kan utføres på en spiller
    - Tester action fra kort og utføre på en spiller

- Dele ut 9 kort:
  - Klart
  - Tester er laget - 

- Velge 5 kort (godkjenne valg/si “nå er jeg klar”): Done

- Eksekvere program utfra valgte kort: Not Done

- Besøke flagg: Not Done

- Hvis robot går av brettet blir den ødelagt og går tilbake til siste backup: Not Done

- Oppdatere backup hvis robot blir stående på skiftenøkkelrute i
  slutten av en fase: Not done

- Flytte backup ved besøk på flagg: Not Done

- Kunne spille en fullverdig runde med alle faser: Not Done

- Få nye kort til ny runde: Almost done

#### Forløpig klassediagram

![RoboRallyDiagram](https://user-images.githubusercontent.com/38794357/54684655-30678d80-4b15-11e9-8eab-d1bbeb20aed9.png)

