# Üzenetküldő

Egy webes üzenetküldő alkalmazás létrehozása. A rendszerbe felhasználók
regisztrálhatnak, akik bejelentkezés után üzeneteket küldhetnek egymásnak. A
felhasználók különböző csoportokba tartozhatnak, így lehetőség van csoporttagság
esetén a csoportnak is üzenetet küldeni. Az üzenetküldés üzenetszálakba szervezve
történik.

## Entitások

- [X] User (felhasználó)
- [ ] Group (felhasználói csoport)
- [X] Message (üzenet)
- [ ] MessageThread (üzenetszál)

## Modell

* User (id, name, email, password, address, birthdate, messagethreads)
* UserGroup(id, name, users, messagethread)
* Message(id, text, date, sender)
* MessageThread(id, messages)

## Plusz felületek

- [ ] Login felület
- [ ] Regisztrációs felület

## Elvárt felületek

- [ ] Adott felhasználóhoz kapcsolható üzenetszálak listázása
- [ ] Adott üzenetszálon belül az üzenetek listázása
- [ ] Csoportok létrehozása, szerkesztése, csoporttagság kezelésének megvalósítása
- [ ] Üzenetküldési lehetőség felhasználónak vagy csoportnak

## Összetett felületek

- [ ] Adott felhasználó számára küldött üzenetek száma, feladónként csoportosítva
- [ ] Legtöbb üzenetet küldő felhasználók
- [ ] Legtöbb üzenetet tartalmazó szálak
