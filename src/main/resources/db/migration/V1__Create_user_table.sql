CREATE TABLE USER
(
    ID BIGINT AUTO_INCREMENT ,
    ACCOUNT_ID VARCHAR(100),
    NAME VARCHAR(100),
    TOKEN VARCHAR(50),
    BIO VARCHAR(256),
    AVATAR_URL VARCHAR(150),
    GMT_CREATE BIGINT,
    GMT_MODIFIED BIGINT,
    CONSTRAINT USER_PK
		PRIMARY KEY (ID)
);