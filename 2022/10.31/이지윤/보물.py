def solution() -> int:
  arr_a.sort(reverse=True)
  arr_b.sort()
  res = 0
  for i in range(n):
    res += (arr_a[i]*arr_b[i])
  return res

n = int(input())
arr_a = list(map(int, input().split()))
arr_b = list(map(int, input().split()))
answer = solution()
print(answer)
