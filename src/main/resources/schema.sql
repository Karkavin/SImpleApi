DROP TABLE IF EXISTS public.organisation CASCADE;
DROP TABLE IF EXISTS public.office CASCADE;
DROP TABLE IF EXISTS public.doc CASCADE;
DROP TABLE IF EXISTS public.country CASCADE;
DROP TABLE IF EXISTS public.user CASCADE;
DROP TABLE IF EXISTS public.user_doc CASCADE;

CREATE TABLE public.organisation
(
      id            SERIAL NOT NULL PRIMARY KEY,
      name          VARCHAR(50) NOT NULL,
      full_name     VARCHAR(100) NOT NULL,
      inn           CHAR(10) NOT NULL,
      kpp           CHAR(9) NOT NULL,
      address       VARCHAR(100) NOT NULL,
      phone         VARCHAR(20),
      is_active     BOOLEAN DEFAULT TRUE
      CONSTRAINT inn_length CHECK (CHAR_LENGTH(inn) = 10),
      CONSTRAINT kpp_length CHECK (CHAR_LENGTH(kpp) = 9)
);

CREATE TABLE public.office
(
      id            SERIAL NOT NULL PRIMARY KEY,
      name          VARCHAR(50) NOT NULL,
      address       VARCHAR(100) NOT NULL,
      phone         VARCHAR(20),
      is_active     BOOLEAN DEFAULT TRUE,
      org_id        BIGINT NOT NULL,
      CONSTRAINT fk_office_organisation FOREIGN KEY (org_id) REFERENCES organisation (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE public.doc
(
      code      SMALLINT NOT NULL PRIMARY KEY,
      name      VARCHAR(150) NOT NULL,
      CONSTRAINT code_length CHECK (code > 0 AND code < 100)
);

CREATE TABLE public.country
(
      code      SMALLINT NOT NULL PRIMARY KEY,
      name      VARCHAR(150) NOT NULL,
      CONSTRAINT code_length CHECK (code > 0 AND code < 1000)
);

CREATE TABLE public.user
(
      id                  SERIAL NOT NULL PRIMARY KEY,
      first_name          VARCHAR(30) NOT NULL,
      second_name         VARCHAR(30),
      middle_name         VARCHAR(30),
      position            VARCHAR(50) NOT NULL,
      phone               VARCHAR(20),
      citizenship_code    SMALLINT,
      is_identified       BOOLEAN DEFAULT FALSE ,
      org_id              BIGINT NOT NULL,
      off_id              BIGINT NOT NULL,
      CONSTRAINT fk_user_organisation FOREIGN KEY (org_id) REFERENCES organisation (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
      CONSTRAINT fk_user_office FOREIGN KEY (off_id) REFERENCES office (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
      CONSTRAINT fk_user_country FOREIGN KEY (citizenship_code) REFERENCES country (code)
      ON DELETE SET NULL
      ON UPDATE CASCADE,
      CONSTRAINT citizenship_code_length CHECK (citizenship_code > 99 AND citizenship_code < 1000)
);

CREATE TABLE public.user_doc
(
      id                  SERIAL NOT NULL PRIMARY KEY,
      doc_number          VARCHAR(30),
      doc_date            DATE,
      doc_code            SMALLINT,
      user_id             BIGINT,
      CONSTRAINT fk_user_doc_user FOREIGN KEY (user_id) REFERENCES public.user (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
      CONSTRAINT fk_user_doc_doc FOREIGN KEY (doc_code) REFERENCES doc (code)
      ON DELETE SET NULL
      ON UPDATE CASCADE,
      CONSTRAINT doc_code_length CHECK (doc_code > 9 AND doc_code < 100)
);

CREATE INDEX IX_organisation_name_inn_is_active ON public.organisation (name, inn, is_active);

CREATE INDEX IX_office_org_id ON public.office (org_id);
CREATE INDEX IX_office_org_id_name_phone_is_active ON public.office (org_id, name, phone, is_active);

CREATE INDEX IX_doc_code ON user_doc (doc_code);
CREATE INDEX IX_user_id ON user_doc (user_id);

CREATE INDEX IX_org_id ON public.user (org_id);
CREATE INDEX IX_off_id ON public.user (off_id);
CREATE INDEX IX_citizenship_code ON public.user (citizenship_code);
CREATE INDEX IX_user_off_id_first_name_second_name_middle_name_position_citizenship_code
  ON public.user (off_id, first_name, second_name, middle_name, position, citizenship_code);