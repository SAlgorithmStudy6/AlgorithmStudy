# import sys; sys.stdin=open("input.txt")
from collections import deque
WHEEL_COUNT = 4
MAGNETIC_RIGHT = 2; MAGNETIC_LEFT = 6
move = [-1, +1]

def makeRotation(_rotate_num, _rotate_type) :
    rotate_arr = arr_wheel_info[_rotate_num]
    if _rotate_type>0:
        temp = rotate_arr.pop()
        rotate_arr.insert(0, temp)
    else :
        temp = rotate_arr.pop(0)
        rotate_arr.append(temp)

def doAction(_start_num, _start_rotate_type) :
    visited = [False] * WHEEL_COUNT
    visited[_start_num] = True
    queue = deque()
    queue.append((_start_num, _start_rotate_type))
    while queue :
        present_num, present_rotate_type = queue.popleft()
        for delta in move:
            neighbor = present_num + delta
            if 0<=neighbor<WHEEL_COUNT and not visited[neighbor]:
                visited[neighbor] = True
                if delta>0 :
                    present_mag_type = arr_wheel_info[present_num][MAGNETIC_RIGHT]
                    next_mag_type = arr_wheel_info[neighbor][MAGNETIC_LEFT]
                else :
                    present_mag_type = arr_wheel_info[present_num][MAGNETIC_LEFT]
                    next_mag_type = arr_wheel_info[neighbor][MAGNETIC_RIGHT]
                if present_mag_type!=next_mag_type:
                    queue.append((neighbor, (-1)*present_rotate_type))
        makeRotation(present_num, present_rotate_type)
    return

def calculateScore() -> int:
    total_score = 0
    for wheel_index in range(WHEEL_COUNT):
        if arr_wheel_info[wheel_index][0] :
            total_score += 2**wheel_index
    return total_score

def solution(_action_arr) -> int:
    for action in _action_arr:
        doAction(action[0]-1, action[1])
    return calculateScore()

T = int(input())
for t in range(1, T+1):
    k = int(input())
    arr_wheel_info = [list(map(int, input().split())) for _ in range(WHEEL_COUNT)]
    arr_actions_k = [list(map(int, input().split())) for _ in range(k)]
    answer = solution(arr_actions_k)
    print(f"#{t} {answer}")
