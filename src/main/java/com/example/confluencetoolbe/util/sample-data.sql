INSERT INTO `user` VALUES(1,'admin@gmail.com','admin','$2a$10$gqHrslMttQWSsDSVRTK1OehkkBiXsJ/a4z2OURU./dizwOQu5Lovu','admin'),
                         (2,'test@gmail.com','test' ,'$2a$12$TYSPPDsgR1T9vpgMSavOteZoqzjGVLt7rzsqKLrGL4oQdE3rWDNru','test');

INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_DEV');

INSERT INTO `user_roles` VALUES (1,1),(2,2);
