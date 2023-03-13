
from itertools import permutations

n = int(input())
p = list(permutations([x for x in range(1, 9)], 8))
board = [list(map(int, input().split(' '))) for x in range(n)]
answer = 0

for i in set(p):
    order = list(i[:3]) + [0] + list(i[3:])
    score = 0
    index = 0
    for inning in range(n):
        out = 0
        base1, base2, base3 = 0, 0, 0
        while out != 3:
            if board[inning][order[index]] == 0:
                out += 1
            elif board[inning][order[index]] == 1:
                score += base3
                base1, base2, base3 = 1, base1, base2
            elif board[inning][order[index]] == 2:
                score += (base2 + base3)
                base1, base2, base3 = 0, 1, base1
            elif board[inning][order[index]] == 3:
                score += (base1 + base2 + base3)
                base1, base2, base3 = 0, 0, 1
            elif board[inning][order[index]] == 4:
                score += (base1 + base2 + base3 + 1)
                base1, base2, base3 = 0, 0, 0
            index += 1
            if index == 9:
                index = 0

    answer = max(answer, score)
print(answer)

# https://bladejun.tistory.com/113