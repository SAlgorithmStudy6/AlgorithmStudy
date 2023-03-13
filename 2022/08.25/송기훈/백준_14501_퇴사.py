import sys

sys.stdin = open("input.txt",
                 "r", encoding="utf-8")

n = int(input())
matrix = [list(map(int, input().split())) for _ in range(n)]
maxValue = -999999999


def dfs(index, tempSum):
    global maxValue
    if index >= n:
        if maxValue < tempSum:
            maxValue = tempSum
            return
        return
    day = matrix[index][0]
    pay = matrix[index][1]
    # 안 더하고 돌림
    dfs(index + 1, tempSum)

    # 범위 안이라면 더하고 돌림
    if index + day <= n:
        tempSum += pay
        dfs(index + day, tempSum)


dfs(0, 0)

print(maxValue)
