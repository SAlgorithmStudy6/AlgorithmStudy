import sys

sys.stdin = open("09.12/protect_film.txt", "r", encoding="UTF-8")

T = int(input())


def checkAll(arr):
    check = [0 for _ in range(w)]
    for x in range(w):
        queue = []
        for y in range(d):
            if not queue:
                queue.append(arr[y][x])
            else:
                if queue[-1] == arr[y][x]:
                    queue.append(arr[y][x])
                else:
                    if len(queue) >= k:
                        check[x] = 1
                    queue = [arr[y][x]]
        if queue:
            if len(queue) >= k:
                check[x] = 1
        if check[x] < 1:
            return -1
    return sum(check)


def dfs(depth, dfsArr, changed):
    global result
    if changed > result:
        return
    if depth == d:
        if checkAll(dfsArr) == w:
            if result > changed:
                result = changed
        return

    keepOrigin = dfsArr[depth][:]
    # depth행에
    # 약품 안 넣기
    dfs(depth + 1, dfsArr, changed)

    # A 약품 넣기 (0), 원상복구
    for x in range(w):
        dfsArr[depth][x] = 0
    dfs(depth + 1, dfsArr, changed + 1)
    dfsArr[depth] = keepOrigin[:]

    # B 약품 넣기 (1), 원상복구
    for x in range(w):
        dfsArr[depth][x] = 1
    dfs(depth + 1, dfsArr, changed + 1)
    dfsArr[depth] = keepOrigin[:]


for test_case in range(1, T+1):
    d, w, k = map(int, input().split())
    matrix = [list(map(int, input().split())) for _ in range(d)]
    result = 999999999
    dfs(0, matrix, 0)

    print("#{} {}".format(test_case, result))
