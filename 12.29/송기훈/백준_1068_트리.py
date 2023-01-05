import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")


def dfs(num):
    treeInput[num] = -9
    visited[num] = True
    for i in range(n):
        if visited[i] == True:
            continue
        if treeInput[i] == num:
            dfs(i)


# 입력 받기
n = int(input())
treeInput = list(map(int, input().split()))
target = int(input())
count = 0
visited = [False for _ in range(n)]

# 자식노드 삭제, 삭제된 노드는 -9
dfs(target)

for i in range(n):
    # 삭제된 노드가 아니고 부모노드가 아니어야 한다.
    if treeInput[i] != -9 and i not in treeInput:
        count += 1

print(count)
