## Message deleter
Dieser Bot löscht alle Nachrichten nache einem Timeout von 24h. Auch wenn der Bot während dieser Zeit neugestartet wird, der Channel zwischendrinnen unlesbar wird, oder der Bot Nachrichten zu Gesicht bekommt welche älter als 2 Wochen sind.

## Setup
`config.json`: 
```json5
{
  "token":"AIUEOHGPOFAIHJFÖDUAIOFEJLAUJIL" /* Das Bot token. Siehe https://discordapp.com/developers/applications/me */,
  "channel": "467792764785000459" /* Die Channel Id. Aktiviere Developer Modus in deinen Discord Einstellungen -> Appearance -> Developer Mode und rechtsklicke den kanal -> copy id*/,
  "deletePinned": false /* Falls `false` werden gepinnte Nachrichten nicht gelöscht.. */
}
```
Command: 
```bash
java -jar message-deleter-all.jar
```
