import sys; sys.stdin = open("input.txt")
from collections import deque
HEX_TO_NUM = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F']

def change_hexNum_to_num(_hex_str) -> int:
    index, num = 0, 0
    for hex_num in _hex_str[::-1]:
        num += (16 ** index) * HEX_TO_NUM.index(hex_num)
        index+=1
    return num

def generate_nums(_queue) -> list:
    num_arr = []
    count, hex_num = 1, ''
    for index in range(len(_queue)-1, -1, -1):
        hex_num += _queue[index]
        if count==n//4:
            num_arr.append(hex_num)
            count=count%(n//4)
            hex_num = ''
        count+=1
    return num_arr

def solution(_k, _queue):
    all_generated_nums = set()
    for i in range(n//4):
        hex_num_arr = generate_nums(_queue)
        all_generated_nums.update(hex_num_arr)
        hex_num = _queue.popleft()
        _queue.append(hex_num)
    all_generated_nums = list(all_generated_nums)
    all_generated_nums.sort(reverse=True)
    return change_hexNum_to_num(all_generated_nums[_k-1])


T = int(input())
for t in range(1, T + 1):
    n, k = map(int, input().split())
    arr = reversed(list(input()))       # 회전 방향과 배열의 데이터 방향이 반대이므로 reverse
    queue = deque(arr)
    answer = solution(k, queue)
    print(f"#{t} {answer}")
