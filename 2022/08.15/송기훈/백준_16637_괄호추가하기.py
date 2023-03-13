import sys
from collections import deque

sys.stdin = open("./input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

n = int(input())
arr = input().rstrip()


def cal(a, b, op):
    if op == "+":
        return int(a) + int(b)
    elif op == "-":
        return int(a) - int(b)
    return int(a) * int(b)


def calArr(arr):
    result = arr[0]
    for i in range(0, len(arr) - 2, 2):
        b = arr[i + 2]
        result = cal(result, b, arr[i + 1])
    return result


def dfs(index, nowArr):
    # 마지막 수 일때는 순차 계산
    if index == n - 1:
        temp = nowArr + [arr[index]]
        return calArr(temp)
    # 괄호 치기
    if index == n - 3:
        # 괄호 없을 때, 기존식 + [ 숫자 + 연산자 ]
        noBracket = nowArr + [arr[index], arr[index + 1]]
        # 괄호 있을 때
        # 기존식 + [ a[i] + a[i+1] + a[i+2] , a[i+3] ]
        temp = cal(arr[index], arr[index + 2], arr[index + 1])
        bracket = nowArr + [temp]
        return max(dfs(index + 2, noBracket), calArr(bracket))
    noBracket = nowArr + [arr[index], arr[index + 1]]
    temp = cal(arr[index], arr[index + 2], arr[index + 1])
    bracket = nowArr + [temp, arr[index + 3]]
    return max(dfs(index + 2, noBracket), dfs(index + 4, bracket))


print(dfs(0, []))
