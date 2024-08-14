insert into users(id, active, email, first_name, last_name, password)
values(1, 1, "admin@example.com", "Admin", "Admin","098f6bcd4621d373cade4e832627b4f6");


insert into brands (id, name)
values (1, "Toyota"),
       (2, "Infinity"),
       (3, "Mercedes");


insert into models(id, model_category, brand_entity_id, name)
values(1, "CAR", 1, "Corola"),
      (2, "CAR", 1, "Yaris Cross"),
      (3, "CAR", 2, "XC30"),
      (4, "CAR", 3, "A160")
