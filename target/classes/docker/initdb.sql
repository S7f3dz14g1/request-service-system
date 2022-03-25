CREATE TABLE IF NOT EXISTS Client(
    id    SERIAL    PRIMARY    KEY,
    firstname    varchar(30) not null,
    lastname varchar(250) not null,
    email varchar (30) not null,
    phone numeric(9)not null
    );

INSERT INTO Client values (1, 'Adam', 'Pawlak','adampawlak@wp.pl',123456789);
INSERT INTO Client values (2, 'Mieczysław', 'Grab','mieczyslawgrab@wp.pl',987654321);

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
    price    numeric(6,2) ,
    tax numeric(3) ,
    discount numeric(6,2)
    );

INSERT INTO Invoice values (1, 500, 23, 0);
INSERT INTO Invoice values (2, 300, 23, 10);

CREATE TABLE IF NOT EXISTS Orders(
    id    SERIAL    PRIMARY    KEY,
    status    varchar(30) not null,
    details varchar(250) not null,
    invoiceId SERIAL ,
    technicianId SERIAL ,
    clientId SERIAL not null,
    createDate TIMESTAMP not null,
    editedDate timestamp ,
    constraint fk_tehnician
        foreign key(technicianId)
            references Users(id),
    constraint fk_invoice
         foreign key(invoiceId)
            references Invoice(id),
    constraint fk_client
        foreign key(clientId)
             references Client(id)
    );

INSERT INTO Orders(id,status,details,invoiceId,technicianId,clientId,createDate,editedDate)
values (1, 'FINISHED', 'Problem z matrycą', 1,1,1,'2022-01-02','2022-01-09');
INSERT INTO Orders values (2, 'DURING DIAGNOSIS', 'Laser nie działa', 2,1,2,'2022-02-08');

CREATE TABLE IF NOT EXISTS Device
(
    id    SERIAL    PRIMARY    KEY,
    device_type    VARCHAR(30) NOT NULL,
    model VARCHAR(100),
    damages VARCHAR(200),
    battery boolean not null,
    charger boolean not null,
    password varchar(30),
    orderid SERIAL,
    constraint fk_order
        foreign key(orderid)
             references Orders(id)
    );

INSERT INTO Device(id,device_type,model,damages,battery,charger,password,orderid)
            values (1, 'PC', 'Pavilion g6', 'Zarysowany ekran',true,true,'pass',1);
INSERT INTO Device(id,device_type,model,damages,battery,charger,orderid)
            values (2, 'Myszka', 'Steel Series Rival 3', 'Brak',false ,false ,2);