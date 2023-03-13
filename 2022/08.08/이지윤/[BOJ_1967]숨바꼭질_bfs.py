from collections import deque

n, k = map(int, input().split())
check = [0] * 100001


def bfs():
    queue = deque()
    queue.append(n)
    while queue:
        _present = queue.popleft()
        if _present == k: return check[_present]
        for _next in (_present - 1, _present + 1, _present*2):
            if 0 <= _next <= 100000 and check[_next] == 0:
                check[_next] = check[_present] + 1
                queue.append(_next)


answer = bfs()
print(answer)
