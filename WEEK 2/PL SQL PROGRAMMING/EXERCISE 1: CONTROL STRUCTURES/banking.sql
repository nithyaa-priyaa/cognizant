-- SCENARIO 1: Apply 1% discount to loan interest rates for customers above 60
DECLARE
    CURSOR c_senior_loans IS
        SELECT c.customer_id, l.loan_id, l.interest_rate
        FROM customers c
        JOIN loans l ON c.customer_id = l.customer_id
        WHERE c.age > 60;
BEGIN
    FOR rec IN c_senior_loans LOOP
        UPDATE loans 
        SET interest_rate = interest_rate - 1
        WHERE loan_id = rec.loan_id;
    END LOOP;
    COMMIT;
END;
/

-- SCENARIO 2: Set VIP status for customers with balance over $10,000
DECLARE
    CURSOR c_customers IS
        SELECT customer_id, balance
        FROM customers;
BEGIN
    FOR rec IN c_customers LOOP
        IF rec.balance > 10000 THEN
            UPDATE customers 
            SET is_vip = 'TRUE'
            WHERE customer_id = rec.customer_id;
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- SCENARIO 3: Print reminders for loans due within 30 days
DECLARE
    CURSOR c_due_loans IS
        SELECT c.first_name, c.last_name, l.loan_id, l.due_date
        FROM customers c
        JOIN loans l ON c.customer_id = l.customer_id
        WHERE l.due_date BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    FOR rec IN c_due_loans LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: ' || rec.first_name || ' ' || rec.last_name || 
                           ' - Loan ID: ' || rec.loan_id || 
                           ' - Due Date: ' || rec.due_date);
    END LOOP;
END;
/
