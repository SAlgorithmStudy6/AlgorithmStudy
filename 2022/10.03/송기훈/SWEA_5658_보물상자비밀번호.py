import sys
from collections import deque

sys.stdin = open("input.txt", "r", encoding="UTF-8")

T = int(input())

for test_case in range(1, T+1):
    n, k = map(int, input().split())
    inputList = list(input())
    q = deque(inputList)

    delta = n // 4
    candidate = []
    for _ in range(delta):
        i = 0
        while i < n:
            temp = list(q)[i:i+delta]
            temp = "".join(temp)
            # 16진수 문자를 10진수로
            tempInt = int(temp, 16)
            if tempInt not in candidate:
                candidate.append(tempInt)
            i += delta
        # 한 칸씩 이동
        q.rotate()
    # 내림차순 정렬
    candidate.sort(reverse=True)
    print("#{} {}".format(test_case, candidate[k-1]))
