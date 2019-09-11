insert into todo (id,createdBy,description,lastModifiedBy,title, createdDate,lastModifiedDate) values (null, null,'Desc1',null, 'Title1',CURRENT_TIMESTAMP(),null);
insert into todo (id,createdBy,description,lastModifiedBy,title, createdDate,lastModifiedDate) values (null, null,'Desc2',null, 'Title2',CURRENT_TIMESTAMP(),null);

insert into role(id,rolename) values (1,'ROLE_USER');
insert into role(id,rolename) values (2,'ROLE_FOO');

insert into appuser(id,email,firstname,lastname,password,username) values(1,'abc@gmail.com','John','Doe','password','user');
insert into appuser(id,email,firstname,lastname,password,username) values(2,'def@gmail.com','Jack','Daniel','password','admin');


insert into user_role(user_id,role_id) values (1,1);
insert into user_role(user_id,role_id) values (2,1);
insert into user_role(user_id,role_id) values (2,2);



