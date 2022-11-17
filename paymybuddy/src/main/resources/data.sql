BEGIN;

INSERT INTO user (name, email, password, phone_number)
		SELECT x.name, x.email, x.password, x.phone_number
			FROM (
				SELECT 'Aimé Malcorps' as name, 'test@test.com' as email, 'aaa' as password, 0606060606 as phone_number
				UNION
				SELECT 'Anna Conda' as name, 'annaConda@gmail.com' as email, 'anna' as password, 0658528822 as phone_number
				UNION
				SELECT 'Claire Voyant' as name, 'cvoyant@gmail.com' as email, 'claire' as password, 0658529654 as phone_number
				UNION
				SELECT 'Steve Rogne' as name, 'srogne@gmail.com' as email, 'steve' as password, 0611223344 as phone_number
				) as x
		WHERE
			(SELECT COUNT(*) FROM paymybuddy.user) <= 0;
			
INSERT INTO paymybuddy.bank_account (user_id, sold)
		SELECT x.user_id, x.sold
			FROM (
				SELECT 1 as user_id, 5000 as sold
				UNION
				SELECT 2 as user_id, 10300 as sold
				UNION
				SELECT 3 as user_id, 10300 as sold
				) as x
		WHERE
			(SELECT COUNT(*) FROM paymybuddy.bank_account) <= 0;
			
INSERT INTO paymybuddy.user_connections (user_id, connections_id)
		SELECT x.user_id, x.connections_id
			FROM (
				SELECT 1 as user_id, 2 as connections_id
				UNION
				SELECT 1 as user_id, 3 as connections_id
				UNION
				SELECT 2 as user_id, 1 as connections_id
				UNION
				SELECT 3 as user_id, 2 as connections_id
				) as x
		WHERE
			(SELECT COUNT(*) FROM paymybuddy.user_connections) <= 0;
			
INSERT INTO paymybuddy.payment (bank_account_id, amount, description, target_id)
		SELECT x.bank_account_id, x.amount, x.description, x.target_id
			FROM (
				SELECT 1 as bank_account_id, 200 as amount, 'Loyer' as description, 2 as target_id
				) as x
		WHERE
			(SELECT COUNT(*) FROM paymybuddy.payment) <= 0;
			
	COMMIT;