import sys

sys.stdin = open("03.16/sample_input.txt", "r", encoding="UTF-8")

T = int(input())

# D C B A
# 0 0 0 0

for test_case in range(1, T+1):
    # 아스키 코드를 이용해서 숫자로 변환
    input_list = list(map(lambda x: ord(x) - ord("A"), input()))
    # dp[n][16] 16가지 경우의 수와 n일
    dp = [[0 for _ in range(16)] for _ in range(len(input_list))]

    for i in range(1, 16):
        # 첫 날 A와 담당자가 있는 경우 (비트연산)
        if i & 1 and i & 1 << input_list[0]:
            dp[0][i] += 1

    # 둘째 날부터...
    for i in range(1, len(input_list)):
        for j in range(1, 16):
            for k in range(1, 16):
                # j 경우, k 경우에 공통으로 같이 있는 사람
                # i일 담당자가 j 경우의 수에 포함되는가
                if j & k and j & 1 << input_list[i]:
                    dp[i][j] = (dp[i][j] + dp[i-1][k]) % 1000000007

    print("#{} {}".format(test_case, sum(dp[-1]) % 1000000007))
