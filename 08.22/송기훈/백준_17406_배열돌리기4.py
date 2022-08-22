import sys
import itertools
import copy

sys.stdin = open("input.txt", "r", encoding="utf-8")

n, m, k = map(int, input().split())
matrix = [list(map(int, input().split())) for _ in range(n)]
rotateList = [list(map(int, input().split())) for _ in range(k)]
minValue = 999999999

for permute in itertools.permutations(rotateList, k):
    tempMatrix = copy.deepcopy(matrix)
    for p in permute:
        r, c, s = p
        r -= 1
        c -= 1
        for i in range(s, 0, -1):
            temp = tempMatrix[r - i][c + i]
            # 윗줄, 오른쪽으로 한칸씩
            tempMatrix[r - i][c - i + 1 : c + i + 1] = tempMatrix[r - i][c - i : c + i]
            # 왼쪽줄, 위로 한칸씩
            for y in range(r - i, r + i):
                tempMatrix[y][c - i] = tempMatrix[y + 1][c - i]
            # 아랫줄, 왼쪽으로 한칸씩
            tempMatrix[r + i][c - i : c + i] = tempMatrix[r + i][c - i + 1 : c + i + 1]
            # 오른쪽줄, 아래로 한칸씩
            for y in range(r + i, r - i, -1):
                tempMatrix[y][c + i] = tempMatrix[y - 1][c + i]
            # 마지막 값 끼워넣기
            tempMatrix[r - i + 1][c + i] = temp
    for line in tempMatrix:
        sumValue = sum(line)
        if minValue > sumValue:
            minValue = sumValue

print(minValue)
