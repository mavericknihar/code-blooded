# Account Data for performing operations.
accounts = {
    1001: {
        'Name': 'User01',
        'Balance': 10000,
        'pin': '1234'
    },
    1002: {
        'Name': 'User02',
        'Balance': 200000,
        'pin': '2023'
    },
    1003: {
        'Name': 'User03',
        'Balance': 45000,
        'pin': '1900'
    },
    1004: {
        'Name': 'User04',
        'Balance': 30000,
        'pin': '2000'
    }
}

# Function to check current balance in the account
def check_balance(account):
    balance = accounts[account]['Balance']
    print(f"Current Balance: ${balance}")

# Function to withdraw funds from the bank account
def withdraw_funds(account):
    amount = float(input("Please Enter the Amount you wish to withdraw: "))
    balance = accounts[account]['Balance']
    if amount > balance:
        print("Insufficient Funds....Unable to Withdraw.")
    else:
        balance -= amount
        accounts[account]['Balance'] = balance
        print(f"Amount Withdrawn: ${amount}")
        print(f"Remaining Balance: ${balance}")

# Function to deposit funds into the bank account
def deposit_funds(account):
    amount = float(input("Enter the amount you wish to deposit in your bank account: "))
    balance = accounts[account]['Balance']
    balance += amount
    accounts[account]['Balance'] = balance
    print(f"Amount Deposited into the account: ${amount}")
    print(f"Current Balance after depositing funds: ${balance}")

# ATM Interface
def atm_menu():
    print("Welcome to Nihar's ATM")
    account = input("Enter Your Account Number: ")
    pin = input("Enter Your Pin Number: ")

    # Verifying if the account credentials are true
    if int(account) in accounts and pin == accounts[int(account)]['pin']:
        print(f"Welcome, {accounts[int(account)]['Name']}!")

        # Proceeding to the menu if the entered credentials are true and providing the user with the ATM interface.
        while True:
            print("Please Select an Option from the below menu")
            print("1. Check Balance")
            print("2. Withdraw Funds")
            print("3. Deposit Funds")
            print("4. Exit")

            choice = input("Enter Your Choice: ")

            if choice == "1":
                check_balance(int(account))
            elif choice == "2":
                withdraw_funds(int(account))
            elif choice == "3":
                deposit_funds(int(account))
            elif choice == "4":
                print("Thank you for using our ATM Service! Hope to see you again. Goodbye!")
                break
            else:
                print("Invalid Choice. Please Try Again.")
    else:
        print("Invalid Account Credentials. Please Try Again.")

if __name__ == '__main__':
    atm_menu()
