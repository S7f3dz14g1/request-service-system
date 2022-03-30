CREATE TABLE IF NOT EXISTS Client(
    id    SERIAL    PRIMARY    KEY,
    firstname    varchar(30) not null,
    lastname varchar(250) not null,
    email varchar (30) not null,
    phone numeric(9)not null
    );

INSERT INTO Client(firstname,lastname,email,phone)
values ( 'Adam', 'Pawlak','adampawlak@wp.pl',123456789);
INSERT INTO Client(firstname,lastname,email,phone)
values ('Mieczysław', 'Grab','mieczyslawgrab@wp.pl',987654321);

CREATE TABLE IF NOT EXISTS Users(
    id    SERIAL    PRIMARY    KEY,
    nick    VARCHAR(30) NOT NULL ,
    password VARCHAR(200) not null,
    status VARCHAR(100) not null,
    UNIQUE(nick)
    );
INSERT INTO Users (nick,password,status)
values ('AdamP', 'password123', 'TECHNICIAN');
INSERT INTO Users (nick,password,status)
values ('MieczyslawG', 'password123', 'ADMIN');

CREATE TABLE IF NOT EXISTS Invoice(
    id    SERIAL    PRIMARY    KEY,
    price    numeric(6,2) ,
    tax numeric(3) ,
    discount numeric(6,2)
    );

INSERT INTO Invoice(price,tax,discount )
values ( 500, 23, 0);
INSERT INTO Invoice(price,tax,discount )
values ( 300, 23, 10);

CREATE EXTENSION pgcrypto;
SELECT gen_random_uuid();

CREATE TABLE IF NOT EXISTS Orders(
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    status    varchar(30) not null,
    details varchar(250) not null,
    invoiceId SERIAL ,
    technicianId SERIAL ,
    done_work varchar(250),
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

INSERT INTO Orders(status,details,invoiceId,technicianId,clientId,createDate,editedDate)
values ('FINISHED', 'Problem z matrycą', 1,1,1,'2022-01-02','2022-01-09');
INSERT INTO Orders(status,details,invoiceId,technicianId,clientId,createDate,editedDate)
 values ('DURING DIAGNOSIS', 'Laser nie działa', 2,1,2,'2022-02-08','2022-02-08');

CREATE TABLE IF NOT EXISTS Device
(
    id    SERIAL    PRIMARY    KEY,
    device_type    VARCHAR(30) NOT NULL,
    model VARCHAR(100),
    damages VARCHAR(200),
    battery boolean not null,
    charger boolean not null,
    password varchar(30),
    orderid uuid,
    constraint fk_order
        foreign key(orderid)
             references Orders(id)
    );

INSERT INTO Device(device_type,model,damages,battery,charger,password,orderid)
            values ( 'PC', 'Pavilion g6', 'Zarysowany ekran',true,true,'pass',(select id from orders where clientId=1));
INSERT INTO Device(device_type,model,damages,battery,charger,orderid)
            values ( 'Myszka', 'Steel Series Rival 3', 'Brak',false ,false ,(select id from orders where clientId=2));