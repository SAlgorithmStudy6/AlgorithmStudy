
E, S, M = 15, 28, 19                    # 주기 상수
e, s, m = map(int, input().split())     # 입력 값

e -= 1
s -= 1
m -= 1                                  # 표현 일치

year = 0

while True:
    if year % E == e and year % S == s and year % M == m:
        print(year + 1)
        break
    else:
        year += 1
