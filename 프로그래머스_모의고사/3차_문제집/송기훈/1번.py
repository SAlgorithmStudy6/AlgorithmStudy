def solution(a, b, n):
    sumValue = 0
    # n은 빈 병
    while n // a > 0:
        nowEmpty = n // a
        nowLeft = n % a + nowEmpty * b
        sumValue += nowEmpty * b
        n = nowLeft
    return sumValue
