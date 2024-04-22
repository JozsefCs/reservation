insert into COMPANY(ID,COMPANY_NAME ,ADDRESS ,OPEN_TIME ,CLOSE_TIME )
values (991,'No Problemo Bt.','Nyíregyháza utca 1',to_timestamp('08:00', 'HH24:MI'),to_timestamp('16:00', 'HH24:MI'));

insert into COMPANY(ID,COMPANY_NAME ,ADDRESS ,OPEN_TIME ,CLOSE_TIME )
values (992,'HairCut Kft.','Haj utca 1',to_timestamp('10:00', 'HH24:MI'),to_timestamp('18:00', 'HH24:MI'));

insert into COMPANY(ID,COMPANY_NAME ,ADDRESS ,OPEN_TIME ,CLOSE_TIME )
values (993,'Élmény Bt.','Tst-köz 13',to_timestamp('00:00', 'HH24:MI'),to_timestamp('14:00', 'HH24:MI'));

insert into COMPANY(ID,COMPANY_NAME ,ADDRESS ,OPEN_TIME ,CLOSE_TIME )
values (994,'Katalin EV.','Metró tér 92',to_timestamp('16:00', 'HH24:MI'),to_timestamp('20:00', 'HH24:MI'));

insert into COMPANY(ID,COMPANY_NAME ,ADDRESS ,OPEN_TIME ,CLOSE_TIME )
values (995,'Kutyakozmetika EV.','Kiss Ernő sugárút 92',to_timestamp('17:00', 'HH24:MI'),to_timestamp('20:00', 'HH24:MI'));

insert into SERVICE(ID ,SERVICE_NAME ,DESCRIPTION ,PERIOD ,COMPANY_ENTITY_ID )
values (991,'Kozmetika (NAGY)','Nagy kutya szőrnyírás',120,995);

insert into SERVICE(ID ,SERVICE_NAME ,DESCRIPTION ,PERIOD ,COMPANY_ENTITY_ID )
values (992,'Kozmetika (KÖZEPES)','Közepes kutya szőrnyírás',90,995);

insert into SERVICE(ID ,SERVICE_NAME ,DESCRIPTION ,PERIOD ,COMPANY_ENTITY_ID )
values (993,'Kozmetika (KICSI)','Kis kutya szőrnyírás',60,995);

insert into SERVICE(ID ,SERVICE_NAME ,DESCRIPTION ,PERIOD ,COMPANY_ENTITY_ID )
values (994,'Férfi hajnyírás','Férfi hajnyírás',15,992);

insert into SERVICE(ID ,SERVICE_NAME ,DESCRIPTION ,PERIOD ,COMPANY_ENTITY_ID )
values (995,'Női hajnyírás','Női hajnyírás',35,992);

insert into RESERVATION (ID, MY_SERVICE_ENTITY_ID, START_TIME, END_TIME )
values (991,993,to_timestamp( '2024-03-25 17.00.00','YYYY-MM-DD HH24:MI:SS'),to_timestamp( '2024-03-25 18.00.00','YYYY-MM-DD HH24:MI:SS'));

insert into RESERVATION (ID, MY_SERVICE_ENTITY_ID, START_TIME, END_TIME )
values (992,992,to_timestamp( '2024-03-25 18.00.00','YYYY-MM-DD HH24:MI:SS'),to_timestamp( '2024-03-25 19.30.00','YYYY-MM-DD HH24:MI:SS'));

insert into RESERVATION (ID, MY_SERVICE_ENTITY_ID, START_TIME, END_TIME )
values (993,991,to_timestamp( '2024-03-26 17.00.00','YYYY-MM-DD HH24:MI:SS'), to_timestamp( '2024-03-26 19.00.00','YYYY-MM-DD HH24:MI:SS'));

insert into RESERVATION (ID, MY_SERVICE_ENTITY_ID, START_TIME, END_TIME )
values (994,993,to_timestamp( '2024-03-27 18.30.00','YYYY-MM-DD HH24:MI:SS'),to_timestamp( '2024-03-27 19.30.00','YYYY-MM-DD HH24:MI:SS'));

insert into RESERVATION (ID, MY_SERVICE_ENTITY_ID, START_TIME, END_TIME )
values (995,993,to_timestamp( '2024-03-27 18.30.00','YYYY-MM-DD HH24:MI:SS'),to_timestamp( '2024-03-27 19.30.00','YYYY-MM-DD HH24:MI:SS'));

insert into RESERVATION (ID, MY_SERVICE_ENTITY_ID, START_TIME, END_TIME )
values (996,994,to_timestamp( '2024-03-27 10.30.00','YYYY-MM-DD HH24:MI:SS'),to_timestamp( '2024-03-27 10.45.00','YYYY-MM-DD HH24:MI:SS'));

insert into RESERVATION (ID, MY_SERVICE_ENTITY_ID, START_TIME, END_TIME )
values (997,995,to_timestamp( '2024-03-27 11.00.00','YYYY-MM-DD HH24:MI:SS'),to_timestamp( '2024-03-27 11.35.00','YYYY-MM-DD HH24:MI:SS'));