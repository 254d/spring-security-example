INSERT INTO customer
(
   id,
   name,
   password,
   role
)
VALUES
(
   'admin',
   'Administrator',
   '{bcrypt}$2a$10$0NiruiEbcF14TCxPMINe9O3P5lqH4.RvFPDie3OtuELoROQVZbjSK',
   'ROLE_ADMIN'
);
INSERT INTO customer
(
   id,
   name,
   password,
   role
)
VALUES
(
   'user',
   'User Name',
   '{bcrypt}$2a$10$0NiruiEbcF14TCxPMINe9O3P5lqH4.RvFPDie3OtuELoROQVZbjSK',
   'ROLE_USER'
);