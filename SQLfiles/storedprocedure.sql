CREATE DEFINER=`niharika`@`%` PROCEDURE `transact`(in sender int,in receiver int)
BEGIN
DECLARE copy int;
select amount into copy from accountdetails where accID=sender;
if copy>1000 then
start transaction;
update accountdetails set amount=amount+1000 where accID=receiver;
update accountdetails set amount=amount-1000 where accID=sender;
insert into transactions(info) values(concat('AN AMOUNT OF 1000/- HAS BEEN TRANSFERED FROM ACC NUM: ',sender,' TO ACC NUM: ',receiver,' on ', current_date()));
commit;
else
rollback;
end if;
END
