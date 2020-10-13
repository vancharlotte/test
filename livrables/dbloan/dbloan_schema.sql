SET sql_mode = '';
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
