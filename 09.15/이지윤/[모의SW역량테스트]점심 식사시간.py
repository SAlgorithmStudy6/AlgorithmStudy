# import sys; sys.stdin = open("input.txt")
from collections import deque

PERSON_TYPE, STAIR_TYPE = 1, 2
PERSON_NUM, PERSONS = 0, []
STAIR_NUM, STAIRS = 2, []

def downStairs(_stair1, _stair2):
    global answer
    wait1, wait2 = deque(_stair1), deque(_stair2)
    stair1, stair2 = deque(), deque()
    time, count_people = 0, 0
    while count_people!=PERSON_NUM:
        time += 1
        # (1) 계단 로직 처리
        while stair1:
            if stair1[0] == time:
                stair1.popleft()
                count_people += 1
                continue
            break
        while stair2:
            if stair2[0] == time:
                stair2.popleft()
                count_people += 1
                continue
            break
        # (2) waiting 로직 처리
        while wait1:
            if wait1[0]==time:
                if len(stair1) < 3:
                    time_people = wait1.popleft()
                    stair1.append(time_people + stair_times_result[1])
                    continue
                else :
                    for wait_index in range(len(wait1)):
                        if wait1[wait_index]==time: wait1[wait_index] += 1
                        else : break
            break
        while wait2:
            if wait2[0]==time:
                if len(stair2) < 3:
                    time_people = wait2.popleft()
                    stair2.append(time_people + stair_times_result[2])
                    continue
                else :
                    for wait_index in range(len(wait2)):
                        if wait2[wait_index]==time: wait2[wait_index] += 1
                        else : break
            break
    answer = min(answer, time)
    return

def combination_dfs(_depth, _choices):   # DFS
    if _depth==PERSON_NUM:                      # 종료 조건 : 깊이==사람수, 즉 사람 마다 계단 선택을 결정 완료 했을 때. (항상 계단은 0 or 1, 2개 중 하나)
        stair1_move_time, stair2_move_time = [], []
        for choice_index in range(PERSON_NUM):
            if _choices[choice_index]==1: stair1_move_time.append(distances_result[choice_index][1])
            else : stair2_move_time.append(distances_result[choice_index][2])
        stair1_move_time.sort(); stair2_move_time.sort()
        downStairs(stair1_move_time, stair2_move_time)
        return
    combination_dfs(_depth+1, _choices)
    _choices[_depth] = 2
    combination_dfs(_depth+1, _choices)
    _choices[_depth] = 1


def calculate_values() -> (list, list):         # Memoization - 계단까지 가는 소요 시간, 계단에서 내려가는 소요 시간
    stair_arr, distant_arr = [0], []
    for person_index in range(PERSON_NUM):      # (1) 계단까지 가는 소요시간
        dist1 = move_to_stair_time(PERSONS[person_index][0], PERSONS[person_index][1], STAIRS[0][0], STAIRS[0][1])
        dist2 = move_to_stair_time(PERSONS[person_index][0], PERSONS[person_index][1], STAIRS[1][0], STAIRS[1][1])
        distant_arr.append([0, dist1, dist2])
    for stair_index in range(STAIR_NUM):        # (2) 계단에서 내려가는 소요시간
        time = move_stair_time(arr[STAIRS[stair_index][1]][STAIRS[stair_index][0]])
        stair_arr.append(time)
    return stair_arr, distant_arr

def move_stair_time(_stair_size) -> int:
    return _stair_size

def move_to_stair_time(_px, _py, _sx, _sy) -> int:
    return abs(_px-_sx) + abs(_py-_sy) + 1  # [※주의] 1분 후 계단을 내려갈 수 있음.

def init():
    global PERSON_NUM
    PERSON_NUM = 0
    PERSONS.clear()
    STAIRS.clear()

T = int(input())
for t in range(1, T+1):
    n = int(input())
    arr = []; init()
    for row_index in range(n):
        row = list(map(int, input().split()))
        for col_index in range(n):
            if row[col_index]==PERSON_TYPE :
                PERSONS.append((col_index, row_index))
                PERSON_NUM += 1
            if row[col_index]>=STAIR_TYPE :
                STAIRS.append((col_index, row_index))
        arr.append(row)
    stair_times_result, distances_result = calculate_values()
    answer = float("inf")
    combination_dfs(0, [1]*PERSON_NUM)
    print(f"#{t} {answer}")