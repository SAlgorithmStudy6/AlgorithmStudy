# 18.8점
# import sys

# sys.setrecursionlimit(10**9)

# def solution(n, roads, sources, destination):
#     answer = []
#     matrix = [[] for _ in range(n + 1)]

#     for r in roads:
#         a = r[0]
#         b = r[1]
#         matrix[r[0]].append(b)
#         matrix[r[1]].append(a)

#     def dfs(source, depth):
#         if destination in matrix[source]:
#             return depth
#         for i in matrix[source]:
#             return dfs(i, depth + 1)
#         return -1

#     for s in sources:
#         if s == destination:
#             answer.append(0)
#         else:
#             answer.append(dfs(s, 1))

#     return answer

from collections import deque


def solution(n, roads, sources, destination):
    answer = []
    matrix = [[] for _ in range(n + 1)]

    for r in roads:
        a = r[0]
        b = r[1]
        matrix[a].append(b)
        matrix[b].append(a)

    q = deque()
    visited = [-1 for _ in range(n + 1)]
    visited[destination] = 0
    q.append((destination, 0))
    while q:
        now, depth = q.popleft()
        for n in matrix[now]:
            if visited[n] < 0:
                q.append((n, depth + 1))
                visited[n] = depth + 1

    for s in sources:
        answer.append(visited[s])

    return answer
