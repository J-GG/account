CREATE TABLE IF NOT EXISTS users (
  id uuid NOT NULL,
  name character varying NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS accounts (
  id uuid NOT NULL,
  name character varying  NOT NULL,
  currency character varying NOT NULL,
  user_id uuid,
  yield_rate bigint NOT NULL DEFAULT 0,
  CONSTRAINT pk_accounts PRIMARY KEY (id),
  CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS categories (
  id bigint,
  name character varying NOT NULL,
  parent_id bigint,
  CONSTRAINT pk_categories PRIMARY KEY (id),
  CONSTRAINT fk_parent FOREIGN KEY (parent_id) REFERENCES categories (id)
);

CREATE TABLE IF NOT EXISTS transactions (
  id uuid NOT NULL,
  account_id uuid NOT NULL,
  date date NOT NULL,
  amount bigint NOT NULL,
  comment character varying,
  category_id bigint,
  CONSTRAINT pktra PRIMARY KEY (id),
  CONSTRAINT fk_accounts FOREIGN KEY (account_id) REFERENCES accounts (id),
  CONSTRAINT fk_categories FOREIGN KEY (category_id) REFERENCES categories (id)
);