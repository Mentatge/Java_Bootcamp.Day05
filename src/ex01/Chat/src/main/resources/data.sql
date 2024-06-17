INSERT INTO chat.users(login, password)
VALUES ('user1', 'password1');
INSERT INTO chat.users(login, password)
VALUES ('user2', 'password2');
INSERT INTO chat.users(login, password)
VALUES ('user3', 'password3');
INSERT INTO chat.users(login, password)
VALUES ('user4', 'password4');
INSERT INTO chat.users(login, password)
VALUES ('user5', 'password5');

INSERT INTO chat.chatroom(name, owner)
VALUES ('Chat1', (SELECT id FROM chat.users WHERE login = 'user1'));
INSERT INTO chat.chatroom(name, owner)
VALUES ('Chat2', (SELECT id FROM chat.users WHERE login = 'user2'));
INSERT INTO chat.chatroom(name, owner)
VALUES ('Chat3', (SELECT id FROM chat.users WHERE login = 'user3'));
INSERT INTO chat.chatroom(name, owner)
VALUES ('Chat4', (SELECT id FROM chat.users WHERE login = 'user4'));
INSERT INTO chat.chatroom(name, owner)
VALUES ('Chat5', (SELECT id FROM chat.users WHERE login = 'user5'));

INSERT INTO chat.message(author, chatroom, message)
VALUES ((SELECT id FROM chat.users WHERE login = 'user1'),
        (SELECT id FROM chat.chatroom WHERE owner = (SELECT id FROM chat.users WHERE login = 'user1')), 'message1');
INSERT INTO chat.message(author, chatroom, message)
VALUES ((SELECT id FROM chat.users WHERE login = 'user2'),
        (SELECT id FROM chat.chatroom WHERE owner = (SELECT id FROM chat.users WHERE login = 'user2')), 'message2');
INSERT INTO chat.message(author, chatroom, message)
VALUES ((SELECT id FROM chat.users WHERE login = 'user3'),
        (SELECT id FROM chat.chatroom WHERE owner = (SELECT id FROM chat.users WHERE login = 'user3')), 'message3');
INSERT INTO chat.message(author, chatroom, message)
VALUES ((SELECT id FROM chat.users WHERE login = 'user4'),
        (SELECT id FROM chat.chatroom WHERE owner = (SELECT id FROM chat.users WHERE login = 'user4')), 'message4');
INSERT INTO chat.message(author, chatroom, message)
VALUES ((SELECT id FROM chat.users WHERE login = 'user5'),
        (SELECT id FROM chat.chatroom WHERE owner = (SELECT id FROM chat.users WHERE login = 'user5')), 'message5');