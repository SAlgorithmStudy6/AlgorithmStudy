import sys

sys.stdin = open("09.22/핀볼게임.txt", "r", encoding="UTF-8")

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

change = [(), (1, 3, 0, 2), (3, 0, 1, 2),
          (2, 0, 3, 1), (1, 2, 3, 0), (1, 0, 3, 2)]


def pinball(y, x, d):
    global wormholeDict
    score = 0
    nowY, nowX = y, x
    nY, nX = y, x
    while True:
        nY += dy[d]
        nX += dx[d]
        # 출발 위치로 돌아오거나 블랙홀 만나면 종료
        if (nY, nX) == (nowY, nowX) or matrix[nY][nX] == -1:
            return score
        # 블록 만나면 방향 바꾸고 점수 + 1
        if 1 <= matrix[nY][nX] <= 5:
            d = change[matrix[nY][nX]][d]
            score += 1
        # 웜홀 만나면 같은 번호의 웜홀로 이동. 방향은 유지
        elif 6 <= matrix[nY][nX] <= 10:
            nY, nX = wormholeDict[(nY, nX)]


T = int(input())
for test_case in range(T):
    N = int(input())
    wormholeTemp = [()] * 11
    wormholeDict = {}
    matrix = [[5] * (N+2)]
    for y in range(1, N+1):
        matrix.append([5] + list(map(int, input().split())) + [5])
        for x in range(1, N+1):
            if matrix[y][x] >= 6:
                num = matrix[y][x]
                if not wormholeTemp[num]:
                    wormholeTemp[num] = (y, x)
                else:   # 같은 번호 웜홀끼리 위치 정보 저장
                    wormholeDict[wormholeTemp[num]] = (y, x)
                    wormholeDict[(y, x)] = wormholeTemp[num]
    matrix.append([5] * (N+2))

    maxValue = -999999
    for y in range(1, N+1):
        for x in range(1, N+1):
            if matrix[y][x] == 0:
                for d in range(4):
                    maxValue = max(maxValue, pinball(y, x, d))
    print("#{} {}".format(test_case+1, maxValue))
