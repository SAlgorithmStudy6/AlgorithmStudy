# pypy로 제출
import sys
import itertools

sys.stdin = open("./input.txt", "r", encoding="utf-8")
input = sys.stdin.readline

n = int(input())
pList = list(itertools.permutations([i for i in range(1, 9)], 8))
innings = [list(map(int, input().split())) for _ in range(n)]
maxValue = 0

for p in pList:
    order = list(p[:3]) + [0] + list(p[3:])
    score = 0
    idx = 0
    for inning in innings:
        outCount = 0
        base1, base2, base3 = 0, 0, 0
        while outCount < 3:
            if inning[order[idx]] == 0:
                outCount += 1
            elif inning[order[idx]] == 1:
                score += base3
                base1, base2, base3 = 1, base1, base2
            elif inning[order[idx]] == 2:
                score = score + base2 + base3
                base1, base2, base3 = 0, 1, base1
            elif inning[order[idx]] == 3:
                score = score + base1 + base2 + base3
                base1, base2, base3 = 0, 0, 1
            elif inning[order[idx]] == 4:
                score = score + base1 + base2 + base3 + 1
                base1, base2, base3 = 0, 0, 0
            idx = (idx + 1) % 9

    maxValue = max(maxValue, score)
print(maxValue)
