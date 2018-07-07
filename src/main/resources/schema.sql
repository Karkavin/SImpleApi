DROP TABLE IF EXISTS public.organisation CASCADE;
DROP TABLE IF EXISTS public.office CASCADE;
DROP TABLE IF EXISTS public.doc CASCADE;
DROP TABLE IF EXISTS public.country CASCADE;
DROP TABLE IF EXISTS public.user CASCADE;

CREATE TABLE public.organisation
(
      id            SERIAL NOT NULL PRIMARY KEY,
      name          VARCHAR(50) NOT NULL,
      full_name     VARCHAR(100) NOT NULL,
      inn           BIGINT NOT NULL,
      kpp           BIGINT NOT NULL,
      address       VARCHAR(100) NOT NULL,
      phone         VARCHAR(20),
      is_active     BOOLEAN DEFAULT TRUE,
      CONSTRAINT inn_length CHECK (inn > 999999999 AND inn < 10000000000),
      CONSTRAINT kpp_length CHECK (kpp > 99999999 AND kpp < 1000000000)
);

CREATE TABLE public.office
(
      id            SERIAL NOT NULL PRIMARY KEY,
      name          VARCHAR(50) NOT NULL,
      address       VARCHAR(100) NOT NULL,
      phone         VARCHAR(20),
      is_active     BOOLEAN DEFAULT TRUE,
      org_id        INT NOT NULL,
      CONSTRAINT fk_office_organisation FOREIGN KEY (org_id) REFERENCES organisation (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE public.doc
(
      code      SMALLINT NOT NULL PRIMARY KEY,
      name      VARCHAR(50) NOT NULL,
      CONSTRAINT code_length CHECK (code > 9 AND code < 100)
);

CREATE TABLE public.country
(
      code      SMALLINT NOT NULL PRIMARY KEY,
      name      VARCHAR(50) NOT NULL,
      CONSTRAINT code_length CHECK (code > 99 AND code < 1000)
);

CREATE TABLE public.user
(
      id                  SERIAL NOT NULL PRIMARY KEY,
      first_name          VARCHAR(30) NOT NULL,
      second_name         VARCHAR(30),
      middle_name         VARCHAR(30),
      position            VARCHAR(50) NOT NULL,
      phone               VARCHAR(20),
      doc_number          VARCHAR(30),
      doc_date            DATE,
      doc_code            SMALLINT,
      citizenship_code    SMALLINT,
      is_indentifies      BOOLEAN DEFAULT FALSE ,
      org_id              INT NOT NULL,
      off_id              INT NOT NULL,
      CONSTRAINT fk_user_organisation FOREIGN KEY (org_id) REFERENCES organisation (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
      CONSTRAINT fk_user_office FOREIGN KEY (off_id) REFERENCES office (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
      CONSTRAINT fk_user_doc FOREIGN KEY (doc_code) REFERENCES doc (code)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
      CONSTRAINT fk_user_country FOREIGN KEY (citizenship_code) REFERENCES country (code)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
      CONSTRAINT doc_code_length CHECK (doc_code > 9 AND doc_code < 100),
      CONSTRAINT citizenship_code_length CHECK (citizenship_code > 99 AND citizenship_code < 1000)
);