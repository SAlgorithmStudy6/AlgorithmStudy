import sys

sys.stdin = open("벌꿀채취.txt", "r", encoding="UTF-8")

T = int(input())

def check(arr):
    arr.sort()
    result = 0
    if sum(arr) > c:
        while sum(arr) < c:
            arr.pop(0)
    for number in arr:
        result += number * number

def calculate(arr):
    global resultA, resultB
    a = arr[:len(arr)//2]
    b = arr[len(arr)//2:]
    a.sort()
    b.sort()
    resultA, resultB = 0, 0
    calDFS(0, a, [], 1)
    calDFS(0, b, [], 2)
    return resultA + resultB

def calDFS(depth, arr, pickList, flag):
    global resultA, resultB
    if depth == len(arr):
        if sum(pickList) <= c:
            temp = 0
            for pick in pickList:
                temp += pick * pick
            if flag == 1:
                if resultA < temp:
                    resultA = temp
            else:
                if resultB < temp:
                    resultB = temp
        return
    calDFS(depth + 1, arr, pickList, flag)
    calDFS(depth + 1, arr, pickList + [arr[depth]], flag)



def pick(depth, pickList):
    global result
    if depth == 2:
        honey = calculate(pickList)
        if result < honey:
            result = honey
        return
    keepPick = pickList[:]
    for y in range(n):
        for x in range(n-m+1):
            if [y, x] not in banList and not visited[y][x]:
                for dx in range(m):
                    visited[y][x + dx] = True
                    pickList.append(matrix[y][x + dx])
                if depth == 0:
                    banList.append([y, x])
                pick(depth + 1, pickList)
                for dx in range(m):
                    visited[y][x + dx] = False
                pickList = keepPick[:]

for test_case in range(1, T+1):
    n, m, c = map(int, input().split())
    matrix = [list(map(int,input().split())) for _ in range(n)]
    visited = [[False] * n for _ in range(n)]
    banList = []
    resultA = -999999999
    resultB = -999999999
    result = -999999999
    pick(0, [])
    print("#{} {}".format(test_case, result))
