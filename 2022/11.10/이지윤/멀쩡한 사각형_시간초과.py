# [넓이를 적분하는 방식] -> 시간초과 발생.(83점)
def solution(w,h):
    answer = 0
    for i in range(1, w):
        max_height = -1*(h/w)*i + h
        height = int(max_height)
        answer += height
    return answer*2
