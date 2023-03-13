N, M = map(int, input().split())

iceCreamList = list(range(N))	# [0, 1, 2, 3, 4]
banList = []
banSet = set()

for _ in range(M):
	a, b = map(int, input().split())
	temp = [a-1, b-1]
	banList.append(temp)
# banList = [[0, 1], [2, 3], [0, 2]]

# 3가지를 뽑는 모든 경우의 수
if N < 3:
	total = 0
elif N == 3:
	total = 1
else:
	total = N * (N-1) * (N-2) // 6

for i in banList:
	for iceCream in iceCreamList:
		# i에 j가 있으면
		if iceCream in i:
			continue
		else:
			i.append(iceCream)
			# tuple로 저장되어 (0,1,2)와 (0,2,1)이 다른 케이스로 저장되는 걸 방지
			i.sort()
			banSet.add(tuple(i))
			# 다시 원본 i 상태로 복귀
			i.remove(iceCream)

# 답이 음수가 될 수도 있으니 삼항 연산자로 처리
result = total - len(banSet) if total - len(banSet) >= 0 else 0

print(result)





