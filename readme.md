# Mock für SIMI Rest Service Endpunkte

## Endpunkt /pubsignal

Der Service-Endpunkt liegt auf dem Pfad **app/rest/pubsignal**. Bei lokalem Starten üblicherweise auf **http://localhost:8080/app/rest/pubsignal**.

Implementiert sind die HTTP-Operationen PUT und GET:

* Mit PUT signalisiert der Publisher die erfolgreiche Publikation.
* GET ist zwecks Dokumentation ebensfalls implementiert. Es zeigt die Strukturierung des Json "Payload:
  * "dataIdent": Identifikator der Datenbereitstellung
  * "published": ISO 8601 formatierter Timestamp-String des Publikationszeitpunktes
  * "partIdentifiers" ist optional und wird zwecks Erläuterung beim GET mal zurückgegeben, mal nicht. --> Einfach mehrmals GET ausführen um beide Varianten zu sehen.

### PUT-Aufruf:

    curl -X PUT \
    -H "Content-Type: application/json" \
    -d '{"dataIdent":"ch.so.afu.gewaesserschutz","published":"2021-12-23T14:54:49.050062", "partIdentifiers":["224","225"]}' \
    'http://localhost:8080/app/rest/pubsignal'

Bei Fehlern wird der treffende Status-Code <> 200 sowie eine Fehlermeldung zurückgegeben.
Kann im Mock provoziert werden durch die Übergabe leerer Strings - Bsp.: `{"dataIdent":""}`

### Log

Im Log wird bei Empfang der Nachricht die folgende Zeile ausgegeben:

    2021-11-17 12:05:19.792 INFO  [qtp148626113-19/app/admin] ch.so.agi.cubarest.web.rest.Controller - Submitted info: identifier: ch.so.agi.av, published: 2021-12-23T14:54:49.050062, parts: 224, 225
    
## Endpunkt /doc

Der Service-Endpunkt liegt auf dem Pfad **app/rest/doc**. Bei lokalem Starten üblicherweise auf **http://localhost:8080/app/rest/doc**.

### GET-Aufruf

    curl -X GET \
    -H "Content-Type: text/html" \
    'http://localhost:8080/app/rest/doc?dataident=ch.so.agi.mopublic&published=2021-12-23T14:50:59.825849' 



Bei Fehlern wird der treffende Status-Code <> 200 sowie eine Fehlermeldung zurückgegeben.
Kann im Mock provoziert werden durch Weglassen oder falsch benannten Parameter "dataident".

## Auth für beide Endpunkte

Die OpenAuth2-Authentifizierung ist im Mock zwecks einfachem Ausprobieren noch optional - produktiv dann zwingend. Benötigt werden zwei Benutzername/Passwort Paare für die Anforderung des Auth-Tokens. Für den Mock gelten die folgenden Paare (Für Integration / Produktion gelten andere):

* Globale Authentifizierung für die Token-Ausgabe
  * User: restid
  * Pass: restpass
  * Bemerkung: Wird bei der Anforderung als base64 codierter Basic-Auth Header übergeben
* Authentifizierung für den Service
  * User: admin
  * Pass: admin
  * Bemerkung: Für den übergebenen Benutzer wird das Ticket ausgestellt

POST für Ausstellung der Tickets:

    curl -X POST \
      http://localhost:8080/app/rest/v2/oauth/token \
      -u 'restid:restpass' \
      -H 'Content-Type: application/x-www-form-urlencoded' \
      -d 'grant_type=password&username=admin&password=admin'

Antwort mit "access_token":

    {"access_token":"n0rri21Kxbuekf2wuSPj0NSuMQw","token_type":"bearer","refresh_token":"lPgeTNwWTajKE1LEva4TkpHsSk4","expires_in":43199,"scope":"rest-api"}

PUT auf Endpunkt /pubsignal mit auth:

    curl -X PUT \
    -H 'Authorization: Bearer n0rri21Kxbuekf2wuSPj0NSuMQw' \
    -H "Content-Type: application/json" \
    -d '{"dataIdent":"ch.so.afu.gewaesserschutz","published":"2021-12-23T14:54:49.050062", "partIdentifiers":["224","225"]}' \
    'http://localhost:8080/app/rest/pubsignal'
    
GET auf Endpunkt /doc mit auth:   

    curl -X GET \
    -H 'Authorization: Bearer n0rri21Kxbuekf2wuSPj0NSuMQw' \
    -H "Content-Type: text/html" \
    'http://localhost:8080/app/rest/doc?dataident=ch.so.agi.mopublic&published=2021-12-23T14:50:59.825849'

## Mock beziehen und starten

Der Mock liegt als Java 11 fat jar unter "releases" vor (in diesem Repo).

Starten:

    java -jar app.jar

## CUBA-Platform Konfiguration (AGI)

Globaler User und Pass für Tokenanforderung konfiguriert in **web-app.properties in Web Module**:

    cuba.rest.client.id=restid
    cuba.rest.client.secret={noop}restpass

{noop} ist der Identifier für den Passwort Encoder (muss angegeben werden)

Für REST den gleichen Security-Kontext verwenden wie für generic UI:

    cuba.rest.securityScope = GENERIC_UI