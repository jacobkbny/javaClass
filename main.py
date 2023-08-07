import mysql.connector
import random
from faker import Faker

fake = Faker()

dataBase = mysql.connector.connect(
    host="localhost",
    user="root",
    passwd="your password",
)
# prepare a curor object

cursorObject = dataBase.cursor()

cursorObject.execute("use Toss")

# def fake_massive_transfer():

def fetch_all_bank_total_index():
    all_bank = {
        1: "KB",
        2: "HANA",
        3: "IBK",
        4: "NH",
        5: "WR",
    }
    for i in range(1,6):    
        sql = f"""SELECT Count(*) from {all_bank[i]}"""
        cursorObject.execute(sql)
        result = cursorObject.fetchall()
        print("result:",result)
#1부터 5까지에 해당되는 모든 은행의 index에 적용한다. 1부터 5까지의 은행 중 특정한 은행을 선택하여 해당 은행의 정보값의 개수를 센 후 그 결과값을 출력하라.




def create_fake_users():
    bank = Bank()
    all_bank = {
        1: "KB",
        2: "HANA",
        3: "IBK",
        4: "NH",
        5: "WR",
    }
    bank_name = all_bank[random.randint(1,5)]
    bank.owner = fake.name()
    bank.account = generate_bank_account()
    bank.activation = True
    bank.password = random.randint(1000,9999)
    bank.balance = 0
    print("은행: ", bank_name, " 계좌번호: ", bank.account)
    create_query(bank_name, bank)
    dataBase.commit()

def print_all_bank():
    print("은행을 선택해주세요.")
    slection = input("1.국민은행 2.하나은행 3.기업은행(IBK) 4.농협은행 5.우리은행\n")
    all_bank = {
        "1": "KB",
        "2": "HANA",
        "3": "IBK",
        "4": "NH",
        "5": "WR",
    }
    return all_bank[slection]


def create_query(bank_name, bank):
    global cursorObject
    sql = f"""
    Insert into {bank_name} (Owner,AccountNumber,Password,Balance) values ('{bank.owner}','{bank.account}','{bank.password}','{bank.balance}')
"""
    cursorObject.execute(sql)
    return


def authentication(bank_name, account, password):
    global cursorObject

    sql = f"""
    SELECT ID from {bank_name} WHERE AccountNumber = '{account}' AND Password = '{password}'
"""
    cursorObject.execute(sql)
    result = cursorObject.fetchall()
    if result is not None:
        return result[0][0]
    else:
        return -1


def fetch_receiver_detail(bank_name, account):
    global cursorObject
    sql = f"""
    SELECT ID from {bank_name} WHERE AccountNumber = '{account}'
"""
    cursorObject.execute(sql)
    result = cursorObject.fetchall()
    if result is not None:
        return result[0][0]
    else:
        return -1


def fetch_client_detail(bank_name, index):
    global cursorObject
    number_index = int(index)
    sql = f"""
    SELECT * from {bank_name} where ID = {number_index}
        """
    cursorObject.execute(sql)
    result = cursorObject.fetchall()
    bank = Bank()
    bank.name = bank_name
    bank.index = result[0][0]
    bank.owner = result[0][1]
    bank.account = result[0][2]
    bank.password = result[0][3]
    bank.balance = result[0][4]
    return bank


def update_balance(bank_name, amount, index):
    global cursorObject
    sql = f"""
    Update {bank_name} set Balance = {amount} WHERE ID = {index}
"""
    cursorObject.execute(sql)
    dataBase.commit()
    return


class Bank:
    def __init__(self):
        self.name = None
        self.index = 0
        self.owner = None
        self.account = None
        self.activation = True
        self.balance = 0
        self.password = None


def create_bank():
    bank = Bank()
    bank_name = print_all_bank()
    bank.owner = input("성함을 입력해주세요: ")
    bank.account = generate_bank_account()
    bank.activation = True
    password = input("설정할 비밀번호 4자리를 입력해주세요: ")
    password_check = input("비밀번호 4자리를 다시 입력해주세요: ")
    if password == password_check:
        bank.password = password
    else:
        print("입력하신 비밀번호가 올바르지 않습니다.")
        return
    bank.balance = 0
    print("은행: ", bank_name, " 계좌번호: ", bank.account)
    create_query(bank_name, bank)
    dataBase.commit()

def check_balance():
    index, bank_name = validate()
    bank = fetch_client_detail(bank_name, index)
    print("고객님의 잔액은 :", bank.balance, "원 입니다.\n")
    return


def add_balance():
    index, bank_name = validate()
    bank = fetch_client_detail(bank_name, index)
    if bank is not None:
        add = int(input("충전하실 액수를 입력해주세요: "))
        previous_balance = bank.balance
        renewed_balance = previous_balance + add
        update_balance(bank.name, renewed_balance, index)
        print("충전후 잔액은 ", renewed_balance, "원 입니다")
    else:
        print("비밀번호가 틀렸습니다.")


def validate():
    bank_name = print_all_bank()
    account = input("계좌번호를 입력해주세요: ")
    password = input("비밀번호를 입력해주세요: ")
    index = authentication(bank_name, account, password)
    return index, bank_name


def fetch_receiver():
    bank_name = print_all_bank()
    account = input("받으시는분의 계좌번호를 입력해주세요: ")
    index = fetch_receiver_detail(bank_name, account)
    return index, bank_name


def generate_bank_account():
    first_part = str(random.randint(1000000, 1999999))
    second_part = str(random.randint(10, 99))
    last_part = str(random.randint(1000000, 1999999))
    return first_part + "-" + second_part + "-" + last_part


def transfer():
    index, bank_name = validate()
    sender_bank_detail = fetch_client_detail(bank_name, index)
    if sender_bank_detail is not None:
        print("받으시는 분의 정보를 입력해주세요.")
        receiver_index, bank_name = fetch_receiver()
        receiver_bank_detail = fetch_client_detail(bank_name, receiver_index)
        amount = int(input("송금하실 금액을 입력해주세요: "))
        if amount > sender_bank_detail.balance:
            print("잔액이 부족합니다.")
        else:
            sender_previous_balance = sender_bank_detail.balance
            sender_renewed_balace = sender_previous_balance - amount
            update_balance(
                sender_bank_detail.name, sender_renewed_balace, sender_bank_detail.index
            )
            receiver_previous_balance = receiver_bank_detail.balance
            receiver_renewed_balance = receiver_previous_balance + amount
            update_balance(
                receiver_bank_detail.name,
                receiver_renewed_balance,
                receiver_bank_detail.index,
            )

            print("송금 후 잔액은 ", sender_renewed_balace, "원 입니다.")


def main():
    while True:
        print("원하시는 서비스를 선택해주세요")
        print("1.계좌생성 2.잔액 조회 3. 잔액 충전 4. 송금 5. 종료")
        user_input = input()
        if user_input == "1":
            create_bank()
        elif user_input == "2":
            check_balance()
        elif user_input == "3":
            add_balance()
        elif user_input == "4":
            transfer()
        elif user_input == "관리자":
            fetch_all_bank_total_index()
        elif user_input == "5":
            print("시스템을 종료합니다")
            break
        else:
            print("존재하지 않는 숫자입니다 다시 입력해주세요.\n")
# for i in range(1000):
#     create_fake_users()
main()
# fetch_all_bank_total_index()
