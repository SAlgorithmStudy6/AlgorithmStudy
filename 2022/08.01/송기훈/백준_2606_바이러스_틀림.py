N = int(input())
netNum = int(input())

netList = [[] for _ in range(N)]
virusList = [0 for _ in range(N)]

virusList[0] = 1

for _ in range(netNum):
    a, b = map(int, input().split())
    netList[a - 1].append(b)
    netList[b - 1].append(a)

for i in range(N):
    if virusList[i]:
        for j in netList[i]:
            virusList[j - 1] = 1

print(sum(virusList) - 1)
