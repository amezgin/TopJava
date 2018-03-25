DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id) VALUES
  ('2018-03-01 11:00:00', 'завтрак', 500, 100000),
  ('2018-03-01 13:00:00', 'обед', 1000, 100000),
  ('2018-03-01 18:00:00', 'ужин', 500, 100000),
  ('2018-03-02 11:00:00', 'завтрак', 500, 100000),
  ('2018-03-02 13:00:00', 'обед', 1000, 100000),
  ('2018-03-02 18:00:00', 'ужин', 600, 100000),
  ('2018-03-01 11:00:00', 'завтрак', 500, 100001),
  ('2018-03-01 13:00:00', 'обед', 1000, 100001),
  ('2018-03-01 18:00:00', 'ужин', 500, 100001),
  ('2018-03-02 11:00:00', 'завтрак', 500, 100001),
  ('2018-03-02 13:00:00', 'обед', 1000, 100001),
  ('2018-03-02 18:00:00', 'ужин', 500, 100001);