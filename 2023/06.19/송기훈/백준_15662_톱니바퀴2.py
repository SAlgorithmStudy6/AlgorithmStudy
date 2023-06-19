import sys
from collections import deque

sys.stdin = open("input.txt", "r", encoding="UTF-8")


def check_right(t_num, t_list, t):
    result = []
    i = t_num
    while i < t-1:
        if t_list[i][2] == t_list[i+1][6]:
            break
        else:
            result.append(i+1)
        i += 1
    return result


def check_left(t_num, t_list):
    result = []
    i = t_num
    while i > 0:
        if t_list[i][6] == t_list[i-1][2]:
            break
        else:
            result.append(i-1)
        i -= 1
    return result


t = int(input())

t_list = []
for _ in range(t):
    t_list.append(deque(map(int, list(input()))))

k = int(input())
for _ in range(k):
    t_num, direction = map(int, input().split())
    t_num -= 1
    temp_direction = direction

    right_list = check_right(t_num, t_list, t)
    left_list = check_left(t_num, t_list)

    # 처음 것 돌리기
    t_list[t_num].rotate(temp_direction)

    # 오른쪽 것을 방향을 바꿔가며 돌림
    for index in right_list:
        temp_direction *= -1
        t_list[index].rotate(temp_direction)

    # 초기 방향으로 초기화하고 왼쪽 것을 방향을 바꿔가며 돌림
    temp_direction = direction
    for index in left_list:
        temp_direction *= -1
        t_list[index].rotate(temp_direction)


count = 0
for gear in t_list:
    if (gear[0] == 1):
        count += 1
print(count)
