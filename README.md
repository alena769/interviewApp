# alena769
Aplikace na založení nového klienta, úpravu, smazání a možnosti zobrazit veškeré klienty z databáze a možnosti filtrovat podle jména(křestní a příjemní), id, statusu a data založení.

Front end měl být HTML, CSS části z Bootstrap a Js a thymeleaf. 

Podařilo se mi založit nového klienta, plus funkce na vyhledávání, update a delete. Problém mám s jednou Query na vyhledání podle keyword. Původně jsem měla hledání jména zvlášď a příjmení zvlášť, ale chtěla jsem to udělat lépe. Zápis co jsem použila jsem našla na stackoverflow, jenže mě to hlásí chybu, našla jsem to na více místech a netuším co je za problém.

Aplikace fungovala dokud jsem nestáhla Bootstrap šablonu která měla udělaný login, když jsem nahrála všechny komponenty do složky tak se to rozsypalo. Po té co jsem to všechno vymazala tam ty chyby pořád jsou ... 

Původně jsem to celé psala v Java 15, ale našla jsem super návod jak to otestovat pomocí Diffiblue chtěla jsem to zkusit, jenže to lze pouze v Java 11 a nižší tak jsem JDK změnila na nižší. K testování jsem se vůbec nedostala.

Zdálo se to jako jednoduchý úkol, něco takového jsme v kurzu dělali, jenže já se rozhodla, že to chci zkusit udělat lépe a tam jsem docela narazila. Našla jsem pěkný návod jak udělat super login pomocí spring boot security, jenže v návodu jaksi chybělo co se má kde nastavit aby si to člověk pak mohl otestovat přes Postmana, s tím mi nakonec musel pomoci můj kamarád. Postamana jsem zvládla dohledat ale nastavení v aplication properties ne.
Na druhou stranu jsem ráda, že jsem to zkusila dost jsem se toho totiž naučila. Nikdy jsme neodesílali email z aplikace, hesla se mi tam šifrují je to prima.
Ještě jsem chtěla vložit potvrzující email do tabulkové html šablony, ale zdrojový kod co jsem našla na internetu vypadal nakonec šíleně, tak jsem to vymazala. 
V návodu byla ještě jedna část a do možnost vložení odkazu na aktivaci emailu (proto status aktivní a neaktivní), ale odesílali se tam tokeny a to se mi vůbec nepodařilo zprovoznit a chybu co tam byla se mi nepodařilo dohledat tak jsem to vynechala, i když by to bylo super. Původní plán totiž byl to navázat na status a podle toho zda došlo ke kliknutí na odkaz a potvrzení tak účet aktivovat. 

při hledání nějakých indícijí jsem narazila na Slf4j API a chtěla jsem to vyzkoušet jak by mi to mohlo pomoci v praxi - k tomu jsem se taky nedostala

nicméně jsem se i na té kostře mé vize naučila dost nového a jsem i ráda, že se mi to sypalo pod rukama