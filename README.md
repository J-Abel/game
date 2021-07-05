Telepítés és indítás:

Figyelem! A telepítéshez, indításhoz Docker megléte szükséges. A parancsok linux környezetre íródtak, más operációs rendszeren eltérhetnek.

1. homework főkönyvtárban terminálban az alábbi parancs kiadásával a konténer buildelése: sudo docker build -t abel/game .

2. konténer indítása: sudo docker run -p 8080:8080 abel/game

3. homework főkönyvtárban start and guess one.postman_collection.json fileban postman collection teszteléshez.
	A start hívásakor a backend küld egy id-t. A guess hívásoknál ezt az id-t meg kell adni. 
	Ha nem létező id-t adunk meg, az api 500-as hibát ad vissza.
	Ha olyan id-tb adunk, amivel már volt helyes megfejtés, a backend over-t küld vissza.

4. H2 adatbázis elérése: http://localhost:8080/h2-console
	JDBC URL: jdbc:h2:mem:testdb
	user name: sa
	password: password
	