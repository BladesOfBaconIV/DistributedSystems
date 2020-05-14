CREATE TABLE FREELANCERS (
    ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    USERNAME VARCHAR(20) NOT NULL,
    PASSWORD VARCHAR(20) NOT NULL,
    DESCRIPTION VARCHAR(500),
    TOKENS INTEGER DEFAULT 0
);

CREATE TABLE SKILLS (
    ID INTEGER NOT NULL,
    SKILL VARCHAR(20) NOT NULL,
    PRIMARY KEY (ID, SKILL),
    FOREIGN KEY (ID) REFERENCES FREELANCERS
);

CREATE TABLE ADMINS (
    ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    USERNAME VARCHAR(20) NOT NULL,
    PASSWORD VARCHAR(20) NOT NULL
);

CREATE TABLE PROVIDERS (
    ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    USERNAME VARCHAR(20) NOT NULL,
    PASSWORD VARCHAR(20) NOT NULL
);

CREATE TABLE JOBS (
    ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    TITLE VARCHAR(30) NOT NULL,
    DESCRIPTION VARCHAR(500) NOT NULL,
    STATUS VARCHAR(9) NOT NULL,
    TOKENS INTEGER NOT NULL,
    PROVIDER INTEGER NOT NULL,
    FREELANCER INTEGER,
    FOREIGN KEY (PROVIDER) REFERENCES PROVIDERS,
    FOREIGN KEY (FREELANCER) REFERENCES FREELANCERS
);

CREATE TABLE KEYWORDS (
    ID INTEGER NOT NULL,
    KEYWORD VARCHAR(15) NOT NULL,
    PRIMARY KEY (ID, KEYWORD),
    FOREIGN KEY (ID) REFERENCES JOBS
);

CREATE TABLE JOB_BIDS (
    JOB_ID INTEGER NOT NULL,
    FREELANCER_ID INTEGER NOT NULL,
    PRIMARY KEY (JOB_ID, FREELANCER_ID),
    FOREIGN KEY (JOB_ID) REFERENCES JOBS,
    FOREIGN KEY (FREELANCER_ID) REFERENCES FREELANCERS
);

INSERT INTO PROVIDERS (USERNAME, PASSWORD) VALUES ('test', 'test');
INSERT INTO ADMINS (USERNAME, PASSWORD) VALUES ('test', 'test');

INSERT INTO JOBS (TITLE, DESCRIPTION, STATUS, TOKENS, PROVIDER, FREELANCER)
    VALUES ('Test Job', 'This is a test job', 'COMPLETED', 0, 1, NULL);