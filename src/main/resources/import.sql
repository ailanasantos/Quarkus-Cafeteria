-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into cafe (sabor, quantidade, temperatura) values ('Capuccino','1','quente');
insert into cafe (sabor, quantidade, temperatura) values ('Baunilha latte','1','quente');
insert into cafe (sabor, quantidade, temperatura) values ('Latte Macchiato','1','quente');
insert into cafe (sabor, quantidade, temperatura) values ('Cafe amricano','2','frio');
insert into cafe (sabor, quantidade, temperatura) values ('Frappuccino de avela','1','frio');