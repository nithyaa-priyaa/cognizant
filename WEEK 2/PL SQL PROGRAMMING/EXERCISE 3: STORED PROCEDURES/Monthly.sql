-- Scenario 1: Process Monthly Interest for Savings Accounts
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
IS
BEGIN
    UPDATE accounts 
    SET balance = balance * 1.01
    WHERE UPPER(account_type) = 'SAVINGS';
    
    COMMIT;
END ProcessMonthlyInterest;
/

-- Scenario 2: Update Employee Bonus by Department
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department_id IN NUMBER,
    p_bonus_percentage IN NUMBER
)
IS
BEGIN
    UPDATE employees 
    SET salary = salary * (1 + p_bonus_percentage/100)
    WHERE department_id = p_department_id;
    
    COMMIT;
END UpdateEmployeeBonus;
/

-- Scenario 3: Transfer Funds Between Accounts
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
)
IS
    v_balance NUMBER;
BEGIN
    -- Check sufficient funds
    SELECT balance INTO v_balance 
    FROM accounts 
    WHERE account_id = p_from_account_id;
    
    IF v_balance >= p_amount THEN
        -- Debit from source account
        UPDATE accounts 
        SET balance = balance - p_amount
        WHERE account_id = p_from_account_id;
        
        -- Credit to destination account
        UPDATE accounts 
        SET balance = balance + p_amount
        WHERE account_id = p_to_account_id;
        
        COMMIT;
    ELSE
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20002, 'Account not found');
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END TransferFunds;
/
