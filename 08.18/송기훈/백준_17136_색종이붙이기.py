import sys

sys.stdin = open("./input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

matrix = [list(map(int, input().split())) for _ in range(10)]
paperList = [5, 5, 5, 5, 5]
total = 25


def sizeCheck(y, x, size):
    for j in range(y, y + size + 1):
        for i in range(x, x + size + 1):
            if matrix[j][i] != 1:
                return False
    return True


def dfs(y, x, count):
    global paperList, total
    if y >= 10:
        total = min(total, count)
        return
    if x >= 10:
        dfs(y + 1, 0, count)
        return
    if matrix[y][x] == 1:
        for s in range(5):
            if paperList[s] == 0:
                continue
            if y + s > 10 or x + s > 10:
                continue
            if not sizeCheck(y, x, s):
                break
            for j in range(y, y + s + 1):
                for i in range(x, x + s + 1):
                    matrix[j][i] = 0
            paperList[s] -= 1
            dfs(y, x + s + 1, count + 1)
            paperList[s] += 1
            for j in range(y, y + s + 1):
                for i in range(x, x + s + 1):
                    matrix[j][i] = 1
    else:
        dfs(y, x + 1, count)


dfs(0, 0, 0)
if total == 25:
    print(-1)
else:
    print(total)
