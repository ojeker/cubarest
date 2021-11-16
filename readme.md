# Proto für SIMI Rest Service

## Generic Rest

Globaler User und Pass für Tokenanforderung konfiguriert in **web-app.properties in Web Module**:

  cuba.rest.client.id=restid
  cuba.rest.client.secret={noop}restpass

{noop} ist der Identifier für den Passwort Encoder (muss angegeben werden)

base64 encoded global user/password tuple --> restid:restpass --> cmVzdGlkOnJlc3RwYXNz

Für REST den gleichen Security-Kontext verwenden wie für generic UI:

  cuba.rest.securityScope = GENERIC_UI

### Token 4 admin

    curl -X POST \
      http://localhost:8080/app/rest/v2/oauth/token \
      -H 'Authorization: Basic cmVzdGlkOnJlc3RwYXNz' \
      -H 'Content-Type: application/x-www-form-urlencoded' \
      -d 'grant_type=password&username=admin&password=admin'

ölkgjdföal

  curl -X GET \
  'http://localhost:8080/app/rest/v2/entities/sec$Role' \
  -H 'Authorization: Bearer <access_token>'

{"access_token":"3xMn-YILpwRNURP-glT2eXfkmZA","token_type":"bearer","refresh_token":"MWMeJIamkqrFSGGigkVHFSlV2hI","expires_in":43199,"scope":"rest-api"}bjsvwjek@JOS:~$ 



  curl -X GET \
  'http://localhost:8080/app/rest/v2/entities/sec$Role' \
  -H 'Authorization: Bearer 3xMn-YILpwRNURP-glT2eXfkmZA'

  curl -X GET \
  'http://localhost:8080/app/rest/v2/entities/cubarest_DataTheme' \
  -H 'Authorization: Bearer 3xMn-YILpwRNURP-glT2eXfkmZA'