DROP SCHEMA IF EXISTS bumsoftdb;
CREATE SCHEMA IF NOT EXISTS bumsoftdb;
SET SCHEMA 'bumsoftdb';

CREATE TABLE IF NOT EXISTS BUMSOFT_USER (
                                            BS_USR_ID         INTEGER NOT NULL,
                                            BS_USR_FIRST_NAME VARCHAR(250),
                                            BS_USR_LAST_NAME  VARCHAR(250),
                                            BS_USR_EMAIL      VARCHAR(250),
                                            BS_USR_PASSWORD   VARCHAR(250),
                                            BS_USR_ENABLED    BOOLEAN,
                                            BS_USR_CREATED_AT DATE,
                                            BS_USR_UPDATED_AT DATE,
                                            BS_USR_DELETED_AT DATE
);


CREATE TABLE IF NOT EXISTS ROLE (
                                    ROLE_ID   INTEGER NOT NULL,
                                    ROLE_NAME VARCHAR(250) NOT NULL,
                                    ROLE_DESC VARCHAR(250)
);


CREATE TABLE IF NOT EXISTS USER_ROLE (
                                         BS_USR_ID INTEGER NOT NULL,
                                         ROLE_ID   INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS OPERATION (
                                         OP_ID   INTEGER NOT NULL,
                                         OP_NAME VARCHAR(250),
                                         OP_DESC VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS ROLE_OPERATION (
                                              ROLE_ID INTEGER NOT NULL,
                                              OP_ID   INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS ACCOUNT (
                                       ACC_ID          INTEGER      NOT NULL,
                                       ACC_NUM         VARCHAR(45)  NOT NULL,
                                       ACC_NAME        VARCHAR(250) NOT NULL,
                                       ACC_DESC        VARCHAR(250),
                                       ACC_BS_USR_ID   INTEGER      NOT NULL,
                                       ACC_TYPE        INTEGER      NOT NULL,
                                       ACC_CREATED_AT  DATE         NOT NULL,
                                       ACC_UPDATED_AT  DATE,
                                       ACC_DELETED_AT  DATE
);


CREATE TABLE IF NOT EXISTS TRANSACTION (
                                           TRX_ID          INTEGER       NOT NULL,
                                           TRX_VALUE       DECIMAL(18,2) NOT NULL,
                                           TRX_DESC        VARCHAR(250),
                                           TRX_DATE        DATE         NOT NULL,
                                           TRX_ACC_ID      INTEGER      NOT NULL,
                                           TRX_REF_TYP_ID  INTEGER      NOT NULL
);

CREATE TABLE IF NOT EXISTS REFERENCE_ENTITY_TYPE (
                                                     REF_TYP_ID    INTEGER NOT NULL,
                                                     REF_TYP_GROUP VARCHAR(250),
                                                     REF_TYP_NAME  VARCHAR(250),
                                                     REF_TYP_DESC  VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS BUDGET (
                                      BDGT_ID         INTEGER       NOT NULL,
                                      BDGT_NAME       VARCHAR(250)  NOT NULL,
                                      BDGT_AMOUNT     DECIMAL(18,2) NOT NULL,
                                      BDGT_CREATED_AT DATE          NOT NULL,
                                      BDGT_UPDATED_AT DATE,
                                      BDGT_ACC_ID     INTEGER       NOT NULL,
                                      BDGT_USER_ID    INTEGER      NOT NULL
);

CREATE TABLE IF NOT EXISTS AUTOMATED_PLAN (
                                              AUP_ID         INTEGER       NOT NULL,
                                              AUP_THRESHOLD  DECIMAL(18,2) NOT NULL,
                                              AUP_ACTIVE     BOOLEAN       NOT NULL,
                                              AUP_CREATED_AT DATE          NOT NULL,
                                              AUP_UPDATED_AT DATE,
                                              AUP_ACC_ID     INTEGER       NOT NULL,
                                              AUP_USER_ID    INTEGER       NOT NULL
);

CREATE TABLE IF NOT EXISTS SUBSCRIPTION (
                                            SUBS_ID         INTEGER NOT NULL,
                                            SUBS_CREATED_AT DATE NOT NULL,
                                            SUBS_UPDATED_AT DATE,
                                            SUBS_DELETED_AT DATE,
                                            SUBS_AUTO_RENEWAL BOOLEAN,
                                            SUBS_START_DT   DATE,
                                            SUBS_END_DT     DATE,
                                            SUBS_PRICE      DECIMAL(18,2),
                                            SUBS_REF_TYP_ID INTEGER,
                                            SUBS_BS_USR_ID  INTEGER
);

CREATE TABLE IF NOT EXISTS PAYMENT_INFO (
                                            PI_ID                 INTEGER NOT NULL,
                                            PI_SUBSCRIPTION_PRICE DECIMAL(18,2) NOT NULL,
                                            PI_CREATED_AT         DATE,
                                            PI_UPDATED_AT         DATE,
                                            PI_DELETED_AT         DATE,
                                            PI_SUBS_ID            INTEGER,
                                            PI_CC_ID              INTEGER
);


CREATE TABLE IF NOT EXISTS CREDIT_CARD_INFO (
                                                CC_ID              INTEGER NOT NULL,
                                                CC_OWNER_FULL_NAME VARCHAR(250),
                                                CC_NUMBER          VARCHAR(250),
                                                CC_EXPIRATION_DT   DATE,
                                                CC_CVV_NUMBER      VARCHAR(250),
                                                CC_CREATED_AT      DATE,
                                                CC_UPDATED_AT      DATE,
                                                CC_DELETED_AT      DATE,
                                                CC_BS_USR_ID       INTEGER
);

-- CONSTRAINTS
ALTER TABLE BUMSOFT_USER ADD CONSTRAINT PK_BS_USR_BS_USR_ID PRIMARY KEY (BS_USR_ID);
ALTER TABLE BUMSOFT_USER ADD CONSTRAINT UQ_BS_USR_BS_USR_EMAIL UNIQUE (BS_USR_EMAIL);
ALTER TABLE BUMSOFT_USER ADD CONSTRAINT UQ_BS_USR_BS_USR_PASSWD UNIQUE (BS_USR_PASSWORD);

ALTER TABLE REFERENCE_ENTITY_TYPE ADD CONSTRAINT PK_REF_REF_TYP_ID PRIMARY KEY (REF_TYP_ID);

ALTER TABLE ROLE ADD CONSTRAINT PK_ROLE_ROLE_ID PRIMARY KEY (ROLE_ID);
ALTER TABLE ROLE ADD CONSTRAINT UQ_ROLE_ROLE_NAME UNIQUE (ROLE_NAME);

ALTER TABLE USER_ROLE ADD CONSTRAINT PK_USER_ROLE_BS_USR_ID_ROLE_ID PRIMARY KEY (BS_USR_ID, ROLE_ID);
ALTER TABLE USER_ROLE ADD CONSTRAINT FK_USER_ROLE_BS_USR_ID FOREIGN KEY (BS_USR_ID) REFERENCES BUMSOFT_USER (BS_USR_ID);
ALTER TABLE USER_ROLE ADD CONSTRAINT FK_USER_ROLE_ROLE_ID FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ROLE_ID);


ALTER TABLE OPERATION ADD CONSTRAINT PK_OP_OP_ID PRIMARY KEY (OP_ID);
ALTER TABLE OPERATION ADD CONSTRAINT UQ_OP_OP_NAME UNIQUE (OP_NAME);

ALTER TABLE ROLE_OPERATION ADD CONSTRAINT PK_ROLE_OPERATION_ROLE_ID_OP_ID PRIMARY KEY (ROLE_ID, OP_ID);
ALTER TABLE ROLE_OPERATION ADD CONSTRAINT FK_ROLE_OPERATION_ROLE_ID FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ROLE_ID);
ALTER TABLE ROLE_OPERATION ADD CONSTRAINT FK_ROLE_OPERATION_OP_ID FOREIGN KEY (OP_ID) REFERENCES OPERATION (OP_ID);

ALTER TABLE ACCOUNT ADD CONSTRAINT PK_ACC_ACC_ID PRIMARY KEY (ACC_ID);
ALTER TABLE ACCOUNT ADD CONSTRAINT FK_ACC_ACC_BS_USR_ID FOREIGN KEY (ACC_BS_USR_ID) REFERENCES BUMSOFT_USER(BS_USR_ID);
ALTER TABLE ACCOUNT ADD CONSTRAINT FK_ACC_ACC_TYPE FOREIGN KEY (ACC_TYPE) REFERENCES REFERENCE_ENTITY_TYPE(REF_TYP_ID);
ALTER TABLE ACCOUNT ADD CONSTRAINT UQ_ACC_ACC_NUM UNIQUE (ACC_NUM);

ALTER TABLE TRANSACTION ADD CONSTRAINT PK_TRX_TRX_ID PRIMARY KEY (TRX_ID);
ALTER TABLE TRANSACTION ADD CONSTRAINT FK_TRX_TRX_REF_TYP_ID FOREIGN KEY (TRX_REF_TYP_ID) REFERENCES REFERENCE_ENTITY_TYPE(REF_TYP_ID);

ALTER TABLE BUDGET ADD CONSTRAINT PK_BUDGET_BDGT_ID PRIMARY KEY (BDGT_ID);
ALTER TABLE BUDGET ADD CONSTRAINT FK_BUDGET_BDGT_ACC_ID FOREIGN KEY (BDGT_ACC_ID) REFERENCES ACCOUNT(ACC_ID);
ALTER TABLE BUDGET ADD CONSTRAINT FK_BUDGET_BDGT_USER_ID FOREIGN KEY (BDGT_USER_ID) REFERENCES BUMSOFT_USER(BS_USR_ID);

ALTER TABLE AUTOMATED_PLAN ADD CONSTRAINT PK_AUTOMATED_PLAN_AUP_ID PRIMARY KEY (AUP_ID);
ALTER TABLE AUTOMATED_PLAN ADD CONSTRAINT FK_AUTOMATED_PLAN_AUP_ACC_ID FOREIGN KEY (AUP_ACC_ID) REFERENCES ACCOUNT(ACC_ID);
ALTER TABLE AUTOMATED_PLAN ADD CONSTRAINT FK_AUTOMATED_PLAN_AUP_USER_ID FOREIGN KEY (AUP_USER_ID) REFERENCES BUMSOFT_USER(BS_USR_ID);

ALTER TABLE SUBSCRIPTION ADD CONSTRAINT PK_SUBS_SUBS_ID PRIMARY KEY (SUBS_ID);
ALTER TABLE SUBSCRIPTION ADD CONSTRAINT FK_SUBS_SUBS_REF_TYP_ID FOREIGN KEY (SUBS_REF_TYP_ID) REFERENCES REFERENCE_ENTITY_TYPE(REF_TYP_ID);
ALTER TABLE SUBSCRIPTION ADD CONSTRAINT FK_SUBS_SUBS_BS_USR_ID FOREIGN KEY (SUBS_BS_USR_ID) REFERENCES BUMSOFT_USER(BS_USR_ID);

ALTER TABLE CREDIT_CARD_INFO ADD CONSTRAINT PK_CC_CC_ID PRIMARY KEY (CC_ID);
ALTER TABLE CREDIT_CARD_INFO ADD CONSTRAINT FK_CC_CC_BS_USR_ID FOREIGN KEY (CC_BS_USR_ID) REFERENCES BUMSOFT_USER (BS_USR_ID);



ALTER TABLE PAYMENT_INFO ADD CONSTRAINT PK_PI_PI_ID PRIMARY KEY (PI_ID);
ALTER TABLE PAYMENT_INFO ADD CONSTRAINT FK_PI_PI_SUBS_ID FOREIGN KEY (PI_SUBS_ID) REFERENCES SUBSCRIPTION(SUBS_ID);
ALTER TABLE PAYMENT_INFO ADD CONSTRAINT FK_PI_PI_CC_ID FOREIGN KEY (PI_CC_ID) REFERENCES CREDIT_CARD_INFO(CC_ID);
