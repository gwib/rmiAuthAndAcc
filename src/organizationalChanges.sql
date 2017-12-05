DELETE From main.UserV1 WHERE Username='Bob';
UPDATE main.UserV1 SET permissions='000111111' where Username='George';
Insert into main.UserV1(Username, password, permissions) Values ('Henry', '1234', '110000000');
INSERT Into main.UserV1(Username, password, permissions) Values ('Ida', '1234', '111001000');

DELETE From main.UserV2 WHERE Username='Bob';
UPDATE main.UserV2 SET RoleID=2 where Username='George';
Insert into main.UserV2(Username, Password, RoleID) Values ('Henry', '1234', 4);
INSERT Into main.UserV2(Username, Password, RoleID) Values ('Ida', '1234', 3);

