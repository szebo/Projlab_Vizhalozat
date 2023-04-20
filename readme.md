```js
console.log(teamLogo)

 _____                         _____  _                
/ ____|                       |  __ \(_)               
| |     __ _ _ __ _ __   ___  | |  | |_  ___ _ __ ___  
| |    / _` | '__| '_ \ / _ \ | |  | | |/ _ \ '_ ` _ \
| |___| (_| | |  | |_) |  __/ | |__| | |  __/ | | | | |
 \_____\__,_|_|  | .__/ \___| |_____/|_|\___|_| |_| |_|
                 | |                                   
                 |_|                                   
```
# **Sivatagi Vízhálózat**

---
# *Fejlesztők*
* **Szentesi Botond**
* **Németh Csongor**
* **Konrád András Noel**
* **Tömösi Botond**
* **Sinka Martin**
---
# *Feladat leírás*

```
A drukmákori sivatagon át bonyolult csőrendszer szállítja a vizet a hegyi forrásokból a sivatagon túl elterülő 
városok ciszternáiba. A csőrendszer egyszerű, elágazás nélküli csövekből és a csövekhez csatlakozó aktív elemekből 
(forrás, ciszterna, napelemmel működő vízátemelő pumpa stb.) áll. Egy pumpa több (de a pumpára jellemző véges számú) 
csövet is összeköthet, és minden pumpán külön-külön állítható, hogy éppen melyik belekötött csőből melyik másik csőbe 
pumpáljon, azonban egyszerre csak egy bemenete és egy kimenete lehet. A többi rákötött cső eközben el van zárva. 
A pumpák véletlen időközönként el tudnak romlani, ilyenkor megszűnik az adott pumpánál a vízáramlás. 
A pumpák mindegyike rendelkezik egy víztartállyal, amit a víz átemelése közben használ átmeneti tárolóként. 
A pumpa csak akkor tud vizet pumpálni egy csőbe, ha a cső szabad kapacitása ezt lehetővé teszi.
A csőhálózat bővíthető, változtatható. A csövek kellően rugalmasak ahhoz, hogy az egyik végüket lecsatlakoztatva egy 
másik aktív elemhez elvihetők és ott felcsatlakoztathatók legyenek. A ciszternáknál folyamatosan készülnek 
az új csövek, amelyek egyik vége a ciszternához kapcsolódik, a másik azonban szabad. A szabad végű csövekből a csőbe 
betáplált víz a homokba folyik.

A csőhálózatot a szerelők tartják karban. Ők javítják meg az elromlott pumpákat, ők állítják át a pumpákat, 
hogy mindig a lehető legtöbb víz tudjon áthaladni a hálózaton, és ha egy cső kilyukad, az ő dolguk a cső 
megfoltozása is. A kilyukadt csövekből a víz kifolyik, a csövek végén lévő pumpához már nem jut belőle. 
A szerelők dolga a ciszternáknál lévő szabad csövekkel a hálózat kapacitásának növelése. A szerelők a ciszternáknál 
magukhoz tudnak venni új pumpát is, amit egy cső közepén tudnak elhelyezni. A csövet ehhez ketté kell vágni, 
és a két végét a pumpához kell csatlakoztatni.

A hálózaton élnek a nomád szabotőrök is, akik a pumpákat tudják átállítani és a csöveket szokták kilyukasztani.

Mivel a sivatag veszélyes hely, a szerelők és a szabotőrök csak a csőhálózaton haladhatnak. 
A pumpáknál kikerülhetik egymást, de a csöveken már nem tudnak elmenni egymás mellett, 
egy csövön egyszerre csak egy ember állhat.

A játékot a két csapat legalább 2-2 játékossal játssza. 
A szabotőrök dolga, hogy minél több víz folyjon el a lyukakon, a szerelők pedig azon dolgoznak, 
hogy minél több víz jusson a ciszternákba. Az a csapat nyer, amelyik a játék végére több vizet szerez.
```

##  [*Változtatások*](https://www.iit.bme.hu/file/11582/feladat "iit.bme.hu")
- [x] Szerelő is tud lyukasztani.
- [ ] Foltozott cső véletlen hosszúságú ideig nem ~~lyukadhat~~ lyukasztható ki.
- [ ] A csöveknek mindkét vége egyidőben le lehet csatolva.
- [x] A szabotőr azt a csövet, amin áll, rövid időre csúszóssá tudja tenni. Ilyenkor aki rálép, véletlenszerűen a cső 
valamelyik végéhez kapcsolódó elemre kerül.
- [x] Mind a szabotőrök, mind a szerelők azt a csövet, amin állnak, rövid időre ragadóssá tudják tenni.
Aki legközelebb rálép, egy ideig nem tud továbblépni.

