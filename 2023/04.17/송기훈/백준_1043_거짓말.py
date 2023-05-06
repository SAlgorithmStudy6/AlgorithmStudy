import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n, m = map(int, input().split())

truth = set(map(int, input().split()[1:]))

party_list = []

for _ in range(m):
    party_list.append(set(map(int, input().split()[1:])))

# 진실을 아는 사람 + 진실을 아는 사람과 같이 파티에 참가한 사람을 걸러야 함
# 같이 참가한 사람 + 그 사람과 참가한 사람...
# 파티 회차마다 생기므로 m번 확인 해야됨
for _ in range(m):
    for party in party_list:
        # 교집합이 있다면 진실을 아는 사람에 추가함
        if party & truth:
            truth = truth.union(party)

answer = 0
for party in party_list:
    if not party & truth:
        answer += 1

print(answer)
