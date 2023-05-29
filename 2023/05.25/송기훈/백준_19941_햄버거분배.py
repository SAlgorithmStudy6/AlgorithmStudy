import sys

sys.stdin = open("input.txt", "r", encoding="utf-8")

n, k = map(int, input().split())
table_list = list(input())
answer = 0

for i in range(n):
    if table_list[i] == "P":
        for j in range(max(i - k, 0), min(i + k + 1, n)):
            if table_list[j] == "H":
                table_list[j] = "X"
                answer += 1
                break
print(answer)

# 테이블을 도면서 사람이면 k 범위 내에서 왼쪽부터 탐색
# 왼쪽부터 탐색해야 뒷 사람이 먹을게 있다

"""
사람 리스트, 햄버거 리스트 구하고
각각 사람마다 사정거리 + 햄버거 있는 위치의 집합을 만듬
그리고 첫번째 사람부터 for문을 돌면서 왼쪽꺼를 고르게 하려고 함
-> for문을 불필요하게 많이 도는 것 같다
people_list = []
hamburger_list = []
possible_set = set()

for i in range(n):
    if table_list[i] == "H":
        hamburger_list.append(i)
    else:
        people_list.append(i)

for person in people_list:
    for delta in range(-k, k+1):
        if ((person + delta) < n) and ((person + delta) >= 0):
            possible_set.add(person + delta)

possible_set = possible_set - set(people_list)

"""




