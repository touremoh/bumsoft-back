set SCHEMA 'bumsoftdb';

INSERT INTO BUMSOFT_USER (BS_USR_ID, BS_USR_FIRST_NAME, BS_USR_LAST_NAME, BS_USR_EMAIL, BS_USR_PASSWORD, BS_USR_ENABLED, BS_USR_CREATED_AT, BS_USR_UPDATED_AT, BS_USR_DELETED_AT)
VALUES (nextval('USER_SEQ'), 'Mohamed', 'Toure', 'mohamed.toure@gmail.com', 'p1234', true, current_date, current_date, null);

INSERT INTO ROLE (ROLE_ID, ROLE_NAME, ROLE_DESC) VALUES ( nextval('ROLE_SEQ'), 'ROLE_ADMIN', 'Administration of the application' );
INSERT INTO ROLE (ROLE_ID, ROLE_NAME, ROLE_DESC) VALUES ( nextval('ROLE_SEQ'), 'ROLE_SUBSCRIBER', 'The user has subscribed to the application' );
INSERT INTO ROLE (ROLE_ID, ROLE_NAME, ROLE_DESC) VALUES ( nextval('ROLE_SEQ'), 'ROLE_FREE_TRIAL', 'The user is trying the application for a certain period of time' );

INSERT INTO USER_ROLE (BS_USR_ID, ROLE_ID)       VALUES ( 1000, 1000);

INSERT INTO OPERATION (OP_ID, OP_NAME, OP_DESC) VALUES ( nextval('OPERATION_SEQ'), 'READ', 'The user has READ privileges');
INSERT INTO OPERATION (OP_ID, OP_NAME, OP_DESC) VALUES ( nextval('OPERATION_SEQ'), 'WRITE', 'The user has WRITE privileges');
INSERT INTO OPERATION (OP_ID, OP_NAME, OP_DESC) VALUES ( nextval('OPERATION_SEQ'), 'UPDATE', 'The user has UPDATE privileges');
INSERT INTO OPERATION (OP_ID, OP_NAME, OP_DESC) VALUES ( nextval('OPERATION_SEQ'), 'DELETED', 'The user has DELETED privileges');
INSERT INTO OPERATION (OP_ID, OP_NAME, OP_DESC) VALUES ( nextval('OPERATION_SEQ'), 'ALL', 'The user has READ-WRITE-UPDATE-DELETE privileges');

INSERT INTO ROLE_OPERATION (ROLE_ID, OP_ID)     VALUES ( 1000, 1004 );

INSERT INTO REFERENCE_ENTITY_TYPE (REF_TYP_ID, REF_TYP_GROUP, REF_TYP_NAME, REF_TYP_DESC) VALUES ( nextval('REFERENCE_ENTITY_SEQ'), 'TRANSACTION_TYPE', 'DEBIT', 'Represents a withdrawal' );
INSERT INTO REFERENCE_ENTITY_TYPE (REF_TYP_ID, REF_TYP_GROUP, REF_TYP_NAME, REF_TYP_DESC) VALUES ( nextval('REFERENCE_ENTITY_SEQ'), 'TRANSACTION_TYPE', 'CREDIT', 'Represents a deposit' );
INSERT INTO REFERENCE_ENTITY_TYPE (REF_TYP_ID, REF_TYP_GROUP, REF_TYP_NAME, REF_TYP_DESC) VALUES ( nextval('REFERENCE_ENTITY_SEQ'), 'ACCOUNT_TYPE', 'LIVE', 'Represents the current account' );
INSERT INTO REFERENCE_ENTITY_TYPE (REF_TYP_ID, REF_TYP_GROUP, REF_TYP_NAME, REF_TYP_DESC) VALUES ( nextval('REFERENCE_ENTITY_SEQ'), 'ACCOUNT_TYPE', 'BUDGET', 'Represents a budget account' );
INSERT INTO REFERENCE_ENTITY_TYPE (REF_TYP_ID, REF_TYP_GROUP, REF_TYP_NAME, REF_TYP_DESC) VALUES ( nextval('REFERENCE_ENTITY_SEQ'), 'SUBSCRIPTION_TYPE', 'MONTHLY', 'Represents a subscription charged monthly' );
INSERT INTO REFERENCE_ENTITY_TYPE (REF_TYP_ID, REF_TYP_GROUP, REF_TYP_NAME, REF_TYP_DESC) VALUES ( nextval('REFERENCE_ENTITY_SEQ'), 'SUBSCRIPTION_TYPE', 'YEARLY', 'Represents a subscription charged yearly' );




