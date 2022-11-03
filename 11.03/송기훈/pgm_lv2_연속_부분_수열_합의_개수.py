def solution(elements):
    n = len(elements)
    resultSet = set()
    elements = elements + elements
    for i in range(n):
        for j in range(n):
            sumValue = sum(elements[j:j+i+1])
            resultSet.add(sumValue)
        
    answer = len(resultSet)
    return answer