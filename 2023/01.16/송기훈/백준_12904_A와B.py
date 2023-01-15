import sys

sys.stdin = open('input.txt', 'r', encoding='UTF-8')


S = list(input())
T = list(input())

# T에서 S로 가는 경우
# 1. A 떼기
# 2. B 떼고 뒤집기
answer = 0
while T:
    if T[-1] == 'A':
        T.pop()
    else:
        T.pop()
        T = T[::-1]

    if S == T:
        answer = 1
        break

print(answer)

# 메모리 초과
# def bfs():
#     target = len(T)
#     q = deque()
#     q.append(S)

#     while q:
#         temp = q.popleft()
#         if len(temp) == target:
#             if temp == T:
#                 return 1
#         else:
#             # A 추가하기
#             q.append(temp+'A')
#             # 뒤집고 B 추기하기
#             q.append(temp[::-1]+'B')

#     return 0


# print(bfs())
