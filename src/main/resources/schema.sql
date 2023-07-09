CREATE TABLE IF NOT EXISTS `users` (
    id INTEGER AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    dob TIMESTAMP NOT NULL,
    primary key(id)
);

INSERT INTO `users`(name, dob) VALUES ('Alex Smith', CURRENT_TIMESTAMP);
INSERT INTO `users`(name, dob) VALUES ('Jacob Seed', CURRENT_TIMESTAMP);
INSERT INTO `users`(name, dob) VALUES ('Reynolds', CURRENT_TIMESTAMP);

CREATE TABLE IF NOT EXISTS posts (
    id INTEGER AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    post_title VARCHAR(100) NOT NULL,
    post_message VARCHAR(200) NOT NULL,
    primary key(id),
    foreign key(user_id) references users(id)
);

INSERT INTO posts(user_id, post_title, post_message) VALUES (1, 'Hello mom', 'This is a message to my mom :)');
INSERT INTO posts(user_id, post_title, post_message) VALUES (2, 'Java is awesome', 'I am trying to build a server on java :S');
INSERT INTO posts(user_id, post_title, post_message) VALUES (1, 'Eid mubarak!', 'Eid mubarak to everyone <3');
INSERT INTO posts(user_id, post_title, post_message) VALUES (2, 'Hello World', 'This is hello world from spring boot!');
INSERT INTO posts(user_id, post_title, post_message) VALUES (1, 'JS vs Java?', 'Who will win? Java or Javascript?');
INSERT INTO posts(user_id, post_title, post_message) VALUES (1, 'Du Bluest!', 'I am German ... haha :D');