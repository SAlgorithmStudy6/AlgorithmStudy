import sys
import itertools

sys.stdin = open("C:/SSAFY/clone/Algorithm_SSAFY/input.txt",
                 "r", encoding="utf-8")

input = sys.stdin.readline

n = int(input())
matrix = [list(map(int, input().split())) for _ in range(n)]
allMembers = [i for i in range(n)]
visited = [False for _ in range(n)]
half = n // 2
minValue = 999999999

teamList = [list(combi) for combi in itertools.combinations(allMembers, half)]

idx = 0
halfTeamList = len(teamList) // 2


def teamDiff(teamA, teamB):
    totalA, totalB = 0, 0
    aCombi = list(itertools.combinations(teamA, 2))
    bCombi = list(itertools.combinations(teamB, 2))

    for i in range(len(aCombi)):
        totalA += matrix[aCombi[i][0]][aCombi[i][1]] + \
            matrix[aCombi[i][1]][aCombi[i][0]]
        totalB += matrix[bCombi[i][0]][bCombi[i][1]] + \
            matrix[bCombi[i][1]][bCombi[i][0]]

    return abs(totalA - totalB)


for team in teamList:
    if idx == halfTeamList:
        break
    otherTeam = [x for x in allMembers if x not in team]
    diff = teamDiff(team, otherTeam)

    if diff < minValue:
        minValue = diff
    idx += 1

print(minValue)
