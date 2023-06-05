import heapq
import sys

# https://joooosan.tistory.com/entry/%ED%8C%8C%EC%9D%B4%EC%8D%AC-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%EC%A4%80-11000%EB%B2%88-%EA%B0%95%EC%9D%98%EC%8B%A4-%EB%B0%B0%EC%A0%95

"""
1. 강의 리스트를 정렬한다.
2. 첫 번째 강의가 끝나는 시간을 우선순위 큐에 추가한다.
3. 강의 리스트의 1번째 인덱스부터 마지막까지 반복문을 실행한다
3-1. 우선순위 큐에 첫 번째 인덱스에 접근한다 (항상 끝나는 시간이 가장 빠른 순)
3-2. 만약 강의의 시작시간이 우선순위 큐의 첫 번째 인덱스보다 작다면 해당 강의의 끝나는 시간을 우선순위 큐에 추가한다.
3-3. 아니라면 우선순위 큐의 첫 번째 인덱스를 pop 한 후 해당 강의의 끝나는 시간을 우선순위 큐에 추가한다.
"""

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n = int(input())

lecture_list = [list(map(int, input().split())) for _ in range(n)]

lecture_list.sort()

lecture_queue = []
heapq.heappush(lecture_queue, lecture_list[0][1])

for i in range(1, n):
    if lecture_list[i][0] < lecture_queue[0]:
        heapq.heappush(lecture_queue, lecture_list[i][1])
    else:
        heapq.heappop(lecture_queue)
        heapq.heappush(lecture_queue, lecture_list[i][1])

print(len(lecture_queue))
