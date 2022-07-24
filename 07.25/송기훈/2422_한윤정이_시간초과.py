N, M = map(int, input().split())

iceList = list(range(1, N + 1))
banList = []
result = []

for _ in range(M):
	a, b = map(int, input().split())
	temp = [a, b]
	banList.append(temp)

# 조합 함수
def combination(list, targetNum):
	combiResult = []

	def nowCombi(nowList, index):
		if len(nowList) == targetNum:
			combiResult.append(nowList)
			return
		for i in range(index, len(list)):
			nowCombi(nowList + [list[i]], i + 1)

	nowCombi([], 0)

	return combiResult

combiList = combination(iceList, 3)

for i in combiList:
	check = 1
	for j in banList:
		if all(elem in i for elem in j):
			check = 0
	if check == 1:
		result.append(i)

print(len(result))
