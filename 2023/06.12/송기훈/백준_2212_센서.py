import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n = int(input())
k = int(input())

sensor_list = list(map(int, input().split()))
sensor_list.sort()

distance_list = []
for i in range(0, n-1):
    distance_list.append(sensor_list[i+1] - sensor_list[i])

distance_list.sort()

print(sum(distance_list[:n-k]))
