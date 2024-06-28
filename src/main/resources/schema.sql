CREATE TABLE IF NOT EXISTS transactions
(
    id           UUID DEFAULT RANDOM_UUID() PRIMARY KEY ,
    user_id      VARCHAR(255),
    amount       NUMERIC(4, 2),
    reference    VARCHAR(255),
    date_created TIMESTAMP WITH TIME ZONE
);