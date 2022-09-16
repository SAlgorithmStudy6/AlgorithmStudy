import sys; sys.stdin = open("input.txt")
from collections import deque
RECEIPT_N, REPAIR_N, CUSTOMER_N = 0, 0, 0
ANSWER_ARR = []
A, B = 0, 0

def findLowestIndex(_arr) -> int:
    for index in range(1, len(_arr)):
        if not _arr[index] : return index   # 비어 있을 경우, 해당 인덱스 리턴
    return -1                               # 비어 있지 않을 경우, -1 리턴

def solution() -> int:
    global ANSWER_ARR
    total_customer_num = 0
    receipt_count, receipt_arr = 0, [[] for _ in range(RECEIPT_N+1)]
    repair_count, repair_arr = 0, [[] for _ in range(REPAIR_N+1)]
    arr_customer = [i for i in range(1, CUSTOMER_N+1)]
    receipt_queue = deque(arr_customer); repair_queue = deque()

    count_people, time = 0, -1
    while count_people != CUSTOMER_N:
        time += 1
        # (1) 접수 창고 로직 처리
        receipt_done_customer = dict()                                 # 접수가 끝난 고객 저장 : 창구번호(키), 고객번호(값)으로 저장
        for receipt_index in range(1, RECEIPT_N+1):                    # 접수가 끝난 고객 체크 로직
            if receipt_arr[receipt_index] and receipt_arr[receipt_index][0]==time:
                customer_index = receipt_arr[receipt_index][1]
                receipt_done_customer[receipt_index] = customer_index
                ANSWER_ARR[customer_index][0] = receipt_index
                receipt_arr[receipt_index] = []
                receipt_count -= 1
        while receipt_queue and receipt_count!=RECEIPT_N:               # 접수를 해야 하는 고객 체크 및 접수 창고 비었는지 체크
            if arr_t_customer[receipt_queue[0]] <= time:
                customer_index = receipt_queue.popleft()
                place_index = findLowestIndex(receipt_arr)
                if place_index < 0: print("ERROR")
                receipt_arr[place_index] = [time + arr_t_receipt[place_index], customer_index]
                receipt_count += 1
                continue
            if receipt_count == RECEIPT_N:
                for queue_index in range(len(receipt_queue)):
                    if arr_t_customer[receipt_queue[queue_index]] == time:
                        arr_t_customer[receipt_queue[queue_index]] += 1
                    else:
                        break
            break
        # (2) 정비 창고 로직 처리
        receipt_done_customer = dict(sorted(receipt_done_customer.items()))
        repair_queue.extend([value for value in receipt_done_customer.values()])  # 접수가 끝난 고객을 정비 창고 대기열에 안착 로직
        for repair_index in range(1, REPAIR_N + 1):                               # 정비가 끝난 고객 체크 로직
            if repair_arr[repair_index] and repair_arr[repair_index][0] == time:
                customer_index = repair_arr[repair_index][1]
                ANSWER_ARR[customer_index][1] = repair_index
                if checkIsAnswerCustomer(ANSWER_ARR[customer_index]) :
                    total_customer_num += customer_index
                repair_arr[repair_index] = []
                repair_count -= 1
                count_people += 1
        while repair_queue and repair_count!=REPAIR_N:                  # 정비를 해야 하는 고객 체크 및 정비 창고 비었는지 체크
            customer_index = repair_queue.popleft()
            place_index = findLowestIndex(repair_arr)
            if place_index<0 : print("ERROR")
            repair_arr[place_index] = [time + arr_t_repair[place_index], customer_index]
            repair_count+=1
    return total_customer_num if total_customer_num!=0 else -1

def checkIsAnswerCustomer(_place_info) -> bool:
    if _place_info[0]==A and _place_info[1]==B : return True
    else : return False

T = int(input())
for t in range(1, T + 1):
    RECEIPT_N, REPAIR_N, CUSTOMER_N, A, B = map(int, input().split())
    ANSWER_ARR = [[0,0] for _ in range(CUSTOMER_N+1)]
    arr_t_receipt = [0]+list(map(int, input().split()))
    arr_t_repair = [0]+list(map(int, input().split()))
    arr_t_customer = [0]+list(map(int, input().split()))
    answer = solution()
    print(f"#{t} {answer}")
