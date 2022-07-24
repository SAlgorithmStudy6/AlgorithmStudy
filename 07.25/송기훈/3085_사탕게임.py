N = int(input())
matrix = []
countSet = set()

# 행 확인
def check_row(matrix: list, countSet: set):
	for y in range(N):
		count = 1
		for x in range(1,N):
			if (matrix[y][x] == matrix[y][x - 1]):
				count += 1
			else:
				countSet.add(count)
				count = 1
			countSet.add(count)


# 열 확인
def check_col(matrix: list, countSet: set):
	for x in range(N):
		count = 1
		for y in range(1,N):
			if (matrix[y][x] == matrix[y - 1][x]):
				count += 1
			else:
				countSet.add(count)
				count = 1
			countSet.add(count)

# 입력 받기
for i in range(N):
	matrix.append(list(input()))

# 좌우 바꿔서 체크
for y in range(N):
	for x in range(1,N):
		if (matrix[y][x] != matrix[y][x - 1]):
			# swap
			temp = matrix[y][x - 1]
			matrix[y][x - 1] = matrix[y][x]
			matrix[y][x] = temp
			check_row(matrix, countSet)
			check_col(matrix, countSet)
			# swap 원위치
			temp = matrix[y][x - 1]
			matrix[y][x - 1] = matrix[y][x]
			matrix[y][x] = temp

# 위아래 바꿔서 체크
for x in range(N):
	for y in range(1,N):
		if (matrix[y][x] != matrix[y - 1][x]):
			# swap
			temp = matrix[y - 1][x]
			matrix[y - 1][x] = matrix[y][x]
			matrix[y][x] = temp
			check_row(matrix, countSet)
			check_col(matrix, countSet)
			# swap 원위치
			temp = matrix[y - 1][x]
			matrix[y - 1][x] = matrix[y][x]
			matrix[y][x] = temp

print(max(countSet))


