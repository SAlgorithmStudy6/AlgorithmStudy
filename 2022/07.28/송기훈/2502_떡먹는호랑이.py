D, K = map(int, input().split())

# arr = [0, 0, 0, 0, 0, 0]
arr = [0 for _ in range(D)]

a = 1

# a = 1, b = 1부터 대입해서 무한 반복
while a <= K:
	for b in range(a, K):
		arr[0] = a
		arr[1] = b

		for i in range(2, D):
			arr[i] = arr[i - 1] + arr[i - 2]

		if arr[D - 1] == K:
			print(arr[0])
			print(arr[1])
			exit()
	a += 1
