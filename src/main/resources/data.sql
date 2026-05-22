insert into pet_categories (name, description)
values ('Kleintiere', 'Kleine Heimtiere wie Kaninchen und Meerschweinchen.');
insert into pet_categories (name, description)
values ('Aquaristik', 'Tiere fuer Suesswasser-Aquarien.');
insert into pet_categories (name, description)
values ('Voegel', 'Voegel fuer Volieren und Innenhaltung.');

insert into pets (name, species, date_of_birth, price, available, category_id)
values ('Momo', 'Zwergkaninchen', '2025-09-14', 49.90, true,
        (select id from pet_categories where name = 'Kleintiere'));
insert into pets (name, species, date_of_birth, price, available, category_id)
values ('Pixel', 'Guppy', '2026-01-10', 4.50, true,
        (select id from pet_categories where name = 'Aquaristik'));
insert into pets (name, species, date_of_birth, price, available, category_id)
values ('Luna', 'Wellensittich', '2025-05-22', 34.00, false,
        (select id from pet_categories where name = 'Voegel'));

