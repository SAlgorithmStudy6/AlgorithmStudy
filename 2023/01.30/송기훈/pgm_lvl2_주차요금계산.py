import math

fees = [180, 5000, 10, 600]
records = ["05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"]
result = [14600, 34400, 5000]

# fees = [1, 461, 1, 10]
# records = ["00:00 1234 IN"]
# result = [14841]

def solution(fees, records):
    answer = []
    # 누적 주차 시간을 저장하는 dictionary
    times = {}
    # 주차장의 들어온 차 번호와 시간을 저장하는 dictionary
    park = {}

    # 기본시간, 기본요금, 단위시간, 단위요금
    basicTime, basicFee, unitTime, unitFee = fees

    for record in records:
        time, carNumber, inout = record.split()
        hour, min = map(int, time.split(":"))
        hourToMin = hour * 60 + min

        carNumber = int(carNumber)

        if inout == "IN":
            park[carNumber] = hourToMin
        elif inout == "OUT":
            parkingTime = hourToMin - park[carNumber]
            if carNumber in times:
                times[carNumber] += parkingTime
            else:
                times[carNumber] = parkingTime
            # 출차되면 삭제
            del(park[carNumber])

    # 주차장에 남은 차 시간 계산
    for carNumber, time in park.items():
        parkingTime = 23 * 60 + 59 - time
        if carNumber in times:
            times[carNumber] += parkingTime
        else:
            times[carNumber] = parkingTime

    # 요금 계산
    for carNumber, time in times.items():
        fee = basicFee
        if time > basicTime:
            time -= basicTime
            fee = basicFee + math.ceil(time / unitTime) * unitFee
        times[carNumber] = fee
    times = sorted(times.items(), key= lambda item: item[0])

    for time in times:
        answer.append(time[1])
    return answer

print(solution(fees, records))
