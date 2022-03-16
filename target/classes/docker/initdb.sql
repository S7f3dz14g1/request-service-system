CREATE TABLE IF NOT EXISTS Device
(
    id    SERIAL    PRIMARY    KEY,
    device_type    VARCHAR(30) NOT NULL,
    model VARCHAR(100),
    destroys VARCHAR(200)
    );

INSERT INTO Device values (1, 'PC', 'Pavilion g6', 'Zarysowany ekran');
INSERT INTO Device values (2, 'Myszka', 'Steel Series Rival 3', 'Brak');

CREATE TABLE IF NOT EXISTS Users(
    id    SERIAL    PRIMARY    KEY,
    nick    VARCHAR(30) NOT NULL ,
    password VARCHAR(200) not null,
    status VARCHAR(100) not null,
    UNIQUE(nick)
    );
INSERT INTO Users values (1, 'AdamP', 'password123', 'ADMIN');
INSERT INTO Users values (2, 'MieczyslawG', 'password123', 'ADMIN');

CREATE TABLE IF NOT EXISTS Invoice(
    id    SERIAL    PRIMARY    KEY,
    price    numeric(6,2) NOT NULL UNIQUE,
    tax numeric(3) not null,
    discount numeric(6,2) not null
    );

INSERT INTO Invoice values (1, 500, 23, 0);
INSERT INTO Invoice values (2, 300, 23, 10);

CREATE TABLE IF NOT EXISTS Orders(
    id    SERIAL    PRIMARY    KEY,
    status    varchar(30) not null,
    details varchar(250) not null,
    deviceId SERIAL not null,
    invoiceId SERIAL not null,
    createDate TIMESTAMP not null,
    editedDate timestamp ,
    constraint fk_device
        foreign key(deviceId)
            references device(id),
    constraint fk_invoice
        foreign key(invoiceId)
            references Invoice(id)
    );

INSERT INTO Orders values (1, 'FINISHED', 'Problem z matrycą', 1,1,'2022-01-02','2022-01-09');
INSERT INTO Orders values (2, 'DURING DIAGNOSIS', 'Laser nie działa', 2,2,'2022-02-08');

