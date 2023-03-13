N, M = map(int, input().split())

matrix = []
distance = [0] * (N * M)
sharkIdx = []

# 1차원 만들기, 상어 좌표 받기
for y in range(N):
	row = list(map(int, input().split()))
	for x in range(M):
		if row[x] == 1:
			sharkIdx.append((y, x))
	matrix += row

for i in range(len(sharkIdx)):
	sharkY = sharkIdx[i][0]
	sharkX = sharkIdx[i][1]
	for idx in range(N * M):
		if matrix[idx] == 1:
			continue

		y = idx // M
		x = idx % M

		diffY = abs(sharkY - y)
		diffX = abs(sharkX - x)

		tempDis = max(diffY, diffX)
		if i == 0:
			distance[idx] = tempDis
		else:
			if distance[idx] > tempDis:
				distance[idx] = tempDis
print(max(distance))
