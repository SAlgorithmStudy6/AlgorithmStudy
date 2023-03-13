

def solution(_n) -> int:
    num, count = _n, 0
    while True:
        count += 1
        r = (num % 10)
        s = ((num // 10) + r) % 10
        new = r * 10 + s
        if new == _n:
            return count
        else:
            num = new

input_num = int(input())
answer = solution(input_num)
print(answer)