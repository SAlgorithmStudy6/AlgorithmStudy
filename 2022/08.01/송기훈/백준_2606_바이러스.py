N = int(input())
netNum = int(input())

netList = [[] for _ in range(N)]
virusList = [0 for _ in range(N)]

for _ in range(netNum):
    a, b = map(int, input().split())
    netList[a - 1].append(b)
    netList[b - 1].append(a)


def dfs(netList, i, virusList):
    virusList[i - 1] = 1
    for element in netList[i - 1]:
        if virusList[element - 1] == 0:
            dfs(netList, element, virusList)
    return


dfs(netList, 1, virusList)
print(sum(virusList) - 1)
