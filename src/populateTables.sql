Delete from main.UserV1;
INSERT into main.UserV1 VALUES (2, 'Bob', '1234', '000111111');
INSERT into main.UserV1 VALUES (3, 'Cecilia', '1234', '11001000');
INSERT into main.UserV1 VALUES (4, 'Alice', '1234', '111111111');
INSERT into main.UserV1 VALUES (5, 'David', '1234', '110000000');
INSERT into main.UserV1 VALUES (6, 'Erica', '1234', '110000000');
INSERT into main.UserV1 VALUES (7, 'Fred', '1234', '110000000');
INSERT into main.UserV1 VALUES (9, 'Mia', '1234', '000000000');

Delete from main.UserV2;
INSERT into main.UserV2 VALUES (2, 'Bob', '1234', 2);
INSERT into main.UserV2 VALUES (3, 'Cecilia', '1234', 3);
INSERT into main.UserV2 VALUES (4, 'Alice', '1234', 1);
INSERT into main.UserV2 VALUES (5, 'David', '1234', 4);
INSERT into main.UserV2 VALUES (6, 'Erica', '1234', 4 );
INSERT into main.UserV2 VALUES (7, 'Fred', '1234', 4 );
INSERT into main.UserV2 VALUES (9, 'Mia', '1234', 0);

DELETE FROM main.Roles;
Insert into main.Roles Values (0, '000000000', 'noUser');
insert into main.Roles Values (1, '111111111', 'serverAdmin');
insert into main.Roles Values (2, '000111111', 'serviceTechnician');
insert into main.Roles Values (3, '111001000', 'powerUser');
insert into main.Roles Values (4, '110000000', 'basicUser');

Create VIEW main.userRoleJoin as
SELECT * from main.UserV2 LEFT join main.Roles on main.UserV2.RoleID=main.Roles.ID



