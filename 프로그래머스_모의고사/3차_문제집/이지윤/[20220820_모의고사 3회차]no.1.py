def solution(a, b, n):
    answer = 0
    while n>=a:
        q, r = n // a, n % a
        answer += q*b
        n = r + q*b
    return answer