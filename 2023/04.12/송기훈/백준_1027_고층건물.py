import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n = int(input())
buildings = list(map(int, input().split()))


def slope(x1, y1, x2, y2):
    return (y2 - y1) / (x2 - x1)


answer = 0

# 건물의 왼쪽은 기울기가 점점 작아져야 됨
# 건물의 오른쪽은 기울기가 점점 커져야 됨
for i in range(len(buildings)):
    temp_answer = 0
    left_slope = float('inf')
    right_slope = -float('inf')
    # 왼쪽
    for j in range(i-1, -1, -1):
        temp_slope = slope(i, buildings[i], j, buildings[j])
        if temp_slope < left_slope:
            left_slope = temp_slope
            temp_answer += 1
    # 오른쪽
    for j in range(i+1, n):
        temp_slope = slope(i, buildings[i], j, buildings[j])
        if temp_slope > right_slope:
            right_slope = temp_slope
            temp_answer += 1
    answer = max(answer, temp_answer)

print(answer)