-- Account
insert into ACCOUNT (ACC_ID, ACC_NUM,  ACC_NAME, ACC_DESC, ACC_BS_USR_ID, ACC_TYPE, ACC_CREATED_AT, ACC_UPDATED_AT, ACC_DELETED_AT)
VALUES ( nextval('ACCOUNT_SEQ'), 'A0001', 'Live', 'Compte courant', 1000, 1002, current_date, current_date, null);

insert into ACCOUNT (ACC_ID, ACC_NUM, ACC_NAME, ACC_DESC, ACC_BS_USR_ID, ACC_TYPE, ACC_CREATED_AT, ACC_UPDATED_AT, ACC_DELETED_AT)
VALUES ( nextval('ACCOUNT_SEQ'), 'A0002', 'Epargne', 'Compte épargne Belfius', 1000, 1003, current_date, current_date, null);

insert into ACCOUNT (ACC_ID, ACC_NUM, ACC_NAME, ACC_DESC, ACC_BS_USR_ID, ACC_TYPE, ACC_CREATED_AT, ACC_UPDATED_AT, ACC_DELETED_AT)
VALUES ( nextval('ACCOUNT_SEQ'), 'A0003', 'Maison', 'Budget pour les charges de maison', 1000, 1003, current_date, current_date, null);

insert into ACCOUNT (ACC_ID, ACC_NUM, ACC_NAME, ACC_DESC, ACC_BS_USR_ID, ACC_TYPE, ACC_CREATED_AT, ACC_UPDATED_AT, ACC_DELETED_AT)
VALUES ( nextval('ACCOUNT_SEQ'), 'A0004', 'Nourriture', 'Budget nourriture', 1000, 1003, current_date, current_date, null);

insert into ACCOUNT (ACC_ID, ACC_NUM, ACC_NAME, ACC_DESC, ACC_BS_USR_ID, ACC_TYPE, ACC_CREATED_AT, ACC_UPDATED_AT, ACC_DELETED_AT)
VALUES ( nextval('ACCOUNT_SEQ'), 'A0005', 'Abonnements', 'Budget abonnements', 1000, 1003, current_date, current_date, null);

-- Transactions
insert into TRANSACTION (TRX_ID, TRX_VALUE, TRX_DESC, TRX_DATE, TRX_ACC_ID, TRX_REF_TYP_ID)
values ( nextval('TRANSACTION_SEQ'), -99.9, 'Débit de compte', current_date, 1001, 1000);

insert into TRANSACTION (TRX_ID, TRX_VALUE, TRX_DESC, TRX_DATE, TRX_ACC_ID, TRX_REF_TYP_ID)
values ( nextval('TRANSACTION_SEQ'), -12.9, 'Débit de compte', current_date, 1001, 1000);

insert into TRANSACTION (TRX_ID, TRX_VALUE, TRX_DESC, TRX_DATE, TRX_ACC_ID, TRX_REF_TYP_ID)
values ( nextval('TRANSACTION_SEQ'), -19.9, 'Débit de compte', current_date, 1000, 1000);

insert into TRANSACTION (TRX_ID, TRX_VALUE, TRX_DESC, TRX_DATE, TRX_ACC_ID, TRX_REF_TYP_ID)
values ( nextval('TRANSACTION_SEQ'), -43.9, 'Débit de compte', current_date, 1000, 1000);

