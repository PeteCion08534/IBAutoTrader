select * from strategy;
update spread set enter_moneymkr_price=185000 where spread_id=13151;
select * from request_seq;
select * from spread where open_or_close
select * from position;
select * from allspreads where open_or_closed='CLOSED';
select * from allheartbeats;
//60 * 4 = 240
//ENTER_MONEYMKR_PRICE = 15 00 + 240 00 = 255 00

select * from spread;
update spread set open_or_closed='KILLED' where spread_id=13200;

update spread
set enter_moneymkr_price=25500
where spread_id=13150;

update position
set goal_num_spreads=2
where position_id = 7302;

select * from position;

    select
        "HEARTBEAT_ID",
        "HEARTBEAT_DATE",
        "IN_PROCESS",
        "THREAD_ID",
        "TERMINATION_STATUS",
        "SNAPSHOT",
        "HEARTBEAT_LOG",
        "CREATED_BY",
        "CREATED_DATE",
        "UPDATED_BY",
        "UPDATED_DATE" 
    from
        "THETA"."HEARTBEAT"
        order by heartbeat_id desc