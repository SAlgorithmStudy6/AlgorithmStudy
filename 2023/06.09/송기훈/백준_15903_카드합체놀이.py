import sys
import heapq

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n, m = map(int, input().split())
numbers = list(map(int, input().split()))
numbers_queue = []

for number in numbers:
    heapq.heappush(numbers_queue, number)

while (m > 0):
    a = heapq.heappop(numbers_queue)
    b = heapq.heappop(numbers_queue)

    heapq.heappush(numbers_queue, a+b)
    heapq.heappush(numbers_queue, a+b)

    m -= 1

print(sum(numbers_queue))