insert into TRANSACTION (TRX_ID, TRX_VALUE, TRX_DESC, TRX_DATE, TRX_ACC_ID, TRX_REF_TYP_ID)
values ( nextval('TRANSACTION_SEQ'), 4000, 'Crédit de compte', current_date, 1000, 1001);

insert into TRANSACTION (TRX_ID, TRX_VALUE, TRX_DESC, TRX_DATE, TRX_ACC_ID, TRX_REF_TYP_ID)
values ( nextval('TRANSACTION_SEQ'), 1200, 'Crédit de compte', current_date, 1001, 1001);

insert into TRANSACTION (TRX_ID, TRX_VALUE, TRX_DESC, TRX_DATE, TRX_ACC_ID, TRX_REF_TYP_ID)
values ( nextval('TRANSACTION_SEQ'), 1789, 'Crédit de compte', current_date, 1000, 1001);

insert into TRANSACTION (TRX_ID, TRX_VALUE, TRX_DESC, TRX_DATE, TRX_ACC_ID, TRX_REF_TYP_ID)
values ( nextval('TRANSACTION_SEQ'), -18.8, 'Débit de compte', current_date, 1000, 1000);

insert into TRANSACTION (TRX_ID, TRX_VALUE, TRX_DESC, TRX_DATE, TRX_ACC_ID, TRX_REF_TYP_ID)
values ( nextval('TRANSACTION_SEQ'), -99.9, 'Débit de compte', current_date, 1000, 1000);

insert into TRANSACTION (TRX_ID, TRX_VALUE, TRX_DESC, TRX_DATE, TRX_ACC_ID, TRX_REF_TYP_ID)
values ( nextval('TRANSACTION_SEQ'), -99.9, 'Débit de compte', current_date, 1000, 1000);

insert into TRANSACTION (TRX_ID, TRX_VALUE, TRX_DESC, TRX_DATE, TRX_ACC_ID, TRX_REF_TYP_ID)
values ( nextval('TRANSACTION_SEQ'), -99.9, 'Débit de compte', current_date, 1000, 1000);

-- Budgets
insert into BUDGET (BDGT_ID, BDGT_NAME, BDGT_AMOUNT, BDGT_CREATED_AT, BDGT_UPDATED_AT, BDGT_ACC_ID, BDGT_USER_ID) values (nextval('BUDGET_SEQ'), 'Loyer', 650, current_date, null, 1002, 1000);
insert into BUDGET (BDGT_ID, BDGT_NAME, BDGT_AMOUNT, BDGT_CREATED_AT, BDGT_UPDATED_AT, BDGT_ACC_ID, BDGT_USER_ID) values (nextval('BUDGET_SEQ'), 'Electricité', 100, current_date, null, 1002, 1000);
insert into BUDGET (BDGT_ID, BDGT_NAME, BDGT_AMOUNT, BDGT_CREATED_AT, BDGT_UPDATED_AT, BDGT_ACC_ID, BDGT_USER_ID) values (nextval('BUDGET_SEQ'), 'Netflix', 15, current_date, null, 1004, 1000);
insert into BUDGET (BDGT_ID, BDGT_NAME, BDGT_AMOUNT, BDGT_CREATED_AT, BDGT_UPDATED_AT, BDGT_ACC_ID, BDGT_USER_ID) values (nextval('BUDGET_SEQ'), 'Epargne', 1500, current_date, null, 1001, 1000);


-- Automated Plan
insert into AUTOMATED_PLAN (AUP_ID, AUP_THRESHOLD, AUP_ACTIVE, AUP_CREATED_AT, AUP_UPDATED_AT, AUP_ACC_ID, AUP_USER_ID) values (nextval('AUTOMATED_PLAN_SEQ'), 2500, true, current_date, null, 1000, 1000);


commit ;