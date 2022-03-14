CREATE TABLE IF NOT EXISTS Device
(
    id          SERIAL PRIMARY KEY,
    device_type       VARCHAR(30)   NOT NULL,
    model               VARCHAR(100),
    destroys  TIMESTAMP
    );


