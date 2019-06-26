DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, datetime, description, calories) VALUES
  (100001, '2019-06-24 10:00:00', 'Завтрак (Админ)', 500),
  (100001, '2019-06-24 14:00:00', 'Обед (Админ)', 1000),
  (100001, '2019-06-24 20:00:00', 'Ужин (Админ)', 500),
  (100000, '2019-06-25 10:00:00', 'Завтрак', 500),
  (100000, '2019-06-25 14:00:00', 'Обед', 1100),
  (100000, '2019-06-25 20:00:00', 'Ужин', 600),
  (100000, '2019-06-26 10:00:00', 'Завтрак', 500),
  (100000, '2019-06-26 14:00:00', 'Обед', 900),
  (100000, '2019-06-26 20:00:00', 'Ужин', 600);
