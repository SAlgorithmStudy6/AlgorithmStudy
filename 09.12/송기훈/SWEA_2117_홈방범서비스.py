import sys

sys.stdin = open("09.12/홈방범서비스.txt", "r", encoding="UTF-8")

T = int(input())

costList = [0] + [(k * k) + (k - 1) * (k - 1) for k in range(1, 22)]

for test_case in range(1, T+1):
    n, m = map(int, input().split())
    maxValue = 0
    matrix = []
    homeList = []
    for y in range(n):
        temp = list(map(int, input().split()))
        matrix.append(temp)
        for x in range(n):
            if temp[x] == 1:
                homeList.append((y, x))
    # k를 늘려가면서 확인,
    # n이 홀수일 때 마름모 속 사각형이 n x n 을 커버
    # n이 짝수일 때는 n x n이 안 들어감...n은 20까지이므로 k=21까지 탐색이 안전
    for k in range(1, n+2):
        for y in range(n):
            for x in range(n):
                count = 0
                for home in homeList:
                    # 거리로 마름모 속에 있는지 확인
                    if abs(home[0] - y) + abs(home[1] - x) < k:
                        count += 1
                    if count * m >= costList[k]:
                        maxValue = max(maxValue, count)

    print("#{} {}".format(test_case, maxValue))
