from collections import deque


def solution(N, road, K):
    graph = [[0 for _ in range(N + 1)] for _ in range(N + 1)]

    for a, b, c in road:
        if graph[a][b] == 0:
            graph[a][b] = c
            graph[b][a] = c
        else:
            graph[a][b] = min(graph[a][b], c)
            graph[b][a] = min(graph[b][a], c)

    distance = [10 ** 9 for _ in range(N + 1)]

    distance[1] = 0
    q = deque()
    q.append(1)
    while q:
        now = q.popleft()
        for i in range(1, N + 1):
            if graph[now][i] == 0:
                continue

            if (distance[i] > distance[now] + graph[now][i]
                    and distance[now] + graph[now][i] <= K):
                distance[i] = distance[now] + graph[now][i]
                q.append(i)

    return len([x for x in distance if x <= K])
