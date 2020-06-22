create type trading_operation_enum as ENUM (
	'BUYING',
	'SALE',
	'DIVIDEND',
	'TAX');

CREATE TABLE portfolio (
	id varchar NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT stocks_pk PRIMARY KEY (id)
);

CREATE TABLE users (
	id uuid NOT NULL,
	created_at timestamptz NOT NULL DEFAULT now(),
	updated_at timestamptz NOT NULL DEFAULT now(),
	"name" varchar NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE categories (
	id int8 NOT NULL,
	"name" varchar NOT NULL,
	parent_id int8 NULL,
	CONSTRAINT categories_pk PRIMARY KEY (id),
	CONSTRAINT categories_unique_name UNIQUE (name),
	CONSTRAINT categories_parent_fk FOREIGN KEY (parent_id) REFERENCES categories(id)
);

CREATE TABLE estates (
	id uuid NOT NULL,
	created_at timestamptz NOT NULL DEFAULT now(),
	updated_at timestamptz NOT NULL DEFAULT now(),
	user_id uuid NOT NULL,
	CONSTRAINT estates_pk PRIMARY KEY (id),
	CONSTRAINT estates_user_fk FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE trading_accounts (
	id uuid NOT NULL,
	created_at timestamptz NOT NULL DEFAULT now(),
	updated_at timestamptz NOT NULL DEFAULT now(),
	estate_id uuid NOT NULL,
	"name" varchar NOT NULL,
	currency varchar NOT NULL,
	CONSTRAINT trading_accounts_pk PRIMARY KEY (id),
	CONSTRAINT trading_accounts_estate_fk FOREIGN KEY (estate_id) REFERENCES estates(id)
);

CREATE TABLE trading_transactions (
	id uuid NOT NULL,
	created_at timestamptz NOT NULL DEFAULT now(),
	updated_at timestamptz NOT NULL DEFAULT now(),
	trading_account_id uuid NOT NULL,
	stock_id varchar NOT NULL,
	"date" date NOT NULL,
	unit_price int8 NOT NULL,
	quantity int8 NOT NULL,
	fees int8 NOT NULL DEFAULT 0,
	operation trading_operation_enum NOT NULL,
	"comment" varchar NULL,
	CONSTRAINT trading_transactions_pk PRIMARY KEY (id),
	CONSTRAINT trading_transactions_account_fk FOREIGN KEY (trading_account_id) REFERENCES trading_accounts(id),
	CONSTRAINT trading_transactions_stock_fk FOREIGN KEY (stock_code) REFERENCES portfolio(id)
);


CREATE TABLE trading_wire_transactions (
	id uuid NOT NULL,
	created_at timestamptz NOT NULL DEFAULT now(),
	updated_at timestamptz NOT NULL DEFAULT now(),
	trading_account_id uuid NOT NULL,
	"date" date NOT NULL,
	amount int8 NOT NULL,
	"comment" varchar NULL,
	CONSTRAINT trading_wire_transactions_pk PRIMARY KEY (id),
	CONSTRAINT trading_wire_transactions_account_fk FOREIGN KEY (trading_account_id) REFERENCES trading_accounts(id)
);


CREATE TABLE cash_accounts (
	id uuid NOT NULL,
	created_at timestamptz NOT NULL DEFAULT now(),
	updated_at timestamptz NOT NULL DEFAULT now(),
	estate_id uuid NOT NULL,
	"name" varchar NOT NULL,
	currency varchar NOT NULL,
	yield_rate int8 NOT NULL DEFAULT 0,
	CONSTRAINT cash_accounts_pk PRIMARY KEY (id),
	CONSTRAINT cash_accounts_estate_fk FOREIGN KEY (estate_id) REFERENCES estates(id)
);


CREATE TABLE cash_transactions (
	id uuid NOT NULL,
	created_at timestamptz NOT NULL DEFAULT now(),
	updated_at timestamptz NOT NULL DEFAULT now(),
	cash_account_id uuid NOT NULL,
	"date" date NOT NULL,
	amount int8 NOT NULL,
	category_id int8 NULL,
	"comment" varchar NULL,
	CONSTRAINT cash_transactions_pk PRIMARY KEY (id),
	CONSTRAINT cash_transactions_account_fk FOREIGN KEY (cash_account_id) REFERENCES cash_accounts(id),
	CONSTRAINT cash_transactions_category_fk FOREIGN KEY (category_id) REFERENCES categories(id)
);

INSERT INTO users(id, "name") VALUES('a28ab999-419c-4e17-8e27-15560ac03a6c', 'George');

INSERT INTO estates (id, user_id) VALUES('f28ab999-419c-4e17-8e27-15560ac03a6c', 'a28ab999-419c-4e17-8e27-15560ac03a6c');

INSERT INTO trading_accounts(id, "name", currency, estate_id) VALUES('e28ab999-419c-4e17-8e27-15560ac03a6c', 'Compte', 'EUR', 'f28ab999-419c-4e17-8e27-15560ac03a6c');

INSERT INTO cash_accounts(id, "name", currency, yield_rate, estate_id) VALUES('828ab999-419c-4e17-8e27-15560ac03a6c', 'Compte', 'EUR', 200, 'f28ab999-419c-4e17-8e27-15560ac03a6c');

