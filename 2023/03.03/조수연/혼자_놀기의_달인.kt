
fun main(){
    val cards = intArrayOf(8,6,3,7,2,5,1,4)
    Solution().solution(cards)
}

class Solution {
    fun solution(cards: IntArray): Int {
        val checked = BooleanArray(cards.size) //카드 뽑기 중복 체크 배열
        val boxes = ArrayList<ArrayList<Int>>()
        var bIndex = 0 //박스 인덱스

        for (i in cards.indices){
            boxes.add(ArrayList())
        }

        for (i in cards.indices){
            if (!checked[i]){
                var cIndex = i //첫번째 카드 위치
                while (true){
                    if (checked[cIndex]) break //이미 열려있는 상자인 경우 break
                    checked[cIndex] = true
                    boxes[bIndex].add(cards[cIndex])
                    cIndex = cards[cIndex]-1
                }
                bIndex++
            }
        }

        boxes.sortByDescending { it.size } //길이 기준으로 내림차순 정렬

        return boxes[0].size * boxes[1].size
    }
}
