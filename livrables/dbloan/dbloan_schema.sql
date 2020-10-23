CREATE DATABASE IF NOT EXISTS dbloan;
USE dbloan;

create table if not exists hibernate_sequence
(
    next_val bigint null
);

create table if not exists loan
(
    id         int         not null
        primary key,
    copy       int         not null,
    end_date   datetime(6) not null,
    renewed    bit         not null,
    returned   bit         not null,
    start_date datetime(6) not null,
    user       int         not null
);

INSERT INTO LOAN
(id, copy, user, start_date, end_date, returned, renewed)
VALUES
(2, 1, 2, '2020-07-18', '2020-08-15', true, false),
(3, 2, 2, '2020-08-13', '2020-09-10', false, false),
(4, 3, 2, '2020-09-01', '2020-09-29', false, false),
(5, 4, 2, '2020-09-01', '2020-09-29', false, false);
