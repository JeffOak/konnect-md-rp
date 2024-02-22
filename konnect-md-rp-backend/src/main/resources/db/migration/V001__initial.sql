CREATE TABLE PRODUTOS (
    ID                INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    RP_ID             INTEGER NOT NULL,
    MD_ID             INTEGER,
    ULTIMA_ALTERACAO  TIMESTAMP NOT NULL,
    ATIVO             BOOLEAN DEFAULT true NOT NULL
);
CREATE TABLE PEDIDOS (
    ID                INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    RP_ID             INTEGER,
    MD_ID             VARCHAR(150) NOT NULL,
    STATUS_RP         INTEGER NOT NULL,
    STATUS_MD         CHAR(2) NOT NULL,
    MENSAGEM_ENVIADA  BOOLEAN NOT NULL,
    ERRO              BOOLEAN DEFAULT false NOT NULL,
    JSON_MD           BLOB SUB_TYPE TEXT,
    DESCRICAO_ERRO    BLOB SUB_TYPE TEXT
);
CREATE TABLE FORMAS_PAGAMENTO (
    ID     INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    RP_ID  INTEGER NOT NULL,
    MD_ID  VARCHAR(100)
);
CREATE TABLE CONFIGURACAO (
    ID                       INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    CODIGO_EMPRESA           VARCHAR(30),
    TOKEN                    BLOB SUB_TYPE 1 SEGMENT SIZE 80,
    INTERVALO_SINCRONIZACAO  INTEGER DEFAULT 2 NOT NULL
);
INSERT INTO CONFIGURACAO(ID, CODIGO_EMPRESA, TOKEN) VALUES (1, '', '');
