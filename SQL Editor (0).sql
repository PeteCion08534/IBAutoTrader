select * from strategy;
update strategy set opt_prices_to_get=16;
select * from position;

select * from spread where spread_id in (4050,4051,4052,4053)
select * from position where position_id in (9,17,25,33)
select * from position where expiry_month = 11 and strategy_account_id in (2,3,4,5)


select * from heartbeat where heartbeat_log like '%MATCH%' order by heartbeat_id desc;
select * from spreads_by_sa;
select * from spread;
select * from profit_loss order by profit_loss_id desc;
select * from strategy;

    select
        "SPREAD_ID",
        "POSITION_ID",
        "OPEN_OR_CLOSED",
        "MONEYMKR_POSITION_ID",
        "INSURANCE_POSITION_ID",
        "STRIKE_MONEYMKR",
        "STRIKE_INSURANCE",
        "ENTER_MONEYMKR_PRICE",
        "ENTER_MONEYMKR_DATE",
        "ENTER_INSURANCE_PRICE",
        "ENTER_INSURANCE_DATE",
        "ENTER_SECURITY_PRICE",
        "ENTER_SECURITY_DATE",
        "ENTER_TRIGGER_DATE",
        "ENTER_CONFIRM_DATE",
        "ENTER_COMMISSION",
        "CURRENT_MONEYMKR_PRICE",
        "CURRENT_INSURANCE_PRICE",
        "CURRENT_VIX_PRICE",
        "CURRENT_SECURITY_PRICE",
        "CURRENT_DATE",
        "EXIT_MONEYMKR_PRICE",
        "EXIT_MONEYMKR_DATE",
        "EXIT_INSURANCE_PRICE",
        "EXIT_INSURANCE_DATE",
        "EXIT_SECURITY_PRICE",
        "EXIT_SECURITY_DATE",
        "EXIT_TRIGGER_DATE",
        "EXIT_CONFIRM_DATE",
        "EXIT_COMMISSION",
        "TRAILING_STOP_IS_SET",
        "EXIT_ABOVE_SPREAD_VALUE",
        "EXIT_BELOW_SPREAD_VALUE",
        "PROFIT_LOSS_UNREALIZED",
        "PROFIT_LOSS_REALIZED",
        "REQUEST_SEQ_NO",
        "REOPEN_DATE",
        "CREATED_BY",
        "CREATED_DATE",
        "UPDATED_BY",
        "UPDATED_DATE",
        "ENTER_ACTUAL_IB",
        "EXIT_ACTUAL_IB",
        "ENTER_PERM_ID_IB",
        "EXIT_PERM_ID_IB",
        "ENTER_REQUEST_SEQ_NO",
        "EXIT_REQUEST_SEQ_NO" 
    from
        "THETA"."SPREAD"