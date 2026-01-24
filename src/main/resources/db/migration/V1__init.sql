create table if not exists policy (
                                      id bigserial primary key,
                                      policy_number varchar(50) not null unique,
    holder_name varchar(120) not null,
    status varchar(30) not null,
    premium numeric(12,2) not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
    );
