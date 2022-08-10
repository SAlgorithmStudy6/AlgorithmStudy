from collections import deque

def solution(_a, _b) -> int:
    queue = deque()
    queue.append((_a, 1))
    while queue:
        num, count = queue.popleft()
        next_num = [10*num+1, 2*num]
        for _next_n in next_num:
            if _next_n == _b : return count + 1
            if _next_n < _b :
                queue.append((_next_n, count+1))
    return -1



a, b = map(int, input().split())
answer = solution(a, b)
print(answer)
