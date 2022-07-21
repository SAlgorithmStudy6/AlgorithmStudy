N = int(input())
target = N
count = 0
while (True):
	ten = N // 10
	unit = N % 10
	N = unit * 10 + (ten + unit) % 10
	count += 1
	if target == N:
		break
print(count)
