DROP SCHEMA if EXISTS chat CASCADE;

CREATE SCHEMA if NOT EXISTS chat;

CREATE TABLE chat.users
(
    id       SERIAL PRIMARY KEY,
    login    TEXT UNIQUE NOT NULL,
    password TEXT        NOT NULL
);

CREATE TABLE chat.chatroom
(
    id    SERIAL PRIMARY KEY,
    name  TEXT                           NOT NULL,
    owner INT REFERENCES chat.users (id) NOT NULL
);

CREATE TABLE chat.message
(
    id       SERIAL PRIMARY KEY,
    author   INT REFERENCES chat.users (id)    NOT NULL,
    chatroom INT REFERENCES chat.chatroom (id) NOT NULL,
    message  TEXT                              NOT NULL,
    date     TIMESTAMP default current_timestamp
);

