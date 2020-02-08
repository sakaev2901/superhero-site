create table hero(
  id bigserial primary key,
  name varchar,
  description varchar,
  power integer,
  endurance integer,
  dexterity integer,
  photo_path varchar
);

create table ability (
  id bigserial primary key,
  name varchar,
  description varchar
);

create table hero_ability(
  hero_id bigint,
  ability_id bigint
);
