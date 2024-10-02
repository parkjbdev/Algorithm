## [정수 삼각형](https://programmers.co.kr/learn/courses/30/lessons/43105)

### 문제 설명

![](https://grepp-programmers.s3.amazonaws.com/files/production/97ec02cc39/296a0863-a418-431d-9e8c-e57f7a9722ac.png)

위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다. 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다. 예를 들어
3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.

삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.

### 제한사항

- 삼각형의 높이는 1 이상 500 이하입니다.
- 삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.

### 입출력 예

| triangle | result |
| :---: | :---:|
| [[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]    | 30 |

[출처](http://stats.ioinformatics.org/countries/SWE)

***

## 풀이

`maxSum(idx1, idx2) ` 함수: `triangle[idx1][idx2]` 을 제일 위의 꼭대기로 하는 삼각형에서 출발해서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰 경우의 거쳐간 숫자의
합 이라고 정의하자

그러면 `maxSum(idx1, idx2) = triangle[idx1][idx2] + MAX(maxSum(왼쪽아들 좌표), maxSum(오른쪽아들 좌표))` 라고 정의할 수 있다.

하지만, 이 재귀함수를 그대로 실행할 경우 같은 연산을 반복하여 비효율적이므로, 메모이제이션을 위해 `sum`배열에 이미 실행한 연산들을 저장하여 같은 연산을 피한다.

```java
private int maxSum(int idx1,int idx2){
  if(idx1>=triangle.length||idx2>=triangle[idx1].length)return 0;
  if(sum[idx1][idx2]==0)
  sum[idx1][idx2]=triangle[idx1][idx2]+Math.max(maxSum(idx1+1,idx2),maxSum(idx1+1,idx2+1));
  return sum[idx1][idx2];
  }
```