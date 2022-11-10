import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")


def check(matrix):
    y = len(matrix)
    x = len(matrix[0])

    if y < r or x < c:
        return False
    if matrix[r - 1][c - 1] == k:
        return True
    return False


def calculateR(matrix):
    sortedDicts = []
    for i in range(len(matrix)):
        numDict = dict()
        rowList = matrix[i]
        for num in rowList:
            if num == 0:
                continue
            if num not in numDict:
                numDict[num] = 1
            else:
                numDict[num] += 1
        sortedDict = sorted(numDict.items(), key=lambda item: [item[1], item[0]])
        sortedDicts.append(sortedDict)
    maxLength = max(map(len, sortedDicts)) * 2
    if maxLength > 100:
        maxLength = 100
    result = [[0 for _ in range(maxLength)] for _ in range(len(sortedDicts))]

    y = 0
    for sortedDict in sortedDicts:
        x = 0
        for element in sortedDict:
            result[y][x] = element[0]
            result[y][x + 1] = element[1]
            x += 2
            if x == 100:
                break
        y += 1
        if y == 100:
            break

    return result


def calculateC(matrix):
    sortedDicts = []
    for x in range(len(matrix[0])):
        numDict = dict()
        columnList = []
        for y in range(len(matrix)):
            columnList.append(matrix[y][x])
        for num in columnList:
            if num == 0:
                continue
            if num not in numDict:
                numDict[num] = 1
            else:
                numDict[num] += 1
        sortedDict = sorted(numDict.items(), key=lambda item: [item[1], item[0]])
        sortedDicts.append(sortedDict)
    maxLength = max(map(len, sortedDicts)) * 2
    if maxLength > 100:
        maxLength = 100
    result = [[0 for _ in range(len(sortedDicts))] for _ in range(maxLength)]

    x = 0
    for sortedDict in sortedDicts:
        y = 0
        for element in sortedDict:
            result[y][x] = element[0]
            result[y + 1][x] = element[1]
            y += 2
            if y == 100:
                break
        x += 1
        if x == 100:
            break

    return result


def solution(r, c, k, A):
    t = 0

    while t <= 100:
        if check(A):
            return t

        nowR = len(A)
        nowC = len(A[0])

        if nowR >= nowC:
            A = calculateR(A)
        else:
            A = calculateC(A)
        t += 1

    return -1


r, c, k = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(3)]

print(solution(r, c, k, A))
