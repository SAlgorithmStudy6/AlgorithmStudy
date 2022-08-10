from collections import deque

A, B = map(int, input().split())

q = deque()

# visited = [0] * (B + 1) 메모리 초과 나서 없애버림
# 튜플 값을 받는 것으로 변경


def bfs(n, i):
    q.append((n, i))
    while q:
        n, i = q.popleft()
        if n == B:
            print(i + 1)
            exit()
        if n > B:
            # 연산이 두 개니까 break 하면 안됨
            continue
        for f in (0, 1):
            if f == 0:
                newN = n * 2
            else:
                newN = n * 10 + 1
            if 0 <= newN <= B:
                q.append((newN, i + 1))


result = bfs(A, 0)

print(-1)
