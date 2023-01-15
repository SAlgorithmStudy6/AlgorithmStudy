answer = 0
num = 0


def dfs(nowPiro, depth, dungeons, visited):
    global answer
    if depth > answer:
        answer = depth

    for i in range(num):
        if nowPiro >= dungeons[i][0] and not visited[i]:
            visited[i] = True
            dfs(nowPiro - dungeons[i][1], depth+1, dungeons, visited)
            visited[i] = False


def solution(k, dungeons):
    global num
    num = len(dungeons)
    visited = [False for _ in range(num)]
    dfs(k, 0, dungeons, visited)

    return answer
