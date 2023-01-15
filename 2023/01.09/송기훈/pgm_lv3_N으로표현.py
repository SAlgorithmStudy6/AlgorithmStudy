def solution(N, number):
    answer = -1
    # dp = [[0개로 만든 수], [1개로 만든 수], ... [8개로 만든 수]]
    dp = [[]]

    # number가 자기 자신이면 1
    if N == number:
        return 1
    # i == N의 개수
    for i in range(1, 9):
        temp = []

        # 숫자 붙이기
        temp.append(int(str(N) * i))

        for j in range(1, i):
            for k in dp[j]:
                for l in dp[i - j]:
                    temp.append(k+l)
                    temp.append(k-l)
                    temp.append(k*l)
                    if l != 0:
                        temp.append(k // l)
        if number in temp:
            return i
        dp.append(list(set(temp)))

    return answer


# number에서 시작해서 N을 만드는 방법? -> 실패
# ex) dp[4] = [dp[1], dp[3] 연산] + [dp[2], dp[2] 연산] + [dp[3], dp[1] 연산]
