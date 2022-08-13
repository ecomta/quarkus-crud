-- INSERT aqui é feito com aspas simples...
 -- Se eu colocar " (aspas duplas) ele vai pensar que é uma coluna...

 -- 'hibernate_sequence' é com aspas simples... Assim como o nome da categoria 'Premium A'

INSERT INTO categoria
    (id, nome)
VALUES
    (nextval('hibernate_sequence'), 'Programador'),
    (nextval('hibernate_sequence'), 'Comerciante'),
    (nextval('hibernate_sequence'), 'Engenheiro'),
    (nextval('hibernate_sequence'), 'Advogado');

INSERT INTO cliente
    (id, username, email, vatNumber, birthdate, categoria_id)
VALUES
    (random_uuid(), 'Ana',      'ana@gmail.com',    'ZZ111111111', DATE '2000-05-12', 1),
    (random_uuid(), 'Maria',    'maria@gmail.com',  'ZZ222222222', DATE '1996-03-25', 1),
    (random_uuid(), 'Paula',    'paula@gmail.com',  'ZZ333333333', DATE '2003-12-30', 2),
    (random_uuid(), 'Lais',     'lais@gmail.com',   'ZZ444444444', DATE '2010-09-13', 3);