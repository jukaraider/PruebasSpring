-- insert into tablename(col1, col2) values(val1, val2)

insert into users(id, name, email, disabled)
values(-1, 'John', 'john@gmail.com', false);

insert into users(id, name, email, disabled)
values(hibernate_sequence.NEXTVAL, 'Rod', 'rod@gmail.com', false);

insert into users(id, name, email, disabled)
values(hibernate_sequence.NEXTVAL, 'Becky', 'becky@gmail.com', true);
