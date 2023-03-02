CREATE SEQUENCE IF NOT EXISTS client_seq;
CREATE TABLE IF NOT EXISTS Client
(
	id int8 NOT NULL PRIMARY KEY DEFAULT nextval('client_seq'),
    password TEXT NOT NULL,
  	status BOOLEAN NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS person_seq;
CREATE TABLE IF NOT EXISTS Person
(
    id int8 NOT NULL PRIMARY KEY DEFAULT nextval('person_seq'),
    name TEXT NOT NULL,
    age INT NOT NULL,
    address TEXT NOT NULL,
    gender TEXT NOT NULL,
    phone TEXT NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS bank_account_seq;
CREATE TABLE IF NOT EXISTS Bank_account
(
    id int8 NOT NULL PRIMARY KEY DEFAULT nextval('bank_account_seq'),
    number INT NOT NULL,
    type TEXT NOT NULL,
    start_balance NUMERIC NOT NULL,
    status BOOLEAN NOT NULL,
    client_id int8 NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS transaction_seq;
CREATE TABLE IF NOT EXISTS Transaction
(
    id int8 NOT NULL PRIMARY KEY DEFAULT nextval('transaction_seq'),
    date DATE NOT NULL,
    type TEXT NOT NULL,
    value NUMERIC NOT NULL,
    balance NUMERIC NOT NULL,
    account_id int8 NOT NULL
);
CREATE INDEX IF NOT EXISTS account_id_idx
    ON Transaction (account_id);

CREATE INDEX IF NOT EXISTS client_id_idx
    ON Bank_account (client_id);
