CREATE TABLE IF NOT EXISTS `users` (
    id INTEGER AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    dob TIMESTAMP NOT NULL,
    primary key(id)
);

INSERT INTO `users`(name, dob) VALUES ('Alex Smith', CURRENT_TIMESTAMP);
INSERT INTO `users`(name, dob) VALUES ('Jacob Seed', CURRENT_TIMESTAMP);
INSERT INTO `users`(name, dob) VALUES ('Reynolds', CURRENT_TIMESTAMP);