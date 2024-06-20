CREATE TABLE subscriptions
(
    id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    chat_id  BIGINT       NOT NULL,
    username VARCHAR(255) NOT NULL
);