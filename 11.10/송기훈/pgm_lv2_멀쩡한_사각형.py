def gcd(w, h):
    a, b = w, h
    while (True):
        remainder = a % b
        if remainder == 0:
            return b
        else:
            a, b = b, remainder

def solution(w,h):
    answer = w * h - (w+h-gcd(w, h))
    return answer

# 시간초과
# import math

# def solution(w,h):
#     answer = 0
#     for i in range(1, w):
#         cutHeight = math.ceil(h / w * i)
#         answer += (h - cutHeight)   
#     return answer * 2