# Jugger-Videotool

Eine Software, die dabei hilft, Daten aus Juggervideos einzutragen, auszuwerten und bestimmte Werte automatisch zu berechnen.  


## Installation

### Voraussetzungen
- Java 17 oder neuer
- Maven installiert (`mvn -v` zum Prüfen)

### Projekt bauen
``` bash
mvn clean package
```

###  Datei ausführen
``` im Terminal
java -jar JuggerVideotool.jar
```
## Verwendung

Nach dem Start des JARs werden Sie gefragt, ob Sie:

1. Eine **vorhandene `.jugger`-Datei** öffnen möchten, oder  
2. Eine **neue Datei** erstellen möchten.

Nachdem Sie Ihre Wahl getroffen haben, wird ein **neues Fenster** geöffnet, das aus **drei Tabs** besteht:

1. Der erste Tab sieht folgendermasen aus:
![Erster Tab](https://postimg.cc/mhBWmqFt)
Hier kann man alle relevanten Informationen für ein Spiel eingeben. Dies sind momentan:
- Turniername
- eigenr Teamname
- gegnerischer Teamname
- Anzahl Züge
- Youtube Link für das Video
- Die Namen aller Spieler*innen

2. Der zweite Tab sieht folgendermasen aus:
![Zweiter Tab](https://postimg.cc/mhBWmqFt)
Hier kann man alle relevanten Informationen für ein Zug eingeben. Dies sind momentan:
- Namen der fünf Spielis
- Pomfpen für alle Spielis (ausser Läufi)
- Ist es ein Druckpunkt?
- Wurde das Duell gewonnen?
- gegnerische Pompfe
- Hatte das eigene Team grün

3. Der dritte Tab sieht folgendermasen aus:
![Dritter Tab](https://postimg.cc/mhBWmqFt)
Hier werden die Informationen genommen und drei Dinge berechnet:
- wieviel Prozent grün hatte das Team
- für jede Person einen eigenen Tab, indem Gewinnrate und wieviel Spielzeit berechnet wird
